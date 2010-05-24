/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.state.actions.gui.NewDealForUserGUIAction;
import hearts.defs.actions.AAction;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.defs.state.SFinalPoints;
import hearts.state.Judge;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Klasa podsumowuje rozdanie, ustawia typ następnego, i rozdaje karty graczom.
 * @author Paweł Trynkiewicz
 */
public class NextModeAction extends AAction {

    private ICard [][]cards=new ICard[4][];
    

    public NextModeAction(int receiver) {
        super(receiver);
    }    

    public void setICard(int number, ICard[] cards){
        this.cards[number]=cards;
    }

    public ICard[] getCards(int number){
        return this.cards[number];
    }
     

    @Override
    public IGameState perform(IGameState old) throws GameStateException {
       if(GameConstants.GET_LOGGER)  Logger.getLogger(NextModeAction.class.getName()).log(Level.INFO,"NextModeAction public IGameState perform(IGameState old) throws GameStateException");
        /**
         * 1. zliczam punkty
         * 2. czyszcze Trick
         * 3. Dodaje karty
         * 4. Zmieniam mode
         * 5. Zeruje licznik Trick
         * 6. Ustawiam  nowego rozdającego, i aktywnego usera.
         */
        


        NewDealForUserGUIAction[] tab = new NewDealForUserGUIAction[4];

        int point=0;
        //obliczam punkty
        SFinalPoints date=Judge.getPoints(old);
    
        for(int i=0;i<4;i++){

            //dodałem punkty graczowi
            old.getUserState(i).addPoints(date.points[i]);
             if(GameConstants.GET_LOGGER) Logger.getLogger(NextModeAction.class.getName()).log(Level.INFO, "gracz: "+ i + "punkty " + date.points[i] );
            old.getUserState(i).clearTricks();
            // Logger.getLogger(NextModeAction.class.getName()).log(Level.INFO, "Gracz: "+ old.getUserState(i).getName());
             for(int k=0;k<13;k++){
                 //  Logger.getLogger(NextModeAction.class.getName()).log(Level.INFO,"|"+cards[i][k].getColor().name()+" "+cards[i][k].getValue()+"|");
                  old.getUserState(i).addCard(cards[i][k]);
             }//dodałem graczowi karty
            

        }


        //dodaję rozgrywkę i rozgrywającego

        for(int i=0;i<date.mode.size();i++){
            old.addMode(date.mode.get(i));
            old.addCommence(date.user.get(i));
        }
        // zmieniam mode

        if(old.nextMode().equals(IGameState.Mode.REAVER)|| old.getMode().equals(IGameState.Mode.WAITING_FOR_PLAYERS)){
            old.setAuction(true);
        } else {
            old.setAuction(false);
        }

        old.setNumTrick(0);

        old.nextDealer();
      //nowy wychodzący do niego bedzie należała decyzja co dalej
        old.setActiveUser(old.removeCommence());
        
        old.clearTrick(false);
         for(int i=0;i<4;i++){
                tab[i]=new NewDealForUserGUIAction(i);
                tab[i].setCards(cards[i]);
                tab[i].setListPoints(new ArrayList(old.getUserState(i).getPointsList()));
               
                tab[i].setMode(old.getMode());
                tab[i].setDealer(old.getDealer());
                tab[i].setActiveUser(old.getActiveUser());
                tab[i].setAuction(old.isAuction());
                old.addAction(tab[i]);//dadałem akcję do wysłania
            }
        if(GameConstants.GET_LOGGER) Logger.getLogger(NextModeAction.class.getName()).log(Level.INFO, "Wychodzący: "+ old.getActiveUser());
        //dodaję aukcje rozpoczynającą licytację.
            if(old.isAuction()) old.addAction(new AuctionBeginAction(GameConstants.SERVER));
        return old;
    }

}
