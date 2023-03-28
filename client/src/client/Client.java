/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import client.HomeController;
import javax.swing.JOptionPane;

/**
 *
 * @author 20101
 */
public class Client extends Application {
    
   @Override
public void start(Stage stage)  {
    try {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));    
        Parent homeRoot = homeLoader.load();
        Scene homeScene = new Scene(homeRoot);
        stage.setScene(homeScene);
        stage.show();
   
    }  catch (IOException ex) {
     JOptionPane.showMessageDialog(null,"Faild to start the I WISH application" +"\n"+"Error Message: "+ex.getMessage());

       }
}
}
