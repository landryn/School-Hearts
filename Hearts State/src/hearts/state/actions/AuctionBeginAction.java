/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.Auction;
import hearts.state.actions.gui.AuctionGUIAction;

/**
 * Akcja rozpoczynająca licytacje. Przygotowuje gameState. Akcja dodawana do wysłania przez NextModeAction.
 * @author pawel
 */
public class AuctionBeginAction extends AAction{

    public AuctionBeginAction(int receiver) {
        super(receiver);
    }

    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        /*
         * 1 W gameState tworzę nową licytajce z aktualnym wychodzącym
         * 2. Rozsyłam do wszystkich informację o stanie licytacji.
         */
        clone.setAuction(new Auction(clone.getActiveUser()));
        clone.setActiveUser(clone.getAuction().getActivetUser());
        AuctionGUIAction auction=null;
        for(int i=0;i<4;i++){
            //nowa akcja
            auction=new AuctionGUIAction(i);
            auction.setCommece(clone.getAuction().getCommence());
            auction.setQuotion(clone.getAuction().getQuotation());
            auction.setLider(clone.getAuction().getLider());
            auction.setActiveUser(clone.getAuction().getActivetUser());
            clone.addAction(auction);

        }

      
        return clone;
    }


}
