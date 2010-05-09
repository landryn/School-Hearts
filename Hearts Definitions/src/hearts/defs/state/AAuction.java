/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna przechowywanie  stanu aukcji.
 * @author pawel
 */
public abstract class  AAuction implements Serializable, Cloneable {
    /*
     * Gracze biorący udział w licytacji.
     */
   protected ArrayList<Integer> bidders=new ArrayList();
    /**
     * Sprzedający, albo jak to woli wychodzący.
     */


    protected int commence;
    /**
     * Stawka
     */
    protected int quotation=0;
    /**
     * Gracz który zaoferował najwięcej w licytacji.
     */
    protected int lider;
    /**
     * Konstruktor wymuszający przypisanie wychodzącego gracza.
     * @param Commence
     */
    public AAuction(int commence) {
        this.commence = commence;
        for(int i=1;i<=3;i++){
            bidders.add((commence+i)%4);
        }

        lider=(commence+1)%4;
    }

    public abstract  void addOffer(int user,int quotation);
    /**
     * Zwraca gracza który w danym momecie zaoferaował najwięcej.
     * @return
     */
    public int getLider() {
        return lider;
    }
    /**
     * Zwraca aktulana stawkę w licytacji.
     * @return
     */
    public int getQuotation() {
        return quotation;
    }
    /**
     * Zwraca wycodzącego gracza.
     * @return
     */
    public int getCommence() {
        return commence;
    }
    /**
     * Zwraca następnego gracza który ma prawo licytować.
     * @return
     */
   public abstract int getActivetUser();

   /**
    * Zwraca status licytacji.
    * @return
    */
   public abstract boolean isEnd();


    @Override
    public  AAuction clone() throws CloneNotSupportedException{
        return (AAuction) super.clone();
    }


}
