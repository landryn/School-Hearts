/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna przechowywanie  stanu aukcji.
 * @author pawel
 */
abstract class  AAuction {
    /*
     * Gracze biorący udział w licytacji.
     */
   protected ArrayList<Integer> bidders=new ArrayList();
    /**
     * Sprzedający, albo jak to woli wychodzący.
     */


    protected int Commence;
    /**
     * Stawka
     */
    protected int quotation;
    /**
     * Aktualny lider
     */
    protected int lider;
    /**
     * Konstruktor wymuszający przypisanie wychodzącego gracza.
     * @param Commence
     */
    public AAuction(int Commence) {
        this.Commence = Commence;
        for(int i=1;i<=3;i++){
            bidders.add((Commence+1)%4);
        }
    }

    abstract  void addOffer(int user,int quotation);

    public int getLider() {
        return lider;
    }

    public int getQuotation() {
        return quotation;
    }

    public int getCommence() {
        return Commence;
    }

    abstract int getNextUser();





}
