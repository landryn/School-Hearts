/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGUIState;

/**
 * Klasa dodaje kartę na stół. 
 * @author Paweł Trynkiewicz
 */
public class AddCardToTrickAGUI extends AGUIAction {

    public AddCardToTrickAGUI(int receiver) {
        super(receiver);
    }
    protected ICard card=null;

    public ICard getCard() {
        return card;
    }

    public void setCard(ICard card) {
        this.card = card;
    }


    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}