package hearts.defs.protocol;

import hearts.defs.actions.IActionListener;
import hearts.defs.actions.IActionNotifier;

/**
 * <p>Socket odbierający i wysyłający obiekty do/z przypisanego połączenia.
 * Odebranym pakietom nadaje pole sender na podstawie wartości z getId()</p>
 *
 * <p>
 * dowolność implementacji: enkapsulacja socketu albo dziedziczenie
 * chociaż lepiej enkapsulacja, bo dziedziczyć powinien po Thread
 * </p>
 * @author szymon
 */
public interface IObjectSocket extends IActionNotifier, IActionListener, Runnable {
    /**
     * Pobiera id
     * @return id, obiektu, GameConstants.SERVER w przypadku implementacji
     * po stronie klienta
     */
    public int getId();
}
