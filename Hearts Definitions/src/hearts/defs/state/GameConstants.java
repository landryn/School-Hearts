
package hearts.defs.state;

/**
 * Różne stałe gry, na razie tutaj, chociaż może inne miejce będzie lepsze dla
 * nich?
 * @author szymon
 */
public class GameConstants {
    /**
     * Stała używana w Action, jeśli chcemy nadać coś do wszystkich
     * użytkowników
     */
    public static final int ALL_USERS = -1;

    /**
     * Stała mówiąca, że nadawcą albo odbiorcą jest serwer.
     * np. akcje usera podczas gry powinny być skierowane do serwera.
     */
    public static final int SERVER = -2;


    /**
     * Wtedy, gdy wiadomość jest wysyłana poza grą, tzn. w Lobby czy przy
     * logowaniu
     */
    public static final int NOT_IMPORTANT = -3;


    /**
     * Wtedy gdy nie ma jeszcze żadnej karty w lewie;
     */

    public static final int NO_CARD_IN_TRIP=-1;

}
