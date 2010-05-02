/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.server;

import hearts.maintenance.*;
import hearts.defs.protocol.IUserSocket;

/**
 * Obiekt tej klasy służy do powiadamiania serwera o zamknięciu połączenia.
 * Jest to po to, żeby serwer wywalił klienta z listy podłączonych klientów.
 * @author Michał Charmas
 */
public class ClientDisconnectedMaintenance implements IMaintenance{

    private IUserSocket socket;

    public ClientDisconnectedMaintenance(IUserSocket socket) {
        this.socket = socket;
    }
    
    public void setUserSocket(IUserSocket userSocket) {
        this.socket = userSocket;
    }

    public IUserSocket getUserSocket() {
        return this.socket;
    }

}
