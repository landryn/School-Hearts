
/**
 * ASDF
 */
package hearts.defs.state;

import hearts.defs.actions.Action;
import java.io.Serializable;

/**
 * Lista obiektów akcji do rozesłania obsługiwana jest przez addAction()
 * i nextAction()
 * @author szymon
 */
public interface GameState extends Serializable, Cloneable {

    /**
     * Implementacja powinna zwracać klon stanu gry z pustą kolejką akcji
     * pierwszą instrukcją powinno być chyba Object o = super.clone();
     * @return
     */
    public GameState clone();

    /**
     * Pobiera stan użytkownika
     * @param id
     * @return
     */
    public UserState getUserState(int id);

    /**
     * Ustawia cały stan usera za jednym zamachem. Powinno być użyte tylko na
     * początku rozgrywki.
     * @param id
     * @param state
     */
    public void setUserState(int id, UserState state)
            throws GameStateException;

    /**
     * Pobiera id następnego usera
     * @return
     */
    public int getActiveUser();

    /**
     * Zmienia na następne id usera
     * @return
     */
    public int nextUser();
    /**
     * Wyliczenie określające tryb gry.
     */
    public enum Mode {

        /**
         * czekanie na użytkowników
         */
        WAITING_FOR_PLAYERS,
        /**
         * zbój (punktowanie ujemne)
         */
        BANDIT,
        /**
         * odgrywka (punktowanie dodatnie)
         */
        WIN_BACK,
        /**
         * rozbójnik
         */
        REAVER,
        /**
         * gra zakończona
         */
        END
    }

    /**
     * Etap gry
     * @return
     */
    public Mode getMode();

    /**
     * Przejdź do następnego etapu gry
     * @return nowy tryb
     */
    public Mode nextMode() throws GameStateException;

    /**
     * Pobierz atu.
     * @return null jeśli tryb jest bezatutowy
     */
    public CardColor getTrump();

    /**
     * Ustaw atu.
     * @param c
     * @throws GameStateException jeśli ustawiamy w złym trybie gry
     */
    public void setTrump(CardColor c) throws GameStateException;

    /**
     * Czy odbywa się aukcja
     * @return
     */
    public boolean isAuction();

    /**
     * Ustaw tryb aukcji
     * @return
     * @throws GameStateException jeśli próbujemy to zrobić
     * w nieodpowienim momencie
     */
    public void setAuction(boolean auction) throws GameStateException;

    /**
     * aktualna wziątka leżąca na stole, wyciągamy karty od użytkowników
     * i wkładamy tutaj. Potem pobieramy wziątkę i wrzucamy userowi.
     * @return
     */
    public Trick getTrick();

    /**
     * Ustawia aktualną wziątkę na nowy obiekt typu trick.
     */
    public void clearTrick();

    /**
     * Jeśli akcja wywołana na stanie gry owocuje rozesłaniem akcji
     * w inne miejsca, to używa tej metody by dodać akcje do rozesłania
     * do kolejki
     */
    public void addAction(Action a);

    /**
     * Zdejmuje z kolejki akcji pierwszą akcję do wysłania.
     * Docelowo do używania w implementacji protokołu.
     * @return akcja albo null, jeśli lista pusta.
     */
    public Action nextAction();
}
