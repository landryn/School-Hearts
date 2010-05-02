/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance.answers;

/**
 * Tylko dziedziczy po LoginAnswer, typ obiektu mówi, że chodzi o
 * odpowiedź na tworzenie konta.
 * @author szymon
 */
public class CreateAccountAnswer extends LoginAnswer {

    public CreateAccountAnswer(boolean success, String message) {
        super(success, message);
    }

}
