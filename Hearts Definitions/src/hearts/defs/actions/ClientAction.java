package hearts.defs.actions;

/**
 * Po tej klasie powinny dziedziczyć wszystkie akcje działające po stronie
 * klienta
 * @author szymon
 */
public abstract class ClientAction extends Action {

    public ClientAction(int receiver) {
        super(receiver);
    }

}
