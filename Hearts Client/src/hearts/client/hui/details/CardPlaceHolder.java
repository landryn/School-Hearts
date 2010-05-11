/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client.hui.details;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author szymon
 */
public class CardPlaceHolder extends JComponent {

    protected CardIcon cardIcon = null;
    protected static CardBack CARD_BACK = new CardBack();
    protected boolean arrow = false;
    protected int arrowOrientation = Arrow.DOWN;

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

    public boolean isArrow() {
        return arrow;
    }

    public void setArrow(boolean arrow) {
        this.arrow = arrow;
        this.repaint();
    }

    public int getArrowOrientation() {
        return arrowOrientation;
    }

    public void setArrowOrientation(int arrowOrientation) {
        this.arrowOrientation = arrowOrientation;
        this.repaint();
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
        if (arrow) {
            Arrow.paint((Graphics2D) g, arrowOrientation);
        }
    }
}
