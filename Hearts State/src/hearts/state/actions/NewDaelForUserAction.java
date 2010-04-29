/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.defs.state.IGameState.Mode;
import hearts.defs.state.ITrick;
import java.util.List;

/**
 * Klasa przygotowana specjalnie z myślą o klijencie, zawiera nowe karty gracza i z aktualizowaną punktacje
 * oraz, nowy Typ rozgrywki.
 * @author Paweł Trynkiewicz
 */
public class NewDaelForUserAction extends AAction {

    private ICard []cards=null;
    private List<Integer> listPoints=null;
    private IGameState.Mode mode;

    public ICard[] getCards() {
        return cards;
    }

    public void setCards(ICard[] cards) {
        this.cards = cards;
    }

    public List<Integer> getListPoints() {
        return listPoints;
    }

    public void setListTriks(List<Integer> list) {
        this.listPoints = list;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    



    
    public NewDaelForUserAction(int receiver) {
        super(receiver);
    }
    

    @Override
    public IGameState perform(IGameState old) throws GameStateException {
        throw new UnsupportedOperationException("This function can not be execute. Sorry");
    }

}
