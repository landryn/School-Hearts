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
import hearts.defs.state.IUserState;
import hearts.state.actions.AddCardToTrickAction;
import hearts.state.actions.NextModeAction;
import hearts.state.actions.NextTripAction;
import hearts.state.exceptions.WrongCardValueException;
import java.util.Date;
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

            if (!state.getTrick().getCards()[0].getColor().equals(((AddCardToTrickAction) (action)).getCard().getColor())) {

                if (state.getUserState(action.getSender()).userHaveCardInColor(((AddCardToTrickAction) (action)).getCard().getColor())) {
                    throw new GameStateException("You can not put card in this color, because you have card in correct color");
                }

            }
            if (state.getMode() == state.getMode().BANDIT) {
                checkBanditRules(state, action);
                //Tu powinny byc sprawdzone wszystkie warunki i waruneczki.

            } else if (state.getMode() == state.getMode().REAVER) {
                checkReaverRules(state, action);

            } else if (state.getMode() == state.getMode().WIN_BACK) {
                checkWinBackRules(state, action);
            } else {
                throw new GameStateException("Unknown error");
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
             * + 3.b do userów wyślie inną klasa a co, tylko z kartami i punktami chce dobre ...
             * 4. Narazie robie dla zbója, bez licytacji -
             * 5. Cieszyć sie możliwością gry w zbója
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
        //chwilo nie zimplementowana póżnie się w nią w gryzę.
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
     * Funkcja określająca ile punktów zdobył gracz. Chwilowo zwraca random. Do testów.
     * @param state
     * @param user
     * @return
     */
    public static int getPoints(IGameState.Mode mode, IUserState user) {
        int point = 0;


        return new Random(new Date().getTime()).nextInt() % 40;
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


