/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.action.client;

import hearts.defs.actions.ClientAction;
import hearts.defs.state.GameState;
import hearts.defs.state.GameStateException;

/**
 *
 * @author Paweł Trynkiewicz
 * 
 */
public class ConnectAction extends ClientAction {

    public ConnectAction(int receiver) {
        super(receiver);
    }


    @Override
    protected GameState performOnCopy(GameState copy) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
