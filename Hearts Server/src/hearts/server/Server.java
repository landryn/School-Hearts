/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.protocol.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class Server extends Thread {

    private int port;
    private String host;
    private java.net.ServerSocket socket = null;

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
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket s = this.socket.accept();
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
}
