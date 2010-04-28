/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.server;

import hearts.defs.actions.Action;
import hearts.defs.actions.ActionListener;
import hearts.defs.protocol.UserSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author orbit
 */
public class ServerClient extends Thread implements UserSocket{
    private Socket socket;
    private ObjectInputStream input=null;
    private ObjectOutputStream output=null;

    private int id;

    /**
     * <p>Tworzy nowego klienta serwera. Obiekt nasłuchuje na sockecie klienta.</p>
     * @param socket Socket klienta.
     * @throws IOException Wyrzucany w momencie gdy nie uda utworzyć się ObjectStreamów
     */
    public ServerClient(Socket socket) throws IOException {
        this.socket = socket;

        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while(true) {

        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void addActionListener(ActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionReceived(Action a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
