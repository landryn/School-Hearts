/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state.actions;

import hearts.defs.actions.AAction;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.state.GameState;
import hearts.state.actions.gui.NextTripAGUI;

/**
 * Zdarzenie rozpoczynającą klejnę lewe. Serwer po każdym pozytywnym zdarzeni powinien sprawdzić czy lewe się zakończyła
 * i wywołać netodę z Judge.judge z new NextTripAction(-1);
 * @author Paweł Trynkiewicz
 */
public class NextTripAction extends AAction {

    private int winer;
    private boolean last;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }



    public int getWiner() {
        return winer;
    }

    public void setWiner(int winer) {
        this.winer = winer;
    }
    
    public NextTripAction(int receiver) {
        super(receiver);
    }
    

    @Override
    public IGameState perform(IGameState old) throws GameStateException {
        System.out.println("NextTripAction public IGameState perform(IGameState old) throws GameStateException");
        GameState game= (GameState) old.clone();
        game.getUserState(winer).addTrick(game.getTrick());
        game.clearTrick(last);


      
       NextTripAGUI ac=null;
       for (int i = 0; i < 4; i++) {
                ac = new NextTripAGUI(i);
                ac.setWiner(winer);
                ac.setSender(this.getSender());
                game.addAction(ac);
            }
        game.setActiveUser(winer);
        return game;
    }



}
