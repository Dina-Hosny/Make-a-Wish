/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20101
 */
public class OTD_Notif extends OTD {
   
    private List<List<Object>> list ;
    String username;

    public OTD_Notif(int id,String username) {
        super(id);
        this.username=username;
        
       list = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void add(List<Object> sublist){
    list.add(sublist);
    }


    public List<List<Object>> getList() {
        return list;
    }

    public void setList(List<List<Object>> list) {
        this.list = list;
    }
    public void addToList(int type,String message){
    List<Object> sublist1 = new ArrayList<>();
    sublist1.add(type);
    sublist1.add(message);
    add(sublist1);
    }
    
    
    
    
    
}
