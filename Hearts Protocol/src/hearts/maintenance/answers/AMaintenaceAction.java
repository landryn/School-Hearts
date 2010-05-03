/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance.answers;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GameConstants;

/**
 *
 * @author szymon
 */
public abstract class AMaintenaceAction extends AGUIAction{

    public AMaintenaceAction() {
        super(GameConstants.NOT_IMPORTANT);
    }

}
