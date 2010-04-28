/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.exceptions;

import hearts.defs.state.GameState.Mode;
import hearts.defs.state.GameStateException;

/**
 *
 * @author szymon
 */
public class IllegalModeChangeException extends GameStateException {
    private Mode mode;

    public IllegalModeChangeException(Mode mode) {
        super("Can't change game mode from " + mode);
        this.mode = mode;
    }

}
