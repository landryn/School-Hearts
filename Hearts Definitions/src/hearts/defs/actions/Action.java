package hearts.defs.actions;

import hearts.defs.state.GameState;
import hearts.defs.state.GameStateException;
import java.io.Serializable;

/**
 * Klasa-matka wszystkich akcji, implementujemy performOnCopy
 * a używająć w Judge czy StateGuardzie perform(), który wywołuje
 * performOnCopy() z kopią stanu gry zamiast oryginałem.
 * @author szymon
 */
public abstract class Action implements Serializable, Cloneable {

    /**
     * Pole musi być ustawione podczas tworzenia obiektu
     */
    protected int receiver;
    /**
     * To pole musi być ustawione podczas odbierania obiektu z socketu
     * na bazie przynależności tego socketu.
     * Jest transient więc nie będzie serializowane.
     */
    protected transient int sender;

    /**
     * Konstruktor wymuszający ustawienie adresata
     * @param receiver
     */
    public Action(int receiver) {
        this.receiver = receiver;
    }

    /**
     * Metoda używana wywoływana przez użytkownika klasy.
     * Wywołuje performOnCopy() z klonem stanu gry.
     * @param old stary stan gry
     * @return nowy stan gry po przeleceniu
     */
    public final GameState perform(GameState old)
            throws GameStateException {
        return performOnCopy(old.clone());
    }

    /**
     * Metoda do zaimplementowania logiki akcji, dostaje kopię stanu gry
     * czyli bezpieczenie moze robić z nim co chce,
     * @param copy
     * @return zmodyfikowana kopia stanu gry przez tę akcję
     * @throws GameStateException
     */
    protected abstract GameState performOnCopy(GameState copy)
            throws GameStateException;

    /**
     * Pobiera nadawcę.
     * @return
     */
    public final int getReceiver() {
        return receiver;
    }

    /**
     * Ustawia pole nadawcy, socket odbierający musi ją ustawić!
     * @param sender
     */
    public void setSender(int sender) {
        this.sender = sender;
    }

    /**
     * Zwraca nadawcę akcji
     * @return nadawca akcji, 0...3 lub GameConstants.SERVER
     */
    public int getSender() {
        return sender;
    }
}
