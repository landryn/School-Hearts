/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.maintenance;

import hearts.defs.protocol.IUserSocket;

/**
 * Obiekty tej klasy przesyłane są by utworzyć konto użytkownika.
 * Przetwarzane są po stronie serwera.
 * Odpowiedzią na żądanie zalogowania jest też obiekt tego typu oznaczony przez
 * isReply() na true oraz isSuccess() na true
 * Jeżeli isSuccess jest false oznacza to, że konto o podanym loginie już istniało na serwerze.
 * @author Michał Charmas
 */
public class CreateAccountMaintenance implements IMaintenance{

    private IUserSocket socket=null;
    private String login;
    private String password;
    private Boolean reply = false;
    private Boolean success = null;

    /**
     * Tworzy obiekt rządania zarejestrowania konta.
     * @param login do zarejestrowania
     * @param password do zarejestrowania
     */
    public CreateAccountMaintenance(String login, String password) {
        this.login = login;
        this.password = password;
        this.reply = false;
    }

    /**
     * Tworzy obiekt odpowiedzi na rządanie rejestracji.
     * @param success czy rejestracja powiodła się
     */
    public CreateAccountMaintenance(boolean success) {
        this(null, null);
        this.success = success;
        this.reply = true;
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

    /**
     *
     * @return Czy jest to pakiet z odpowiedza na rządanie.
     */
    public Boolean isReply() {
        return reply;
    }

    /**
     * Pole success ustawiane jest na false tylko w momencie gdy rządany login był już zajęty.
     * @return Czy rejestracja powiodła się.
     */
    public Boolean isSuccess() {
        return success;
    }    
}
