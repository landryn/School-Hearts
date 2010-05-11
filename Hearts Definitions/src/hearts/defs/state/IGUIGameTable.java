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

    /**
     * Ustaw tryb gry
     * @param mode
     */
    public void setMode(IGameState.Mode mode);

    public IGameState.Mode getMode();

    public String getTableName();

    public void setTableName(String name);
}
