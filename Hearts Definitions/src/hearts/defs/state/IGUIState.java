package hearts.defs.state;

import hearts.defs.actions.IActionListener;
import hearts.defs.protocol.IServerSocket;

/**
 * Interfejs stanu gui, na którym operować mają akcje gui.
 * @author szymon
 */
public interface IGUIState extends IActionListener, IGUIPanel {

    /**
     * ustaw socket jako tenże tutaj dany
     * powinien się dodać jako Maintenace Listener
     * @param socket
     */
    public void setSocket(IServerSocket socket);

    /**
     * Pobierz socket.
     * @return
     */
    public IServerSocket getSocket();

    public void setPanel(Panel p);

    public void showMessage(String title, int type, String message);

    public ILoginPanel getLoginPanel();
}
