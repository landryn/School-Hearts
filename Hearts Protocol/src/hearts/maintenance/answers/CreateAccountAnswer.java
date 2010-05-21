/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.maintenance.answers;

import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;
import javax.swing.JOptionPane;

/**
 * Tylko dziedziczy po LoginAnswer, typ obiektu mówi, że chodzi o
 * odpowiedź na tworzenie konta.
 * @author szymon
 */
public class CreateAccountAnswer extends LoginAnswer {

    public CreateAccountAnswer(boolean success, String message) {
        super(success, message, null);
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        if (success) {
            gui.showMessage("Założono konto", JOptionPane.INFORMATION_MESSAGE,
                    "Konto zostało założone pomyślnie");
            gui.getLoginPanel().setCreateAccountSelected(false);
        } else {
            gui.showMessage("Błąd", JOptionPane.ERROR_MESSAGE, message);
        }
        gui.getLoginPanel().setEnabled(true);
    }
}
