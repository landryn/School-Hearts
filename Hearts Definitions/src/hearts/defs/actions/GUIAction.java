package hearts.defs.actions;

/**
 * Po tej klasie powinny dziedziczyć wszystkie akcje wywołujące się na GUI.
 * @author szymon
 */
public abstract class GUIAction extends Action {

    public GUIAction(int receiver) {
        super(receiver);
    }

}
