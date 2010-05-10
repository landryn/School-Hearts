/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 *  Akcja kończąca rozgrywkę.
 * @author Paweł Trynkiewicz
 */
public class GameEndAGUIAuction extends AGUIAction {

    public GameEndAGUIAuction(int receiver) {
        super(receiver);
    }


    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
