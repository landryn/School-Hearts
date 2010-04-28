package hearts.defs.actions.gui;

import hearts.defs.actions.*;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.IGUIState;

/**
 * Po tej klasie powinny dziedziczyć wszystkie akcje wywołujące się na GUI.
 * @author szymon
 */
public abstract class AGUIAction extends AAction {

    public AGUIAction(int receiver) {
        super(receiver);
    }

    /**
     * Blokuje implementację pe
     * @param old
     * @return
     * @throws GameStateException
     */
    @Override
    public final IGameState perform(IGameState old) throws GameStateException {
        throw new GameStateException("GUIActions can't perform on GameState.");
    }

    public abstract void perform(IGUIState gui);

}
