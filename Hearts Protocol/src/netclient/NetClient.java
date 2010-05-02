/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netclient;

import hearts.defs.actions.AAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.actions.IActionNotifier;
import hearts.maintenance.IMaintenance;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class NetClient extends Thread implements IActionListener, IActionNotifier {

    private Socket socket = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public NetClient(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                Serializable object = (Serializable) input.readObject();
                Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Got data from server.");
                if (object == null) {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
            }
        }
    }

    public void sendAction(AAction action) {
        try {
            sendObject(action);
        } catch (IOException ex) {
            Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Error sending ACTION!", ex);
        }
    }

    public void sendMaintenece(IMaintenance maintenece) {
        try {
            sendObject(maintenece);
        } catch (IOException ex) {
            Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Error sending MAINTENECE!", ex);
        }
    }

    private void sendObject(Serializable object) throws IOException {
        output.writeObject(object);
        output.flush();
    }

    public void actionReceived(AAction a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addActionListener(IActionListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
