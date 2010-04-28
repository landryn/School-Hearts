/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state;

import hearts.defs.actions.Action;
import hearts.defs.state.Trick;
import hearts.defs.state.UserState;

/**
 *
 * @author szymon
 */
public class GameState implements hearts.defs.state.GameState{

    public GameState clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public UserState getUserState(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUserState(int id, UserState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getActiveUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int nextUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Mode getMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Mode nextMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Mode getTrump() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAuction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Trick getTrick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearTrick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addAction(Action a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Action nextAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
