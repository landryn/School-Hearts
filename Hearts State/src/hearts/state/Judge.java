/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state;

import hearts.defs.actions.AAction;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.actions.AddCardToTrickAction;

/**
 *Klasa implementująca logikę gry. Dostaje na wejsciu stan gry i zwraca jego zmodyfikowaną instancje.
 * @author Paweł Trynkiewicz
 */
public class Judge implements hearts.defs.judge.IJudge {
    /**
     * Lista akcji do wykonania
     */
    protected AActionList actionToDo=null;

    public AActionList getActionTodo() {
        return actionToDo;
    }

    public void setActionTodo(AActionList actionTodo) {
        this.actionToDo = actionTodo;
    }

     /**
      * Cała logika gry tu będzie implementowa.
      * @param state stan gry
      * @param action akcja do wykonania
      * @return nowy stan gry
      * @throws GameStateException
      */

    public IGameState judge(IGameState state, AAction action) throws GameStateException {
       GameState copyState=null;
       /**
        * Sprawdzam jaki to typ akcji.
        */
       if(action instanceof AddCardToTrickAction){
            if(state.getActiveUser()!=action.getSender()|| state.isAuction()) throw new GameStateException("You can not put card in this moment");

            if(!state.getTrick().getCards()[0].getColor().equals(((AddCardToTrickAction)(action)).getCard().getColor())) {

                if(state.getUserState(action.getSender()).userHaveCardInColor(((AddCardToTrickAction)(action)).getCard().getColor()))
                    throw new GameStateException("You can not put card in this color, because you have card in correct color");
                
            }
            cherRuleBandit(state, action);
            //Tu powinny byc sprawdzone wszystkie warunki i waruneczki.
            AddCardToTrickAction ac=null;

            for(int i=0;i<4;i++){
                ac=new AddCardToTrickAction(i);
                ac.setCard(((AddCardToTrickAction)(action)).getCard());
                ac.setSender(action.getSender());
                this.actionToDo.addAction(ac);
            }


       }


       return copyState;
    }
    /** 
     * Sprawdza dodatkowe obwarowania, dotyczące zasad obowiązujących w danej rozgrywce.
     * @param state
     * @param action
     */
    private void cherRuleBandit(IGameState state, AAction action) throws GameStateException {
    
    }

   

}
