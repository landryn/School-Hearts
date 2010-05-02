/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.actions.IActionNotifier;
import hearts.defs.protocol.IServerSocket;
import hearts.defs.state.GameConstants;
import hearts.maintenance.CreateAccountMaintenance;
import hearts.defs.protocol.IMaintenaceListener;
import hearts.defs.protocol.IMaintenance;
import hearts.maintenance.LoginMaintenance;
import hearts.maintenance.answers.CreateAccountAnswer;
import hearts.maintenance.answers.LoginAnswer;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Głowna klasa serwera.
 * Przechowuję listę połączonych klientów.
 * Nasłuchuje w oczekiwaniu na połączenia.
 * @author Michał Charmas
 */
public class Server
        implements IMaintenaceListener,
        IActionListener, IActionNotifier,
        Runnable {

    private int port;
    private String host;
    private java.net.ServerSocket socket = null;
    private ArrayList<ServerClient> clientsList = null;
    private ArrayList<IActionListener> listeners = null;

    private UserAuthenticator authenticator = new UserAuthenticator();

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
                sc.addActionListener(this);
                sc.addMaintenanceListener(this);
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

    /**
     * Według przykazania zwraca GameConstants.SERVER
     * @return GameConstants.SERVER
     */
    public int getId() {
        return GameConstants.SERVER;
    }

    public void addActionListener(IActionListener listener) {
        listeners.add(listener);
    }

    /**
     * Powiadamia wszystkich actionsListenerów o zdarzeniu.
     * @param action o której powiadamia
     */
    public void notifyListeners(AAction action) {
        for(IActionListener listener: listeners) {
            listener.actionReceived(action);
        }
    }

    public void actionReceived(AAction a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void maintenanceReceived(IMaintenance maintenance) {

        // LOGOWANIE ####################################################
        // TODO: dodać sprawdzanie czy przypadkiem ktoś nie jest już zalogowany
        if(maintenance instanceof LoginMaintenance) {
            ServerClient sc = (ServerClient) maintenance.getUserSocket();
            LoginMaintenance m = (LoginMaintenance) maintenance;
            if(sc.isLoggedIn()) {
                sc.actionReceived(new LoginAnswer(true, ""));
            } else if(authenticator.checkUser(m.getLogin(), m.getPassword())) {
                sc.setName(m.getLogin());
                sc.setLoggedIn(true);
                sc.actionReceived(new LoginAnswer(true, ""));
            } else {
                sc.actionReceived(new LoginAnswer(false, "Bad username or password."));
            }
        }

        // ZAKŁADANIE KONTA ##############################################
        if(maintenance instanceof CreateAccountMaintenance) {
            ServerClient sc = (ServerClient) maintenance.getUserSocket();
            CreateAccountMaintenance m = (CreateAccountMaintenance) maintenance;
            if(m.getLogin() != null && m.getPassword()!=null) {
                if(authenticator.addUser(m.getLogin(), m.getPassword())) {
                    sc.actionReceived(new CreateAccountAnswer(true, null));
                    Logger.getLogger(Server.class.getName()).log(Level.INFO, "Konto zostało założone: " + m.getLogin());
                } else {
                    sc.actionReceived(new CreateAccountAnswer(false, "User already exists."));
                    Logger.getLogger(Server.class.getName()).log(Level.INFO, "Błąd zakładania konta! Użytkownik już istniał: " + m.getLogin());
                }
            }
        }

        // ZAKOŃCZENIE WĄTKU KLIENTA ######################################
        if(maintenance instanceof ClientDisconnectedMaintenance) {            
            this.clientsList.remove((ServerClient)(maintenance.getUserSocket()));
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Klient został rozłączony i jest usuwany z listy.\n" +
                    "Ilość podłączonych klientow: " + String.valueOf(clientsList.size()));
        }
    }
}
