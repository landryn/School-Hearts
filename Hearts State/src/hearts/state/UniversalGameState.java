/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state;

import hearts.defs.actions.Action;
import hearts.defs.judge.Judge;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameState;
import hearts.defs.state.GameStateException;
import hearts.defs.state.Trick;
import hearts.defs.state.UserState;
import hearts.state.exceptions.IllegalModeChangeException;
import hearts.state.exceptions.UserExistsException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author szymon
 */
public class UniversalGameState implements GameState {

    protected List<Action> actions = new LinkedList<Action>();
    protected Trick trick; // TODO - implementacja wziątki
    protected CardColor trump = null;
    protected Judge judge;
    protected UserState[] userStates = {null, null, null, null};
    protected int activeUserId;
    protected boolean auction = false;
    protected Mode mode = Mode.WAITING_FOR_PLAYERS;

    public UniversalGameState(Judge judge) {
        this.judge = judge;
    }

    @Override
    public GameState clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public UserState getUserState(int id) {
        return userStates[id];
    }

    public synchronized void setUserState(int id, UserState state) throws GameStateException {
        if (userStates[id] == null) {
            userStates[id] = state;
        } else {
            throw new UserExistsException(state, id);
        }
    }

    public synchronized int getActiveUser() {
        return activeUserId;
    }

    public synchronized int nextUser() {
        activeUserId = (activeUserId + 1) % 4;
        return activeUserId;
    }

    public synchronized Mode getMode() {
        return mode;
    }

    public synchronized Mode nextMode() throws IllegalModeChangeException {
        Mode[] modes = Mode.values();
        try {
            for (int i = 0; i < modes.length; ++i) {
                if (mode.equals(modes[i])) {
                    mode = modes[i + 1];
                    break;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalModeChangeException(mode);
        }
        return mode;
    }

    public synchronized CardColor getTrump() {
        return trump;
    }

    public synchronized void setTrump(CardColor c) throws GameStateException {
        // TODO: w zaleznosci od trybu i stanu gry rzucić wyjątkiem.
        this.trump = c;
    }

    public synchronized boolean isAuction() {
        return auction;
    }

    public synchronized void setAuction(boolean auction) throws GameStateException {
        // TODO: w zaleznosci od trybu i stanu gry rzucić wyjątkiem.
        this.auction = auction;
    }
    
    public synchronized Trick getTrick() {
        return trick;
    }

    public void clearTrick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public synchronized void addAction(Action a) {
        actions.add(a);
    }

    public synchronized Action nextAction() {
        Action a = null;
        try {
            a = actions.remove(0);
        } catch (IndexOutOfBoundsException ex) {
            // ma zwrócić null.
        }
        return a;
    }

}
