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

/**
 *
 * @author 20101
 */
public class Client extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //Home
        
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        
        Parent homeRoot = homeLoader.load();
        
        Scene homeScene = new Scene(homeRoot);
        
        stage = new Stage();
        
        stage.setScene(homeScene);
        stage.show();
       
       
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
