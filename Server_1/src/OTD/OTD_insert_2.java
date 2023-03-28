/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

/**
 *
 * @author 20101
 */
public class OTD_insert_2 extends OTD {
    
    String itemName;
    int price;
    String username;
    String cat;

    public OTD_insert_2( int id,String itemName, int price, String username, String cat) {
        super(id);
        this.itemName = itemName;
        this.price = price;
        this.username = username;
        this.cat = cat;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
    
    
    
    
}
