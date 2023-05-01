/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import users.entity.User;
import users.gui.login.LoginController;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class ProfilAdminController implements Initializable {

    @FXML
    private Label firstname_id;
    @FXML
    private Label email_id;
    @FXML
    private Label lastname_id;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button showid;
    @FXML
    private Button showid1;
    @FXML
    private Circle c2;
    @FXML
    private Button blockid;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        user_crud uc = new user_crud() ; 
        User u = uc.getByID(DashAdminController.idprof);
        firstname_id.setText(u.getFirstname());
        email_id.setText(u.getEmail());
        lastname_id.setText(u.getName());
Image imgcer = new Image(u.getImage(),false) ;
            c2.setFill(new ImagePattern(imgcer));
            
            if (u.isBlocked())
                blockid.setText("Unblock");
            else
               blockid.setText("block");

                

    }    


    @FXML
    private void rmvbtn(ActionEvent event) {
            user_crud uc = new user_crud() ; 
            uc.supprimer(DashAdminController.idprof);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Profil deleted !");
            alert.showAndWait();
                       try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/dashAdmin.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @FXML
    private void backbtn(ActionEvent event) {
           try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/dashAdmin.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void blockbtn(ActionEvent event) {
            user_crud uc = new user_crud() ; 
        User u = uc.getByID(DashAdminController.idprof);
        System.out.println(u); 
        System.out.println(u.isBlocked());
        if (u.isBlocked())
         { 
             u.setBlocked(false);
             uc.nomodifierblocker(u) ;  
             blockid.setText("block");}

            else
         { u.setBlocked(true);
          blockid.setText("Unblock");
            uc.modifierblocker(u) ;  

         }

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User Blocked !");
            alert.showAndWait();

    }

    
}
