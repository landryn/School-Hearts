/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.protocol.IServerSocket;
import hearts.defs.state.GameConstants;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Server implements IServerSocket {

    private int port;
    private String host;
    private java.net.ServerSocket socket = null;
    private ArrayList<ServerClient> clientsList = null;

    private ArrayList<IActionListener> listeners = null;

    /**
     * <p>Tworzy obiekt serwera, który później trzeba wystartować.</p>
     * Tworzy i otwiera socket.
     * @param port port na którym nasłuchuje serwer
     * @param host host na którym nasłuchuje serwer
     */
    public Server(int port, String host) throws IOException {
        this.port = port;
        this.host = host;

        this.socket = new java.net.ServerSocket(port);
        clientsList = new ArrayList<ServerClient>();
        listeners = new ArrayList<IActionListener>();
    }

    /**
     * Metoda nasłuchuje w oczekiwaniu na klientów.
     * Dla każdego nowego klienta tworzny obiekt ServerClient,
     * dodaje go do list i startuje wątek nasłuchujący.
     */
    public void run() {
        try {
            while (true) {
                Socket s = this.socket.accept();
                Logger.getLogger(Server.class.getName()).log(Level.INFO, "Ktoś się połączył.");
                ServerClient sc = new ServerClient(s);
                clientsList.add(sc);
                Thread th = new Thread(sc);
                th.start();

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "IOException on listening for clients.", ex);
        } finally {
            try {
                if (!this.socket.isClosed()) {
                    this.socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
            }
        }
    }

    public int getId() {
        return GameConstants.SERVER;
    }

    public void addActionListener(IActionListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(AAction action) {
        for(IActionListener listener: listeners) {
            listener.actionReceived(action);
        }
    }

    public void actionReceived(AAction a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
