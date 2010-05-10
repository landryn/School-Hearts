/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna przechowywanie  stanu licytacji. Zbierna informacje o stanie licytacji,
 * przecowuje informacje o graczy wychodzącym, graczu który dał najwięcej i pozostałych biorących udział w licytacji.
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
/**
 * Dodaje ofertę gracza do puli. Jeśli gracz nic nie dorzuca, zakładam że pasuje i uswam go z kolejki licytujących.
 * @param user gracz
 * @param quotation stawka
 */
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

/**
 * Fukcja zwracająca klon obiektu.
 * @return
 * @throws CloneNotSupportedException
 */
    @Override
    public  AAuction clone() throws CloneNotSupportedException{
        AAuction ob=(AAuction) super.clone();
        ob.bidders=new ArrayList();
        for(int i=0;i<this.bidders.size();i++ ){
            ob.bidders.add(this.bidders.get(i));
        }
        return ob;
    }


}
