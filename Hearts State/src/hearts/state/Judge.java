/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state;

import hearts.defs.actions.AAction;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import hearts.defs.state.SFinalPoints;
import hearts.state.actions.AddCardToTrickAction;
import hearts.state.actions.FirstModeAction;
import hearts.state.actions.NextModeAction;
import hearts.state.actions.NextTripAction;
import hearts.state.exceptions.WrongCardValueException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Klasa implementująca logikę gry. Dostaje na wejsciu stan gry i zwraca jego zmodyfikowaną instancje.
 * @author Paweł Trynkiewicz
 */
public class Judge implements hearts.defs.judge.IJudge {

    /**
     * Lista akcji do wykonania


    public AActionList getActionTodo() {
        return actionToDo;
    }

    public void setActionTodo(AActionList actionTodo) {
        this.actionToDo = actionTodo;
    }

    /**
     * Cała logika gry tu będzie implementowa.
     * @param state stan gry
     * @param action akcja do wykonania
     * @return nowy stan gry
     * @throws GameStateException
     */
    public IGameState judge(IGameState state, AAction action) throws GameStateException {
        IGameState copyState = null;

        copyState=this.cloneState(state);

        /**
         * Sprawdzam jaki to typ akcji.
         */
        if (action instanceof AddCardToTrickAction) {
            if (state.getMode() == state.getMode().WAITING_FOR_PLAYERS) {
                throw new GameStateException("WAITING_FOR_PLAYERS");
            }
            if (state.getMode() == state.getMode().END) {
                throw new GameStateException("End");
            }

            if (state.getActiveUser() != action.getSender() || state.isAuction()) {
                throw new GameStateException("You can not put card in this moment");
            }
            // jesli kotś już połorzył karte, i karta ma inny kolor niz wistjący
           
            if (state.getTrick().getFirst()!=-1&&!state.getTrick().getCards()[state.getTrick().getFirst()].getColor().equals((( AddCardToTrickAction)action).getCard().getColor())) {
                //ale ma w ręce kolor wistujący
                if (state.getUserState(action.getSender()).userHaveCardInColor(((AddCardToTrickAction) (action)).getCard().getColor())) {
                    throw new GameStateException("You can not put card in this color, because you have card in correct color");
                }

            }
            if (state.getMode() == state.getMode().BANDIT) {
                checkBanditRules(state, action);
          

            } else if (state.getMode() == state.getMode().REAVER) {
                checkReaverRules(state, action);

            } else if (state.getMode() == state.getMode().WIN_BACK) {
                checkWinBackRules(state, action);
            } else {
                throw new GameStateException("Unknown state");
            }

            
           


        }
        if (action instanceof NextTripAction) {
            if (!state.trickEnds()) {
                throw new GameStateException("Trick not end");
            }
           
            ((NextTripAction) action).setWiner(this.findWiner(state));

            if (state.getMode() == state.getMode().BANDIT && state.getNumTrick() >= 11) {
                ((NextTripAction) action).setLast(true);
            } else {
                ((NextTripAction) action).setLast(false);
            }

            ((NextTripAction) action).setWiner(this.findWiner(state));
           


        }
        if (action instanceof NextModeAction) {
            if (!state.dealEnds()) {
                throw new GameStateException("Deal not end");
            }
            /*Zrobione skreślić
             *
             * 1. zliczyć punktu z lew dla każdego gracz xd - to zrobię w judge
             * +2. Wygenerować nową talie kart - to też zrobie w judge
             * +3. Zamieszczać karty, rozdać graczom - to też to  judge, dodam pola do NextModeException          
             
             */
            NextModeAction ac = (NextModeAction) action;
            

            ICard[] pack = this.generateNewCardTab();
            for (int i = 0; i < 4; i++) {

               
                ICard[] tabC = new ICard[13];

                for (int k = 0; k < 13; k++) {
                    tabC[k] = pack[i * 13 + k];

                }//mam talię kart gracza
                
                ac.setICard(i, tabC);
            }// mam nowe rozdanie kart
            
            //nowy stan gry

           
        }else if (action instanceof FirstModeAction) {
            /* Przygotuwuje pierwsze rozdanie.
             *
             */
            FirstModeAction act=(FirstModeAction)action;
            if (act.getModes()%4!=0) throw new GameStateException("Wrong number of mode");

            /*Dodaje kart graczom
             */

            ICard[] pack = this.generateNewCardTab();
            for (int i = 0; i < 4; i++) {


                ICard[] tabC = new ICard[13];

                for (int k = 0; k < 13; k++) {
                    tabC[k] = pack[i * 13 + k];

                }//mam talię kart gracza

                act.setCards(tabC, i);
            }


        }

        /**
         * Wykonuję akcję na kopi stanu gry.
         */
        return action.perform(copyState);
       
    }

    /**
     * Sprawdza dodatkowe obwarowania, dotyczące zasad obowiązujących w zbóju.
     * @param state
     * @param action
     */
    private void checkBanditRules(IGameState state, AAction action) throws GameStateException {

        /* W pierwszej lewie nie wolno odrzucić króla kier,
         * a kiery są kolorem granym wówczas gdy wistujący nie ma w ręce innego koloru.
         * Można kiery też wyrzucać wtedy gdy nie posiadamy koloru wistowanego. */
        AddCardToTrickAction act=(AddCardToTrickAction)action;

        ICard card=act.getCard();
        if(state.getNumTrick()==1&&card.getColor()==CardColor.HEART&&card.getValue()==ICard.KING)
            throw new GameStateException("You can not put king heart in first trick");
        if(state.getTrick().getFirst()==-1) {
            if(card.getColor()==CardColor.HEART) {
                IUserState user=state.getUserState(action.getSender());
                if(user.userHaveCardInColor(CardColor.CLUB)||user.userHaveCardInColor(CardColor.DIAMOND) || user.userHaveCardInColor(CardColor.SPADE))
                    throw new GameStateException("You can not put heart, you have card in other color");
            }
        }

        
    }

    /**
     * Sprawdza dodatkowe obwarowania, dotyczące zasad obowiązujących w odgrywce.
     * @param state
     * @param action
     */
    private void checkWinBackRules(IGameState state, AAction action) throws GameStateException {
    }

    /**
     * Sprawdza dodatkowe obwarowania, dotyczące zasad obowiązujących w rozbójniku.
     * @param state
     * @param action
     */
    private void checkReaverRules(IGameState state, AAction action) throws GameStateException {
    }

    /**
     * Znajduje zwycięzce, lewie.
     * @param state
     */
    private int findWiner(IGameState state) {
        //znajduje zwyciężce dla rozbójnika
        if (state.getMode() == state.getMode().BANDIT) {
            //nie ma atutu, sprawdzam czy kto połorzył najwyższą karte w wistującym kolorze

            ICard[] cards = state.getTrick().getCards();
            int first = state.getTrick().getFirst();
            int win = first;
            for (int i = 0; i < cards.length; i++) {
                if (i == first) {
                    continue;
                }
                if (cards[i].getColor() == cards[first].getColor()) {
                    if (cards[i].getValue() > cards[win].getValue()) {
                        win = i;
                    }
                }

            }
            return win;
        }
        return 0;//tak dla draki
    }
    /**
     * Fukcja generyje nową talie, a następnie ją miesza.
     * @return ICard[]
     */
    @SuppressWarnings("empty-statement")
    public ICard[] generateNewCardTab() {
        ICard[] tab = new ICard[52];
        for (int i = 0; i < 13; i++) {
            try {
                tab[i] = new Card(CardColor.CLUB, 2 + i);
                tab[13 + i] = new Card(CardColor.DIAMOND, 2 + i);
                tab[26 + i] = new Card(CardColor.HEART, 2 + i);
                tab[39 + i] = new Card(CardColor.SPADE, 2 + i);
                //mam tablice hee
            } catch (WrongCardValueException ex) {
                Logger.getLogger(Judge.class.getName()).log(Level.SEVERE, null, ex);
            }



        }

        Random ran = new Random(new Date().getTime()); //zamieszałem random
        ICard tmp;
        for (int i = 0; i < tab.length; i++) {
            int r = ran.nextInt(52);
            int q = 0;
            while ((q = ran.nextInt(52)) == r);
            tmp = tab[r];
            tab[r] = tab[q];
            tab[q] = tab[r];


        }//zamieszałem talię kart, cheja!!!

        return tab;
    }

    /**
     * Funkcja określająca ile punktów zdobył każdy z graczy. A takrze, jak rozgrywki trzeba dodać.
     * @param state
     * @param user
     * @return
     */
    public static SFinalPoints getPoints(IGameState state) {
        SFinalPoints userPoints=new SFinalPoints();
        if (state.getMode().equals(IGameState.Mode.BANDIT)){
            /**
             * 1. Sumowanie punktów z lew, jaki wzioł gracz.
             * 2. Okreslenie czy ktos nic nie wzioł
             * 3. Dodanie odpowiedniech etapów gry.
             */
            for(int i=0;i<4;i++){
                userPoints.points[i]=0;
                IUserState ust=state.getUserState(i);
                List<ITrick> list=ust.getTricks();
                for(int k=0;k<list.size();k++){
                    //punkty za lewę
                    userPoints.points[i]-=2;
                    //karty gracza
                    ICard []cards=list.get(k).getCards();
                    for(int l=0;l<cards.length;l++) {
                        //punkty za pana
                        if(cards[l].getValue()==Card.KING || cards[l].getValue()==Card.JACK) userPoints.points[i]-=3;
                        //punkty za dame
                          if(cards[l].getValue()==Card.QUEEN)  userPoints.points[i]-=5;
                        //punkty za kiera
                        if(cards[l].getColor().equals(CardColor.HEART))  userPoints.points[i]-=3;
                        //punkty za ostatnią lewe
                        //opis w jest dośc nie jasny
                        if (list.get(k).isLast()) userPoints.points[i]-=2*9;
                    }


                }

            }// gracze mają podliczone punkty

            int zero=0;
            for(int i=0;i<4;i++){
                if(userPoints.points[i]==0){
                    //dodaje rozbójnika
                    userPoints.mode.add(IGameState.Mode.REAVER);
                    zero++;
                }
            }//wiem ilu graczy nie wieło żadne lewy
            if(zero==3) {
                // zero punktów dla gracza który wzioł wszystkie wziątki
                // -130 dla pozostałych
                for(int i=0;i<4;i++) {
                    if(userPoints.points[i]==0 ) userPoints.points[i]= -130;
                    else userPoints.points[i]=0;
                }
            } else {
                //jeśli każdy coś wziął to pętla się nie wykona
                for( int i=0;i<zero;i++){
                    // zmieniamy punkty graczy
                    //- jeden gracz 0pkt – pozostali pkt ujemne x2
                    //- dwóch graczy 0pkt – pozostali pkt ujemne x3
                    userPoints.points[i]= userPoints.points[i]*(zero+1);
                }
            }
        }//mamy punkty policzone dla rozbójnika

        return userPoints;


       
    }
    /**
     * Funkcja zwraca głęboką kopję stanu gry.
     * @param state
     * @return
     */
    public IGameState cloneState(IGameState state){
        return state.clone();
    }
}


