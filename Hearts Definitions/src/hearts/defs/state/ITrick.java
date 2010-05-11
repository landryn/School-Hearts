package hearts.defs.state;

/**
 * Lewa czy wziątka, jeden kij, przechowuje 4 karty w tablicy, gdzie indeksem
 * jest id usera dokłającego kartę.
 * @author szymon
 */
public interface ITrick {
    /**
     * Dodaje kartę zaznaczając, który user ją dodał
     * @param c
     * @param userId
     * @throws TrickException
     */
    public void addCard(ICard c, int userId) throws TrickException;


    /**
     * Zwraca tablicę o wielkości 4 kart w lewej.
     * Indeks tablicy określa id usera
     * @return
     */
    public ICard[] getCards();

    /**
     * Czy ta wziątka jest jedną z dwóch ostatnich (punktowanych podwójnie)
     * @return
     */
    public boolean isLast();
    

    /**
     * Robi głęboki klon obiektu. Musi sklonować tablicę obiektów.
     * @return
     */
    public ITrick clone();
    /**
     * Zwraca gracza który wyłorzył pierwszy kartę w lewie. Albo GameConstants.NO_CARD_IN_TRIP
     * @return
     */
    public int getFirst();
    /**
     * Ustawia gracza który pierwszy wyszedł w lewie.
     * @param user
     */
    public void setFirst(int user);

    /**
     * Lewa zakończona. Czyli wszyscy dorzucili kartę.
     * @return
     */
    public boolean ends();
    
}
