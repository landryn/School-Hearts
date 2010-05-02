/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.protocol.IUserSocket;
import hearts.maintenance.IMaintenaceListener;
import hearts.maintenance.IMaintenance;
import hearts.maintenance.IMaintenanceNotifier;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wątek klienta tworzony przez serwer w celu nasłuchiwania na socketcie klienta.
 * Powiadamia ActionListenerów o otrzymanych akcjach gry pod warunkiem że klient jest zalogowany.
 * Powiadamia MaintanaceListenerów o otrzymancych akcjach typu (zalogowanie, utworzenie stołu, ...).
 * @author Michał Charmas
 */
public class ServerClient implements IUserSocket, IMaintenanceNotifier {

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

    /**
     * Lista mainenance listenerów powiadamianych o zdarzeniach Maintanace.
     */
    private ArrayList<IMaintenaceListener> maintenanceListeners = null;

    private boolean loggedIn = false;

    private int id;
    private String name = "";

    /**
     * <p>Tworzy nowego klienta serwera. Obiekt nasłuchuje na sockecie klienta.</p>
     * @param socket Socket klienta.
     * @throws IOException Wyrzucany w momencie gdy nie uda utworzyć się ObjectStreamów
     */
    public ServerClient(Socket socket) throws IOException {
        this.socket = socket;

        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        
        listeners = new ArrayList<IActionListener>();
        maintenanceListeners = new ArrayList<IMaintenaceListener>();
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
                Serializable object = (Serializable) input.readObject();
                Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "Otrzymano obiekt.");
                if(object instanceof AAction && isLoggedIn()) {
                    Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "Powiadomiam actionListenerow");
                    notifyListeners((AAction) object);
                } else if (object instanceof IMaintenance){
                    ((IMaintenance)object).setUserSocket(this);
                    Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "Powiadomiam mainteneceListenerow");
                    notifyMaintenanceListeners((IMaintenance) object);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.INFO, "Klient mógł się rozłączyć.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Błąd odbierania danych.", ex);
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
                notifyMaintenanceListeners(new ClientDisconnectedMaintenance(this));
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

    public void sendMaintenance(IMaintenance m) {
        try {
            this.output.writeObject(m);
            this.output.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Bład wysyłania maintenece.", ex);
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

    /**
     * Dodaje maintenance listenera.
     * @param listener
     */
    public void addMaintenanceListener(IMaintenaceListener listener) {
        maintenanceListeners.add(listener);
    }

    /**
     * Powiadamia maintenece listenerów.
     * @param maintenance
     */
    public void notifyMaintenanceListeners(IMaintenance maintenance) {
        for(IMaintenaceListener listener: maintenanceListeners) {
            listener.maintenanceReceived(maintenance);
        }
    }

    /**
     *
     * @return czy użytkownik jest zalogowany.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Ustawia czy użytkownik jest zalgoowany.
     * @param loggedIn czy użytkownik jest zalogowany.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        if(loggedIn) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, "Użytkownik " + this.getName() + " został zalogowany.");
        }
    }

}
