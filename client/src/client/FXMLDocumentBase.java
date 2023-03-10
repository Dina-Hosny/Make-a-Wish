package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import OTD.OTD_REG;
import OTD.OTD;
import OTD.OTD_Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXMLDocumentBase extends TitledPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final TextField username;
    protected final TextField fName;
    protected final PasswordField password;
    protected final Button register;
     protected final Button login;
Socket s;
ObjectOutputStream oos;
ObjectInputStream ois;
    public FXMLDocumentBase() {
        try{
        s = new Socket("127.0.0.1",5005);
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        }
        catch (SocketException e) {
            
            Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            Alert errorAlert = new Alert(AlertType.ERROR);
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
                            Alert errorAlert = new Alert(AlertType.ERROR);
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
            

        anchorPane = new AnchorPane();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        username = new TextField();
        fName = new TextField();
        password = new PasswordField();
        register = new Button();
        login = new Button();

        setAnimated(false);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setText("untitled");

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(180.0);
        anchorPane.setPrefWidth(200.0);

        label.setLayoutX(81.0);
        label.setLayoutY(61.0);
        label.setText("Username");

        label0.setLayoutX(83.0);
        label0.setLayoutY(99.0);
        label0.setText("Password");

        label1.setLayoutX(79.0);
        label1.setLayoutY(146.0);
        label1.setText("First Name");

        username.setLayoutX(169.0);
        username.setLayoutY(57.0);

        fName.setLayoutX(169.0);
        fName.setLayoutY(142.0);

        password.setLayoutX(169.0);
        password.setLayoutY(104.0);

        register.setLayoutX(208.0);
        register.setLayoutY(254.0);
        register.setMnemonicParsing(false);
        register.setOnAction(this::register);
        register.setText("Register");
        
        login.setLayoutX(150.0);
        login.setLayoutY(150.0);
        login.setMnemonicParsing(false);
        login.setOnAction(this::register);
        login.setText("login");
        setContent(anchorPane);

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(username);
        anchorPane.getChildren().add(fName);
        anchorPane.getChildren().add(password);
        anchorPane.getChildren().add(register);

    }

    public void register(javafx.event.ActionEvent actionEvent){
        if (password.getText().isEmpty() ||username.getText().isEmpty() ||fName.getText().isEmpty()){
            System.out.println("Please make sure that are filled all the fields");
        }else{
            OTD_REG S = new OTD_REG(1,username.getText().toLowerCase(),password.getText().toLowerCase(),fName.getText().toLowerCase());
            try {
                OTD x = (OTD) S;
                oos.writeObject(x);
                OTD_Status status =(OTD_Status) ois.readObject();
                if(status.isStatus()){
                    System.out.println(status.getMessage());
                }else{
                    System.out.println(status.getMessage());
                }
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }};
    public void login(javafx.event.ActionEvent actionEvent){
        
    };

}
