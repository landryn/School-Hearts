/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

/**
 *
 * @author szymon
 */
public interface ILoginPanel extends IGUIPanel {

    IGUIState getGui();

    void setEnabled(boolean enabled);

    void setGui(IGUIState gui);

    void setCreateAccountSelected(boolean selected);
}
