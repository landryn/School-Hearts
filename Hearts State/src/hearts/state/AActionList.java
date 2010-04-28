package hearts.state;

import hearts.defs.actions.AAction;
import hearts.defs.state.IGameState;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa zajmująca się gromadzeniem wiadomości.
 * @author szymon
 */
public abstract class AActionList
        implements IGameState, Serializable, Cloneable {

    protected List<AAction> actions = new LinkedList<AAction>();

    public synchronized void addAction(AAction a) {
        actions.add(a);
    }

    public synchronized AAction nextAction() {
        AAction a = null;
        try {
            a = actions.remove(0);
        } catch (IndexOutOfBoundsException ex) {
            // ma zwrócić null.
        }
        return a;
    }

    @Override
    public IGameState clone() {
        AActionList stateClone = null;
        try {
            stateClone = (AActionList) super.clone();
            stateClone.actions = new LinkedList<AAction>();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AActionList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stateClone;
    }

}
