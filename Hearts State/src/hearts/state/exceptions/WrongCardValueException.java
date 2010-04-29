package hearts.state.exceptions;

import hearts.defs.state.GameStateException;

/**
 *
 * @author szymon
 */
public class WrongCardValueException extends GameStateException {
int value;

    public WrongCardValueException(int value) {
        super("Card value " + value + " is prohibited.");
        this.value = value;
    }


}
