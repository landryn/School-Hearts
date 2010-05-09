
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
        if(quotation> this.quotation) this.lider=user;
        else {
            int index=this.bidders.indexOf(user);
            //nastepny gracz
            activeUser=this.bidders.get((index+1)%this.bidders.size());
            //usuwam gracza z licytacji
            if(!this.bidders.remove((Integer)index)){
                end=true;
            }
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
            stateClone.bidders=new ArrayList<Integer>();
            for(int i=0;i<this.bidders.size();i++){
              stateClone.bidders.add(this.bidders.get(i));
            }

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
