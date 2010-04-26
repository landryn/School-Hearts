package hearts.defs.actions;

/**
 * Wszystkie obiekty chcące przechwytywać akcje muszą implementować ten
 * interfejs.
 * @author szymon
 */
public interface ActionListener {
    /**
     * Otrzymano akcję a
     * @param a
     */
    public void actionReceived(Action a);
}
