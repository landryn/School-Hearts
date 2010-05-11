/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance.answers;

import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;

/**
 *
 * @author orbit
 */
public class JoinTableAnswer extends AMaintenaceAction {

    private String tableName;
    private Boolean success;

    public JoinTableAnswer(String tableName, Boolean success) {
        this.tableName = tableName;
        this.success = success;
    }
    
    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
