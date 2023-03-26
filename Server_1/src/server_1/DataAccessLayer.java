/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_1;

import OTD.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import org.apache.derby.jdbc.ClientDriver;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Aly&Dina&Shrouk
 */
public class DataAccessLayer {
    Connection conn;

    /**
     * Used to initialize the connection to the database
     * @param URL
     * @param User
     * @param Password
     */
    public DataAccessLayer(String URL,String User,String Password) {
        try {
            //DriverManager.registerDriver(new ClientDriver() );
            
             conn=DriverManager.getConnection(URL,User,Password);
             conn.setAutoCommit( true );
            JOptionPane.showMessageDialog(null,"Successfully connected with database ");

                    } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"Faild to connect with database"+"\n"+"Error Message: "+ex.getMessage());

                    }
        
    }
    
    public static int operation(OTD otd){
        return otd.getId();
        
}

    /**
     * This method is used to insert user information to the database
     * @param otd
     * @return boolean 
     */
    public boolean insert(OTD_REG otd){
        try {
            PreparedStatement pst = conn.prepareStatement("insert into users values(?,?,?,?,?)");
            pst.setString(1, otd.getUsername());
            pst.setString(2, otd.getPassword());
            pst.setString(3, otd.getEmail());
            pst.setString(4, otd.getPhone());
            pst.setInt(5, otd.getBalance());
            pst.executeUpdate();
            return true;

            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Somthing Wrong!"+"\n"+"Error Message: "+ex.getMessage());

            return false;
        }
    
    }
    
    /**
     * This method is used to insert items to wish list of the user
     * @param otd
     * @return true if it executed
     * 
     */
    public boolean insert(OTD_item_insert otd){
        try {
            
            PreparedStatement pst2 = conn.prepareStatement("insert into wishlist values(?,?,?,?,?,?,?)");
            pst2.setBlob(1, otd.getImage());
            pst2.setDate(2, Date.valueOf(LocalDate.now()));
            pst2.setString(3,otd.getUsername());
            pst2.setString(4,otd.getItemName());
            pst2.setString(5, otd.getCat());
            pst2.setInt(6, otd.getPrice());
            pst2.setInt(7, otd.getPrice());

            
            pst2.executeUpdate();
            return true;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
            
        }

    /**
     * This code takes the result set and return json format but it is only for data without images
     * @param rs resultSet
     * @return json 
     * @throws JsonProcessingException
     */
    public static String getData(ResultSet rs) throws JsonProcessingException{
        String json = "";
        try {
            List<Map<String, Object>> rows = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                    
                }
                rows.add(row);
                System.out.println(row);
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writeValueAsString(rows);
                
            }
            
            return json; 
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public static String getWishListData(DataAccessLayer database,String username){
        try {
            PreparedStatement statm = database.conn.prepareStatement("SELECT image,date,itemname,price,category,remained FROM wishlist where username=?;");
            statm.setString(1, username);
            ResultSet rs = statm.executeQuery();
            return gatDataWithPic(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return null;
    }
 public static String gatDataWithPic(ResultSet rs){   
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<wishListView> dataList = new ArrayList<>();
            
            while (rs.next()) {
                
                byte[] image= rs.getBytes("image");
                String item = rs.getString("itemname");
                int price = rs.getInt("price");
                int remained = rs.getInt("remained");
                Date date = rs.getDate("date");
                String cat = rs.getString("category");
                
                wishListView data = new wishListView(image,item,price,remained,date,cat);
                
                dataList.add(data);
            }
            
            String jsonOutput = objectMapper.writeValueAsString(dataList);
            return jsonOutput;
        } catch (SQLException | JsonProcessingException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}
public  boolean remove(OTD_Remove otd){
    
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM wishlist WHERE itemname = ? and username=?;");
            pst.setString(2, otd.getUsername());
            pst.setString(1, otd.getItem());
            pst.executeUpdate();
            
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


}

    boolean insert(OTD_insert_2 otd,DataAccessLayer database) {
        Blob image = null;
        try {
            PreparedStatement statm = database.conn.prepareStatement("SELECT image FROM wishlist where username=? and itemname =?;");
            statm.setString(1, "root");
            statm.setString(2, otd.getItemName());
            ResultSet rs = statm.executeQuery();
            while(rs.next()){
            
             image= rs.getBlob("image");
            }
            PreparedStatement pst2 = conn.prepareStatement("insert into wishlist values(?,?,?,?,?,?,?)");
            pst2.setBlob(1,image);
            pst2.setDate(2, Date.valueOf(LocalDate.now()));
            pst2.setString(3,otd.getUsername());
            pst2.setString(4,otd.getItemName());
            pst2.setString(5, otd.getCat());
            pst2.setInt(6, otd.getPrice());
            pst2.setInt(7, otd.getPrice());

            
            pst2.executeUpdate();
            return true;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public String getFriends(OTD_Request otd,DataAccessLayer database){
            PreparedStatement statm;
        try {
            statm = database.conn.prepareStatement("select friendname ,email,date from friends join users on friends.friendname = users.username where friends.username=?;");
            statm.setString(1, otd.getUsername());
            
            ResultSet rs =statm.executeQuery();
            return getData(rs);
            
        } catch (SQLException ex) {
                       

            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        } catch (JsonProcessingException ex) {
            
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
         return null;
        }
        
            
    
    }
    public String getRequests(OTD_Request otd){
        PreparedStatement statm;
        try {
            statm = this.conn.prepareStatement("select fromuser ,email,date from friendrequest join users on friendrequest.fromuser = users.username where friendrequest.touser=? and status=?;");
            statm.setString(1, otd.getUsername());
            statm.setString(2,"pending");
            
            ResultSet rs =statm.executeQuery();
            return getData(rs);
            
        } catch (SQLException ex) {
                       

            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        } catch (JsonProcessingException ex) {
            
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
         return null;
        }
    
    }
    public  boolean remove(OTD_Remove otd,DataAccessLayer database){
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM friends WHERE username = ? and friendname=?;");
            pst.setString(1, otd.getUsername());
            pst.setString(2, otd.getFriendName());
            pst.executeUpdate();
            pst.setString(2, otd.getUsername());
            pst.setString(1, otd.getFriendName());
            pst.executeUpdate();
            
            System.out.println("done");
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
     public  boolean remove(OTD_Remove otd,int anything){
        try {
            PreparedStatement pst = conn.prepareStatement("UPDATE friendrequest SET status = ? WHERE fromuser = ? and touser=?;");
            pst.setString(3, otd.getUsername());
            pst.setString(2, otd.getFriendName());
            pst.setString(1, "Accepted");

            pst.executeUpdate();
            
            
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
     public  boolean remove(int anything,OTD_Remove otd){
        try {
            PreparedStatement pst = conn.prepareStatement("UPDATE friendrequest SET status = ? WHERE fromuser = ? and touser=?;");
            pst.setString(3, otd.getUsername());
            pst.setString(2, otd.getFriendName());
            pst.setString(1, "Decline");

            pst.executeUpdate();
            
            
            System.out.println("done");
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
     public String getUserData(String username,String username2){
        try {
            PreparedStatement pst = conn.prepareStatement("select username,email from users where username like ? and username!=? and username not in (select touser from friendrequest where fromuser =? and status=?) and username not in (select friendname from friends where username=?);   ");
            pst.setString(1, username+"%");
            pst.setString(2, username2);
            pst.setString(3, username2);
            pst.setString(4,"Pending");
            pst.setString(5,username2);
            
           ResultSet rs =pst.executeQuery();
           return getData(rs);
        } catch (SQLException | JsonProcessingException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return null ;
        }}
        public void insertFriend(String username,String username2){
        try {
            PreparedStatement pst = conn.prepareStatement("insert into friendrequest(date,fromuser,touser) values(?,?,?); ");
            pst.setDate(1, Date.valueOf(LocalDate.now()));
            pst.setString(2, username2);
            pst.setString(3, username);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public OTD_Notif getNotification(String username){
        try {
            OTD_Notif requests=new OTD_Notif(300,"") ;
            PreparedStatement pst = conn.prepareStatement("Select * from friendrequest where notified = ? and touser = ? and status=? ");
            pst.setString(1,"no" );
            pst.setString(2,username );
            pst.setString(3, "Pending");
            ResultSet rs =pst.executeQuery();
            int rowCount = 0;
            while(rs.next()){
                rowCount+=1;
            String user=rs.getString("fromuser");
            String message=user+" "+"send you a friend request";
            requests.addToList(1, message);
            }
            PreparedStatement pst1 = conn.prepareStatement("Select * from friendrequest where notified = ? and fromuser = ? and status=? ");
            pst1.setString(1,"Yes" );
            pst1.setString(2,username );
            pst1.setString(3, "Accepted");
            ResultSet rs1=pst1.executeQuery();
            while(rs1.next()){
                rowCount+=1;
            String user=rs1.getString("touser");
            String message=user+" "+"accept the friend request";
            System.out.println(message);
            requests.addToList(1, message);
            }
            //----------------------
            PreparedStatement pst2 = conn.prepareStatement("Select * from transaction where notifiy = ? and to_ = ? ; ");
            pst2.setString(1,"no" );
            pst2.setString(2,username );
            
            ResultSet rs2=pst2.executeQuery();
            while(rs2.next()){
                rowCount+=1;
            String user=rs2.getString("from_");
            Date date =rs2.getDate("date");
            int amount = rs2.getInt("amo");
            String item=rs2.getString("item");
            String message=user+" "+"has contributed with"+amount+"LE "+item+"at "+date ;
            System.out.println(message);
            requests.addToList(1, message);
            }
            PreparedStatement pst3 = conn.prepareStatement("SELECT collected.date,collected.itemname,collected.username from transaction , collected  \n" +
"where transaction.to_=collected.username and transaction.item=collected.itemname and transaction.from_=? and transaction.notifiy_after=?; ");
            pst3.setString(2,"no" );
            pst3.setString(1,username );
            
            ResultSet rs3=pst3.executeQuery();
            while(rs3.next()){
                rowCount+=1;
            String user=rs3.getString("username");
            Date date =rs3.getDate("date");
            String item=rs3.getString("itemname");
            String message="The item "+item+" that you have controunbted "+"is now ready to collect by the "+ user+ " Thank you " ;
            System.out.println(message);
            requests.addToList(1, message);
            }
            PreparedStatement pst4 = conn.prepareStatement("SELECT collected.date,collected.itemname,collected.username from  collected  \n" +
"where username=? and notify=?; ");
            pst4.setString(2,"no" );
            pst4.setString(1,username );
            
            ResultSet rs4=pst4.executeQuery();
            while(rs4.next()){
                rowCount+=1;
            String user=rs4.getString("username");
            Date date =rs4.getDate("date");
            String item=rs4.getString("itemname");
            String message="Congrats "+item+" is ready to collect" ;
            System.out.println(message);
            requests.addToList(1, message);
            }
            if (rowCount>0){
            return requests;
            }else{
            return null;
            }
            
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        }
        public void update(String username){
        try {
            PreparedStatement pst = conn.prepareStatement("update friendrequest set notified=? where touser = ? and status =? ");
            pst.setString(1, "Yes");
            pst.setString(2, username);
            pst.setString(3, "Pending");
            pst.executeUpdate();
             pst = conn.prepareStatement("update friendrequest set notified=? where fromuser = ? and  status =? ");
            pst.setString(1, "Yesss");
            pst.setString(2, username);
            pst.setString(3, "Accepted");
            pst.executeUpdate();
             pst = conn.prepareStatement("update transaction set notifiy=? where to_ = ?  ");
            pst.setString(1, "Yes");
            pst.setString(2, username);
           
            pst.executeUpdate();
            pst=conn.prepareStatement("UPDATE transaction \n" +
"SET notifiy_after = 'Yes' \n" +
"WHERE transaction.from_ IN (\n" +
"    SELECT x.from_ \n" +
"    FROM (\n" +
"        SELECT t1.from_ \n" +
"        FROM transaction AS t1\n" +
"        JOIN collected ON t1.to_ = collected.username AND t1.item = collected.itemname \n" +
"        WHERE t1.from_ = ? AND t1.notifiy_after = ?\n" +
"    ) AS x\n" +
") \n" +
"AND \n" +
"transaction.item IN (\n" +
"    SELECT z.item \n" +
"    FROM (\n" +
"        SELECT t2.item \n" +
"        FROM transaction AS t2 \n" +
"        JOIN collected ON t2.to_ = collected.username AND t2.item = collected.itemname \n" +
"        WHERE t2.from_ = ? AND t2.notifiy_after = ?\n" +
"    ) AS z\n" +
");");
            pst.setString(1, username);
            pst.setString(2,"no" );
            pst.setString(3, username);
            pst.setString(4,"no" );
            
            pst.executeUpdate();
             pst = conn.prepareStatement("update collected set notify=? where username = ?  ");
            pst.setString(1, "Yes");
            pst.setString(2, username);
           
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        }
        public int checkAndInsert(contr otd){
        try {
         

            CallableStatement callableStatement = conn.prepareCall("{ ? = call checkamount(?, ?,?,?) }");
            

            callableStatement.setString(2, otd.getFromuser());
            callableStatement.setString(3, otd.getTouser());
            callableStatement.setString(4, otd.getItemName());
            
            callableStatement.setInt(5, otd.getAmount());
            callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);
            callableStatement.execute();
            int result = callableStatement.getInt(1);
            callableStatement.close();
            
            if (result==1){
            PreparedStatement pst = conn.prepareStatement("delete from wishlist where remained=0");
            
           pst.executeUpdate();
            
            }
            
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        }
        public static String getWishListData1(DataAccessLayer database,String username){
        try {
            PreparedStatement statm = database.conn.prepareStatement("SELECT image,date,itemname,price FROM collected where username=?;");
            statm.setString(1, username);
            ResultSet rs = statm.executeQuery();
            return gatDataWithPic1(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return null;
    }
         public static String gatDataWithPic1(ResultSet rs){   
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<wishListView> dataList = new ArrayList<>();
            
            while (rs.next()) {
                
                byte[] image= rs.getBytes("image");
                String item = rs.getString("itemname");
                int price = rs.getInt("price");
                
                Date date = rs.getDate("date");
                
                
                wishListView data = new wishListView(image,item,price,date);
                
                dataList.add(data);
            }
            
            String jsonOutput = objectMapper.writeValueAsString(dataList);
            return jsonOutput;
        } catch (SQLException | JsonProcessingException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}
         public  boolean remove(OTD_Remove1 otd){
    
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM collected WHERE itemname = ? and username=?;");
            pst.setString(2, otd.getUsername());
            pst.setString(1, otd.getItem());
            pst.executeUpdate();
            
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


}
         public int getUserData(String username){
        try {
            PreparedStatement pst = conn.prepareStatement("select balance from users where username =?;   ");
            pst.setString(1, username);
            
            
           ResultSet rs =pst.executeQuery();
           if (rs.next()){
           return rs.getInt("balance");
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
         }
         public boolean updateBalance(String username,int balance){
        try {
            PreparedStatement pst = conn.prepareStatement("update users set balance=? where username=?;   ");
            pst.setString(2, username);
            pst.setInt(1, balance);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

         
         return false;}

     }


