/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;

import hearts.state.actions.gui.AddCardToTrickAGUI;

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



    /**
     * Funkcja dodaje kartę do lewy, pocztym do stanu gry dodaje odpowiednie komunikaty.
     * @param old
     * @return
     * @throws GameStateException
     */
    @Override
    public IGameState perform(IGameState old) throws GameStateException {
        System.out.println("public class AddCardToTrickAction  extends  AAction public IGameState perform(IGameState old) throws GameStateException");
      if (old.getTrick().getFirst()==-1) old.getTrick().setFirst(sender);
       //dodaje karte
       old.getTrick().addCard(card, sender);
       //ustawiam aktywnego usera
       old.nextUser();

       AddCardToTrickAGUI ac = null;
       //Dodaje komunikaty do wysłania
            for (int i = 0; i < 4; i++) {
                ac = new AddCardToTrickAGUI(i);
                ac.setCard(this.getCard());
                ac.setSender(this.getSender());
                old.addAction(ac);
            }
       
       return old;
    }

}
