package hearts.defs.state;

import java.io.Serializable;
import java.util.List;

/**
 * Stan usera
 * @author szymon
 */
public interface IUserState extends Serializable, Cloneable {

    /**
     * 
     * @return głęboka kopia obiektu
     */
    public IUserState clone();

    /**
     * Dodaje kartę do kart
     * @param c
     */
    public void addCard(ICard c);

    /**
     * Wyciągnij kartę c z puli usera
     * @param c karta do wyciągnięcia
     * @throws UserStateException jeśli karty nie ma w puli
     */
    public void withdrawCard(ICard c) throws UserStateException;
    /**
     * Sprawdza czy user ma taką kartę w tali.
     * @param c
     * @return
     */
    public boolean haveThisCard(ICard c);

    /**
     * Pobierz nazwę usera przyporządkowanego temu obiektowi
     * @return nazwa usera
     */
    public String getName();

    /**
     * Pobierz Id usera (0...3)
     * @return
     */
    public int getId();

    /**
     * Dodaj userowi wziątkę do jego listy
     * @param trick
     */
    public void addTrick(ITrick trick);

    /**
     * Pobierz listę wziątek usera.
     * @return
     */
    public List<ITrick> getTricks();

    /**
     * Wyczyść wziątki, na koniec tury.
     */
    public void clearTricks();

    /**
     * Zwraca listę punktów użytkownika od początku gry
     * @return
     */
    public List<Integer> getPointsList();

    /**
     * Dodaj punkty graczowi
     * @param points
     */
    public void addPoints(int points);
    /**
     * czyści tablicę punktów gracza.
     */
    public void clearPoints();
    /**
     * Usuwa wszystkie karty gracza.
     */
    public void removeAllCard();
    /**
     * Sprawdza czy gracz ma kartę  w tym kolorze.
     * @param color
     * @return
     */
    public boolean userHaveCardInColor(CardColor color);
}
