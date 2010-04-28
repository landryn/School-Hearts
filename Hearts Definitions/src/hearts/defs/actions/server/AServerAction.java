package hearts.defs.actions.server;

import hearts.defs.actions.*;

/**
 * Po tej klasie powinny dziedziczyć wszystkie akcje wywoływane na serwerze
 * @author szymon
 */
public abstract class AServerAction extends AAction {

    public AServerAction(int receiver) {
        super(receiver);
    }
}
