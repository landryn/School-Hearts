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
public class AddCardToTrickGUIAction extends AGUIAction {

    public AddCardToTrickGUIAction(int receiver) {
        super(receiver);
    }
    protected ICard card=null;
    protected int userId=0;

    public ICard getCard() {
        return card;
    }

    public void setCard(ICard card) {
        this.card = card;
    }

    public void setUserId(int id) {
        this.userId=id;
    }

    public int getUserId() {
        return this.userId;
    }


    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
