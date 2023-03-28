/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package readJson;

import OTD.OTD;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import OTD.OTD;
import OTD.OTD_REG;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author 20101
 */
public class readJson {
    public static List<Map<String, Object>> readData(Socket s, int id){
        ObjectOutputStream oos = null;
        try {
                    oos = new ObjectOutputStream(s.getOutputStream());
        OTD request = new OTD(11);    
        oos.writeObject(request);
        InputStream inputStream = s.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String json = in.readLine();
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> rows =mapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});

            return rows;
        } catch (IOException ex) {
            Logger.getLogger(readJson.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(readJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
