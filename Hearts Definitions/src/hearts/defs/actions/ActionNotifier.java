package hearts.defs.actions;

/**
 * Obiekt implementujący ten interfejs jest zobowiązany do rozsyłania akcji
 * ActionListenerom i ustawiania akcjom odpowiedniego sendera.
 * @author szymon
 */
public interface ActionNotifier {
    public void addActionListener(ActionListener listener);
}
