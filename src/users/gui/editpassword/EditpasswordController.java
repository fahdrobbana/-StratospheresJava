/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.editpassword;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import users.entity.User;
import users.gui.editprofil.EditprofilController;
import users.gui.login.LoginController;
import users.gui.profil.ProfilController;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class EditpasswordController implements Initializable {

    @FXML
    private PasswordField newp_id;
    @FXML
    private PasswordField confirmp_id;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
   
    
    @FXML
    private void updatep_button(ActionEvent event) {
        if(newp_id.getText().equals(confirmp_id.getText()) && newp_id.getText().length()>6){
         user_crud uc = new user_crud();
        User u = uc.getByID(LoginController.userc);
        u.setPassword(newp_id.getText());
        uc.modifier(u);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password updated !");
            alert.showAndWait();
            try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../profil/profil.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(EditpasswordController.class.getName()).log(Level.SEVERE, null, ex);
         }
     } 
     else
     {
          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setHeaderText(null);
            alert.setContentText("Password incorrect !!");
            alert.showAndWait();
     }
    }

    @FXML
    private void cancelp_button(ActionEvent event) {
                try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../profil/profil.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(EditpasswordController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
