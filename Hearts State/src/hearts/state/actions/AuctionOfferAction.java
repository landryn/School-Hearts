/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;

/**
 * Akcja odpowiedzialna, za przesłanie oferty gracza w licytacji.
 * @author Paweł Trynkiewicz
 */
public class AuctionOfferAction extends AAction {

    public AuctionOfferAction(int receiver) {
        super(receiver);
    }


    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
