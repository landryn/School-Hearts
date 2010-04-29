package hearts.state;

import hearts.defs.state.ICard;
import hearts.defs.state.ITrick;
import hearts.defs.state.TrickException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementacja wziątki
 * @author szymon
 */
public class Trick implements ITrick, Serializable, Cloneable {

    protected ICard[] cards = new ICard[4];
    protected boolean last;

    /**
     * Tworzy nową wziątkę.
     * @param last jeśli jest jedną z dwóch ostatnich wziątek.
     */
    public Trick(boolean last) {
        this.last = last;
    }

    public void addCard(ICard c, int userId) throws TrickException {
        if (userId > 3 || userId < 0) {
            throw new TrickException("Wrong user id: " + userId, userId, cards);
        }
        if (cards[userId] != null) {
            throw new TrickException("User " + userId + " already put card.", userId, cards);
        }
    }

    public ICard[] getCards() {
        return cards;
    }

    public boolean isLast() {
        return last;
    }

    /**
     * Jako, że obiekty kart są niemodyfikowalne, klonowana jest tylko
     * tablica kart z tymi samymi kartami.
     * @return
     */
    @Override
    public ITrick clone() {
        Trick trickClone = null;
        try {
            trickClone = (Trick) super.clone();
            trickClone.cards = trickClone.cards.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Trick.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trickClone;
    }
}
