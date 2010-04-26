/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

/**
 * Lewa czy wziątka, jeden kij, przechowuje 4 karty w tablicy, gdzie indeksem
 * jest id usera dokłającego kartę.
 * @author szymon
 */
public interface Trick {
    /**
     * Dodaje kartę zaznaczając, który user ją dodał
     * @param c
     * @param userId
     * @throws TrickException
     */
    public void addCard(Card c, int userId) throws TrickException;


    /**
     * Zwraca tablicę o wielkości 4 kart w lewej.
     * Indeks tablicy określa id usera
     * @return
     */
    public Card[] getCards();

    /**
     * Czy ta wziątka jest jedną z dwóch ostatnich (punktowanych podwójnie)
     * @return
     */
    public boolean isLast();

}
