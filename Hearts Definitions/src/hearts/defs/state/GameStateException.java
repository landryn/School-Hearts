package hearts.defs.state;

/**
 * Ogólna klasa informująca o wyjątku w grze. Wymyślenie dziedziczących wyjątków
 * zostawiam Łukaszowi i Pawłowi ;)
 * @author szymon
 */
public class GameStateException extends Exception {

    public GameStateException(String message) {
        super(message);
    }
    
}
