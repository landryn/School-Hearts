package hearts.defs.actions;

/**
 * Wszystkie obiekty chcące przechwytywać akcje muszą implementować ten
 * interfejs.
 * @author szymon
 */
public interface IActionListener {
    /**
     * Otrzymano akcję a
     * @param a
     */
    public void actionReceived(AAction a);
}
