/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance.answers;

import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Odpowiedź na logowanie.
 * @author szymon
 */
public class LoginAnswer extends AMaintenaceAction implements Serializable {

    protected Boolean success;
    protected String message;

    /**
     * Opowiedź serwera na logowanie
     * @param success czy udało się zalogować
     * @param message ew. wiadomość mówiąca o przyczynie.
     */
    public LoginAnswer(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        gui.getSocket().setLoggedIn(success);
        gui.showMessage("YEAH!", JOptionPane.INFORMATION_MESSAGE, success.toString());
    }

    /**
     * Zwraca wiadomość mówiącą o przyczynie ew. błędu.
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Czy udało się zalogować
     * @return
     */
    public Boolean getSuccess() {
        return success;
    }

}
