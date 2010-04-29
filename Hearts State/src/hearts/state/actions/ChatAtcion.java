/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AChatAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;

/**
 *
 * @author pawel
 */
public class ChatAtcion extends AChatAction {

    public ChatAtcion(int receiver) {
        super(receiver);
    }
    

    @Override
    public IGameState perform(IGameState old) throws GameStateException {
        old.addAction(this);
        return old;
    }

}
