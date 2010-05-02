package hearts.client;

import hearts.maintenance.CreateAccountMaintenance;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Thread th = new Thread(nc);
            th.start();
            nc.sendMaintenece(new CreateAccountMaintenance("dupa123", "jasio"));
            //nc.sendMaintenece(new LoginMaintenance("dupa", "jasio"));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
