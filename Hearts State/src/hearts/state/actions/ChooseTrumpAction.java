/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;

/**
 * Wychodzący wysyła, jaki atut będzie obowiązywał w odgrywce.
 * @author Paweł Trynkiewicz
 */
public class ChooseTrumpAction  extends AAction{

    public ChooseTrumpAction(int receiver) {
        super(receiver);
    }

CardColor turmp;

    public CardColor getTurmp() {
        return turmp;
    }

    public void setTurmp(CardColor turmp) {
        this.turmp = turmp;
    }

    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        clone.setTrump(this.getTurmp());
        clone.setAuction(false);
        System.out.println(clone.getTrump());
        return clone;
    }

}
