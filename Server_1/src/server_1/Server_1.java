package server_1;
import OTD.OTD;
import OTD.OTD_Login;
import OTD.OTD_Notif;
import OTD.OTD_REG;
import OTD.OTD_Remove;
import OTD.OTD_Remove1;
import OTD.OTD_Request;
import OTD.OTD_Status;
import OTD.OTD_insert_2;
import OTD.OTD_item_insert;
import OTD.contr;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.EOFException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;


public class Server_1 {
    ServerSocket serverSocket;
    DataAccessLayer database;
    static HashMap<String,String> userAndPass;
    static HashSet<String> emailSet;
     
    ResultSet rs;
    String json;
   
    
    public Server_1()  {
        userAndPass = new HashMap<>();
        emailSet = new HashSet<>();
        
        try {
            serverSocket = new ServerSocket(5005);
            database =new DataAccessLayer("jdbc:mysql://localhost:3306/iwish", "iwish", "iwish");
    //database =new DataAccessLayer("jdbc:mysql://localhost:3306/iwish", "root", "root");
    rs = database.conn.createStatement().executeQuery("SELECT username, password,email FROM users");
                while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String Email = rs.getString("email");
                userAndPass.put(username, password);
                emailSet.add(Email);
            }
            
             while(true) 
            {
            Socket s = serverSocket.accept() ;
            new Client(s);
            }
        
        } catch (IOException ex) {
            Logger.getLogger(Server_1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Connection faild! Try Again"+"\n"+"Error Message: "+ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Server_1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error with entred query"+"\n"+"Error Message: "+ex.getMessage());
        }
       
    }
        
        public static void main(String[] args) {
            new Server_1();
        }
        


class Client extends Thread 
{
    ObjectInputStream ois ;
    ObjectOutputStream oos ;
    PrintWriter pw;
    
    public Client(Socket cs)
    {
        try {
             
            ois = new ObjectInputStream  (cs.getInputStream()) ;
            oos = new ObjectOutputStream (cs.getOutputStream());
            pw = new PrintWriter(cs.getOutputStream(),true);
            start();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
             
            JOptionPane.showMessageDialog(null,"Error while connecting with the clients"+"\n"+"Error Message: "+ex.getMessage());
        }
      
    }
    
    @Override
    public void run () 
    {
         
        while(true) {
            try {
                 
                OTD otd =  (OTD) ois.readObject();
                
                if(otd !=null && DataAccessLayer.operation(otd)==1)
                {
                    OTD_REG otdReg = (OTD_REG) otd;
                    System.out.println(otdReg.getUsername());
                    if(Server_1.userAndPass.containsKey(otdReg.getUsername()) ){
                        System.out.println("Username is dublicated");
                        oos.writeObject(new OTD_Status(1,false,"Username is dublicated"));
                    }else if (Server_1.emailSet.contains(otdReg.getEmail())) {
                        System.out.println("Email is dublicated");
                        oos.writeObject(new OTD_Status(1,false,"email is dublicated"));
                    }
                    else{
                        if(database.insert(otdReg )){
                            Server_1.userAndPass.put(otdReg.getUsername(), otdReg.getPassword());
                            Server_1.emailSet.add(otdReg.getEmail());
                            System.out.println("Username and password is added");
                            oos.writeObject(new OTD_Status(1,true,"Member is succsfully register"));
                        }}
        } else if (otd !=null && DataAccessLayer.operation(otd)==999){
            OTD_Request newBalance = (OTD_Request) otd;
            boolean flag = database.updateBalance(newBalance.getUsername(), newBalance.getNewAmount());
            if(flag){
                oos.writeObject(new OTD_Status(1,true,"The Balance is added"));
            }else{
            oos.writeObject(new OTD_Status(1,false,"There is an error"));
            }
        }
                else if (otd !=null && DataAccessLayer.operation(otd)==900){
            int x=database.checkAndInsert((contr) otd);
            if(x>0){
            oos.writeObject(new OTD_Status(1,true,"The contribuiton is done"));
            }
            else{oos.writeObject(new OTD_Status(1,false,"Your balance is not enough or you contribute with amount greater than the allowed"));
            }
        }
                else if (otd !=null && DataAccessLayer.operation(otd)==300){
            OTD_Notif x=(OTD_Notif) otd;
            OTD_Notif y=database.getNotification(x.getUsername());
            oos.writeObject(y);
            database.update(x.getUsername());
        }
                else if(otd !=null && DataAccessLayer.operation(otd)==15){
          json=database.getFriends((OTD_Request) otd,database);
          System.out.println(json);
           pw.println(json);
        }
        else if(otd !=null && DataAccessLayer.operation(otd)==16){
          json=database.getRequests((OTD_Request) otd);
          System.out.println(json);
           pw.println(json);
        }
                else if (otd !=null && DataAccessLayer.operation(otd)==22){
           if(database.insert((OTD_insert_2)otd,database)){
                oos.writeObject(new OTD_Status(1,true,"done"));
            }else{
                oos.writeObject(new OTD_Status(1,false,"no"));
            } 
        } else if(otd !=null && DataAccessLayer.operation(otd)==33){
            int balance=database.getUserData(((OTD_Request)otd).getUsername());
                oos.writeObject(new OTD_Status(balance,true,"done"));
            }
                else if(otd !=null && DataAccessLayer.operation(otd)==21){
            if(database.insert((OTD_item_insert)otd)){
                oos.writeObject(new OTD_Status(1,true,"done"));
            }else{
                oos.writeObject(new OTD_Status(1,false,"no"));
            }
        }else if (otd !=null && DataAccessLayer.operation(otd)==55){
                 OTD_Remove otdRem = (OTD_Remove) otd;
                 database.remove(otdRem,otdRem.getId());
        }
        else if (otd !=null && DataAccessLayer.operation(otd)==66){
                 OTD_Remove otdRem = (OTD_Remove) otd;
                 database.remove(5,otdRem);
        }else if (otd !=null && DataAccessLayer.operation(otd)==100){
            
                 json=database.getUserData(((OTD_Request) otd).getUsername(),((OTD_Request) otd).getUsername2());
                 System.out.println(json);
                 pw.println(json);
        }
         else if (otd !=null && DataAccessLayer.operation(otd)==150){
                 OTD_Request request = (OTD_Request) otd;
                 database.insertFriend(request.getUsername(),request.getUsername2());
        }
        
                else if (otd !=null && DataAccessLayer.operation(otd)==50){
                 OTD_Remove otdRem = (OTD_Remove) otd;
                 database.remove(otdRem,database);
        }
                else if (otd !=null && DataAccessLayer.operation(otd)==121){
                 OTD_Remove1 otdRem = (OTD_Remove1) otd;
                 if(database.remove(otdRem)){
                 oos.writeObject(new OTD_Status(1,true,"Item is removed"));}
                 else{
                  oos.writeObject(new OTD_Status(1,false,"Item isn't removed"));}

                 }
                 
                else if (otd !=null && DataAccessLayer.operation(otd)==12){
                 OTD_Remove otdRem = (OTD_Remove) otd;
                 if(database.remove(otdRem)){
                 oos.writeObject(new OTD_Status(1,true,"Item is removed"));}
                 else{
                  oos.writeObject(new OTD_Status(1,false,"Item isn't removed"));}

                 }
                 


        
        
                else if(otd !=null && DataAccessLayer.operation(otd)==2) {
            OTD_Login otdLogin = (OTD_Login) otd;
            
            if(userAndPass.containsKey(otdLogin.getUsername()) && userAndPass.get(otdLogin.getUsername()).equals(otdLogin.getPassword())){
                oos.writeObject(new OTD_Status(2,true,"LoginDone"));

            }else{
                oos.writeObject(new OTD_Status(2,false,"LoginDenied"));
            }
            
        }else if(otd !=null && DataAccessLayer.operation(otd)==111){
            OTD_Request otdRequest = (OTD_Request) otd;
         json=DataAccessLayer.getWishListData1(database,otdRequest.getUsername());
         System.out.println(json);
         pw.println(json);
                 }
        else if(otd !=null && DataAccessLayer.operation(otd)==11){
            OTD_Request otdRequest = (OTD_Request) otd;
         json=DataAccessLayer.getWishListData(database,otdRequest.getUsername());
         System.out.println(json);
         pw.println(json);
                 }

                else {
                    ois.close();
                    oos.close();
                    pw.close();
                    
                    
                }
            } 
            catch(EOFException e){
                
            }
            catch(SocketException ex){
                try {
                    ois.close();
                    oos.close();
                    pw.close();
                } catch (IOException ex1) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
                     
                    JOptionPane.showMessageDialog(null,"Faild to connect with the database" +"\n"+"Error Message: "+ex1.getMessage());
                }
                
            }
            
            
        
            catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Faild to connect with the database" +"\n"+"Error Message: "+ex.getMessage());
            } catch (ClassNotFoundException ex) {
                
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Client not connected!" +"\n"+"Error Message: "+ex.getMessage());
                
            } 
            
        }
    }
    
   
              
}}