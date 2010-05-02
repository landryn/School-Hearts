package hearts.defs.state;

import hearts.defs.protocol.IServerSocket;

/**
 *
 * @author szymon
 */
public interface IGUIState {

    public void setSocket(IServerSocket socket);

    public IServerSocket getSocket();
}
