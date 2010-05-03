/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import hearts.defs.state.IGameState.Mode;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Klasa do której judge będzie zapisywał, informacje o punktach zdobytych przez graczy
 * w konkretnym rozdaniu.
 * @author Paweł Trynkiewicz
 */
public class  SFinalPoints {
    //punkty zdobyte przez gracza w rozdaniu
    public int []points=new int[4];
    // tablica 
    public ArrayList<Mode> mode=new ArrayList<Mode>();

}
