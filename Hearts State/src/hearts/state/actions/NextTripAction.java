/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.actions.gui.NextTripGUIAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Zdarzenie rozpoczynającą klejnę lewe.
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
       
       
       old.getUserState(getWiner()).addTrick(old.getTrick());
       old.clearTrick(isLast());
       old.setNumTrick(old.getNumTrick()+1) ;
       
      
       NextTripGUIAction ac=null;
       for (int i = 0; i < 4; i++) {
                ac = new NextTripGUIAction(i);
                ac.setWiner(getWiner());
                ac.setSender(this.getSender());
                old.addAction(ac);
            }
        old.setActiveUser(winer);
        //koniec rozdania, dodaje akcję o nowym rozdaniu.
       if(GameConstants.GET_LOGGER) Logger.getLogger(NextTripAction.class.getName()).log(Level.INFO, "Aktywny gracz: "+old.getActiveUser());
        if(old.dealEnds()) old.addAction(new NextModeAction(GameConstants.SERVER));
        return old;
    }



}
