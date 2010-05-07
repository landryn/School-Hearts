/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 * Akcja wysyłana do wychodzącego informacje kto wygrał licytację i jego ofertę.
 * @author Paweł Trynkiewicz
 */
public class AuctionEndAGUI  extends AGUIAction{

    public AuctionEndAGUI(int receiver) {
        super(receiver);
    }
    

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
