/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package client;

import OTD.OTD;
import OTD.OTD_REG;
import OTD.OTD_Status;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 20101
 */
public class RegistrationController implements Initializable {
    Socket s;
        ObjectOutputStream oos;
        ObjectInputStream ois;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;

    /**
     * Initializes the controller class.
     */
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
        // TODO
       

    @FXML
            public void signUp(ActionEvent event){
        if (password.getText().isEmpty() ||username.getText().isEmpty() ||email.getText().isEmpty() ){
            System.out.println("Please make sure that are filled all the fields");
        }else{
            OTD_REG S = new OTD_REG(1,username.getText().toLowerCase(),password.getText(),email.getText().toLowerCase(),phone.getText().toLowerCase());
            try {
                OTD x = (OTD) S;
                oos.writeObject(x);
                OTD_Status status =(OTD_Status) ois.readObject();
                if(status.isStatus()){
                    System.out.println(status.getMessage());
                    Parent table1 = FXMLLoader.load(getClass().getResource("login_f.fxml"));
                    Scene scene =new Scene(table1);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    
                }else{
                    System.out.println(status.getMessage());
                }
                
            }catch (RuntimeException e) {
    e.printStackTrace();
}
            catch (IOException ex) {
                ex.printStackTrace();
            }
            // handle the exception
             catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }};

        
    
    
}
