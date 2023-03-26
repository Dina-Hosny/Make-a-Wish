/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package client;

import OTD.OTD;
import OTD.OTD_Remove;
import OTD.OTD_Request;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UserData;
import model.friendsView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.UserData;
import model.friendsView;
import model.wishListView;


/**
 * FXML Controller class
 *
 * @author 20101
 */
public class FriendsController implements Initializable {

    @FXML
    private TableView<friendsView> friendsView;
    @FXML
    private TableColumn<friendsView, Date> date;
    @FXML
    private TableColumn<friendsView, String> username;
    @FXML
    private TableColumn<friendsView, String> email;
    @FXML
    private Button remove;
    @FXML
    private TableView<friendsView> pendingView;
    @FXML
    private TableColumn<friendsView, Date> pDate;
    @FXML
    private TableColumn<friendsView, String> pUsername;
    @FXML
    private TableColumn<friendsView, String> pEmail;
    @FXML
    private Button accept;
    @FXML
    private Button Decline;
    
    Socket s;
            ObjectOutputStream oos;
            ObjectInputStream ois;
              String  usernameString ;
              
              ObservableList<friendsView> data;
    private TableView<friendsView> sView;
    private TableColumn<friendsView, String> sName;
    private TableColumn<friendsView, String> Semail;
    private TextField userSearch;
    private Button search;
    InputStream inputStream;
    
    
    
    /////new GUI/////////
    
   @FXML
    private Pane slider;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label balance;


    @FXML
    private Button friend;

    @FXML
    private Button wishListFriends;

    @FXML
    private Button add1;

    @FXML
    private Button logout;

    @FXML
    private Button menuBtn;

    @FXML
    private Button menuBackBtn;
    
    
    @FXML
    private Label addfroendLBL;

    @FXML
    private Button addfriendBTN;

    
    @FXML
    private Pane askaddpane;
    private int balance1;
    @FXML
    private Button HomeBtn;
    @FXML
    private Button addfriend;
    @FXML
    private Button chrgBalance;
    @FXML
    private Button about;
     
     
    
              
                
  @FXML
    private void remove(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you really want to perform this action?");

// add buttons for "Yes" and "No"
    ButtonType yesButton = new ButtonType("Yes");
    ButtonType noButton = new ButtonType("No");
    alert.getButtonTypes().setAll(yesButton, noButton);

// show the dialog and wait for the user's response
    alert.showAndWait().ifPresent(response -> {
        
    // if the user clicked "Yes", return true
    if (response == yesButton) {
       String friendName= friendsView.getSelectionModel().getSelectedItem().getUsername();
       OTD deleteFriend = new OTD_Remove(usernameString,friendName,50);
      try{ oos.writeObject(deleteFriend);
        data = friendsView.getItems();
        friendsView wlv = friendsView.getSelectionModel().getSelectedItem();
        data.remove(wlv);
      
      }
      catch (IOException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE,null,ex);
   
    
            }
    }
    });
            }

    @FXML
    private void accept(ActionEvent event) {
        try{String friendName= pendingView.getSelectionModel().getSelectedItem().getUsername();
       OTD deleteFriend = new OTD_Remove(usernameString,friendName,55);
       oos.writeObject(deleteFriend);
        
        
        data = friendsView.getItems();
        friendsView wlv = pendingView.getSelectionModel().getSelectedItem();
        wlv.setDate(Date.valueOf(LocalDate.now()));
        
        data.add(wlv);
        data=pendingView.getItems();
        wlv = pendingView.getSelectionModel().getSelectedItem();
        data.remove(wlv);
        
       
        }
       catch (IOException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE,null,ex);
   
    
            }
    }

    @FXML
    private void decline(ActionEvent event) {
        try{String friendName= pendingView.getSelectionModel().getSelectedItem().getUsername();
       OTD deleteFriend = new OTD_Remove(usernameString,66,friendName);
       oos.writeObject(deleteFriend);
       
        data=pendingView.getItems();
       friendsView wlv = pendingView.getSelectionModel().getSelectedItem();
        data.remove(wlv);
        
       
        }
       catch (IOException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE,null,ex);
   
    
            }
    }

      
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            usernameString = UserData.getInstance().getUsername();  
            usernameLabel.setText(usernameString);
            balance1=UserData.getInstance().getBalance();
            balance.setText(String.valueOf(balance1));
        
          
            
            
            
            s = new Socket("127.0.0.1",5005);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());           
            // TODO
            
            //-------------------------------------------------
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            pDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            pUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            pEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            
            
            //--------------------------------------------
            OTD request = new OTD_Request(usernameString,15);
            
         
        
            oos.writeObject(request);
             inputStream = s.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String json = in.readLine();
            
            
            
            
            
            
            
            //---------------------------------------------
            ObjectMapper mapper = new ObjectMapper();
            try{
            List<Map<String, Object>> rows = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
            ObservableList<friendsView> fr = FXCollections.observableArrayList();
            
            for (Map<String, Object> row : rows) {
                
                
                
                
                String username =  (String) row.get("friendname");
                String email = (String) row.get("email");
                Date date = new Date((long) row.get("date"));
                
                friendsView fv = new friendsView(date, username, email);
                System.out.println(fv.getUsername());
                fr.add(fv);
            }
            friendsView.setItems(fr);
            System.out.println("Here2");
            }catch(MismatchedInputException e){
            }
        
            
            
            OTD request1 = new OTD_Request(usernameString,16);
            System.out.println("Here3");
            oos.writeObject(request1);
             in = new BufferedReader(new InputStreamReader(inputStream));
            
            System.out.println("Here4");
            
            
            

            json = in.readLine();
            System.out.println("Pending"+json);
            mapper = new ObjectMapper();
            try{
            List<Map<String, Object>> rows1 = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
            ObservableList<friendsView> fr1 = FXCollections.observableArrayList();
            for (Map<String, Object> row : rows1) {
                
                
                String username1 =  (String) row.get("fromuser");
                String email = (String) row.get("email");
                Date date = new Date((long) row.get("date"));
                
                friendsView fr2 = new friendsView(date, username1, email);
                System.out.println(fr2.getUsername());
                fr1.add(fr2);
            }
            pendingView.setItems(fr1);}catch(MismatchedInputException e){}
        } catch (IOException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ///// new GUI //////       
        
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
  
  
  
/*friendnameLBL.setVisible(false);
cancelrequest.setVisible(false);
sView.setVisible(false);
send.setVisible(false);
userSearch.setVisible(false);
search.setVisible(false);
friendpane.setVisible(false);

askaddpane.setVisible(true);
addfroendLBL.setVisible(true);
addfriendBTN.setVisible(true);

*/


/*addfriendBTN.setOnMouseClicked(event -> {
     

addfriendBTN.setVisible(false);
askaddpane.setVisible(false);
addfroendLBL.setVisible(false);

friendnameLBL.setVisible(true);
cancelrequest.setVisible(true);
sView.setVisible(true);
send.setVisible(true);
userSearch.setVisible(true);
search.setVisible(true);
friendpane.setVisible(true);

     
     
 });*/

/*cancelrequest.setOnMouseClicked(event -> {
 
friendnameLBL.setVisible(false);
cancelrequest.setVisible(false);
sView.setVisible(false);
send.setVisible(false);
userSearch.setVisible(false);
search.setVisible(false);
friendpane.setVisible(false);    


    
addfriendBTN.setVisible(true);
askaddpane.setVisible(true);
addfroendLBL.setVisible(true);


 
     
 });

*/
      
        
        } 


    private void send(ActionEvent event) {
        try {
            data = sView.getItems();
            friendsView wlv = sView.getSelectionModel().getSelectedItem();
            
            OTD otd = new OTD_Request(wlv.getUsername(),usernameString,150);
            if (otd!=null){
            
            oos.writeObject(otd);
            }
            search.fire();
        } catch (IOException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void search(ActionEvent event) {
        try {
            OTD request =new OTD_Request(userSearch.getText(),usernameString,100);
            oos.writeObject(request);
             BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            
            String json = in.readLine();
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            if("".equals(json)){
            sView.setItems(null);
            }else{
            List<Map<String, Object>> rows10 = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
            ObservableList<friendsView> fr10 = FXCollections.observableArrayList();
            for (Map<String, Object> row : rows10) {
               
                
                String username1 =  (String) row.get("username");
                String email = (String) row.get("email");
                
                
                friendsView fr3 = new friendsView(username1, email);
                System.out.println(fr3.getUsername());
                fr10.add(fr3);
            }
            sView.setItems(fr10);
            }
        } catch (IOException ex) {
            Logger.getLogger(FriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void home(ActionEvent event) {
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

       
    
    

   
