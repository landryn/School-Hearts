package hearts.defs.actions;

/**
 * Po tej klasie powinny dziedziczyć wszystkie akcje wywoływane na serwerze
 * @author szymon
 */
public abstract class ServerAction extends Action {

    public ServerAction(int receiver) {
        super(receiver);
    }
}
