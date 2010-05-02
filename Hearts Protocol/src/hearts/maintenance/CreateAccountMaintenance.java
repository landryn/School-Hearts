/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

import hearts.defs.protocol.IUserSocket;

/**
 * Obiekty tej klasy przesyłane są by utworzyć konto użytkownika.
 * Przetwarzane są po stronie serwera.
 * @author Michał Charmas
 */
public class CreateAccountMaintenance implements IMaintenance{

    private IUserSocket socket=null;
    private String login;
    private String password;

    /**
     * Tworzy obiekt rządania zarejestrowania konta.
     * @param login do zarejestrowania
     * @param password do zarejestrowania
     */
    public CreateAccountMaintenance(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setUserSocket(IUserSocket userSocket) {
        this.socket = userSocket;
    }

    public IUserSocket getUserSocket() {
        return this.socket;
    }

    /**
     *
     * @return login do rejestracji
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @return hasło do rejestracji
     */
    public String getPassword() {
        return password;
    }
}
