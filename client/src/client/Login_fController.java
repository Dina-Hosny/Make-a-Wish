/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import OTD.OTD;
import OTD.OTD_Login;
import OTD.OTD_Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.UserData;

/**
 *
 * @author 20101
 */
public class Login_fController implements Initializable {
    Socket s;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            s = new Socket("127.0.0.1",5005);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
        } catch (SocketException e) {
            
        JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+e.getMessage());

              
            }
        catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null,"UnknownHost" +"\n"+"Error Message: "+e.getMessage());

            }
        catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Faild to connect with the server" +"\n"+"Error Message: "+ex.getMessage());
        }
    }
    
    
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @FXML
    private void login(ActionEvent event) {
        if (password.getText().isEmpty() || username.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please make sure that all fields is filled");
            
            
        }
        
        
        
        else{
                        OTD_Login S = new OTD_Login(2,username.getText().toLowerCase(),password.getText());
                        try {
                OTD x = (OTD) S;
                oos.writeObject(x);
                OTD_Status status =(OTD_Status) ois.readObject();
                if(status.isStatus()){
                     JOptionPane.showMessageDialog(null,status.getMessage());
          
                    UserData.getInstance().setUsername(username.getText().toLowerCase());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    Parent table = loader.load();
                    

                    HomePageController homecontroller = loader.getController();
                    //homecontroller.setData("jane_smith");
                    Scene scene =new Scene(table);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    
                }else{
                    JOptionPane.showMessageDialog(null,status.getMessage());
                }
                
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Faild to connect with the client" +"\n"+"Error Message: "+ex.getMessage());
            }

                    }
    
    }
    
}
