/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

/**
 *
 * @author 20101
 */
public class contr extends OTD {
    String fromuser;
    String touser;
    String itemName;
    int amount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public contr(String fromuser, String touser, String itemName, int amount, int id) {
        super(id);
        this.fromuser = fromuser;
        this.touser = touser;
        this.itemName = itemName;
        this.amount = amount;
    }
    

    public contr(String fromuser, String touser, int amount, int id) {
        super(id);
        this.fromuser = fromuser;
        this.touser = touser;
        this.amount = amount;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
