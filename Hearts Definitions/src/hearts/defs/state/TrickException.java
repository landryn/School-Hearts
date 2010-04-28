package hearts.defs.state;

/**
 * Wyjątek rzucany, gdy do wziątki (Trick) próbujemy dodać 2 razy tę samą kartę
 * lub za dużo kart
 * @author szymon
 */
public class TrickException extends GameStateException {

    protected ICard[] cards;
    protected int userId;

    public TrickException(String message, int userId, ICard[] cards) {
        super(message);
        this.cards = cards.clone();
        this.userId = userId;
    }

    public ICard[] getCards() {
        return cards;
    }

    public int getUserId() {
        return userId;
    }
}
