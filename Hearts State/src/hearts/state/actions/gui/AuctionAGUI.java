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
public class AuctionAGUI extends AGUIAction{

    public AuctionAGUI(int receiver) {
        super(receiver);
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}
