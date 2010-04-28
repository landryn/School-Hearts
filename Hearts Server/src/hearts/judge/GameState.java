/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.judge;

import hearts.defs.actions.AAction;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import java.io.Serializable;

/**
 *
 * @author pawel
 */
public class GameState implements  IGameState, Cloneable, Serializable{

    public IGameState clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IUserState getUserState(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUserState(int id, IUserState state) throws GameStateException {
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

    public Mode nextMode() throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CardColor getTrump() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTrump(CardColor c) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAuction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAuction(boolean auction) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ITrick getTrick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearTrick(boolean last) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addAction(AAction a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AAction nextAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
