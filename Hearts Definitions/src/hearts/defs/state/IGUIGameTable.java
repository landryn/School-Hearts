/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.defs.state;

/**
 * Stan stołu gui
 * @author szymon
 */
public interface IGUIGameTable extends IGUIPanel {

    /**
     * Ustaw karty
     * @param cards
     */
    public void setCards(ICard[] cards);

    /**
     * Ustaw, czy karty mają być odwrócone, czyli
     * pokazywać awers.
     * @param flipped
     */
    public void setFlipped(boolean flipped);

    /**
     * Pobierz obiekt wziątki na stole
     * @return
     */
    public ITrick getTrick();

    /**
     * Wyczyść wziątkę na stole
     */
    public void clearTrick();

    /**
     * Ustawia usera na stole
     * @param place
     * @param name
     */
    public void setUser(int place, String name);

    public void setLocalUserId(int id);

    public int getLocalUserId();

    /**
     * Ustaw tryb gry
     * @param mode
     */
    public void setMode(IGameState.Mode mode);

    public IGameState.Mode getMode();

    public String getTableName();

    public void setTableName(String name);

    public void appendToChatArea(String line);

    public void setActiveUser(int id);

    public int getActiveUser();

    public IOpponentCardStack getCardsStack(int id);

    /**
     * Wyciągnij kartę c z kart trzymanych prze lokalnego użytkownika
     * @param c
     */
    public void withdrawCard(ICard c) throws GameStateException;

    public void showChooseTrumpDialog();
}
