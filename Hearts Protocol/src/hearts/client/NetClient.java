/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.client;

import hearts.defs.actions.AAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.protocol.IServerSocket;
import hearts.defs.state.GameConstants;
import hearts.defs.protocol.IMaintenaceListener;
import hearts.defs.protocol.IMaintenance;
import hearts.maintenance.clientinternal.ServerDisconnectedAction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa klienta serwera sieciowego.
 * @author Michał Charmas
 */
public class NetClient implements IServerSocket {

    private Socket socket = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private ArrayList<IActionListener> listeners = new ArrayList<IActionListener>();
    private ArrayList<IMaintenaceListener> maintenanceListeners = new ArrayList<IMaintenaceListener>();
    private boolean loggedIn = false;

    /**
     * Stara się połączyć z serwerem.
     * @param host host serwera
     * @param port port serwera
     * @throws UnknownHostException w momencie gdy nie moze znaleźć hosta
     * @throws IOException w momencie gdy coś poszło nie tak z tworzeniem streamów
     */
    public NetClient(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());

        addMaintenanceListener(this);
    }

    /**
     * Pętla główna wątku.
     * Czeka na obiekty. Gdy coś przyjdzie jakaś Akcja powiadamia actionListenerów.
     * Gry przyjdzie Maintenence to powiadamia mainteneceListenerów.
     * Teoretycznie gdy coś pójdzie nie tak powinna ładnie pozamykać sockety i strumienie.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Serializable object = (Serializable) input.readObject();
                Logger.getLogger(NetClient.class.getName()).log(Level.INFO, "Dostałem obiekt: "+object.getClass().getName());
                if (object == null) {
                    break;
                }
                if (object instanceof AAction) {
                    notifyActionListeners((AAction) object);
                } else if (object instanceof IMaintenance) {
                    notifyMaintenanceListeners((IMaintenance) object);
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
                notifyActionListeners(new ServerDisconnectedAction());
            } catch (IOException ex) {
                Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Error closing socket.", ex);
            }
        }
    }

    /**
     * Wysyła akcję na serwer.
     * @param action akcja.
     */
    public void sendAction(AAction action) {
        try {
            sendObject(action);
        } catch (IOException ex) {
            Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Error sending ACTION!", ex);
        }
    }

    /**
     * Wysyła Maintenence na serwer.
     * @param maintenece do wysłania
     */
    public void sendMaintenece(IMaintenance maintenece) {
        try {
            sendObject(maintenece);
        } catch (IOException ex) {
            Logger.getLogger(NetClient.class.getName()).log(Level.SEVERE, "Error sending MAINTENECE!", ex);
        }
    }

    /**
     * Wysyła obiekt serializowalny na serwer.
     * Funkcja użyta żeby nie powielać kodu w sendMaintenance i sendAction.
     * @param object do wysłania
     * @throws IOException gdy coś pójdzie nie tak z wysłaniem
     */
    private void sendObject(Serializable object) throws IOException {
        output.writeObject(object);
        output.flush();
    }

    /**
     * Metoda z ActionListenera. Gdy NetClient dostanie jakąś akcję wysyła ją do serwera.
     * @param a akcja do wysłania
     */
    public void actionReceived(AAction a) {
        sendAction(a);
    }

    /**
     * Dodaje actionListenera który będzie powiadamiany gdy od serwera przyjdzie jakaś akcja.
     * @param listener
     */
    public void addActionListener(IActionListener listener) {
        listeners.add(listener);
    }

    /**
     * Metoda zaimplementowana z MainteneceListenera.
     * @param maintenance
     */
    public void maintenanceReceived(IMaintenance maintenance) {
        sendMaintenece(maintenance);
    }

    /**
     * Dodaje listenera powiadamianego o obiektach typu Maintenece.
     * @param listener
     */
    public void addMaintenanceListener(IMaintenaceListener listener) {
        maintenanceListeners.add(listener);
    }

    /**
     * Powiadamia wszystkich mainence listenerów o zainstniałym zdarzeniu.
     * @param maintenance
     */
    public void notifyMaintenanceListeners(IMaintenance maintenance) {
        for (IMaintenaceListener listener : maintenanceListeners) {
            listener.maintenanceReceived(maintenance);
        }
    }

    /**
     * Powiadamia wszystkich ActionListenerów.
     * @param a
     */
    private void notifyActionListeners(AAction a) {
        for (IActionListener listener : listeners) {
            listener.actionReceived(a);
        }
    }

    public int getId() {
        return GameConstants.SERVER;
    }

    /**
     * Zwraca czy klient jest zalogowany.
     * Domyślnie jest false. Dopiero po wysłaniu do serwera pakietu LoginMaintenece ten stan może ulec zmianie.
     * Ulega on zmianie po otrzymaniu odpowiedzi.
     * @return czy klient jest zalogowany
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Ustawia czy jest zalogowany
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
