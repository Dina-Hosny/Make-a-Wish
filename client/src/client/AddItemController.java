/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package client;

import OTD.OTD;
import OTD.OTD_Request;
import OTD.OTD_Status;
import OTD.OTD_insert_2;
import OTD.OTD_item_insert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.UserData;
import model.wishListView;

/**
 * FXML Controller class
 *
 * @author 20101
 */
public class AddItemController implements Initializable {

    @FXML
    private TextField itemName;
    @FXML
    private TextField price;
    @FXML
    private Button selectPhoto;
    @FXML
    private Button save;
    Image image;
    Socket s;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    @FXML
    private ImageView uploadedImage;
    @FXML
    private TableView<wishListView> item;
    @FXML
    private TableColumn<wishListView, String> itemCol;
    @FXML
    private TableColumn<wishListView, Image> imageCol;
    @FXML
    private TableColumn<wishListView, String> catList;
    @FXML
    private TableColumn<wishListView, Integer> priceCol;
    
    OTD otd;
        ObservableList<wishListView> data;

    @FXML
    private CheckBox iWill;
    InputStream inputStream;
    
    
    
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
    private Pane uploadPane;

    
    @FXML
    private Label itemnameLBL;

    @FXML
    private Label itempriceLBL;

    @FXML
    private Label itemcatLBL;

   

    @FXML
    private Label itemphotoLBL;

    @FXML
    private ChoiceBox<String> itemCatChoose;
    
      String usernameString;
      int balance1;
    @FXML
    private Button save1;
    @FXML
    private Button uploadextraBTN;
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
            ObservableList<String> dataCat = FXCollections.observableArrayList(
            "Electronics", "Home Appliances", "Kitchen Appliances", "Clothes","other");
            itemCatChoose.setItems(dataCat);
            itemCatChoose.setValue("Electronics");
            

          
            
            imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
            itemCol.setCellValueFactory(new PropertyValueFactory<>("item_name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            catList.setCellValueFactory(new PropertyValueFactory<>("cat"));
            s = new Socket("127.0.0.1",5005);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            inputStream = s.getInputStream();
            
            OTD request = new OTD_Request("root",11);
            
            oos.writeObject(request);
             
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
    String cat = (String) row.get("cat");
     wishListView  wlv = new wishListView(image_11, item, price,cat);
    wlvList.add(wlv);
}
System.out.println(wlvList.toString());

item.setItems(wlvList);
        } catch (IOException ex) {
            Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
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
        
      
uploadPane.setVisible(false);
itemnameLBL.setVisible(false);
itempriceLBL.setVisible(false);
itemcatLBL.setVisible(false);
itemphotoLBL.setVisible(false);

itemName.setVisible(false);
price.setVisible(false);
selectPhoto.setVisible(false);
save.setVisible(false);

uploadedImage.setVisible(false);

iWill.setVisible(true);

itemCatChoose.setVisible(false);




 
        
        
        
    }    

    @FXML
    private void selectPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(save.getScene().getWindow());
            if (selectedFile != null) {
                 image = new Image(selectedFile.toURI().toString());
                
            }
    }

    @FXML
    private void save(ActionEvent event) {
        
          
          otd = new OTD_item_insert(21,convert(image),itemName.getText(),Integer.parseInt(price.getText()),UserData.getInstance().getUsername(),itemCatChoose.getValue(),Integer.parseInt(price.getText()));
          if (otd==null){
              JOptionPane.showMessageDialog(null,"Please make sure that the item you inserted is corrected");
          }else{
        try {                      
            oos.writeObject(otd);
            OTD_Status otd_status=(OTD_Status) ois.readObject();
            if (otd_status.isStatus()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    Parent table = loader.load();
                    

                    HomePageController homecontroller = loader.getController();
                    //homecontroller.setData("jane_smith");
                    Scene scene =new Scene(table);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
            }else{
         JOptionPane.showMessageDialog(null,"The item insertion is failed,Please try again later");

        }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Please make sure that the item you inserted is corrected");
        } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null,"Please make sure that the item you inserted is corrected");        }
    }}
    private Blob convert(Image image) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
        String format = "png";
        if (image instanceof javafx.scene.image.WritableImage) {
            javafx.scene.image.WritableImage wi = (javafx.scene.image.WritableImage) image;
            format = wi.getPixelReader().getPixelFormat().getType().toString();
            format = format.substring(format.indexOf(".") + 1);
        }
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), format, baos);
    } catch (IOException e) {
        e.printStackTrace();
    }
    byte[] imageBytes = baos.toByteArray();

    Blob blob = null;
    try {
        blob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
        return blob;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


    @FXML
    private void iWill(ActionEvent event) {
        if(iWill.isSelected()){
             uploadPane.setVisible(true);
            itemnameLBL.setVisible(true);
            itempriceLBL.setVisible(true);
            itemcatLBL.setVisible(true);
            itemphotoLBL.setVisible(true);

            itemName.setVisible(true);
            price.setVisible(true);
            selectPhoto.setVisible(true);
            save.setVisible(true);

            uploadedImage.setVisible(true);

itemCatChoose.setVisible(true);}
        else{
             uploadPane.setVisible(false);
             itemnameLBL.setVisible(false);
             itempriceLBL.setVisible(false);
              itemcatLBL.setVisible(false);
itemphotoLBL.setVisible(false);

itemName.setVisible(false);
price.setVisible(false);
selectPhoto.setVisible(false);
save.setVisible(false);

uploadedImage.setVisible(false);

itemCatChoose.setVisible(false);
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

    private void option2(ActionEvent event) {
        uploadPane.setVisible(true);
itemnameLBL.setVisible(true);
itempriceLBL.setVisible(true);
itemcatLBL.setVisible(true);
itemphotoLBL.setVisible(true);

itemName.setVisible(true);
price.setVisible(true);
selectPhoto.setVisible(true);
save.setVisible(true);

uploadedImage.setVisible(true);

iWill.setVisible(true);
itemCatChoose.setVisible(true);
        
    }

    private void pre(ActionEvent event) {
        save.setVisible(true);
    }
    

    private void newItem(ActionEvent event) {
                uploadPane.setVisible(true);
itemnameLBL.setVisible(true);
itempriceLBL.setVisible(true);
itemcatLBL.setVisible(true);
itemphotoLBL.setVisible(true);

itemName.setVisible(true);
price.setVisible(true);
selectPhoto.setVisible(true);
save.setVisible(true);


uploadedImage.setVisible(true);

itemCatChoose.setVisible(true);
    }

    @FXML
    private void save1(ActionEvent event) {
        data = item.getItems();
        String item1 =item.getSelectionModel().getSelectedItem().getItem_name();
        int price =item.getSelectionModel().getSelectedItem().getPrice();
        int remained =item.getSelectionModel().getSelectedItem().getRemained();
        String cat=item.getSelectionModel().getSelectedItem().getCat();
        otd = new OTD_insert_2(22,item1,price,UserData.getInstance().getUsername(),cat);
        if (otd==null){
              JOptionPane.showMessageDialog(null,"Please make sure that the item you inserted is corrected");
          }else{
         try {                      
            oos.writeObject(otd);
            OTD_Status otd_status=(OTD_Status) ois.readObject();
            if (otd_status.isStatus()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    Parent table = loader.load();
                    

                    HomePageController homecontroller = loader.getController();
                    //homecontroller.setData("jane_smith");
                    Scene scene =new Scene(table);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
            }else{
         JOptionPane.showMessageDialog(null,"The item insertion is failed,Please try again later");

        }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Please make sure that the item you inserted is corrected");
        } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null,"Please make sure that the item you inserted is corrected");        }
    }}
    
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
    
    
    
    

