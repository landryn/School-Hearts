/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 *  Odświerżająca stól, zawierająca informacje kto wygrał lewę.
 * @author Paweł Trynkiewicz
 */
public class NextTripGUIAction  extends AGUIAction{
    private int winer;

    public NextTripGUIAction(int receiver) {
        super(receiver);
    }

    public int getWiner() {
        return winer;
    }

    public void setWiner(int winer) {
        this.winer = winer;
    }
    
    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}
