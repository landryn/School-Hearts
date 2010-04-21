/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import java.io.Serializable;

/**
 *
 * @author szymon
 */
public interface Card  extends Serializable, Cloneable {
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    public CardColor getColor();
    public int getValue();
}