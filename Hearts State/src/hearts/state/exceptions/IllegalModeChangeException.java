package hearts.state.exceptions;

import hearts.defs.state.IGameState.Mode;
import hearts.defs.state.GameStateException;

/**
 * Wyjątek występujący, gdy chcemy przeskoczyć np. 2 tryby gry na raz.
 * używany w GameState.nextMode().
 * @author szymon
 */
public class IllegalModeChangeException extends GameStateException {

    private Mode mode;

    public IllegalModeChangeException(Mode mode) {
        super("Can't change game mode from " + mode);
        this.mode = mode;
    }
}
