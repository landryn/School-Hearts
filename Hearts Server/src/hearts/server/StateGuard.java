/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.AChatAction;
import hearts.defs.actions.IActionNotifier;
import hearts.defs.judge.IJudge;
import hearts.defs.protocol.IUserSocket;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.IServerStateGuard;
import hearts.maintenance.answers.JoinTableAnswer;
import hearts.maintenance.answers.TableUpdate;
import hearts.state.DumbState;
import hearts.state.GameState;
import hearts.state.Judge;
import hearts.state.UserState;
import hearts.state.actions.FirstModeAction;
import hearts.state.exceptions.ExceptionGUIAction;

/**
 * Klasa implementująca StateGuarda
 * @author Michał Charmas
 */
public class StateGuard implements IServerStateGuard {

    IGameState chatState = new DumbState();
    IGameState gameState = new GameState();
    IJudge judge = new Judge();
    IUserSocket[] users = new IUserSocket[4];
    int userCount = 0;
    String name = "Dupa słonia.";

    Server server;

    public StateGuard(Server server) {
        this.server = server;
    }

    /**
     * Metoda dodaje użytkownika do stołu.
     * Ustawia siebie jako actionListenera na sockecie gracza.
     * Nadaje graczowi ID.
     * Wywołuje metodę powiadamiającą innych użytkowników o przybyciu kolejnego.
     *
     * @param socket użtywkonik
     * @return id użytkownika
     * @throws GameStateException w przypadku gdy liczba użytkowników jest równa 4
     */
    public int addUser(IUserSocket socket) throws GameStateException {
        if (userCount == 4) {
            throw new GameStateException(null);
        }
        users[userCount] = socket;
        socket.setId(userCount);
        socket.actionReceived(new JoinTableAnswer(name, Boolean.TRUE, userCount));
        notifyAboutTableChange();
        socket.addActionListener(this);
        userCount++;
        
        gameState.addUser(new UserState(socket.getId(), socket.getName()));
        if (userCount == 4) {
            gameState = judge.judge(gameState, new FirstModeAction(GameConstants.SERVER));
        }
        
        return userCount - 1;
    }

    /**
     * @param id
     */
    private void notifyAboutTableChange() {
        TableUpdate update = new TableUpdate(name, users[0].getName());
        for (int i = 0; i < users.length; i++) {
            IUserSocket iUserSocket = users[i];
            if(iUserSocket!=null) {
                update.setPlayer(i, iUserSocket.getName());            
            } else {
                update.setPlayer(i, null);
            }            
        }
        server.notifyListeners(update);
    }

    /**
     * Opróżnia daną kolejkę wysyłając wszystkie akcje na sockety użytkowników.
     * @param state kolejka do opróżnienia
     */
    private void sendQueue(IGameState state) {
        if (state == null) {
            return;
        }
        
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

    /**
     * Zwraca stan gry.
     * @return
     */
    public IGameState getState() {
        return gameState;
    }

    /**
     * Narazie ta metoda jest wywoływana przez actionNotifiera, który powiadamia
     * przez actionReceived o każdej otrzymanej akcji.
     * Działa tylko filtracja po akcjach chata. Każda akcja Chata jest dodawana do kolejki
     * chatState.
     * @param a
     */
    public void actionReceived(AAction a) {
        if(a instanceof AChatAction) {
            chatState.addAction(a);
        } else {
            try {
                this.gameState = judge.judge(gameState, a);
            } catch (GameStateException ex) {
                ExceptionGUIAction reply = new ExceptionGUIAction(a.getSender(), ex);
                users[a.getSender()].actionReceived(reply);
            }
        }
        sendQueue(chatState);
        sendQueue(gameState);
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getName() {
        return name;
    }

}
