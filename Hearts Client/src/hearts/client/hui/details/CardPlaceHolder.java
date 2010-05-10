/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client.hui.details;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author szymon
 */
public class CardPlaceHolder extends JComponent {

    protected CardIcon cardIcon = null;
    protected static CardBack CARD_BACK = new CardBack();

    public CardIcon getCardIcon() {
        return cardIcon;
    }
    protected boolean flipped = true;

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
        this.repaint();
    }

    public void setCardIcon(CardIcon cardIcon) {
        this.cardIcon = cardIcon;
        this.repaint();
    }

    public CardPlaceHolder() {
        Dimension size = new Dimension(CardBack.CARD_WIDTH, CardBack.CARD_HEIGHT);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (cardIcon != null) {
            if (!flipped) {
                cardIcon.paint(g);
            } else {
                CARD_BACK.paint(g);
            }
        }
    }
}
