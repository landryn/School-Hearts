package hearts.defs.actions.gui;

import hearts.defs.actions.*;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.IGUIState;

/**
 * Po tej klasie powinny dziedziczyć wszystkie akcje wywołujące się na GUI.
 * perform na stanie gry jest zablokowana
 * @author szymon
 */
public abstract class AGUIAction extends AAction {

    public AGUIAction(int receiver) {
        super(receiver);
    }

    /**
     * Blokuje implementację perform na stanie gry.
     * @param old
     * @return
     * @throws GameStateException zawsze.
     */
    @Override
    public final IGameState perform(IGameState old) throws GameStateException {
        throw new GameStateException("GUIActions can't perform on GameState.");
    }

    /**
     * Wykonaj akcję na gui
     * @param gui
     */
    public abstract void perform(IGUIState gui) throws GUIStateException;

}
