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
public class OpponentCardsStack extends JComponent {

    private int count = 13;
    private boolean vertical = true;
    protected boolean inverted = false;
    private final static CardBack cardBack = new CardBack();
    private final static double ANGLE = Math.PI / 2;

    {
        Dimension size = new Dimension(CardBack.CARD_HEIGHT, CardBack.CARD_HEIGHT);
        this.setMinimumSize(size);
    }

    @Override
    public void paint(Graphics g) {
        int moveX = 0;
        Graphics2D gg = (Graphics2D) g;
        int width = 0;

        if (vertical) {
            gg.translate(CardBack.CARD_HEIGHT - 1, 0);
            gg.rotate(ANGLE);
            width = this.getHeight();
        } else { // horizontal
            width = this.getWidth();
        }

        if (inverted) {
            gg.translate(width - 1, CardBack.CARD_HEIGHT - 1);
            gg.rotate(Math.PI);
        }

        double step = ((double) (width - CardBack.CARD_WIDTH)) / 12;

        for (int i = 0; i < count; ++i) {
            moveX = (int) (step * i);
            gg.translate(moveX, 0);
            cardBack.paint(gg);
            gg.translate(-moveX, 0);
        }

        if (inverted) {
            gg.rotate(-Math.PI);
            gg.translate(-width + 1, -CardBack.CARD_HEIGHT + 1);
        }

        if (vertical) {
            gg.rotate(-ANGLE);
            gg.translate(-CardBack.CARD_HEIGHT, 0);
        }
    }

    private void checkCount() throws WrongCardsCountInOpponentStackException {
        if (count > 13 || count < 0) {
            throw new WrongCardsCountInOpponentStackException(count);
        }
    }

    public int increase() throws WrongCardsCountInOpponentStackException {
        ++count;
        checkCount();
        this.repaint();
        return count;
    }

    public int decrease() throws WrongCardsCountInOpponentStackException {
        --count;
        checkCount();
        this.repaint();
        return count;
    }

    public void setCount(int val) throws WrongCardsCountInOpponentStackException {
        count = val;
        checkCount();
        this.repaint();
    }

    public int getCount() {
        return count;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
        this.repaint();
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
        this.repaint();
    }
}
