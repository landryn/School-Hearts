/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.GameState;
import hearts.state.actions.gui.NextTripAGUI;

/**
 * Zdarzenie rozpoczynającą klejnę lewe. Serwer po każdym pozytywnym zdarzeni powinien sprawdzić czy lewe się zakończyła
 * i wywołać netodę z Judge.judge z new NextTripAction(-1);
 * @author Paweł Trynkiewicz
 */
public class NextTripAction extends AAction {

    private int winer;
    private boolean last;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }



    public int getWiner() {
        return winer;
    }

    public void setWiner(int winer) {
        this.winer = winer;
    }
    
    public NextTripAction(int receiver) {
        super(receiver);
    }
    

    @Override
    public IGameState perform(IGameState old) throws GameStateException {
       
       
       old.getUserState(winer).addTrick(old.getTrick());
       old.clearTrick(last);
       old.setNumTrick(old.getNumTrick()+1) ;

      
       NextTripAGUI ac=null;
       for (int i = 0; i < 4; i++) {
                ac = new NextTripAGUI(i);
                ac.setWiner(winer);
                ac.setSender(this.getSender());
                old.addAction(ac);
            }
        old.setActiveUser(winer);
        return old;
    }



}
