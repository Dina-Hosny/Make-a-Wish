/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package client;

import Notification.NotificationService;
import OTD.OTD;
import OTD.OTD_REG;
import OTD.OTD_Remove;
import OTD.OTD_Remove1;
import OTD.OTD_Request;
import OTD.OTD_Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Blob;
import java.sql.Date;
//import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import model.UserData;
import model.wishListView;
import readJson.readJson;
import java.util.Base64;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author 20101
 */
public class HomePageController implements Initializable {
     private String usernameString;


    private Label username;
    
    @FXML
    private Label usernameLabel;
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
    ObservableList<wishListView> data;
     Socket s;
      private NotificationService notificationService;
        ObjectOutputStream oos;
        ObjectInputStream ois;
    @FXML
    private TableColumn<wishListView, String> cat;
    @FXML
    private Button friend;
    @FXML
    private Button wishListFriends;
    @FXML
    private TableView<wishListView> wishListTable1;
    @FXML
    private TableColumn<wishListView, Image> image1;
    @FXML
    private TableColumn<wishListView, Date> date1;
    @FXML
    private TableColumn<wishListView, String> item_name1;
    @FXML
    private TableColumn<wishListView, Integer> price1;
    @FXML
    private Button collect;
    @FXML
    private Label balance;
    
    
    /////new GUI/////
    
    @FXML
    private Pane slider;
    
    @FXML
    private Button menuBtn;
    
    @FXML
    private Button menuBackBtn;
     
    @FXML
    private Button HomeBtn;
     
    @FXML
    private Button add1;
    
    @FXML
    private Button logout;
    
    @FXML
    private Label collectedlbl;
    
    @FXML
    private Button showCollected;
    
    @FXML
    private Pane collectPane;
    
    @FXML
    private Button hideCollected;
    @FXML
    private Button addfriend;
    @FXML
    private Button chrgBalance;
    @FXML
    private Button about;
    @FXML
    private Button remove;
    @FXML
    private Button refresh;
    @FXML
    private Button add;
    int balance1;
    
    
    
    
    
     
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameString = UserData.getInstance().getUsername();  
        usernameLabel.setText(usernameString);
        balance1=UserData.getInstance().getBalance();
        balance.setText(String.valueOf(balance1));
        notificationService = new NotificationService();
        notificationService.startThread();
        
       
   
    try{
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        item_name.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        remained.setCellValueFactory(new PropertyValueFactory<>("remained"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        cat.setCellValueFactory(new PropertyValueFactory<>("cat"));

                
        
        s = new Socket("127.0.0.1",5005);
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        
        OTD request = new OTD_Request(usernameString,11);
        
        oos.writeObject(request);
        InputStream inputStream = s.getInputStream();
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

wishListTable.setItems(wlvList);

}



catch (IOException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,"Faild to connect with the database" +"\n"+"Error Message: "+ex.getMessage());
         }
        //---------------------------------------------------------------------------
        try{
        image1.setCellValueFactory(new PropertyValueFactory<>("image"));
        item_name1.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        price1.setCellValueFactory(new PropertyValueFactory<>("price"));
        date1.setCellValueFactory(new PropertyValueFactory<>("date"));
        

                
        
        s = new Socket("127.0.0.1",5005);
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        
        OTD request = new OTD_Request(usernameString,111);
        
        oos.writeObject(request);
        InputStream inputStream = s.getInputStream();
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
    
    Date date = new Date((long) row.get("date"));
    
     wishListView  wlv = new wishListView(image_11, item, price,date);
    wlvList.add(wlv);
}

wishListTable1.setItems(wlvList);

}
        


catch (IOException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,"Faild to connect with the database" +"\n"+"Error Message: "+ex.getMessage());
         }
        

            

      
      ////// new GUI//////
      
 slider.setTranslateX(-500);
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
  
  
  wishListTable1.setVisible(false);
  collect.setVisible(false);
  collectedlbl.setVisible(false);
  collectPane.setVisible(false);
  hideCollected.setVisible(false);
    
  
 showCollected.setOnMouseClicked(event -> {
     
  wishListTable1.setVisible(true);
  collect.setVisible(true);
  collectedlbl.setVisible(true);
  collectPane.setVisible(true);
  showCollected.setVisible(false);
  hideCollected.setVisible(true);
  
     
     
     
 });
 
 hideCollected.setOnMouseClicked(event -> {
     
  wishListTable1.setVisible(false);
  collect.setVisible(false);
  collectedlbl.setVisible(false);
  collectPane.setVisible(false);
  hideCollected.setVisible(false);
  showCollected.setVisible(true);
  
     
     
     
 });
 
 
 }
    
  
       
    
    public void setData(String username){
        this.usernameString = username ;
        System.out.println(usernameString);
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
    private void remove(ActionEvent event) {
        data = wishListTable.getItems();
        wishListView wlv = wishListTable.getSelectionModel().getSelectedItem();
        String item =wishListTable.getSelectionModel().getSelectedItem().getItem_name();
        int price =wishListTable.getSelectionModel().getSelectedItem().getPrice();
        int remained =wishListTable.getSelectionModel().getSelectedItem().getRemained();
        
        
        
        
        
        
        
        if(price!=remained){
           

// create an alert dialog with the specified title, header, and message
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
            OTD_Remove remove =new OTD_Remove(12,usernameString,item);
             OTD remove_otd = (OTD) remove;
         try {
             
             oos.writeObject(remove_otd);
             OTD_Status otd=(OTD_Status) ois.readObject();
             if(otd.isStatus()){
                         data.remove(wlv);

             }else{
              System.out.println("Please check the item ");
             }
         } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    // if the user clicked "No", return false
    else if (response == noButton) {
        
    }
});

        
        }else{
          OTD_Remove remove =new OTD_Remove(12,usernameString,item);
        OTD remove_otd = (OTD) remove;
         try {
             
             oos.writeObject(remove_otd);
             OTD_Status otd=(OTD_Status) ois.readObject();
             if(otd.isStatus()){
                         data.remove(wlv);

             }else{
              System.out.println("Please check the item ");
             }
         } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
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
    private void collect(ActionEvent event) {
         data = wishListTable1.getItems();
        wishListView wlv = wishListTable1.getSelectionModel().getSelectedItem();
        String item =wishListTable1.getSelectionModel().getSelectedItem().getItem_name();
        int price =wishListTable1.getSelectionModel().getSelectedItem().getPrice();
         OTD_Remove1 remove =new OTD_Remove1(121,usernameString,item);
        OTD remove_otd = (OTD) remove;
         try {
             
             oos.writeObject(remove_otd);
             OTD_Status otd=(OTD_Status) ois.readObject();
             if(otd.isStatus()){
                         data.remove(wlv);

             }else{
              System.out.println("Please check the item ");
             }
         } catch (IOException | ClassNotFoundException ex) {
             Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }

    
    @FXML
    private void refresh(ActionEvent event) {
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
    private void Home(ActionEvent event) {
         try {
            Parent table = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
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
   
    

