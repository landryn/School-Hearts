/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.protocol;

/**
 *Klasa powiadamiacza o zdarzeniach typu Maintanence.
 * @author Michał Charmas
 */
public interface IMaintenanceNotifier {
    /**
     * Dodaje listenera.
     * @param listener
     */
    void addMaintenanceListener(IMaintenaceListener listener);

    /**
     * Powiadamia wszystkich listenerów o zdarzeniu.
     * @param maintenance zdarzenie o którym trzeba powiadomić.
     */
    void notifyMaintenanceListeners(IMaintenance maintenance);
}
