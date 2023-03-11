package server_1;
import OTD.OTD;
import OTD.OTD_REG;
import OTD.OTD_Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Server_1 {
    ServerSocket serverSocket;
    DataAccessLayer database;
    static HashMap<String,String> userAndPass;
    ResultSet rs;
    
    public Server_1()  {
        userAndPass = new HashMap<>();
        userAndPass.put("root", "root");
        try {
            serverSocket = new ServerSocket(5005);
            database =new DataAccessLayer("jdbc:mysql://localhost:3306/iwish", "root", "Dina123");
            //database =new DataAccessLayer("jdbc:derby://localhost:1527/Wishlist", "root", "Dina123");
            rs = database.conn.createStatement().executeQuery("SELECT user_name, password FROM users");
                while (rs.next()) {
                String username = rs.getString("user_name");
                String password = rs.getString("password");
                userAndPass.put(username, password);
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
        }
       
    }
        
        public static void main(String[] args) {
            new Server_1();
        }
        


class Client extends Thread 
{
    ObjectInputStream ois ;
    ObjectOutputStream oos ;
    
    public Client(Socket cs)
    {
        try {
             
            ois = new ObjectInputStream  (cs.getInputStream()) ;
            oos = new ObjectOutputStream (cs.getOutputStream());
            start();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
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
                    }else{
                        if(database.insert(otdReg )){
                            Server_1.userAndPass.put(otdReg.getUsername(), otdReg.getPassword());
                            System.out.println("Username and password is added");
                            oos.writeObject(new OTD_Status(1,true,"Member is succsfully register"));
                        }}
        }

                else {
                    ois.close();
                    oos.close();
                    
                    
                }
            } 
            catch(SocketException ex){
                try {
                    ois.close();
                    oos.close();
                } catch (IOException ex1) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
                }
                
            }
            
            
        
            catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
    }
    
    void sendMessageToAll(String msg)
    {
    
    }
              
}}