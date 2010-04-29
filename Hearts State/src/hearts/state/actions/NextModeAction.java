/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.ICard;
import hearts.defs.state.IGameState;
import hearts.state.GameState;
import hearts.state.Judge;

/**
 *
 * @author pawel
 */
public class NextModeAction extends AAction {

    private ICard [][]cards=new ICard[4][];
    

    public NextModeAction(int receiver) {
        super(receiver);
    }    

    public void setICard(int number, ICard[] cards){
        this.cards[number]=cards;
    }

    public ICard[] getCards(int number){
        return this.cards[number];
    }
     

    @Override
    public IGameState perform(IGameState old) throws GameStateException {
        /**
         * 1. zliczam punktu
         * 2. czyszcze Trick
         * 3. Dodaje karty
         * 4. Zmieniam mode
         * 5. Zeruje licznik Trick
         */
        GameState game= (GameState) old.clone();

        int point;
        for(int i=0;i<4;i++){
            point=Judge.getPoints(game.getMode(), game.getUserState(i));
            game.getUserState(i).addPoints(point);
            game.getUserState(i).clearTricks();
             for(int k=0;k<13;k++){
                  game.getUserState(i).addCard(cards[i][k]);
             }//dodałem graczowi karty
            

        }
        game.nextMode();

        game.setNumTrick(0);


        return game;
    }

}
