/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client.hui.details;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author szymon
 */
public class Arrow {

    public static final int DOWN = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int RIGHT = 3;
    // do wywalenia?
    //protected static final int[] X_TRANS = { 0, 0, 0, 0};
    //protected static final int[] Y_TRANS = { 0, 0, 0, 0};
    protected static final double[] ROTATIONS = {0, Math.PI / 2, Math.PI, -Math.PI / 2};
    protected static final Polygon arrow;

    static {
        // 3/8 i 2/8 wymiarów szerokości karty, potrzebne do strzały
        int wp3 = (CardBack.CARD_WIDTH * 3) / 8;
        int wp2 = (CardBack.CARD_WIDTH * 2) / 8;

        // powstały poligon powinien rysować strzałę
        // z centrum w zerze układu współrzędnych.
        int[] xs = {0, wp3, wp2, wp2, -wp2, -wp2, -wp3};
        int[] ys = {wp3, 0, 0, -wp2, -wp2, 0, 0};
        arrow = new Polygon(xs, ys, xs.length);
    }

    public static void paint(Graphics2D g, int orientation) {
        // przenosimy sie na srodek powierzchni do rysowania
        g.translate(CardBack.CARD_WIDTH / 2, CardBack.CARD_HEIGHT / 2);
        // i obracamy
        g.rotate(ROTATIONS[orientation]);

        g.setColor(Color.white);
        g.fillPolygon(arrow);
        g.setColor(Color.black);
        g.drawPolygon(arrow);

        // cofamy transformacje z poczatku tej metody
        g.rotate(-ROTATIONS[orientation]);
        g.translate(-CardBack.CARD_WIDTH / 2, -CardBack.CARD_HEIGHT / 2);
    }
}
