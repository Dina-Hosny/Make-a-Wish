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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    try{
        s = new Socket("127.0.0.1",5005);
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        }
        catch (SocketException e) {
            
            Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Coonection refused");
                            errorAlert.setContentText("Connection is refused,try to reconnect with Reconnect Button");
                            errorAlert.showAndWait();
                         
                        }});
              
            }
 
            // Catch block 3
            catch (UnknownHostException e) {
                Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Unknown Host");
                            errorAlert.setContentText("Please make sure your host address is right and open the application again");
                            errorAlert.showAndWait();
                            Platform.exit();
                           
                            
                        }});
            }
 
            // Catch block 4
            catch (IOException e) {
                e.printStackTrace();
            }
        // TODO
    }
    
    
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @FXML
    private void login(ActionEvent event) {
        if (password.getText().isEmpty() || username.getText().isEmpty()){
            System.out.println("Please make sure that all fields is typed");
        }else{
                        OTD_Login S = new OTD_Login(2,username.getText().toLowerCase(),password.getText());
                        try {
                OTD x = (OTD) S;
                oos.writeObject(x);
                OTD_Status status =(OTD_Status) ois.readObject();
                if(status.isStatus()){
                    System.out.println(status.getMessage());
                    
                }else{
                    System.out.println(status.getMessage());
                }
                
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Login_fController.class.getName()).log(Level.SEVERE, null, ex);
            }

                    }
    
    }
    
}
