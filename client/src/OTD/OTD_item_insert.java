/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

import java.sql.Blob;
import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author 20101
 */
public class OTD_item_insert extends OTD {
    Blob image;
    String itemName;
    int price;
    String username;
    String cat;
    int remained;
    Date date;

    public OTD_item_insert(int id,Blob image, String itemName, int price, String username, String cat, int remained) {
        super(id);
        this.image = image;
        this.itemName = itemName;
        this.price = price;
        this.username = username;
        this.cat = cat;
        this.remained = remained;
        
    }

    public OTD_item_insert(int id,String itemName, int price, String username, String cat ) {
        super(id);
        this.itemName = itemName;
        this.price = price;
        this.username = username;
        this.cat = cat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public int getRemained() {
        return remained;
    }

    public void setRemained(int remained) {
        this.remained = remained;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

   

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
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
    
    
}
