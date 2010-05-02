/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

/**
 *
 * @author orbit
 */
public interface IMaintenanceNotifier {
    void addMaintenanceListener(IMaintenaceListener listener);
    void notifyMaintenanceListeners(IMaintenance maintenance);
}
