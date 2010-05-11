/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.actions.gui.AuctionFinalGUIAction;
import hearts.state.actions.gui.ChooseTrumpGUIAction;

/**
 *  Aktualny wychodzący wysyła informacje o tym, czy akceptyję wyniki licytacj. Czy nie.
 * @author Paweł Trynkiewicz
 */
public class AuctionDecisionAction extends AAction {

    public AuctionDecisionAction(int receiver) {
        super(receiver);
    }
    boolean accep = true;

    public boolean isAccep() {
        return accep;
    }

    public void setAccep(boolean accep) {
        this.accep = accep;
    }

    @Override
    public IGameState perform(IGameState clone) throws GameStateException {
        clone.setAuction(false);
        if (isAccep()) {
            clone.setActiveUser(sender);

            AuctionFinalGUIAction act = null;
            for (int i = 0; i < 4; i++) {
                //nowa akcja
                act = new AuctionFinalGUIAction(i);
                act.setCommece(clone.getAuction().getCommence());
                act.setQuotion(0);
                act.setLider(clone.getAuction().getCommence());
                act.setActiveUser(clone.getAuction().getCommence());
                act.setSender(GameConstants.SERVER);
                clone.addAction(act);

            }

            if (clone.getMode() == IGameState.Mode.WIN_BACK) {
                ChooseTrumpGUIAction actt = new ChooseTrumpGUIAction(clone.getAuction().getCommence());
                actt.setSender(GameConstants.SERVER);
                clone.addAction(actt);
                clone.setAuction(true);

            }



        } else {
            //ustaiwłem prametry gracza.
            clone.getUserState(clone.getAuction().getLider()).setBanker(clone.getAuction().getCommence());
            clone.getUserState(clone.getAuction().getLider()).setDebet(clone.getAuction().getQuotation());


            clone.setActiveUser(clone.getAuction().getLider());
            AuctionFinalGUIAction act = null;
            for (int i = 0; i < 4; i++) {
                //nowa akcja
                act = new AuctionFinalGUIAction(i);
                act.setCommece(clone.getAuction().getCommence());
                act.setQuotion(0);
                act.setLider(clone.getAuction().getLider());

                act.setActiveUser(clone.getAuction().getLider());
                act.setSender(GameConstants.SERVER);
                clone.addAction(act);

            }

            if (clone.getMode() == IGameState.Mode.WIN_BACK) {
                ChooseTrumpGUIAction actt = new ChooseTrumpGUIAction(clone.getAuction().getLider());
                actt.setSender(GameConstants.SERVER);
                clone.addAction(actt);
                clone.setAuction(true);

            }

        }

      
        return clone;
    }
}
