/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.actions.gui;

import hearts.defs.actions.gui.AGUIAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGUIGameTable;
import hearts.defs.state.IGUIState;
import hearts.defs.state.IGameState;
import hearts.defs.state.IGameState.Mode;
import hearts.defs.state.WrongCardsCountInOpponentStackException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa przygotowana specjalnie z myślą o klijencie, zawiera nowe karty gracza i zaktualizowaną punktacje
 * oraz, nowy Typ rozgrywki.
 * @author Paweł Trynkiewicz
 */
public class NewDealForUserGUIAction extends AGUIAction {


    private ICard []cards=null;
    //private List<Integer> listPoints=null;
    private List<Integer> listPoints[]=null;
    private IGameState.Mode mode;
    private int dealer;
    private int activeUser;
    private boolean auction;

    public boolean isAuction() {
        return auction;
    }

    public void setAuction(boolean auction) {
        this.auction = auction;
    }

    public int getDealer() {
        return dealer;
    }

    public void setDealer(int dealer) {
        this.dealer = dealer;
    }

    public int getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int activeUser) {
        this.activeUser = activeUser;
    }

    public ICard[] getCards() {
        return cards;
    }

    public void setCards(ICard[] cards) {
        this.cards = cards;
    }

    public List<Integer>[] getListPoints() {
        return listPoints;
    }

    public void setListPoints(List<Integer>[] listPoints) {
        this.listPoints = listPoints;
    }

    public void setListPointsAt(List<Integer> listPoints, int index) {
        this.listPoints[index]=listPoints;
    }


    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public NewDealForUserGUIAction(int receiver) {
        super(receiver);
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        IGUIGameTable table = gui.getGameTable();
        /* TODO: nieuzyte dane:
         * dealer
         * listpoints
         */
        table.setCards(cards);
        for(int id = 0; id < 4; ++id) {
            if(id != table.getLocalUserId()) {
                try {
                    table.getCardsStack(id).setCount(13);
                } catch (WrongCardsCountInOpponentStackException ex) {
                    Logger.getLogger(NewDealForUserGUIAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        table.showAuction(auction);
        table.setFlipped(false);
        table.setMode(mode);
        table.setActiveUser(activeUser);
    }
}
