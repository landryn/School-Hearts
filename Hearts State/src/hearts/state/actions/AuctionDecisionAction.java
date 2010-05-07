/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;

/**
 *  Aktualny wychodzący wysyła informacje o tym, czy akceptyję wyniki licytacj. Czy nie.
 * @author Paweł Trynkiewicz
 */
public class AuctionDecisionAction extends AAction{

    public AuctionDecisionAction(int receiver) {
        super(receiver);
    }


    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
