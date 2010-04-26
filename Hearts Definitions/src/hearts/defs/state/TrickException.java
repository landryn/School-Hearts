package hearts.defs.state;

import java.util.List;

/**
 * Wyjątek rzucany, gdy do wziątki (Trick) próbujemy dodać 2 razy tę samą kartę
 * lub za dużo kart
 * @author szymon
 */
class TrickException extends Exception {
    List<Card> cards;

    public TrickException(String message, List<Card> cards) {
        super(message);
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
