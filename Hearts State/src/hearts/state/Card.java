package hearts.state;

import hearts.state.exceptions.WrongCardValueException;
import hearts.defs.state.CardColor;
import hearts.defs.state.ICard;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implemantacja karty.
 * prezentuje obiekt niezmienny, nie potrzeby klonowania samego obiektu.
 * @author szymon
 */
public class Card implements ICard, Serializable, Cloneable {

    protected CardColor color;
    protected int value;

    public Card(CardColor color, int value) throws WrongCardValueException {
        if (value > ICard.ACE || value < 2) {
            throw new WrongCardValueException(value);
        }
        this.color = color;
        this.value = value;
    }

    public CardColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Card clone() {
        Card ob = null;
        try {
            ob = (Card) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ICard) {
            ICard other = (ICard) obj;
            if (this.color.equals(other.getColor()) &&
                    this.value == other.getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return color + "_" + value;
    }
}
