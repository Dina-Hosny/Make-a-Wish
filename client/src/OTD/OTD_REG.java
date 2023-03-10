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
public class OTD_REG extends OTD  implements Serializable  {
    
    String username;
    String password;
    String fName;

    public OTD_REG(int Operation,String username, String password, String fName) {
        super(Operation);
        this.username = username;
        this.password = password;
        this.fName = fName;
        
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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
            
    
    
}
