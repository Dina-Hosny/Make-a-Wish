/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package client;

import Notification.NotificationService;
import OTD.OTD;
import OTD.OTD_Request;
import OTD.OTD_Status;
import OTD.contr;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class FriendsWishesController implements Initializable {

   @FXML
    private TableView<wishListView> wishListTable;
    @FXML
    private TableColumn<wishListView, Image> image;
    @FXML
    private TableColumn<wishListView, String> item_name;
    @FXML
    private TableColumn<wishListView, Integer> price;
    @FXML
    private TableColumn<wishListView, Integer> remained;
    @FXML
    private TableColumn<wishListView, Date> date;
    @FXML
    private TableView<friendsView> friendsView;
    @FXML
    private TableColumn<friendsView, Date> date2;
    @FXML
    private TableColumn<wishListView, String> cat;
    @FXML
    private TableColumn<friendsView, String> username;
    @FXML
    private TableColumn<friendsView, String> email;
    @FXML
    private Button show;
    Socket s;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String  usernameString ;
    String friendName;
    String itemName;
              
    ObservableList<friendsView> data2;
    ObservableList<wishListView> data;
    InputStream inputStream;
    @FXML
    private TextField amount;
    
    
    /////new GUI/////////
    
    @FXML
    private Pane conPane2;

    @FXML
    private Button senBTN;
    
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
    private Pane contPane;

    @FXML
    private Label ContLBL;

    @FXML
    private Label priceLBL;

    @FXML
    private Button cancelBTN;
    private int balance1;
    @FXML
    private Button HomeBtn;
    @FXML
    private Button addfriend;
    @FXML
    private Button chrgBalance;
    @FXML
    private Button about;

    
    
     
    
       
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
        
           
           image.setCellValueFactory(new PropertyValueFactory<>("image"));
           item_name.setCellValueFactory(new PropertyValueFactory<>("item_name"));
           price.setCellValueFactory(new PropertyValueFactory<>("price"));
           remained.setCellValueFactory(new PropertyValueFactory<>("remained"));
           date.setCellValueFactory(new PropertyValueFactory<>("date"));
           cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
           
           //---------------------------------------------------------------
           s = new Socket("127.0.0.1",5005);
           oos = new ObjectOutputStream(s.getOutputStream());
           ois = new ObjectInputStream(s.getInputStream());
           //-----------------------------------------------
    
            date2.setCellValueFactory(new PropertyValueFactory<>("date"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            
            
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



           
           // TODO
       } catch (IOException ex) {
           Logger.getLogger(FriendsWishesController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }  catch (IOException ex) {
           Logger.getLogger(FriendsWishesController.class.getName()).log(Level.SEVERE, null, ex);
       }
    
       
wishListTable.setVisible(false);
conPane2.setVisible(false);
senBTN.setVisible(false);
contPane.setVisible(false);
ContLBL.setVisible(false);
priceLBL.setVisible(false);
cancelBTN.setVisible(false);
amount.setVisible(false);

cancelBTN.setOnMouseClicked(event -> {
     
wishListTable.setVisible(false);
conPane2.setVisible(false);
senBTN.setVisible(false);
contPane.setVisible(false);
ContLBL.setVisible(false);
priceLBL.setVisible(false);
cancelBTN.setVisible(false);
amount.setVisible(false);
        
     
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
private void send(ActionEvent event) {
    int amountValue = 0;
    try {
        // Get the text from the amount textField
        String amountText = amount.getText();

        // Parse the text into an integer
        amountValue = Integer.parseInt(amountText);
        UserData.getInstance().getBalance();

        // Check if the parsed integer value is greater than or equal to zero
        if (amountValue < 0) {
            JOptionPane.showMessageDialog(null,"Invalid input. Please enter a non-negative integer value.");
            amount.setText("");
            amountValue=0;
             // Return or throw an exception based on your program's requirements
        }
    } catch (NumberFormatException e) {
        // Handle the case where the textField does not contain a valid integer
        System.out.println("Invalid input. Please enter a valid integer value.");
        amount.setText("");
        amountValue=0;
    }

    itemName = wishListTable.getSelectionModel().getSelectedItem().getItem_name();

    if (itemName == null | amountValue==0) {
        JOptionPane.showMessageDialog(null, "Please make sure that you select an item and amount is greater than zero.");
    } else {
        OTD contr = new contr(usernameString, friendName, itemName, amountValue, 900);
        try {
            oos.writeObject(contr);
        } catch (IOException ex) {
            Logger.getLogger(FriendsWishesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            OTD_Status r = (OTD_Status) ois.readObject();
            JOptionPane.showMessageDialog(null, r.getMessage());
            show.fire();

        } catch (IOException ex) {
            Logger.getLogger(FriendsWishesController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FriendsWishesController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    balance1=UserData.getInstance().getBalance();
            balance.setText(String.valueOf(balance1));
}


    @FXML
    private void show(ActionEvent event) {
       try {
           
           
            wishListTable.setVisible(true);
            conPane2.setVisible(true);
            senBTN.setVisible(true);
            contPane.setVisible(true);
            ContLBL.setVisible(true);
            priceLBL.setVisible(true);
            cancelBTN.setVisible(true);
            amount.setVisible(true);
           
           
            friendName= friendsView.getSelectionModel().getSelectedItem().getUsername();
           if(friendName!=null){
           OTD request = new OTD_Request(friendName,11);
           oos.writeObject(request);
            inputStream = s.getInputStream();
           BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
           
           
           
           
           String json = in.readLine();
           System.out.println(json);
           ObjectMapper mapper = new ObjectMapper();
           List<Map<String, Object>> rows = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
           ObservableList<wishListView> wlvList = FXCollections.observableArrayList();
           
           for (Map<String, Object> row : rows) {
               String imageString = (String) row.get("image");
               byte[] image_1 = Base64.getDecoder().decode(imageString);
               byte[] imageData = image_1;
               Image image = new Image(new ByteArrayInputStream(imageData));
               ImageView image_11=new ImageView(image);
               image_11.setFitHeight(100);
               image_11.setFitWidth(100);
               System.out.println(image);
               String item = (String) row.get("item_name");
               int price = (int) row.get("price");
               int remained = (int) row.get("remained");
               Date date = new Date((long) row.get("date"));
               String cat = (String) row.get("cat");
               wishListView  wlv = new wishListView(image_11, item, price, remained, date,cat);
               wlvList.add(wlv);
           }
           System.out.println(wlvList.toString());
           
           wishListTable.setItems(wlvList);}
           
           
           
            
           
       } catch (IOException ex) {
           Logger.getLogger(FriendsWishesController.class.getName()).log(Level.SEVERE, null, ex);
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
