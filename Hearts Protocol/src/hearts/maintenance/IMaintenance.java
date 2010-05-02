/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

import hearts.defs.protocol.IUserSocket;
import java.io.Serializable;

/**
 * Interfejs do zadań związanych z logowaniem, rejestracją, tworzeniem stołu
 * i innych zadań specjalnych tego typu. 
 */
public interface IMaintenance extends Serializable {
    public void setUserSocket(IUserSocket userSocket);
    public IUserSocket getUserSocket();
}
