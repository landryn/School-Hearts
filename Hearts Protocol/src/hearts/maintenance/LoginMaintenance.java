/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

import hearts.defs.protocol.IUserSocket;

/**
 * Obiekty tej klasy używane są do wysyłania requestów do zalogowania.
 * @author Michał Charmas
 */
public class LoginMaintenance implements IMaintenance{
    
    private String login;
    private String password;
    transient private IUserSocket userSocket = null;

    /**
     * Konstruktor używany bezparametrowy.
     */
    public LoginMaintenance() {
        this.login = null;
        this.password = null;
    }


    /**
     * Konstruktor używany do tworzenia obiektu rządania do zalogowania.
     * @param login login użytkownika
     * @param password hasło użytkownika
     */
    public LoginMaintenance(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Ustawia referencje na userSocket od którego request przyszyszedł żeby
     * do właściwego klienta odesłać informacje czy powiodło się logowania czy nie
     * i odpowiednio go oznaczyć. Tylko i wyłącznie po stronie serwera.
     * @param userSocket zazwyczaj będzie this, po stronie klienta null
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
     * Zwraca login do sprawdzenia przy rządaniu.
     * W innym przypadku null.
     * @return login do sprawdzenia
     */
    public String getLogin() {
        return login;
    }

    /**
     * Hasło do sprawdzenia przy rządaniu
     * W innym przypadku null
     * @return hasło do sprawdzenia.
     */
    public String getPassword() {
        return password;
    }
  
}
