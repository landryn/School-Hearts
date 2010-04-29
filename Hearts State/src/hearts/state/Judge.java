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
     * Lista akcji do wykonania
     */
    protected AActionList actionTodo=null;

    public AActionList getActionTodo() {
        return actionTodo;
    }

    public void setActionTodo(AActionList actionTodo) {
        this.actionTodo = actionTodo;
    }

     /**
      * Cała logika gry tu będzie implementowa.
      * @param state stan gry
      * @param action akcja do wykonania
      * @return nowy stan gry
      * @throws GameStateException
      */

    public IGameState judge(IGameState state, AAction action) throws GameStateException {
       GameState copyState=(GameState) state.clone();
       


       return copyState;
    }

}
