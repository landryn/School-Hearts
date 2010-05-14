/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client.hui.details;

import hearts.defs.state.CardColor;
import hearts.defs.state.ICard;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author szymon
 */
public class CardIcon extends CardBack implements ICard {

    private ICard card;
    protected static final Map<CardColor, String> CARD_SYMBOLS = new Hashtable<CardColor, String>(4);
    protected static final String[] CARD_CHARACTERS = new String[15];

    static {
        CARD_SYMBOLS.put(CardColor.CLUB, "♣");
        CARD_SYMBOLS.put(CardColor.DIAMOND, "♦");
        CARD_SYMBOLS.put(CardColor.HEART, "♥");
        CARD_SYMBOLS.put(CardColor.SPADE, "♠");

        for(int i = 0; i < JACK; ++i) {
            CARD_CHARACTERS[i] = "" + i;
        }
        
        CARD_CHARACTERS[JACK] = "J";
        CARD_CHARACTERS[QUEEN] = "Q";
        CARD_CHARACTERS[KING] = "K";
        CARD_CHARACTERS[ACE] = "A";

    }

    public CardIcon(ICard card) {
        this.card = card;
    }

    @Override
    protected void paintSpecyfic(Graphics g) {
        if (card.getColor().equals(CardColor.DIAMOND) ||
                card.getColor().equals(CardColor.HEART)) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        g.drawString(CARD_SYMBOLS.get(card.getColor()), 21, 35);
        g.drawString(CARD_CHARACTERS[card.getValue()], 16, 63);
    }

    public int getValue() {
        return card.getValue();
    }

    public CardColor getColor() {
        return card.getColor();
    }

    @Override
    public boolean equals(Object obj) {
        if(this.card == obj) {
            return true;
        }
        if(this.card != null) {
            return this.card.equals(obj);
        }
        return false;
    }
   
}
