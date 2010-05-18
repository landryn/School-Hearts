/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.exceptions;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGUIState;

/**
 *
 * @author orbit
 */
public class ExceptionGUIAction extends AGUIAction{

    private GameStateException exception;

    public ExceptionGUIAction(int receiver, GameStateException exception) {
        super(receiver);
        this.exception = exception;
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
