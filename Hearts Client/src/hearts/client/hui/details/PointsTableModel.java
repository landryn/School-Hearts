/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.client.hui.details;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author szymon
 */
public class PointsTableModel extends DefaultTableModel{

    public PointsTableModel() {
        super(new String[4], 0);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

}
