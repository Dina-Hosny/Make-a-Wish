/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import OTD.OTD;
import OTD.OTD_Request;
import OTD.OTD_Status;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20101
 */
public class UserData {
    private String username;
    private static final UserData instance = new UserData();
     private Socket s;
     private ObjectInputStream ois;
     private ObjectOutputStream oos;
     private int balance;

    public int getBalance() {
        return getBalanceData();
    }

    public void setBalance() {
        this.balance = getBalanceData();
    }
InputStream inputStream;

    private UserData() {
        try {
            s = new Socket("127.0.0.1",5005);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            inputStream = s.getInputStream();
            this.setBalance();
        } catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public ObjectInputStream getOis() {
        return ois;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    

    public ObjectOutputStream getOos() {
        return oos;
    }

   

    public static UserData getInstance() {
        
        return instance;
    }
    public Socket getSocket(){
        
    
        return s;
    
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getBalanceData(){
        try {
            oos.writeObject((OTD) new OTD_Request(username,33));
            OTD_Status c =(OTD_Status) ois.readObject();
            return c.getId();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}

