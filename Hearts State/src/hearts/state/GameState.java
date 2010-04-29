package hearts.state;

import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import hearts.state.exceptions.IllegalModeChangeException;
import hearts.state.exceptions.UserExistsException;
import java.io.Serializable;

/**
 *
 * @author szymon
 */
public class GameState
        extends AActionList
        implements IGameState, Cloneable, Serializable {

    protected ITrick trick = new Trick(false);
    protected CardColor trump = null;
    protected IUserState[] userStates = {null, null, null, null};
    
    protected int activeUserId=0;
    protected boolean dealEnd=false;
    private int menyTricks=0;
    protected boolean auction = false;
    protected Mode mode = Mode.WAITING_FOR_PLAYERS;

    @Override
    public IGameState clone() {
        GameState stateClone = (GameState) super.clone();
        // kopiowanie zmiennych obiektów:
        for (int i = 0; i < stateClone.userStates.length; ++i) {
            stateClone.userStates[i] = stateClone.userStates[i].clone();
        }
        stateClone.trick = stateClone.trick.clone();
        return stateClone;
    }

    @Override
    public IUserState getUserState(int id) {
        return userStates[id];
    }

    @Override
    public synchronized void setUserState(int id, IUserState state)
            throws GameStateException {
        if (userStates[id] == null) {
            userStates[id] = state;
        } else {
            throw new UserExistsException(state, id);
        }
    }

    @Override
    public synchronized int getActiveUser() {
        return activeUserId;
    }

    @Override
    public synchronized int nextUser() {
        activeUserId = (activeUserId + 1) % 4;
        return activeUserId;
    }

    @Override
    public synchronized Mode getMode() {
        return mode;
    }

    @Override
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

    @Override
    public synchronized CardColor getTrump() {
        return trump;
    }

    @Override
    public synchronized void setTrump(CardColor c) throws GameStateException {
        // TODO: w zaleznosci od trybu i stanu gry rzucić wyjątkiem.
        this.trump = c;
    }

    @Override
    public synchronized boolean isAuction() {
        return auction;
    }

    @Override
    public synchronized void setAuction(boolean auction) throws GameStateException {
        // TODO: w zaleznosci od trybu i stanu gry rzucić wyjątkiem.
        this.auction = auction;
    }

    @Override
    public synchronized ITrick getTrick() {
        return trick;
    }

    @Override
    public void clearTrick(boolean last) {
        trick = new Trick(last);
        trick.setFirst(activeUserId);
    }
    
    public Mode addUser(IUserState user) {
        int i=0;
        while(i<userStates.length && userStates[i]==null) i++;
        if (i<userStates.length ) {
            userStates[i]=user;
            if(i==3) mode=Mode.BANDIT;
        }
        return mode;

    }

    public boolean trickEnds() {
        return trick.ends();
    }

    public boolean dealEnds() {
        return (menyTricks==13);
       
    }

    public int getNumTrick() {
        return menyTricks;
    }

    public void setNumTrick(int i){
        this.menyTricks=i;
    }

    
}
