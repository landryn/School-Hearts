/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.state;

import hearts.defs.state.CardColor;
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
public class UserState implements  IUserState,Cloneable, Serializable {
    private int id;
    private Vector <ICard> cardList=new Vector();
    private String name;
    private List<ITrick> listTriks=new ArrayList<ITrick>();
    private int points;

      //osoba której gracz ma oddać lewy
    protected int banker;
    //ilość lew do oddania
    protected int debet;

    
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
            state.removeAllCard();
            for (int i = 0; i < cardList.size(); i++) {
                    state.addCard(cardList.get(i));

            }
            state.clearTricks();
            for (int i = 0; i < listTriks.size(); i++) {
                state.addTrick(listTriks.get(i));

            }
            state.setName(this.getName());
            state.clearPoints();
            for(int i=0;i<pointList.size();i++){
                state.addPoints(pointList.get(i));
            }

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
        int number=0;
        while(number<cardList.size()){

            Card card=(Card) cardList.get(number);
            if(card.getColor()==c.getColor()&&card.getValue()==c.getValue()){
                Logger.getLogger(UserState.class.getName()).log(Level.INFO, "Szukałem karty:"+c.getColor()+" "+c.getValue()+" Znalazłem: " +card.getColor()+" "+card.getValue() );
                this.cardList.remove(number);
                return;
            }
            ++number;
        }
       throw new UserStateException("User do not have this card "+c.getColor()+" "+c.getValue());
    }

    public String getName() {
       return new String(this.name);
    }

    public void addTrick(ITrick trick) {
        listTriks.add(trick);
    }

    public List<ITrick> getTricks() {
       return this.listTriks;
    }

    public void clearTricks() {
        this.listTriks=new ArrayList<ITrick>();
        this.banker=id;
        this.debet=0;
    }

    public List<Integer> getPointsList() {
        return this.pointList;
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

    public boolean userHaveCardInColor(CardColor color) {
       boolean ok=false;

       for(int i=0;i<cardList.size();i++){
           if (cardList.get(i).getColor().equals(color)) {
               ok=true;
               break;
           }
       }

       return ok;
    }

    public void clearPoints() {
        this.pointList=new ArrayList<Integer>();
        points=0;
    }

    public boolean haveThisCard(ICard c) {

        int number=0;
        while(number<cardList.size()){

            Card card=(Card) cardList.get(number);
            if(card.getColor()==c.getColor()&&card.getValue()==c.getValue()){
                Logger.getLogger(UserState.class.getName()).log(Level.INFO, "Szukałem karty:"+c.getColor()+" "+c.getValue()+" Znalazłem: " +card.getColor()+" "+card.getValue() );
                break;
            }
            ++number;
        }

        if(number<cardList.size()) return true;
        else return false;
    }

    public void setDebet(int debet) {
        this.debet=debet;
    }

    public int getDebet() {
        return this.debet;
    }

    public void setBanker(int banker) {
        this.banker=banker;
    }

    public int getBanker() {
        return this.banker;
    }

    public boolean uHHigerCardIColor(ICard card) {

        for(int i=0;i<cardList.size();i++){
            if (cardList.get(i).getColor()==card.getColor() && cardList.get(i).getValue() > card.getValue())
                return true;
        }
        return false;
    }



}
