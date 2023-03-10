/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_1;

import OTD.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.jdbc.ClientDriver;
import server_1.Server_1;

/**
 *
 * @author 20101
 */
public class DataAccessLayer {
    Connection conn;
    public DataAccessLayer(String URL,String User,String Password) {
        try {
            DriverManager.registerDriver(new ClientDriver() );
            
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
    public boolean insert(OTD_REG otd){
        try {
            PreparedStatement pst = conn.prepareStatement("insert into users values(?,?,?)");
            pst.setString(1, otd.getUsername());
            pst.setString(2, otd.getPassword());
            pst.setString(3, otd.getfName());
            pst.executeUpdate();
            return true;

            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Somthing Wrong!"+"\n"+"Error Message: "+ex.getMessage());

            return false;
        }
    
    }
}
