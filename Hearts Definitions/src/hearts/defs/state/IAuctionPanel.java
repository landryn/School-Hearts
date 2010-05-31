/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import hearts.defs.state.IGUIState;

/**
 *
 * @author szymon
 */
public interface IAuctionPanel {

    IGUIState getGui();

    void setActive(boolean active);

    void setGui(IGUIState gui);

    /**
     * Ustawia etykietę z ilością wziątek (bid) zaoferowanych przez użytkownika
     * id.
     * @param place
     * @param bid
     */
    void setUserBid(int id, int bid);

}
