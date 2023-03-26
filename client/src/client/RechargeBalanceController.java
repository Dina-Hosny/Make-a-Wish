/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package client;

import Notification.NotificationService;
import OTD.OTD;
import OTD.OTD_Request;
import OTD.OTD_Status;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.UserData;

/**
 * FXML Controller class
 *
 * @author 20101
 */
public class RechargeBalanceController implements Initializable {
      Socket s;
      private NotificationService notificationService;
        ObjectOutputStream oos;
        ObjectInputStream ois;

    @FXML
    private Button menuBtn;
    @FXML
    private Button menuBackBtn;
    @FXML
    private Pane slider;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label balance;
    @FXML
    private Button HomeBtn;
    @FXML
    private Button friend;
    @FXML
    private Button wishListFriends;
    @FXML
    private Button add1;
    @FXML
    private Button logout;
    @FXML
    private Button addfriend;
    @FXML
    private Button chrgBalance;
    @FXML
    private Button about;
    @FXML
    private TextField balanceAmount;
    @FXML
    private Label balance1;
    private String usernameString;
    private int balance2;
    private InputStream inputStream;
    @FXML
    private Button addBalance;
    @FXML
    private TextField balanceAmount1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
              usernameString = UserData.getInstance().getUsername();
              usernameLabel.setText(usernameString);
              balance2=UserData.getInstance().getBalance();
              balance1.setText(String.valueOf(balance2));
              balance.setText(String.valueOf(balance2));
              //-------------------------------------------------------------
            try{  s = new Socket("127.0.0.1",5005);
              oos = new ObjectOutputStream(s.getOutputStream());
              ois = new ObjectInputStream(s.getInputStream());
              inputStream = s.getInputStream();
              // TODO
          } catch (IOException ex) {
              Logger.getLogger(RechargeBalanceController.class.getName()).log(Level.SEVERE, null, ex);
          }
            chrgBalance.setOnMouseClicked(event -> {

             try {
            Parent table = FXMLLoader.load(getClass().getResource("rechargeBalance.fxml"));
            Scene scene =new Scene(table);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Faild to load the scene" +"\n"+"Error Message: "+ex.getMessage());
        } 
            
            
            });
            
            
            
            
            slider.setTranslateX(-500);
        
 menuBtn.setOnMouseClicked(event -> {

            
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                
                slide.setToX(0);
                slide.play();
                
                slider.setTranslateX(-500);
                
                slide.setOnFinished((ActionEvent e)->{
                    menuBtn.setVisible(false);
                    menuBackBtn.setVisible(true);
                    
                });  
            
            
            });
 
  menuBackBtn.setOnMouseClicked(event -> {

            
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                
                slide.setToX(-500);
                slide.play();
                
                slider.setTranslateX(0);
                
                slide.setOnFinished((ActionEvent e)->{
                    menuBtn.setVisible(true);
                    menuBackBtn.setVisible(false);
                    
                });  
            
            
            });
    
    }    

    @FXML
    private void addBalance(ActionEvent event) {
        if (balanceAmount != null && balanceAmount.getText().matches("\\d+")) {
            if(Integer.parseInt(balanceAmount.getText()) > 0){
                try {
                    int newBalance =  Integer.parseInt(balanceAmount.getText());
                    
                    OTD requestUpdate = new OTD_Request(usernameString,newBalance+balance2,999);
                    oos.writeObject(requestUpdate);
                   OTD_Status status= (OTD_Status) ois.readObject();
                   JOptionPane.showMessageDialog(null,status.getMessage());
                    balance2=UserData.getInstance().getBalance();
                    balance1.setText(String.valueOf(balance2));
                    balance.setText(String.valueOf(balance2));
                   

                } catch (IOException ex) {
                    Logger.getLogger(RechargeBalanceController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RechargeBalanceController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    // The balanceAmount is not null, is a pure integer, and is greater than zero
    // Your code goes here
} else {
    // The balanceAmount is null, not a pure integer, or less than or equal to zero
    // Handle the error case
     JOptionPane.showMessageDialog(null,"Please make sure that is integer and greater than zero");

    
}

        
        
    }

    @FXML
    private void Home(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
             Parent table = loader.load();
             
             
             HomePageController homecontroller = loader.getController();
             //homecontroller.setData("jane_smith");
             Scene scene =new Scene(table);
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
             window.setScene(scene);
             window.show();
         } catch (IOException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void friend(ActionEvent event) {
                 try {
            Parent table = FXMLLoader.load(getClass().getResource("friends.fxml"));
            Scene scene =new Scene(table);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Faild to load the scene" +"\n"+"Error Message: "+ex.getMessage());
        }
    }

    @FXML
    private void wishListFriends(ActionEvent event) {
         try {
             Parent table = FXMLLoader.load(getClass().getResource("friendsWishes.fxml"));
             Scene scene =new Scene(table);
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
             window.setScene(scene);
             window.show();
         } catch (IOException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void add_item(ActionEvent event) {
         try {
            Parent table = FXMLLoader.load(getClass().getResource("AddItem.fxml"));
             
                 Scene scene =new Scene(table);
                 Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                 window.setScene(scene);
                 window.show();
             
             
             
             
         } catch (IOException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void logOut(ActionEvent event) {
        try {
            Parent table = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene =new Scene(table);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            s.close();
            oos.close();
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Faild to load the scene" +"\n"+"Error Message: "+ex.getMessage());
        }
    }

    @FXML
    private void addfriend(ActionEvent event) {
           try {
            Parent table = FXMLLoader.load(getClass().getResource("addFriends.fxml"));
            Scene scene =new Scene(table);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Faild to load the scene" +"\n"+"Error Message: "+ex.getMessage());
        }
    }
    
    
    @FXML
    private void about(ActionEvent event) {
           try {
            Parent table = FXMLLoader.load(getClass().getResource("about.fxml"));
            Scene scene =new Scene(table);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Faild to load the scene" +"\n"+"Error Message: "+ex.getMessage());
        }
    }
    
}
