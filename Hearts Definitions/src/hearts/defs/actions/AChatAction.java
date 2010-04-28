package hearts.defs.actions;

/**
 * <p>Klasa służąca o chatu. Sędzia powinien obsługiwać chat
 * podając do akcji dziedziczących po ChatAction pusty stan gry
 * np. klasa DumbState implementująca tylko listę akcji do rozesłania.</p>
 *
 * <p>Generalnie akcje implementujące ten interfejs nie powinny modifikować
 * stanu gry oprócz ew. dodania komunikatów do rozesłania</p>
 * @author szymon
 */
public abstract class AChatAction extends AAction {

    public AChatAction(int receiver) {
        super(receiver);
    }

}
