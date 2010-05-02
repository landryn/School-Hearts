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
 * @author Michał Charmas
 */
public interface IMaintenance extends Serializable {
    /**
     * Metoda służy do ustawienia referencji do samego siebie po stronie serwera.
     * Służy po to żeby łatwo możnabyło zidentyfikować od którego klienta przyszło rządanie.
     * @param userSocket
     */
    public void setUserSocket(IUserSocket userSocket);

    /**
     * Metoda zwracająca od kogo przyszło rządanie.
     * Używane to jest tylko po stronie serwera.
     * Po stronie klienta powinno zazwyczaj zwracać null.
     * @return od kogo przyszło rządanie.
     */
    public IUserSocket getUserSocket();
}
