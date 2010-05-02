/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

/**
 * 
 * @author orbit
 */
public interface IMaintenaceListener {
    /**
     * Metoda wywoływana w momencie otrzymania Maintanence.
     * @param maintenance
     */
    void maintenanceReceived(IMaintenance maintenance);
}
