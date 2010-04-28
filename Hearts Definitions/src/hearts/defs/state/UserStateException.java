/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import hearts.defs.state.*;

/**
 * Jeśli coś złego miałoby się stać ze stanem usera.
 * @author szymon
 */
public class UserStateException extends GameStateException {

    public UserStateException(String message) {
        super(message);
    }

}
