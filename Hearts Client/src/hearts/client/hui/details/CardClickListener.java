/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client.hui.details;

import hearts.defs.state.GameConstants;
import hearts.defs.state.ICard;
import hearts.defs.state.IGUIState;
import hearts.state.actions.AddCardToTrickAction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author szymon
 */
public class CardClickListener extends MouseAdapter {

    private IGUIState gui;
    private CardPlaceHolder holder;

    public CardClickListener(IGUIState gui, CardPlaceHolder holder) {
        this.gui = gui;
        this.holder = holder;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        AddCardToTrickAction action = new AddCardToTrickAction(GameConstants.SERVER);
        CardIcon icon = holder.getCardIcon();
        if (icon != null) {
            ICard card = icon.getCard();
            if (card != null) {
                Logger.getLogger(CardClickListener.class.getName()).log(Level.INFO, card.toString());
                action.setCard(card);
                gui.getSocket().actionReceived(action);
            }
        }
    }

    public IGUIState getGui() {
        return gui;
    }

    public void setGui(IGUIState gui) {
        this.gui = gui;
    }

    public CardPlaceHolder getHolder() {
        return holder;
    }

    public void setHolder(CardPlaceHolder holder) {
        this.holder = holder;
    }
}
