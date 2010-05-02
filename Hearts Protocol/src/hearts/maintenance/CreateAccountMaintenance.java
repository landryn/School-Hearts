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
public class CreateAccountMaintenance implements IMaintenance{

    private IUserSocket socket=null;
    private String login;
    private String password;
    private Boolean reply = false;
    private Boolean success = null;

    public CreateAccountMaintenance(String login, String password) {
        this.login = login;
        this.password = password;
        this.reply = false;
    }

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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getReply() {
        return reply;
    }

    public Boolean getSuccess() {
        return success;
    }
    
}
