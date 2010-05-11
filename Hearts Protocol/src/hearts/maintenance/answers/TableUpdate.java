/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance.answers;

import hearts.defs.state.GUIStateException;
import hearts.defs.state.IGUIState;
import hearts.defs.state.IGameState;

/**
 *
 * @author orbit
 */
public class TableUpdate extends AMaintenaceAction{

    private String tableName;
    private String owner;
    private String []players = null;
    private IGameState.Mode gameMode = IGameState.Mode.WAITING_FOR_PLAYERS;
    private Boolean removed = Boolean.FALSE;

    public TableUpdate(String tableName, String owner) {
        this.tableName = tableName;
        this.owner = owner;
    }

    public void setPlayer(int place, String name) {
        if(players == null) {
            players = new String[4];
        }
        players[place] = name;
    }

    @Override
    public void perform(IGUIState gui) throws GUIStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
