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
public class AuctionEndGUIAction  extends AGUIAction{

    public AuctionEndGUIAction(int receiver) {
        super(receiver);
    }

    int lider;
    int commece;
    int quotion;
    int activeUser;

    public int getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int activeUser) {
        this.activeUser = activeUser;
    }

    public int getCommece() {
        return commece;
    }

    public void setCommece(int commece) {
        this.commece = commece;
    }

    public int getLider() {
        return lider;
    }

    public void setLider(int lider) {
        this.lider = lider;
    }

    public int getQuotion() {
        return quotion;
    }

    public void setQuotion(int quotion) {
        this.quotion = quotion;
    }

    
    

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
