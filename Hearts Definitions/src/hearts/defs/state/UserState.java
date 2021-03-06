/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.defs.state;

import java.io.Serializable;
import java.util.List;

/**
 * Stan usera
 * @author szymon
 */
public interface UserState extends Serializable, Cloneable {

    /**
     * Dodaje kartę do kart
     * @param c
     */
    public void addCard(Card c);

    /**
     * Wyciągnij kartę c z puli usera
     * @param c karta do wyciągnięcia
     * @throws UserStateException jeśli karty nie ma w puli
     */
    public void withdrawCard(Card c) throws UserStateException;

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
    public void addTrick(Trick trick);

    /**
     * Pobierz listę wziątek usera.
     * @return
     */
    public List<Trick> getTricks();

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
}
