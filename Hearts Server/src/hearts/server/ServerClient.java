/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.protocol.IUserSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class ServerClient implements IUserSocket {

    /**
     * Socket do komunikacji.
     */
    private Socket socket;

    /**
     * Stream do odbierania obiektów.
     */
    private ObjectInputStream input = null;

    /**
     *  Stream do wysyłania obiektów.
     */
    private ObjectOutputStream output = null;

    /**
     * Lista actionListenerów powiadamianych o odebranych akcjach.
     */
    private ArrayList<IActionListener> listeners = null;

    private int id;
    private String name = "";

    /**
     * <p>Tworzy nowego klienta serwera. Obiekt nasłuchuje na sockecie klienta.</p>
     * @param socket Socket klienta.
     * @throws IOException Wyrzucany w momencie gdy nie uda utworzyć się ObjectStreamów
     */
    public ServerClient(Socket socket) throws IOException {
        this.socket = socket;

        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());

        listeners = new ArrayList<IActionListener>();
        Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "ServerClient utworzony.");
    }

    /**
     * <p>Nasłuchuje w nieskończonej pętli na dane.</p>
     * Odebrane dane są deserializowane do obiektu AAction.
     * Następnie powiadamiani są wszyscy ActionListenerzy.
     * 
     * Po zakończeniu nasłuchiwania zamyka wszystkie streamy i socket.
     */
    public void run() {
        try {
            while (true) {
                AAction action = (AAction) input.readObject();
                if (action == null) {
                    break;
                }
                Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "Otrzymano akcję.");
                notifyListeners(action);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Klient mógł się rozłączyć.", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Błąd odbierania danych.", ex);
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Błąd zamykania socketów.", ex);
            }
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void addActionListener(IActionListener listener) {
        this.listeners.add(listener);
    }

    public void actionReceived(AAction a) {
        try {
            this.output.writeObject(a);
            this.output.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Bład wysyłania akcji.", ex);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Powiadamia actionListenerów.
     * @param action
     */
    public void notifyListeners(AAction action) {
        for (IActionListener listener : this.listeners) {
            listener.actionReceived(action);
        }
    }

    /**
     * Rozłącza użytkownika przez brutalne zamknięcie socketa i spowodowanie wyjątku w pętli wątku.
     */
    public void disconnect() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Bład zamykania socketu przy rozłączniu użytkownika.", ex);
        }
    }
}
