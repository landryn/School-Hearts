package hearts.state;

import hearts.state.exceptions.WrongCardValueException;
import hearts.defs.state.CardColor;
import hearts.defs.state.ICard;
import java.io.Serializable;

/**
 *
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
}
