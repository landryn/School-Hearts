package hearts.state.exceptions;

import hearts.defs.state.GameStateException;
import hearts.defs.state.IUserState;

/**
 *
 * @author szymon
 */
public class UserExistsException extends GameStateException {

    private IUserState userState;
    private int userId;

    public UserExistsException(IUserState userState, int userId) {
        super("User with id " + userId + " exists in GameState.");
        this.userState = userState;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public IUserState getUserState() {
        return userState;
    }
}
