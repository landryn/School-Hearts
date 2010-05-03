package hearts.defs.state;

/**
 * Wyjątek rzucany, gdy do wziątki (Trick) próbujemy dodać 2 razy tę samą kartę
 * lub za dużo kart
 * @author szymon
 */
public class TrickException extends GameStateException {

    protected ICard[] cards;
    protected int userId;

    /**
     * Tworzy nowy obiekt wyjątku.
     * @param message
     * @param userId userId, które spowodowało problem
     * @param cards zostanie sklonowany w konstruktorze.
     */
    public TrickException(String message, int userId, ICard[] cards) {
        super(message);
        this.cards = cards.clone();
        this.userId = userId;
    }

    /**
     * zwraca tablicę kart
     * @return
     */
    public ICard[] getCards() {
        return cards;
    }

    /**
     *
     * @return id usera, które spowoedowało problem.
     */
    public int getUserId() {
        return userId;
    }
}
