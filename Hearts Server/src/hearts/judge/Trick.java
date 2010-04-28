/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.judge;

import hearts.defs.state.ICard;
import hearts.defs.state.ITrick;
import hearts.defs.state.TrickException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa trzymająca informacje o rozdaniu.
 * @author Paweł Trynkiewicz
 */
public class Trick implements ITrick , Cloneable, Serializable{
    private ICard []cardTab=new ICard[4];

    private boolean last=false;



    public void addCard(ICard c, int userId) throws TrickException {
        if(userId>3 ) {

            throw new  TrickException("Zły id usera",userId,cardTab);
           
        }
        cardTab[userId]=c;
       
    }

    public ICard[] getCards() {
        return cardTab;
    }

    public boolean isLast() {
        return last;
    }

    @Override
    public ITrick clone() {
         ITrick t=null;
        try {
           t=new Trick(this);
            
        } catch (TrickException ex) {
            Logger.getLogger(Trick.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
       
    }




    public Trick(Trick ob) throws TrickException{
        this.last=this.isLast();
        ICard[]t=ob.getCards();
        for (int i = 0; i < t.length; i++) {
            this.addCard(t[i], i);

        }
        
    }








}
