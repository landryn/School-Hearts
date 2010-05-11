package hearts.defs.actions;

import hearts.defs.actions.gui.AGUIAction;

/**
 * <p>Klasa służąca o chatu. Sędzia powinien obsługiwać chat
 * podając do akcji dziedziczących po ChatAction pusty stan gry,
 * klasa DumbState! </p>
 *
 * <p>Generalnie akcje implementujące ten interfejs nie powinny modifikować
 * stanu gry oprócz ew. dodania komunikatów do rozesłania</p>
 * @author szymon
 */
public abstract class AChatAction extends AGUIAction {

    public AChatAction(int receiver) {
        super(receiver);
    }
}
