/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

import hearts.defs.protocol.IUserSocket;

/**
 * Obiekty tej klasy używane są do wysyłania requestów o zalogowanie i obsługiwanie odpowiedzi.
 * Gdy obiekt jest rządaniem isRequest jest prawdą.
 * Gdy obiekt jest odpowiedzią na rządanie isRequest powinno być fałszem a o tym, czy udało się zalogować
 * można dowiedzieć się przez isSuccess
 * @author Michał Charmas
 */
public class LoginMaintenance implements IMaintenance{
    
    private String login;
    private String password;
    transient private IUserSocket userSocket = null;
    private Boolean request;
    private Boolean success = false;

    /**
     * Konstruktor używany bezparametrowy.
     */
    public LoginMaintenance() {
        this.login = null;
        this.password = null;
        this.request = false;
    }

    /**
     * Konstrutor używany to tworzenia obiektu z odpowiedzią na rządanie logowania.
     * @param success czy udało się zalogować
     */
    public LoginMaintenance(Boolean success) {
        this();
        this.success = success;
    }

    /**
     * Konstruktor używany do tworzenia obiektu rządania do zalogowania.
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
     * Czy logowanie było prawidłowe.
     *
     * @return czy logowanie było prawidłowe.
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * Zwraca typ pakietu.
     * @return true jeżeli to jest prośba o zalogowanie, false jeżeli odpowiedź.
     */
    public Boolean isRequest() {
        return request;
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
