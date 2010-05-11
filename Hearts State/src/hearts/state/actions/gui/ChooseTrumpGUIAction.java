/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 *  Acja informująca gracza że ma tybrać atuat
 * @author Paweł Trynkiewicz
 */
public class ChooseTrumpGUIAction extends AGUIAction{

    public ChooseTrumpGUIAction(int receiver) {
        super(receiver);
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
