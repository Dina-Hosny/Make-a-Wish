/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

/**
 *
 * @author 20101
 */
public class OTD_Remove extends OTD {
    String username;
    String item;
    String friendName;

    public OTD_Remove(int id,String username, String item) {
        super(id);
        this.username = username;
        this.item = item;
    }

    public OTD_Remove(String username, String friendName, int id) {
        super(id);
        this.username = username;
        this.friendName = friendName;
    }

    public OTD_Remove(String usernameString, int i, String friendName) {
        super(i);
        this.username = usernameString;
        this.friendName = friendName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
   
    
}
