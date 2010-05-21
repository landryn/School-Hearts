/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.state.actions;

import hearts.defs.actions.AChatAction;
import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author pawel
 */
public class ChatAction extends AChatAction {

    protected String message;
    protected Date date;

    public ChatAction(int receiver, String message) {
        super(receiver);
        this.message = message;
        this.date = new Date();
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        String dateString = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(date);
        gui.getGameTable().appendToChatArea(senderName + " [" + dateString +
                "]>\n" + message);
    }
}
