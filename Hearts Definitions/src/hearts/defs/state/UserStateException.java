package hearts.defs.state;

/**
 * Jeśli coś złego miałoby się stać ze stanem usera.
 * @author szymon
 */
public class UserStateException extends GameStateException {

    public UserStateException(String message) {
        super(message);
    }
}
