/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTD;

import java.io.Serializable;

/**
 *
 * @author 20101
 */
public class OTD_Login extends OTD  implements Serializable  {
    
    String username;
    String password;

    public OTD_Login(int id,String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
