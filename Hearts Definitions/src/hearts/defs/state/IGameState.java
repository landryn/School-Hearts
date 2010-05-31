/**
 * ASDF
 */
package hearts.defs.state;

import hearts.defs.actions.AAction;
import java.io.Serializable;

/**
 * Lista obiektów akcji do rozesłania obsługiwana jest przez addAction()
 * i nextAction()
 * @author szymon
 */
public interface IGameState extends Serializable, Cloneable {

    /**
     * Implementacja powinna zwracać klon stanu gry z pustą kolejką akcji
     * pierwszą instrukcją powinno być chyba Object o = super.clone();
     * @return
     */
    public IGameState clone();

    /**
     * Pobiera stan użytkownika
     * @param id
     * @return
     */
    public IUserState getUserState(int id);

    /**
     * Ustawia cały stan usera za jednym zamachem. Powinno być użyte tylko na
     * początku rozgrywki.
     * @param id
     * @param state
     */
    public void setUserState(int id, IUserState state)
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
     * Ustawia rozdającego;
     * @param dealer
     */
    public void setDealer(int dealer);

    /**
     *Wyznacza nastepnego rozdającego.
     * @return
     */
    public int nextDealer();

    /**
     * Zwraca aktualnego rozdającego.
     * @return
     */
    public int getDealer();

    /**
     * Ustawia aktywnego usera.
     * @param user
     */
    public void setActiveUser(int user);

    /**
     * Wyliczenie określające tryb gry.
     */
    public enum Mode {

        /**
         * czekanie na użytkowników
         */
        WAITING_FOR_PLAYERS,
        /**
         * kiedy już użytkownicy się zbiorą,
         * wszyscy muszą klinknąć "start"
         */
        WAITING_FOR_ACCEPTATIONS,
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
     * Przejdź do następnego etapu gry.
     * @return nowy tryb
     */
    public Mode nextMode() throws GameStateException;

    /**
     * Fukcja usuwa wszystkie rozgrywki.
     */
    public void clearMode();

    /**
     * Fukcja dodająca typ rozgrywki do kolejki. Na początku trzeba dodać odpowiednią liczbę rozbójników i odgrywek.
     *
     * @param mode
     */
    public void addMode(Mode mode);

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
    public ITrick getTrick();

    /**
     * Ustawia aktualną wziątkę na nowy obiekt typu trick.
     */
    public void clearTrick(boolean last);

    /**
     * Jeśli akcja wywołana na stanie gry owocuje rozesłaniem akcji
     * w inne miejsca, to używa tej metody by dodać akcje do rozesłania
     * do kolejki
     */
    public void addAction(AAction a);

    /**
     * Zdejmuje z kolejki akcji pierwszą akcję do wysłania.
     * Docelowo do używania w implementacji protokołu.
     * @return akcja albo null, jeśli lista pusta.
     */
    public AAction nextAction();

    /**
     * Dodaje urzytkownika do stanu gry.
     * @param user
     * @return zwraca stan zorgrywki.
     */
    public Mode addUser(IUserState user);

    /**
     * Zwraca czy lew została zakończona
     * @return
     */
    public boolean trickEnds();

    /**
     * Sprawdza czy rozdanie zostało zakończone
     * @return
     */
    public boolean dealEnds();

    /**
     * Zwraca ile, było lew
     * @return
     */
    public int getNumTrick();

    /**
     * Ustawiam numer lewy.
     * @param i
     */
    public void setNumTrick(int i);

    /**
     * Dodaje który będzie, rozpoczynał dane rozdanie.
     * @param user
     */
    public void addCommence(int user);

    /**
     * Zwraca gracza który bedzie rozpoczynał rozdanie. Usuwając go z listy.
     * @return
     */
    public int removeCommence();

    /**
     * Zwraca gracza który ma prawo rozpocząć rozdanie
     * @return
     */
    public int getCommence();

    /**
     * Ustania aukcje.
     * @param auction
     */
    public void setAuction(AAuction auction);

    /**
     * Zwraca aukcje.
     * @return
     */
    public AAuction getAuction();
}

