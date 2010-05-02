/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.server;

import java.io.IOException;
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
            Server s = new Server(9999, "localhost");
            Thread th = new Thread(s);
            th.start();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }





}
