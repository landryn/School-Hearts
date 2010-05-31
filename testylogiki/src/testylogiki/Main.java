/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testylogiki;

import com.sun.xml.internal.ws.api.pipe.NextAction;
import hearts.defs.actions.AAction;
import hearts.defs.state.CardColor;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState.Mode;
import hearts.state.Card;
import hearts.state.UserState;
import hearts.state.GameState;
import hearts.state.Judge;
import hearts.state.actions.AddCardToTrickAction;
import hearts.state.actions.AuctionBeginAction;
import hearts.state.actions.AuctionDecisionAction;
import hearts.state.actions.AuctionOfferAction;
import hearts.state.actions.ChooseTrumpAction;
import hearts.state.actions.FirstModeAction;
import hearts.state.actions.NextModeAction;
import hearts.state.actions.NextTripAction;
import hearts.state.exceptions.WrongCardValueException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pawel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws WrongCardValueException, GameStateException {
        GameState g = new GameState();
        System.out.println(g.getMode());
        Judge j = new Judge();
        g.addUser(new UserState(1, "Pawel"));
        g.addUser(new UserState(2, "Pioterk"));
        g.addUser(new UserState(3, "Miachał"));
        AddCardToTrickAction ac = null;
        System.out.println(g.addUser(new UserState(4, "Szymon")));

        FirstModeAction kkkkkk = new FirstModeAction(1);
        kkkkkk.setModes(4);

        try {
            g = (GameState) j.judge(g, kkkkkk);
        } catch (GameStateException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.clearMode();
        g.haks2(Mode.REAVER);
  
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Typ rozgrywki "+g.getMode());
        try {
            g.setAuction(false);
        } catch (GameStateException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        g.setTrump(CardColor.SPADE);
        

        for (int i = 2; i <= 14; i++)
        {
            AddCardToTrickAction dodajKarte = null;
            dodajKarte = new AddCardToTrickAction(1);

            Card karta = null;
            dodajKarte.setSender(1);

            karta = new Card(CardColor.DIAMOND, i);
            dodajKarte.setCard(karta);

            //wsk=new AuctionBeginAction(1);

            try {
                g = (GameState) j.judge(g, dodajKarte);
            } catch (GameStateException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }



            karta = new Card(CardColor.HEART, i);
            dodajKarte.setCard(karta);
            dodajKarte.setSender(2);
            try {
                g = (GameState) j.judge(g, dodajKarte);
            } catch (GameStateException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            karta = new Card(CardColor.SPADE, i);
            dodajKarte.setCard(karta);
            dodajKarte.setSender(3);



            try {
                g = (GameState) j.judge(g, dodajKarte);
            } catch (GameStateException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }


            karta = new Card(CardColor.CLUB, i);
            dodajKarte.setCard(karta);
            dodajKarte.setSender(0);
            try {
                g = (GameState) j.judge(g, dodajKarte);
            } catch (GameStateException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            AAction c = null;

            while (true) {
                c = g.nextAction();
                if (c instanceof NextTripAction) {
                    try {
                        g = (GameState) j.judge(g, c);
                    } catch (GameStateException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }



                if (c instanceof NextModeAction) {
                    try {
                        g = (GameState) j.judge(g, c);
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Nowe rozdanie");
                    } catch (GameStateException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }
            }

        }


        AAction c = null;

            while (true) {
                c = g.nextAction();
                if (c instanceof NextTripAction) {
                    try {
                        g = (GameState) j.judge(g, c);
                         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "kolejna wziątka");
                    } catch (GameStateException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }



                if (c instanceof NextModeAction) {
                    try {
                        g = (GameState) j.judge(g, c);
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Nowe rozdanie");
                    } catch (GameStateException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }
            }





       List<Integer> l= g.getUserState(1).getPointsList();
       Logger.getLogger(Main.class.getName()).log(Level.SEVERE,"Punkty "+g.getUserState(1).getPointsList().get(0));



        


        // System.out.println("Aktywny gracz: "+ g.getActiveUser());

        // System.out.println(g.getAuction().isEnd());

        int obrót = 0;

        System.out.println("Koniec rozdania");

    }
}
