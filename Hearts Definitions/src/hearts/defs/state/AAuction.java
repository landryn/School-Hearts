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
    ArrayList<Integer> bidders=new ArrayList();
    /**
     * Sprzedający, albo jak to woli wychodzący.
     */
    int Commence;
    /**
     * Stawka
     */
    int quotation;
    /**
     * Aktualny lider
     */
    int lider;
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

    

}
