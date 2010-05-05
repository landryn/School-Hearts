/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import hearts.defs.state.IGUIState;

/**
 *
 * @author szymon
 */
public interface ILoginPanel {

    IGUIState getGui();

    void setEnabled(boolean enabled);

    void setGui(IGUIState gui);

}
