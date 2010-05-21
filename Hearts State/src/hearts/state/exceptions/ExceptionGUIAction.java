/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.exceptions;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGUIState;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author orbit
 */
public class ExceptionGUIAction extends AGUIAction {

    private GameStateException exception;

    public ExceptionGUIAction(int receiver, GameStateException exception) {
        super(receiver);
        this.exception = exception;
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        gui.showMessage("Błąd ze strony serwera", JOptionPane.ERROR_MESSAGE, exception.getLocalizedMessage());
        Logger.getLogger(ExceptionGUIAction.class.getName()).log(Level.SEVERE, null, "Server caught: " + exception);
        exception.printStackTrace();
    }
}
