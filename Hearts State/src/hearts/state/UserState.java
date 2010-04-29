/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state;

import hearts.defs.state.ICard;
import hearts.defs.state.ITrick;
import hearts.defs.state.IUserState;
import hearts.defs.state.UserStateException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Klasa przechowyjąca informacje o graczu i jego kartach.
 * @author Paweł Trynkiewicz
 */
class UserState implements  IUserState,Cloneable, Serializable {
    private int id;
    private Vector <ICard> cardList=new Vector();
    private String name;
    private List<ITrick> listTriks=new ArrayList<ITrick>();
    private int points;
    
    ArrayList<Integer> pointList=new ArrayList<Integer>();

    /**
     * Tworzy nowego gracza, z nadając id w grze oraz imie.
     * @param id
     * @param name
     */
    public UserState(int id, String name) {
        this.id = id;
        this.name = name;
    }




    @Override
    public IUserState clone() {
        UserState state=null;
        try {
            state = (UserState) super.clone();
            for (int i = 0; i < cardList.size(); i++) {
                    state.addCard(cardList.get(i));

            }
            state.clearTricks();
            for (int i = 0; i < listTriks.size(); i++) {
                state.addTrick(listTriks.get(i));

            }
            state.setName(this.getName());

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UserState.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }
    public void setName(String name){
        this.name=name;

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

    public void removeAllCard() {
        this.cardList=new Vector();
    }

}
