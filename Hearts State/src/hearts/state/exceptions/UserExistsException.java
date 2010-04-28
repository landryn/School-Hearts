/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.exceptions;

import hearts.defs.state.GameStateException;
import hearts.defs.state.UserState;

/**
 *
 * @author szymon
 */
public class UserExistsException extends GameStateException {

    private UserState userState;
    private int userId;

    public UserExistsException(UserState userState, int userId) {
        super("User with id " + userId + " exists in GameState.");
        this.userState = userState;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public UserState getUserState() {
        return userState;
    }
}
