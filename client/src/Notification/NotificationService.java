package Notification;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import OTD.OTD_Notif;
import OTD.OTD;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.UserData;


public class NotificationService extends Service<Void> {
    private boolean running = false;
    private Stage notificationStage;
    boolean flag=true;
    private Socket s;
     private ObjectInputStream ois;
     private ObjectOutputStream oos;
     InputStream inputStream;
    public NotificationService() {
        try {
            s = new Socket("127.0.0.1",5005);
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            inputStream = s.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(NotificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                        OTD n = new OTD_Notif(300,UserData.getInstance().getUsername());
                    oos.writeObject(n);
                    OTD_Notif notification=(OTD_Notif) ois.readObject();
                    if  (notification == null){
                    }else{
                        
                        
                        for (List<Object> innerList : notification.getList()) {
    // Iterate through each inner list
                 
        // Access the second element of each inner list
                    String message = (String) innerList.get(1);
                    
                    if(flag){
                    updateUI(message);
                    }
        // Do something with the second element...
    
                    }
                        
                    }
                    
                    // Simulate delay for demo purposes
                    Thread.sleep(3000);
                    
                    // Show notification
                    
                }
            }
        };
    }

    public void startThread() {
        running = true;
        if (!isRunning()) {
            start();
        }
    }

    public void stopThread() {
        running = false;
    }

    private String requestUpdatesFromServer() {
        // TODO: implement requesting updates from the server
        return "Sample update from server";
    }

  private void updateUI(String updates) {
    // update the UI with the received updates
    Platform.runLater(() -> {
        flag=false;
        notificationStage=new Stage();
        notificationStage.initModality(Modality.APPLICATION_MODAL);


        
      
                
        
        
        // Add notification text
        Text text = new Text(updates);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        
        // Add OK button
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> notificationStage.close());
        okButton.setDefaultButton(true);
        okButton.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-font-size: 16;");
        
        // Add visual elements to a VBox
        VBox vbox = new VBox( text, okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));
        
        Scene scene = new Scene(vbox);
        notificationStage.setScene(scene);
        
        // Customize stage appearance
        notificationStage.setTitle("Notification");
        notificationStage.setResizable(false);
        notificationStage.initStyle(StageStyle.UTILITY);
        notificationStage.centerOnScreen();
        
        notificationStage.showAndWait();
        flag=true;
    });
}
}
