/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.client;

import hearts.defs.state.Card;

/**
 * Interfejs w implemntowany przez giu, modyfikujący jego wygląd.
 * @author Paweł Trynkiewicz
 */
public interface updateGui {
    /**
     * Pokazuje karyt jakie gracz trzyma w ręce
     * @param Card[] cards lista kart
     */
    public void showUserCard(Card[] cards);

    /**
     * Pokazuje karty które leżą na stole
     * @param cards
     */
    public void showCardOnTable(Card[] cards);
    /**
     * Uaktualnia stan punktów gracza
     * @param points aktualna suma punktów
     */
    public void updateUserPoints(int points);

    /**
     * Uaktualnia czat.
     * @param message
     */

    public void updateChat(String message);
}
