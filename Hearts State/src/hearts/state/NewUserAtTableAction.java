/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 *
 * @author orbit
 */
public class NewUserAtTableAction extends AGUIAction {

    String username;
    int id;

    public NewUserAtTableAction(int receiver, String username, int id) {
        super(receiver);
        this.username = username;
        this.id = id;
    }
    
    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
