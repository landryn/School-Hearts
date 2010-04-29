/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.judge;

import hearts.defs.state.ICard;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import hearts.defs.state.UserStateException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *Klasa przechowyjąca informacje o graczu i jego kartach.
 * @author Paweł Trynkiewicz
 */
class User implements  IUserState,Cloneable, Serializable {
    private int id;
    private Vector <ICard> cardList=new Vector();
    private String name;
    private List<ITrick> listTriks=new ArrayList<ITrick>();
    private int points;
    
    ArrayList<Integer> pointList=new ArrayList<Integer>();


    @Override
    public IUserState clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addCard(ICard c) {
        cardList.add(c);
    }

    public void withdrawCard(ICard c) throws UserStateException {
       cardList.remove(c);
    }

    public String getName() {
       return new String(this.name);
    }

    public void addTrick(ITrick trick) {
        listTriks.add(trick);
    }

    public List<ITrick> getTricks() {
       return new ArrayList<ITrick>(this.listTriks);
    }

    public void clearTricks() {
        this.listTriks=new ArrayList<ITrick>();
    }

    public List<Integer> getPointsList() {
        return new ArrayList<Integer>(this.pointList);
    }

    public void addPoints(int points) {
        this.points+=points;
        this.pointList.add(id);
    }

    public int getId() {
        return id;
    }

}
