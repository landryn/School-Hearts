/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts.server;

import hearts.defs.actions.AAction;
import hearts.defs.actions.AChatAction;
import hearts.defs.actions.IActionNotifier;
import hearts.defs.protocol.IUserSocket;
import hearts.defs.state.GameConstants;
import hearts.defs.state.GameStateException;
import hearts.defs.state.IGameState;
import hearts.defs.state.IServerStateGuard;
import hearts.state.DumbState;
import hearts.state.NewUserAtTableAction;

/**
 * Klasa implementująca StateGuarda
 * @author Michał Charmas
 */
public class StateGuard implements IServerStateGuard {

    IGameState chatState = new DumbState();
    IGameState gameState = null;
    IUserSocket[] users = new IUserSocket[4];
    int userCount = 0;

    public StateGuard() {        
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
        notifyAboutNewUser(userCount);
        socket.addActionListener(this);
        userCount++;
        return userCount - 1;
    }

    /**
     * Powiadamia użytkowników o przybyciu nowego.
     * Podłączonemu użytkownikowi wysyła powiadomienia o wszystkich użytkownikach przy stole.
     * Robi to przez chatState ktory jest tylko kolejką do wysyłania powiadomień.
     * @param id
     */
    private void notifyAboutNewUser(int id) {
        //AAction action = new (GameConstants.ALL_USERS, users[id].getName(), id);
        //chatState.addAction(action);

        for (int i = 0; i < userCount; i++) {
            if (i != id) {
                chatState.addAction(new NewUserAtTableAction(id, users[i].getName(), users[i].getId()));
            }
        }
        sendQueue(chatState);
    }

    /**
     * Opróżnia daną kolejkę wysyłając wszystkie akcje na sockety użytkowników.
     * @param state kolejka do opróżnienia
     */
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
        }
        sendQueue(chatState);
        //sendQueue(gameState);
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
