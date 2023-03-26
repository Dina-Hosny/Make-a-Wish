/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

/**
 *
 * @author 20101
 */
public class OTD_Request extends OTD {
    String username;
    String username2;
    int newAmount;

    public int getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(int newAmount) {
        this.newAmount = newAmount;
    }

    public OTD_Request(String username, int newAmount, int id) {
        super(id);
        this.username = username;
        this.newAmount = newAmount;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public OTD_Request(String username, String username2, int id) {
        super(id);
        this.username = username;
        this.username2 = username2;
    }

    public OTD_Request(String username, int id) {
        super(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
                
          
    
}
