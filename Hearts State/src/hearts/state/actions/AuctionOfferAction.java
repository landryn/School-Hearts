/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.Auction;
import hearts.state.actions.gui.AuctionAGUI;
import hearts.state.actions.gui.AuctionEndAGUI;

/**
 * Akcja odpowiedzialna, za przesłanie oferty gracza w licytacji.
 * @author Paweł Trynkiewicz
 */
public class AuctionOfferAction extends AAction {

    int quotation;

    public int getQuotation() {
        return quotation;
    }

    public void setQuotation(int quotation) {
        this.quotation = quotation;
    }

    public AuctionOfferAction(int receiver) {
        super(receiver);
    }

    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        /*
         * 1. próbuje dodać ofertę
         * 2. sprawdzam stan licytacji
         * 3. rozsyłam odpowiednie akcje
         */
        Auction auction = (Auction) clone.getAuction();


        if (auction.isEnd()) {

            clone.setActiveUser(auction.getActivetUser());
            //aukcja zkończona wysyłam do wszystkich informacje o tym

            AuctionEndAGUI act = null;
            for (int i = 0; i < 4; i++) {
                //nowa akcja
                act = new AuctionEndAGUI(i);
                act.setCommece(clone.getAuction().getCommence());
                act.setQuotion(clone.getAuction().getQuotation());
                act.setLider(clone.getAuction().getLider());
                act.setActiveUser(clone.getAuction().getActivetUser());
                clone.addAction(act);

            }


        } else {

            clone.getAuction().addOffer(sender, quotation);
            clone.setActiveUser(clone.getAuction().getActivetUser());


            AuctionAGUI act = null;
            for (int i = 0; i < 4; i++) {
                //nowa akcja
                act = new AuctionAGUI(i);
                act.setCommece(clone.getAuction().getCommence());
                act.setQuotion(clone.getAuction().getQuotation());
                act.setLider(clone.getAuction().getLider());
                act.setActiveUser(clone.getAuction().getActivetUser());
                clone.addAction(act);
            }




        }
        //Sprawdzam jeszcze raz, czy aukcja się nie skończyła.
        if (auction.isEnd()) {

            clone.setActiveUser(auction.getActivetUser());
            //aukcja zkończona wysyłam do wszystkich informacje o tym

            AuctionEndAGUI act = null;
            for (int i = 0; i < 4; i++) {
                //nowa akcja
                act = new AuctionEndAGUI(i);
                act.setCommece(clone.getAuction().getCommence());
                act.setQuotion(clone.getAuction().getQuotation());
                act.setLider(clone.getAuction().getLider());
                act.setActiveUser(clone.getAuction().getActivetUser());
                clone.addAction(act);

            }

        }

        return clone;
    }
}
