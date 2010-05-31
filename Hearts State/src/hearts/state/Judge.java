/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state;

import hearts.defs.actions.AAction;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import hearts.defs.state.SFinalPoints;
import hearts.state.actions.AddCardToTrickAction;
import hearts.state.actions.AuctionBeginAction;
import hearts.state.actions.AuctionDecisionAction;
import hearts.state.actions.AuctionOfferAction;
import hearts.state.actions.ChooseTrumpAction;
import hearts.state.actions.FirstModeAction;
import hearts.state.actions.NextModeAction;
import hearts.state.actions.NextTripAction;
import hearts.state.exceptions.WrongCardValueException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class SortCards implements Comparator<ICard> {

    public int compare(ICard c1, ICard c2) {
        return c1.getColor().compareTo(c2.getColor());
    }
}

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



        /**
         * Sprawdzam jaki to typ akcji.
         */
        if (action instanceof AddCardToTrickAction) {
            if (state.isAuction()) {
                throw new GameStateException("Trwa aukcja");
            }
            if (state.getMode() == state.getMode().WAITING_FOR_PLAYERS) {
                throw new GameStateException("WAITING_FOR_PLAYERS");
            }
            if (state.getMode() == state.getMode().END) {
                throw new GameStateException("End");
            }


            if (state.getActiveUser() != action.getSender() || state.isAuction()) {
                throw new GameStateException("Nie mozna polozyc tej karty");
            }
            // jesli kotś już połorzył karte, i karta ma inny kolor niz wistjący

            if (!state.getUserState(action.getSender()).haveThisCard(((AddCardToTrickAction) action).getCard())) {
                throw new GameStateException("Blad: nie masz tej karty...!");
            }

            if (state.getMode() == state.getMode().BANDIT) {
                checkBanditRules(state, action);


            } else if (state.getMode() == state.getMode().REAVER) {
                checkReaverRules(state, action);

            } else if (state.getMode() == state.getMode().WIN_BACK) {
                checkWinBackRules(state, action);
            } else {

                throw new GameStateException("Nieznany blad");
            }





        }
        if (action instanceof NextTripAction) {
            if (state.isAuction()) {
                throw new GameStateException("Trwa aukcja");
            }
            if (!state.trickEnds()) {
                throw new GameStateException("Wziatka niekompletna");
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
            if (state.isAuction()) {
                throw new GameStateException("Trwa aukcja");
            }
            if (!state.dealEnds()) {
                throw new GameStateException("Trwa zaklad");
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

                List<ICard> list = Arrays.asList(tabC);
                Collections.sort(list, new SortCards());
                tabC = (ICard[]) list.toArray();

                ac.setICard(i, tabC);
            }// mam nowe rozdanie kart

            //nowy stan gry


        } else if (action instanceof FirstModeAction) {
            /* Przygotuwuje pierwsze rozdanie.
             *
             */
            FirstModeAction act = (FirstModeAction) action;
            if (act.getModes() % 4 != 0) {
                throw new GameStateException("Niepoprawny numer mode");
            }

            /*Dodaje kart graczom
             */

            ICard[] pack = this.generateNewCardTab();
            for (int i = 0; i < 4; i++) {


                ICard[] tabC = new ICard[13];

                for (int k = 0; k < 13; k++) {
                    tabC[k] = pack[i * 13 + k];

                }//mam talię kart gracza

                List<ICard> list = Arrays.asList(tabC);
                Collections.sort(list, new SortCards());
                tabC = (ICard[]) list.toArray();
                act.setCards(tabC, i);
            }


        } else if (action instanceof AuctionBeginAction) {
            if (!state.isAuction()) {
                throw new GameStateException("Aukcja nieaktywna");
            }

        } else if (action instanceof AuctionOfferAction) {

            if (!state.isAuction()) {
                throw new GameStateException("Aukcja nieaktywna");
            }
            if (state.getAuction().getActivetUser() != action.getSender()) {
                throw new GameStateException("Niedozwolony ruch");
            }

        } else if (action instanceof AuctionDecisionAction) {

            if (!state.isAuction()) {
                throw new GameStateException("Aukcja nieaktywna");
            }
            if (state.getActiveUser() != action.getSender() || (!state.getAuction().isEnd())) {
                throw new GameStateException("Nie mozna wykonac tego ruchu");
            }


        } else if (action instanceof ChooseTrumpAction) {
            if (!state.isAuction()) {
                throw new GameStateException("Nie mozna zmienic atutu");
            }
            if (state.getActiveUser() != action.getSender() || (!state.getAuction().isEnd()) || state.getMode() != IGameState.Mode.WIN_BACK) {
                throw new GameStateException("Nie Twoja kolej");
            }

        } else {
            throw new GameStateException("Nieznana akcja");
        }

        /**
         * Wykonuję akcję na kopi stanu gry.
         */
        copyState = this.cloneState(state);
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
        AddCardToTrickAction act = (AddCardToTrickAction) action;

        ICard card = act.getCard();
        if (state.getNumTrick() == 1 && card.getColor() == CardColor.HEART && card.getValue() == ICard.KING) {
            throw new GameStateException("Nie mozna wylozyc krola kier w pierwszym wyjsciu");
        }

        if (state.getTrick().getFirst() == GameConstants.NO_CARD_IN_TRIP) {
            if (card.getColor() == CardColor.HEART) {
                IUserState user = state.getUserState(action.getSender());
                if (user.userHaveCardInColor(CardColor.CLUB) || user.userHaveCardInColor(CardColor.DIAMOND) || user.userHaveCardInColor(CardColor.SPADE)) {
                    throw new GameStateException("Kiery wykladamy gdy nie mamy innych kolorow...!");
                }
            } else {
                return;
            }
        } else {
            int first = state.getTrick().getFirst();
            CardColor firstColor = state.getTrick().getCards()[first].getColor();
            CardColor userColor = act.getCard().getColor();
            if (firstColor == userColor) {
                return;
            }
            if (state.getUserState(action.getSender()).userHaveCardInColor(firstColor)) {
                throw new GameStateException("Na pewno nie masz do koloru, cwaniaczku?");
            }


        }


    }

    /**
     * Sprawdza dodatkowe obwarowania, dotyczące zasad obowiązujących w odgrywce.
     * @param state
     * @param action
     */
    private void checkWinBackRules(IGameState state, AAction action) throws GameStateException {
        /*
         * W kolorowych, gdy nie mamy do koloru to przymus rzucenia atutu.
        W atu wyżej
         */

        /*
         * Możliwe przypadki
         * 1. Jest to pierwsza karta w lewie: ok
         * 2. Karta jest w kolorze wistującym: ok
         *  else gracz ma kartę w kolorze wistującym error
         * 3. Karta jest w atucie i najwyższa kart jest atutem
         *  a. Bije inne kart ok
         *  b. Nie bije, ale gracz nie ma karty wyższej ok
         *      else błąd
         * 4 . inna karta, ale gracz nie ma ani atutu ani karty wyższej ok
         */

        //1
        int first = state.getTrick().getFirst();

        if (first == GameConstants.NO_CARD_IN_TRIP) {
            return;
        }
        AddCardToTrickAction act = (AddCardToTrickAction) action;
        CardColor firstColor = state.getTrick().getCards()[first].getColor();
        CardColor trump = state.getTrump();
        CardColor userColor = act.getCard().getColor();

        int sender = act.getSender();
        //2.
        if (firstColor == act.getCard().getColor()) {
            return;
        } else {
            if (state.getUserState(sender).userHaveCardInColor(firstColor)) {
                throw new GameStateException("Na pewno nie masz do koloru, cwaniaczku?");
            }
        }

        //najwyższa karta w lewie
        ICard higerCard = null;
        ICard[] cards = state.getTrick().getCards();
        int win = this.findWiner(state);
        higerCard = cards[win];
        //znam najwyższą kartę
        if (userColor == trump && higerCard.getColor() == trump) {
            if (act.getCard().getValue() > higerCard.getValue()) {
                return;
            } else {
                if (!state.getUserState(sender).uHHigerCardIColor(higerCard)) {
                    return;
                } else {
                    throw new GameStateException("A wlasnie, ze masz!");
                }
            }
        }




    }

    /**
     * Sprawdza dodatkowe obwarowania, dotyczące zasad obowiązujących w rozbójniku.
     * @param state
     * @param action
     */
    private void checkReaverRules(IGameState state, AAction action) throws GameStateException {

        /* Jest przymus bicia
         * 1.Karta jest w kolorze wistującym
         * a.gracz nie ma wyższej od najwyzszej w rozdaniu ok
         * else bład
         * 2. Gracz ma karte w kolorze wistującym bład
         * 3. gracz nie ma karty w kolorze wistującym.
         */

        int first = state.getTrick().getFirst();

        if (first == GameConstants.NO_CARD_IN_TRIP) {
            return;
        }
        AddCardToTrickAction act = (AddCardToTrickAction) action;
        CardColor firstColor = state.getTrick().getCards()[first].getColor();
        CardColor userColor = act.getCard().getColor();
        int sender = act.getSender();

        ICard higerCard = null;
        ICard[] cards = state.getTrick().getCards();
        int win = this.findWiner(state);
        higerCard = cards[win];

        if (firstColor == act.getCard().getColor()) {
            if (act.getCard().getValue() > higerCard.getValue()) {
                return;
            } else {
                if (state.getUserState(sender).uHHigerCardIColor(higerCard)) {
                    throw new GameStateException("Masz wyzsza karte");
                } else {
                    return;
                }

            }


        }
        if (state.getUserState(sender).userHaveCardInColor(firstColor)) {
            throw new GameStateException("Masz karte koloru " + firstColor);
        }
    }

    /**
     * Znajduje zwycięzce, lewie.
     * @param state
     */
    private int findWiner(IGameState state) {
        //karty w lewie
        ICard[] cards = state.getTrick().getCards();
        int first = state.getTrick().getFirst();
        int win = first;
        //znajduje zwyciężce dla zbója lub rozbójnika
        if (state.getMode() == state.getMode().BANDIT || state.getMode() == state.getMode().REAVER) {
            //nie ma atutu, sprawdzam czy kto połorzył najwyższą karte w wistującym kolorze


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
            //zwycięzca w odgrywce, tu jest atut
        }
        if (state.getMode() == state.getMode().WIN_BACK) {
            //stut bije wszystko, albo najwyższa karta w kolorze wistującym
            CardColor trump = state.getTrump();

            for (int i = 0; i < cards.length; i++) {
                if (i == first || cards[i] == null) {
                    continue;
                }
                //najpierw sprawdzam czy karta nie jest w atutem
                if (cards[i].getColor() == trump) {
                    //najlepszy ma kartę w kolorze wistującym
                    if (cards[win].getColor() == trump) {
                        if (cards[win].getValue() < cards[i].getValue()) {
                            win = i;
                        }
                    } else {
                        win = i;
                    }
                    //nasz i gracz nia ma atuta, ale ma kartę w kolorze zwycięzcy
                } else if (cards[win].getColor() == cards[i].getColor()) {
                    if (cards[win].getValue() < cards[i].getValue()) {
                        win = i;
                    }
                }


            }


        }
        return win;
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

        List<ICard> raw = new ArrayList<ICard>(52);
        for(ICard card : tab) {
            raw.add(card);
        }
        Collections.shuffle(raw);
        tab = raw.toArray(tab);

        return tab;
    }

    /**
     * Funkcja określająca ile punktów zdobył każdy z graczy. A takrze, jak rozgrywki trzeba dodać.
     * @param state
     * @param user
     * @return
     */
    public static SFinalPoints getPoints(IGameState state) {
        SFinalPoints userPoints = new SFinalPoints();
        if (state.getMode().equals(IGameState.Mode.BANDIT)) {
            /**
             * 1. Sumowanie punktów z lew, jaki wzioł gracz.
             * 2. Okreslenie czy ktos nic nie wzioł
             * 3. Dodanie odpowiedniech etapów gry.
             */
            for (int i = 0; i < 4; i++) {
                userPoints.points[i] = 0;
                IUserState ust = state.getUserState(i);
                List<ITrick> list = ust.getTricks();
                for (int k = 0; k < list.size(); k++) {
                    //punkty za lewę
                    userPoints.points[i] -= 2;
                    //karty gracza
                    ICard[] cards = list.get(k).getCards();
                    for (int l = 0; l < cards.length; l++) {
                        //punkty za pana
                        if (cards[l].getValue() == Card.KING || cards[l].getValue() == Card.JACK) {
                            userPoints.points[i] -= 3;
                        }
                        //punkty za dame
                        if (cards[l].getValue() == Card.QUEEN) {
                            userPoints.points[i] -= 5;
                        }
                        //punkty za kiera
                        if (cards[l].getColor().equals(CardColor.HEART)) {
                            userPoints.points[i] -= 3;
                        }
                        //punkty za ostatnią lewe
                        //opis w jest dośc nie jasny
                        if (list.get(k).isLast()) {
                            userPoints.points[i] -= 2 * 9;
                        }
                    }


                }

            }// gracze mają podliczone punkty

            int zero = 0;
            for (int i = 0; i < 4; i++) {
                if (userPoints.points[i] == 0) {
                    //dodaje rozbójnika

                    userPoints.addMode(IGameState.Mode.REAVER, i);


                    zero++;
                }
            }//wiem ilu graczy nie wieło żadne lewy
            if (zero == 3) {
                // zero punktów dla gracza który wzioł wszystkie wziątki
                // -130 dla pozostałych
                for (int i = 0; i < 4; i++) {
                    if (userPoints.points[i] == 0) {
                        userPoints.points[i] = -130;
                    } else {
                        userPoints.points[i] = 0;
                    }
                }
            } else {
                //jeśli każdy coś wziął to pętla się nie wykona zero=1 lub 2
                for (int i = 0; i < zero; i++) {
                    // zmieniamy punkty graczy
                    //- jeden gracz 0pkt – pozostali pkt ujemne x2
                    //- dwóch graczy 0pkt – pozostali pkt ujemne x3
                    userPoints.points[i] = userPoints.points[i] * (zero + 1);
                }
            }
        }//mamy punkty policzone dla zbója
        if (state.getMode().equals(IGameState.Mode.REAVER) || state.getMode().equals(IGameState.Mode.WIN_BACK)) {
            for (int i = 0; i < 4; i++) {
                userPoints.points[i] = 0;
                IUserState ust = state.getUserState(i);
                List<ITrick> list = ust.getTricks();
                for (int k = 0; k < list.size(); k++) {
                    userPoints.points[i] += 10;
                }
            }//mamy z sumowane punkty

            int zero = 0;
            for (int i = 0; i < 4; i++) {
                if (userPoints.points[i] == 0) {
                    //dodaje rozgrywkę i wychodzącego
                    userPoints.addMode(IGameState.Mode.BANDIT, i);


                    zero++;
                }

            }

            //zwraca lewy za deklarowane w licytacji
            //jak nie ma, tyly lew co trzeba to sprzedający
            // dosataje tyle co ma, dłużnik -130, reszta zostaje z tym co ma
            //i gramy rozbójnika gdzie zaczyna dłuznik
            for (int i = 0; i < 4; i++) {
                if (state.getUserState(i).getDebet() > 0) {
                    //wiem kto ma dług
                    if (state.getUserState(i).getDebet() * 10 <= userPoints.points[i]) {
                        //gitara ma tyle co trzeba
                        userPoints.points[state.getUserState(i).getBanker()] += 10 * state.getUserState(i).getDebet();
                        userPoints.points[i] -= 10 * state.getUserState(i).getDebet();
                    } else {
                        //biedakowi brakuje kart
                        userPoints.points[state.getUserState(i).getBanker()] += userPoints.points[i];
                        userPoints.points[i] = -130;
                        userPoints.addMode(IGameState.Mode.REAVER, i);

                    }
                    break;
                }

            }
            //zwiększa punkty w wypdaku slizgu bądź naksa
            for (int i = 0; i < 4 && zero != 0; i++) {
                if (userPoints.points[i] > 0) {
                    userPoints.points[i] *= (zero + 1);
                }
            }


        }
        return userPoints;



    }

    /**
     * Funkcja zwraca głęboką kopję stanu gry.
     * @param state
     * @return
     */
    public IGameState cloneState(IGameState state) {
        return state.clone();
    }
}
