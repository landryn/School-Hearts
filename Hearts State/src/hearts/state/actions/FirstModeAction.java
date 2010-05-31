/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.state.actions.gui.NewDealForUserGUIAction;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Akcja odpowiedzialna u ustawienie pierwszego rozdania. Wywoływana raz po dołączeniu czwartego
 * gracz, tylko raz. Rozsyła akcję NewDealForUserGUIAction.
 * @author Paweł Trynkiewicz
 */
public class FirstModeAction  extends AAction{
    private ICard [][]cards=new ICard[4][];
    //ilość zbójów i odgrywek w grze
    private int modes;

    public int getModes() {
        return modes;
    }

    public void setModes(int modes) {
        this.modes = modes;
    }


    public ICard[] getCards(int user) {
        return cards[user];
    }

    public void setCards(ICard[] cards, int user) {
        this.cards[user] = cards;
    }


    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        System.out.println("public class FirstModeAction  extends AAction public IGameState perform(IGameState clone) throws GameStateException");
        /*
         * 1. Rozdaje karty graczom.
         * 2. Dodaje 0 do punktacji
         * 3. Dodaje rozgrywki
         * 4. Wysyłam akcję do urzytkowników
         */

        //tablica akcji dla urzytkowników
        NewDealForUserGUIAction[] tab = new NewDealForUserGUIAction[4];


        for(int i=0;i<4;i++){
            if(GameConstants.GET_LOGGER) Logger.getLogger(FirstModeAction.class.getName()).log(Level.INFO, "Gracz: "+ clone.getUserState(i).getName());
          
            for(int k=0;k<13;k++){
          if(GameConstants.GET_LOGGER)  Logger.getLogger(FirstModeAction.class.getName()).log(Level.INFO,"|"+cards[i][k].getColor().name()+" "+cards[i][k].getValue()+"|");
                clone.getUserState(i).addCard(cards[i][k]);
               
            }
            clone.getUserState(i).addPoints(0);
            //dodałem karty dla urzytkowników.


        }
        int commence=1;
        
        for(int i=0;i<modes;i++){
            clone.addMode(IGameState.Mode.BANDIT);
            clone.addCommence(commence%4);
            ++commence;

        }
        for(int i=0;i<modes;i++){
            clone.addMode(IGameState.Mode.REAVER);
            clone.addCommence(commence%4);
            ++commence;

        }
  
        //ustawiłem nowy typ rozgrywki.
        clone.nextMode();
        clone.setDealer(0);
        clone.setActiveUser(clone.removeCommence());
        //ustawiłem numer lewy
        clone.setNumTrick(0);
        //ustawiam akcje urzytkowników

        for(int i=0;i<tab.length;i++){
            tab[i]=new NewDealForUserGUIAction(i);
            tab[i].setSender(this.sender);
            //dodaje karty
            tab[i].setCards(cards[i]);
            //dodaje punkty do tali gracza
            //tab[i].setListPoints(new ArrayList(clone.getUserState(i).getPointsList()));
            for (int j = 0; j < tab.length; j++) {
                tab[i].setListPointsAt(new ArrayList(clone.getUserState(j).getPointsList()), j);
            }
            tab[i].setMode(clone.getMode());
            tab[i].setDealer(clone.getDealer());
            tab[i].setActiveUser(clone.getActiveUser());
            //dodanie akcji do wysłania
            clone.addAction(tab[i]);
        }
       if(GameConstants.GET_LOGGER) Logger.getLogger(FirstModeAction.class.getName()).log(Level.INFO, "Wychodzący: "+ clone.getActiveUser());
        return clone;
    }

    public FirstModeAction(int receiver) {
        super(receiver);
    }

}
