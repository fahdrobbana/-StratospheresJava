/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.profil;

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
public class ProfilController implements Initializable {

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
    private Circle c2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        user_crud uc = new user_crud() ; 
        User u = uc.getByID(LoginController.userc);
        firstname_id.setText(u.getFirstname());
        email_id.setText(u.getEmail());
        lastname_id.setText(u.getName());
        if(u.getRoles().equals("Admin"))
             showid.setVisible(true);
        else        
            showid.setVisible(false);
Image imgcer = new Image(u.getImage(),false) ;
            c2.setFill(new ImagePattern(imgcer));

    }    

    @FXML
    private void editprofil_id(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../editprofil/editprofil.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void editpassword_id(ActionEvent event) {
                try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../editpassword/editpassword.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void showusers(ActionEvent event) {
          try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../admin/dashAdmin.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void event(ActionEvent event) {
                  try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../../vues/participationback.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Annonce(ActionEvent event) {
                          try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../Menu.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Articles(ActionEvent event) {
                                  try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../Acceuil.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
