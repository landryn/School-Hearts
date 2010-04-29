/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.state.GameState;

/**
 * Akcja próbuje połorzyć kartę na stole.
 * @author Paweł Trynkiewicz
 */
public class AddCardToTrickAction  extends  AAction{

    protected ICard card=null;


    public AddCardToTrickAction(int receiver) {
        super(receiver);
    }

    public ICard getCard() {
        return card;
    }

    public void setCard(ICard card) {
        this.card = card;
    }




    @Override
    public IGameState perform(IGameState old) throws GameStateException {
       GameState game= (GameState) old.clone();
       game.getTrick().addCard(card, sender);
       return game;
    }

}
