package hearts.client;

import hearts.maintenance.LoginMaintenance;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import netclient.NetClient;

/**
 *
 * @author szymon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            NetClient nc = new NetClient("localhost", 9999);
            nc.start();
            nc.sendMaintenece(new LoginMaintenance("dupa", "jasio"));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
