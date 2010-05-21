/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 * Klasa informująca graczy licytacji. Pierwszy tę akcje otrzyna następny gracz po
 * wychodzącym graczu z iformacją o maksymalnej ofercie w tym przypadku 0. W odpowiedzi odsyła AuctionOfferAction,
 * z jego ofertą. Aukcja kończy się w momęcie gdy, w licytacji zostanie jeden gracz.
 * @author Paweł Trynkiewicz
 */
public class AuctionGUIAction extends AGUIAction {

    int lider;
    int commece;
    int quotion;
    int activeUser;

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

    public int getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int activeUser) {
        this.activeUser = activeUser;
    }

    public AuctionGUIAction(int receiver) {
        super(receiver);
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
