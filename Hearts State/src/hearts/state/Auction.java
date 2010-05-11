
package hearts.state;

import hearts.defs.state.AAuction;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paweł Trynkiewicz
 */
public class Auction extends AAuction {

    private int activeUser=0;
    private boolean end=false;
    public Auction(int Commence) {
        super(Commence);
        activeUser=(Commence+1)%4;
    }

    

    @Override
    public void addOffer(int user, int quotation) {
        int indexs=bidders.indexOf(user);
        activeUser=bidders.get((indexs+1)%bidders.size());
       
        if(quotation> this.quotation) {
            this.quotation=quotation;
            this.lider=user;

        } else {

            int index=bidders.indexOf(user);
            bidders.remove(index);
        }
        if(this.bidders.size()==1||this.quotation==13) {
            activeUser=commence;
            end=true;
        } 
        
    }

    @Override
    public AAuction clone(){
        Auction stateClone = null;
        try {
            stateClone = (Auction) super.clone();
           

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AActionList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stateClone;
    }
   
    @Override
    public int getActivetUser() {
       return activeUser;
    }

    @Override
    public boolean isEnd() {
        return this.end;
    }






}
