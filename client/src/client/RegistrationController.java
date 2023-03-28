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
import javax.swing.JOptionPane;
import model.UserData;

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
    @FXML
    private TextField balance;

    /**
     * Initializes the controller class.
     */
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
        // TODO
       

    @FXML
            public void signUp(ActionEvent event){
        // Regular expression pattern to validate email format
// Regular expression pattern to validate email format
String emailPattern = "^[\\w-\\.]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
// Regular expression pattern to validate password format (minimum 6 characters)
String passwordPattern = "^.{6,}$";

// Check if all fields are filled and email is in the correct format
if (password.getText().isEmpty() || username.getText().isEmpty() || email.getText().isEmpty() || balance.getText().isEmpty() || !email.getText().matches(emailPattern)) {
    JOptionPane.showMessageDialog(null, "Please make sure that all fields are filled and email is in the correct format.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Check if balance is greater than zero
else if (Integer.parseInt(balance.getText()) < 0) {
    JOptionPane.showMessageDialog(null, "Balance must be greater than or equal zero.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Check if password meets the required format
else if (!password.getText().matches(passwordPattern)) {
    JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// All validations passed, continue with the rest of the code


// All validations passed, continue with the rest of the code

        else{
            OTD_REG S = new OTD_REG(1,username.getText().toLowerCase(),password.getText(),email.getText().toLowerCase(),phone.getText().toLowerCase(),Integer.parseInt(balance.getText()));
            try {
                OTD x = (OTD) S;
                oos.writeObject(x);
                OTD_Status status =(OTD_Status) ois.readObject();
                if(status.isStatus()){
                    JOptionPane.showMessageDialog(null,status.getMessage());
                    Parent table1 = FXMLLoader.load(getClass().getResource("login_f.fxml"));
                    Scene scene =new Scene(table1);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    
                }else{
                    
                    JOptionPane.showMessageDialog(null,status.getMessage());
                    
                }
                
            }
             catch (IOException ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Faild loading the scene" +"\n"+"Error Message: "+ex.getMessage());
            }
            // handle the exception
             catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Scene is not exist!" +"\n"+"Error Message: "+ex.getMessage());
            }
            
        }};

        
    
    
}
