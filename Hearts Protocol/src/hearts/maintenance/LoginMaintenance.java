/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

import hearts.defs.protocol.IUserSocket;

/**
 *
 * @author orbit
 */
public class LoginMaintenance implements IMaintenance{
    
    private String login;
    private String password;
    transient private IUserSocket userSocket = null;
    private Boolean request;
    private Boolean success = false;

    /**
     * Konstruktor używany przy wysyłaniu odpowiedzi.
     * Ustawia automatycznie typ na REPLY (odpowiedź na rządanie logowania).
     */
    public LoginMaintenance() {
        this.login = null;
        this.password = null;
        this.request = false;
    }

    public LoginMaintenance(Boolean success) {
        this();
        this.success = success;
    }

    /**
     * Konstruktor używany do wysyłania requestu zalogowania.
     * Typ automatycznie ustawiany jest na REQUEST.
     * @param login login użytkownika
     * @param password hasło użytkownika
     */
    public LoginMaintenance(String login, String password) {
        this.login = login;
        this.password = password;
        this.request = true;
    }

    /**
     * Ustawia referencje na userSocket od którego request przyszyszedł żeby
     * do właściwego klienta odesłać informacje czy powiodło się logowania czy nie
     * i odpowiednio go oznaczyć.
     * @param userSocket zazwyczaj będzie this
     */
    public void setUserSocket(IUserSocket userSocket) {
        this.userSocket = userSocket;
    }

    /**
     * Zwraca userSocket po stronie serwera.
     * Po stronie klienta zawsze powinno być null.
     * @return userSocket od którego jest ten pakiet.
     */
    public IUserSocket getUserSocket() {
        return userSocket;
    }

    /**
     * Czy logowanie było prawidłowe.
     *
     * @return czy logowanie było prawidłowe.
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * Ustawia prawidłowość logowania.
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Zwraca typ pakietu.
     * @return true jeżeli to jest prośba o zalogowanie, false jeżeli odpowiedź.
     */
    public Boolean isRequest() {
        return request;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
  
}
