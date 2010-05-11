package hearts.defs.actions;

import hearts.defs.state.IGameState;
import hearts.defs.state.GameStateException;
import java.io.Serializable;

/**
 * Klasa-matka wszystkich akcji, implementujemy performOnCopy
 * a używająć w Judge czy StateGuardzie perform(), który wywołuje
 * performOnCopy() z kopią stanu gry zamiast oryginałem.
 * @author szymon
 */
public abstract class AAction implements Serializable, Cloneable {

    /**
     * Pole musi być ustawione podczas tworzenia obiektu
     * 
     */
    protected int receiver;
    /**
     * To pole musi być ustawione podczas odbierania obiektu z socketu
     * na bazie przynależności tego socketu.
     * Jest transient więc nie będzie serializowane.
     *
     * wartości -1 autorem zdarzenia jest serwer, 0-3 któryś z graczy Paweł
     */
    protected transient int sender;

    /**
     * Konstruktor wymuszający ustawienie adresata
     * @param receiver
     */
    public AAction(int receiver) {
        this.receiver = receiver;
    }

    /**
     * Powinno być wywołane z klonem stanu gry!
     * Tu umieszczamy implementację
     * @param klon stanu gry
     * @return stan gry po wykonaniu akcji
     */
    public abstract IGameState perform(IGameState clone)
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
