package hearts.defs.protocol;

/**
 * Implementacja powinna trzymać socket z klientem i powiadamiać
 * ActionListenerów o tym co przyszło na ten socket
 *
 * Jest także ActionListenerem, popychającym obiekty Action do klienta.
 * @author szymon
 */
public interface IUserSocket extends IObjectSocket {
    /**
     * Ustawia id (0...3) obowiązujące na stole, w którym gra user.
     *
     * Id ma znaczenie tylko dla sesji gry, przy przeglądaniu
     * dostępnych stołów
     * @param id
     */
    public void setId(int id);

    /**
     * Ustawia nazwę
     * @param name
     */
    public void setName(String name);
    public String getName();
}
