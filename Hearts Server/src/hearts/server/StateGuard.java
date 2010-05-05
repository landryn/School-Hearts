/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.AChatAction;
import hearts.defs.actions.IActionListener;
import hearts.defs.actions.IActionNotifier;
import hearts.defs.protocol.IUserSocket;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.IServerStateGuard;
import hearts.state.DumbState;
import hearts.state.NewUserAtTableAction;
import java.util.ArrayList;

/**
 *
 * @author orbit
 */
public class StateGuard implements IServerStateGuard {

    IGameState chatState = new DumbState();
    IGameState gameState = null;
    IUserSocket[] users = new IUserSocket[4];
    int userCount = 0;

    public StateGuard() {        
    }

    public int addUser(IUserSocket socket) throws GameStateException {
        if (userCount == 4) {
            throw new GameStateException(null);
        }
        users[userCount] = socket;
        socket.setId(userCount);
        notifyAboutNewUser(userCount);
        ((IActionNotifier) socket).addActionListener(this);
        userCount++;
        return userCount - 1;
    }

    private void notifyAboutNewUser(int id) {
        AAction action = new NewUserAtTableAction(GameConstants.ALL_USERS, users[id].getName(), id);
        chatState.addAction(action);

        for (int i = 0; i < userCount; i++) {
            if (i != id) {
                chatState.addAction(new NewUserAtTableAction(id, users[i].getName(), users[i].getId()));
            }
        }
        sendQueue(chatState);
    }

    private void sendQueue(IGameState state) {
        AAction action = null;
        while ((action = state.nextAction()) != null) {
            if (action.getReceiver() == GameConstants.ALL_USERS) {
                for (int i = 0; i < userCount; i++) {
                    users[i].actionReceived(action);
                }
            } else {
                users[action.getReceiver()].actionReceived(action);
            }
        }
    }

    public IGameState getState() {
        return gameState;
    }

    public void actionReceived(AAction a) {
        if(a instanceof AChatAction) {
            chatState.addAction(a);
        }
        sendQueue(chatState);
        //sendQueue(gameState);
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
