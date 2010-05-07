/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;

/**
 * Wychodzący wysyła, jaki atut będzie obowiązywał w odgrywce.
 * @author Paweł Trynkiewicz
 */
public class ChooseTrumpAction  extends AAction{

    public ChooseTrumpAction(int receiver) {
        super(receiver);
    }


    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
