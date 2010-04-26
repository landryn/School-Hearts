
package hearts.defs.state;

/**
 * Różne stałe gry, na razie tutaj, chociaż może inne miejce będzie lepsze dla
 * nich?
 * @author szymon
 */
public interface GameConstants {
    /**
     * Stała używana w Action, jeśli chcemy nadać coś do wszystkich
     * użytkowników
     */
    int ALL_USERS = -1;

    /**
     * Stała mówiąca, że nadawcą albo odbiorcą jest serwer.
     * np. akcje usera podczas gry powinny być skierowane do serwera.
     */
    int SERVER = -2;

}
