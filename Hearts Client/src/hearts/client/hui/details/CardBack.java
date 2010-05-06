/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client.hui.details;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JComponent;

/**
 *
 * @author szymon
 */
public class CardBack extends JComponent {

    public final static int ROUNDNESS = 10;
    public final static int CARD_WIDTH = 100;
    public final static int CARD_HEIGHT = 140;
    protected final static int SPECIFIC_WIDTH = CARD_WIDTH - 1 - 2 * ROUNDNESS;
    protected final static int SPECIFIC_HEIGHT = CARD_HEIGHT - 1 - 2 * ROUNDNESS;
    protected final static int[] xs = {0, 20, 20, 40, 40, 0};
    protected final static int[] ys = {0, 0, 40, 40, 20, 20};

    public CardBack() {
        Dimension size = new Dimension(CARD_WIDTH, CARD_HEIGHT);

        // nie wiem, które tak naprawdę trzeba ustawić :P
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
    }

    @Override
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        // rysowanie samej ramki
        g.setColor(Color.white);
        g.fillRoundRect(0, 0, CARD_WIDTH - 1, CARD_HEIGHT - 1, ROUNDNESS, ROUNDNESS);
        g.setColor(Color.black);
        g.drawRoundRect(0, 0, CARD_WIDTH - 1, CARD_HEIGHT - 1, ROUNDNESS, ROUNDNESS);
        g.drawRect(ROUNDNESS, ROUNDNESS, SPECIFIC_WIDTH, SPECIFIC_HEIGHT);
        paintSpecyfic(g);
    }

    protected void paintSpecyfic(Graphics g) {
        Polygon p = new Polygon(xs, ys, xs.length);
        p.translate(10, 10);
        for (int i = 0; i < 3; ++i) {
            g.fillPolygon(p);
            p.translate(40, 0);
            g.fillPolygon(p);
            p.translate(-40, 40);
        }
    }
}
