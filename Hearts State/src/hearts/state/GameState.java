package hearts.state;

import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import hearts.state.exceptions.IllegalModeChangeException;
import hearts.state.exceptions.UserExistsException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implementacja stanu gry
 * @author szymon / Paweł Trynkiewicz
 */
public class GameState
        extends AActionList
        implements IGameState, Cloneable, Serializable {

    protected ITrick trick = new Trick(false);
    protected CardColor trump = null;
    protected IUserState[] userStates = {null, null, null, null};

    protected int dealer=0;
    
    protected int activeUserId=0;
    protected boolean dealEnd=false;
    private int menyTricks=0;
    protected boolean auction = false;
    protected Mode mode = Mode.WAITING_FOR_PLAYERS;

    protected ArrayList<Mode> modeList=new ArrayList<Mode>();


    /**
     * Klonowanie głębokie stanu gry.
     * Wszystkie modyfikowalne obiekty są klonowane:
     * <ul>
     * <li>stan userów</li>
     * <li>aktualna wziątka na stole</li>
     * </ul>
     * @return
     */
    @Override
    public IGameState clone() {
        GameState stateClone = (GameState) super.clone();
        // kopiowanie zmiennych obiektów,
        // najpierw pusta tablica
        stateClone.userStates = new IUserState[this.userStates.length];

        // do ktorej kopiuję stany użytkowników:
        for (int i = 0; i < this.userStates.length; ++i) {
            stateClone.userStates[i] = this.userStates[i].clone();
        }

        // klonowanie wziątki:
        stateClone.trick = this.trick.clone();

        //klonowanie rozgrywek

        stateClone.clearMode();

        for(int i=0; i< modeList.size(); i++) {
            stateClone.addMode(modeList.get(i));
        }
        return stateClone;
    }

    @Override
    public IUserState getUserState(int id) {
        return userStates[id];
    }

    /**
     * Ustawia obiekt state jako stan usera i indeksie id.
     * Można wykonać tylko raz taką akcję dla id.
     * @param id
     * @param state
     * @throws GameStateException gdy probujemy dwa razy ustawić
     * stan usera na tym samym indeksie
     */
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
        Mode mode = null;
        if (modeList.size()==0) {
            mode = Mode.END;
        } else {
            mode=modeList.remove(0);
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

    /**
     * Ustawia wziątkę na nowo stoworzony obiekt.
     * @param last czy wziątka jest jedną z dwóch ostatnich
     */
    @Override
    public void clearTrick(boolean last) {
        trick = new Trick(last);
        trick.setFirst(activeUserId);
    }
    
    public Mode addUser(IUserState user) {
        int i=0;
        while(i<userStates.length && userStates[i]!=null) i++;
        
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

    public void setActiveUser(int user) {
        this.activeUserId=user;
    }

    public void setDealer(int dealer) {
       this.dealer=dealer;
    }

    public int nextDealer() {
        dealer=(dealer+1)%4;
        return dealer;
    }

    public int getDealer() {
       return this.dealer;
    }

    public void addMode(Mode mode) {
        modeList.add(mode);
    }

    public void clearMode() {
       modeList=new ArrayList<Mode>();
    }

    
}
