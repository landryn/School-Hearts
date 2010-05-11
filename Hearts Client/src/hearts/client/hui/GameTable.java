/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * gameTable.java
 *
 * Created on 2010-05-06, 19:06:12
 */

package hearts.client.hui;

import hearts.client.hui.details.CardIcon;
import hearts.client.hui.details.CardPlaceHolder;
import hearts.defs.state.CardColor;
import hearts.defs.state.ICard;
import hearts.defs.state.IGUIGameTable;
import hearts.defs.state.IGUIPanel.Panel;
import hearts.defs.state.ITrick;
import hearts.state.Card;
import hearts.state.exceptions.WrongCardValueException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author szymon
 */
public class GameTable extends javax.swing.JPanel implements IGUIGameTable {

    private CardPlaceHolder[] placeHolders = new CardPlaceHolder[13];
    /** Creates new form gameTable */
    public GameTable() {
        initComponents();
        for(int i = 0; i < placeHolders.length; ++i ) {
            CardPlaceHolder tmp = new CardPlaceHolder();
            placeHolders[i] = tmp;
            cardsPanel.add(tmp);
        }
        // dodanie jednego placeholdera na koniec:
        cardsPanel.add(new CardPlaceHolder());

        uglyTest();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        opponentCardsStack2 = new hearts.client.hui.details.OpponentCardsStack();
        opponentCardsStack1 = new hearts.client.hui.details.OpponentCardsStack();
        opponentCardsStack3 = new hearts.client.hui.details.OpponentCardsStack();
        cardsPanel = new javax.swing.JPanel();
        trick = new hearts.client.hui.details.GUITrick();
        opponentLabel2 = new javax.swing.JLabel();
        opponentLabel3 = new javax.swing.JLabel();
        opponentLabel1 = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        chatInput = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        opponentCardsStack2.setVertical(false);

        javax.swing.GroupLayout opponentCardsStack2Layout = new javax.swing.GroupLayout(opponentCardsStack2);
        opponentCardsStack2.setLayout(opponentCardsStack2Layout);
        opponentCardsStack2Layout.setHorizontalGroup(
            opponentCardsStack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
        opponentCardsStack2Layout.setVerticalGroup(
            opponentCardsStack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(opponentCardsStack2, gridBagConstraints);

        opponentCardsStack1.setInverted(true);

        javax.swing.GroupLayout opponentCardsStack1Layout = new javax.swing.GroupLayout(opponentCardsStack1);
        opponentCardsStack1.setLayout(opponentCardsStack1Layout);
        opponentCardsStack1Layout.setHorizontalGroup(
            opponentCardsStack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        opponentCardsStack1Layout.setVerticalGroup(
            opponentCardsStack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(opponentCardsStack1, gridBagConstraints);

        javax.swing.GroupLayout opponentCardsStack3Layout = new javax.swing.GroupLayout(opponentCardsStack3);
        opponentCardsStack3.setLayout(opponentCardsStack3Layout);
        opponentCardsStack3Layout.setHorizontalGroup(
            opponentCardsStack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );
        opponentCardsStack3Layout.setVerticalGroup(
            opponentCardsStack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(opponentCardsStack3, gridBagConstraints);

        cardsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Karty do wyłożenia", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.BELOW_TOP));
        cardsPanel.setLayout(new java.awt.GridLayout(2, 7, 3, 3));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(cardsPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(trick, gridBagConstraints);

        opponentLabel2.setText("przeciwnik2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 10, 2);
        add(opponentLabel2, gridBagConstraints);

        opponentLabel3.setText("przeciwnik3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 2);
        add(opponentLabel3, gridBagConstraints);

        opponentLabel1.setText("przeciwnik1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 10);
        add(opponentLabel1, gridBagConstraints);

        userLabel.setText("user");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 2);
        add(userLabel, gridBagConstraints);

        chatArea.setColumns(10);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jScrollPane1, gridBagConstraints);

        chatInput.setMinimumSize(new java.awt.Dimension(140, 29));
        chatInput.setPreferredSize(new java.awt.Dimension(120, 29));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(chatInput, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardsPanel;
    private javax.swing.JTextArea chatArea;
    private javax.swing.JTextField chatInput;
    private javax.swing.JScrollPane jScrollPane1;
    private hearts.client.hui.details.OpponentCardsStack opponentCardsStack1;
    private hearts.client.hui.details.OpponentCardsStack opponentCardsStack2;
    private hearts.client.hui.details.OpponentCardsStack opponentCardsStack3;
    private javax.swing.JLabel opponentLabel1;
    private javax.swing.JLabel opponentLabel2;
    private javax.swing.JLabel opponentLabel3;
    private hearts.client.hui.details.GUITrick trick;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables

    public Panel getPanelType() {
        return Panel.GAME;
    }

    public void setCards(ICard[] cards) {
        int i = 0;

        // ustawianie kart
        for(; i < cards.length && i < placeHolders.length; ++i) {
            placeHolders[i].setCardIcon(new CardIcon(cards[i]));
        }
        // kasowanie ew. pozostałych kart
        for(; i < placeHolders.length; ++i) {
            placeHolders[i].setCardIcon(null);
        }
    }

    public void setFlipped(boolean flipped) {
        for(CardPlaceHolder placeHolder : placeHolders) {
            placeHolder.setFlipped(flipped);
        }
    }

    private void uglyTest() {
        ICard[] cards = new ICard[13];
        for(int i = 0; i < 13; ++i) {
            try {
                cards[i] = new Card(CardColor.HEART, i + 2);
            } catch (WrongCardValueException ex) {
                Logger.getLogger(GameTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setCards(cards);
        this.setFlipped(false);
    }

    public ITrick getTrick() {
        return this.trick;
    }

    public void clearTrick() {
        this.trick.clear();
    }

}
