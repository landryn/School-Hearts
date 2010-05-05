/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.state.actions.gui.NewDaelForUserAGUI;
import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.defs.state.SFinalPoints;
import hearts.state.Judge;
import java.util.ArrayList;

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
        System.out.println("NextModeAction public IGameState perform(IGameState old) throws GameStateException");
        /**
         * 1. zliczam punkty
         * 2. czyszcze Trick
         * 3. Dodaje karty
         * 4. Zmieniam mode
         * 5. Zeruje licznik Trick
         * 6. Ustawiam  nowego rozdającego, i aktywnego usera.
         */
        


        NewDaelForUserAGUI[] tab = new NewDaelForUserAGUI[4];

        int point=0;
        //obliczam punkty
        SFinalPoints date=Judge.getPoints(old);
    
        for(int i=0;i<4;i++){

            //dodałem punkty graczowi
            old.getUserState(i).addPoints(date.points[i]);
            old.getUserState(i).clearTricks();
             for(int k=0;k<13;k++){
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
      //nowy wychodzący do nie bedzie należała decyzja co dalej
        old.setActiveUser(old.removeCommence());
        

         for(int i=0;i<4;i++){
                tab[i]=new NewDaelForUserAGUI(i);
                tab[i].setCards(cards[i]);
                tab[i].setListPoints(new ArrayList(old.getUserState(i).getPointsList()));
               
                tab[i].setMode(old.getMode());
                tab[i].setDealer(old.getDealer());
                tab[i].setActiveUser(old.getActiveUser());
                old.addAction(tab[i]);//dadałem akcję do wysłania
            }
            //ustawiam nowego rozdającego
            
        return old;
    }

}
