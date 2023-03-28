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
    String email;
    String phone;
    int balance;

    public OTD_REG( int id,String username, String password, String email, String phone,int balance) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.balance=balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   

 
            
    
    
}
