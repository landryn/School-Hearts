/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import hearts.defs.state.IGameState.Mode;
import java.util.ArrayList;

/**
 * Struktura do której judge będzie zapisywał, informacje o punktach zdobytych przez graczy
 * w konkretnym rozdaniu.
 * @author Paweł Trynkiewicz
 */
public class  SFinalPoints {
    /**
     * Punkty zdobyte przez graczy w rozdaniu.
     */
    public int []points=new int[4];
    /**
     * Lista rozgrywek jak maja zostac dodane
     */
    public ArrayList<Mode> mode=new ArrayList<Mode>();
    /**
     * Lista graczy kto ma wychodzić w rozdaniu.
     */
    public ArrayList<Integer> user=new ArrayList<Integer>();

    public void addMode(Mode mode,int user){
        this.mode.add(mode);
        this.user.add(user);
    }


}
