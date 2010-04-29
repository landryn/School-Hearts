/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;

/**
 *Klasa implementująca logikę gry. Dostaje na wejsciu stan gry i zwraca jego zmodyfikowaną instancje.
 * @author Paweł Trynkiewicz
 */
public class Judge implements hearts.defs.judge.IJudge {

    

     /**
      * Cała logika gry tu będzie implementowa.
      * @param state stan gry
      * @param action akcja do wykonania
      * @return nowy stan gry
      * @throws GameStateException
      */
    public IGameState judge(IGameState state, AAction action) throws GameStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
