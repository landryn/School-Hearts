/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.maintenance.clientinternal;

import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIPanel.Panel;
import hearts.defs.state.IGUIState;
import hearts.maintenance.answers.AMaintenaceAction;

/**
 *
 * @author szymon
 */
public class ServerDisconnectedAction extends AMaintenaceAction {

    @Override
    public void perform(final IGUIState gui) throws GUIStateException {

        gui.getGameTable().clearTrick();
        gui.getGameTable().setTableName(null);

        gui.getLoginPanel().setEnabled(true);

        gui.setPanel(Panel.LOGIN);
    }
}
