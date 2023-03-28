/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author hocin
 */
public class wishListView {
    ImageView image;
    String item_name;
    int price,remained;
    Date date;

   

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
    String cat;

    public wishListView(ImageView image, String item_name, int price, int remained, Date date,String cat) {
        this.image = image;
        this.item_name = item_name;
        this.price = price;
        this.remained = remained;
        this.date = date;
        this.cat = cat;
    }

    public wishListView(ImageView image, String item_name, int price, Date date) {
        this.image = image;
        this.item_name = item_name;
        this.price = price;
        this.date = date;
    }

    public wishListView(ImageView image, String item_name, int price, String cat) {
        this.image = image;
        this.item_name = item_name;
        this.price = price;
        this.cat = cat;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemained() {
        return remained;
    }

    public void setRemained(int remained) {
        this.remained = remained;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    
   

 
    
    
    
    
}