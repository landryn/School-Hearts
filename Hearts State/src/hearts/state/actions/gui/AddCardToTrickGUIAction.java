/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGUIGameTable;
import hearts.defs.state.IGUIState;
import hearts.defs.state.TrickException;
import hearts.defs.state.WrongCardsCountInOpponentStackException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            // TODO: user ma byc przesylany jako pole w tej akcji

            IGUIGameTable table = gui.getGameTable();
            int activeUser = table.getActiveUser();

            table.getTrick().addCard(card, activeUser);
            if (activeUser == table.getLocalUserId()) {
                // wyciaga karte z kart lokalnego usera
                table.withdrawCard(card);
            } else {
                // badz z ktoregos z przeciwnikow
                table.getCardsStack(activeUser).decrease();
            }
            table.setActiveUser((activeUser + 1) % 4);
        } catch (TrickException ex) {
            Logger.getLogger(AddCardToTrickGUIAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongCardsCountInOpponentStackException ex) {
            Logger.getLogger(AddCardToTrickGUIAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GameStateException ex) {
            Logger.getLogger(AddCardToTrickGUIAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
