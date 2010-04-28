package hearts.defs.state;

import java.util.List;

/**
 * Wyjątek rzucany, gdy do wziątki (Trick) próbujemy dodać 2 razy tę samą kartę
 * lub za dużo kart
 * @author szymon
 */
class TrickException extends Exception {
    List<ICard> cards;

    public TrickException(String message, List<ICard> cards) {
        super(message);
        this.cards = cards;
    }

    public List<ICard> getCards() {
        return cards;
    }
}
