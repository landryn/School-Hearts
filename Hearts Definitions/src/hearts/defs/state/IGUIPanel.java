/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.defs.state;

/**
 *
 * @author szymon
 */
public interface IGUIPanel {

    public enum Panel {
        LOGIN,
        LOBBY,
        GAME
    }

    public Panel getPanelType();
}
