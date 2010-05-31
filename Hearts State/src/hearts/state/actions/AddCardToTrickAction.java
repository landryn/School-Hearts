/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;

import hearts.state.actions.gui.AddCardToTrickGUIAction;

/**
 * Akcja próbuje połorzyć kartę na stole. Jeśli lewa się zakończyła dodaje NextTripAction.
 * @author Paweł Trynkiewicz
 */
public class AddCardToTrickAction  extends  AAction{

    protected ICard card=null;
    protected int userId=0;

    public AddCardToTrickAction(int receiver) {
        super(receiver);
    }

    public ICard getCard() {
        return card;
    }

    public void setCard(ICard card) {
        this.card = card;
    }

    public void setUserId(int id) {
        this.userId=id;
    }

    public int getUserId() {
        return this.userId;
    }

    /**
     * Funkcja dodaje kartę do lewy, pocztym do stanu gry dodaje odpowiednie komunikaty.
     * @param old
     * @return
     * @throws GameStateException
     */
    @Override
    public IGameState perform(IGameState old) throws GameStateException {
       
      if (old.getTrick().getFirst()==GameConstants.NO_CARD_IN_TRIP) old.getTrick().setFirst(sender);
       //dodaje karte
       old.getTrick().addCard(card, sender);
       //wyciągam kartę z pilu gracza
       old.getUserState(sender).withdrawCard(card);
       //ustawiam aktywnego usera
       old.nextUser();

       AddCardToTrickGUIAction ac = null;
       //Dodaje komunikaty do wysłania
            for (int i = 0; i < 4; i++) {
                ac = new AddCardToTrickGUIAction(i);
                ac.setCard(this.getCard());
                ac.setSender(this.getSender());
                ac.setUserId(this.getSender());
                old.addAction(ac);
            }
       if(old.getTrick().ends()) old.addAction(new NextTripAction(GameConstants.SERVER));
       
       return old;
    }

}
