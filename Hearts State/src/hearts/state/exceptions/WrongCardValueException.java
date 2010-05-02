package hearts.state.exceptions;

import hearts.defs.state.GameStateException;

/**
 * Wyjątek mówiący, że dana wartość karty jest ble.
 * @author szymon
 */
public class WrongCardValueException extends GameStateException {

    int value;

    public WrongCardValueException(int value) {
        super("Card value " + value + " is prohibited.");
        this.value = value;
    }

    /**
     * Wartość karty, która była poza zakresem
     * @return
     */
    public int getValue() {
        return value;
    }

}
