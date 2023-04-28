/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import users.entity.User;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class DashAdminController implements Initializable {

    @FXML
    private ListView<MyData> lv;
MyData v ; 
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane pane;
    
    public static int idprof; 
    @FXML
    private TextField searchfield;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        /**
         * *****************************************************************
         */
        List<User> lv2 = new ArrayList<>();
       user_crud uc = new user_crud() ; 
        lv2.addAll(uc.afficheruser());

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++)           
        {
                lmd.add(new MyData(lv2.get(i).getName(),lv2.get(i).getFirstname(),lv2.get(i).getImage(),lv2.get(i).getEmail(),lv2.get(i).getId()));
            System.out.println(lv2.get(i).getId()) ;}
        

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });   
    lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyData>() {
            @Override
            public void changed(ObservableValue<? extends MyData> observable, MyData oldValue, MyData newValue) {
                v = lv.getSelectionModel().getSelectedItem();
               idprof=v.getId();
                System.out.println(v.getEmail());
                if (v != null) {
               try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("profilAdmin.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
            }

        });
    
        searchfield.setPromptText("Filter");
               

        // Create a FilteredList that will hold the filtered data
        FilteredList<MyData> filteredData = new FilteredList<>(data);

        // Bind the FilteredList to the ListView and the TextField
        lv.setItems(filteredData);
        searchfield.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(item -> newValue == null || newValue.isEmpty() ||
                        item.getName().toLowerCase().contains(newValue.toLowerCase())));    
    }    

    @FXML
    private void back_btn(ActionEvent event) {
      try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../profil/profil.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(DashAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
