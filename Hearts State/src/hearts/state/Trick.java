package hearts.state;

import hearts.defs.state.ICard;
import hearts.defs.state.ITrick;
import hearts.defs.state.TrickException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Przechowuje informacje o wziątce.
 * @author szymon
 */
public class Trick implements ITrick, Serializable, Cloneable {

    protected ICard[] cards = new ICard[4];
    private int manyCards=0;
    protected boolean last;
    protected int first;
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
        manyCards++;
        cards[userId]=c;
    }

    public ICard[] getCards() {
        return cards;
    }

    public boolean isLast() {
        return last;
    }

    @Override
    public ITrick clone() {
        Trick trickClone = null;
        try {
            trickClone = (Trick) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Trick.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trickClone;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int user) {
        first=user;
    }

    public boolean ends() {
       return (manyCards==4);
    }
}
