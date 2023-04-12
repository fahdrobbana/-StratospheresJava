/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.editprofil;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import users.entity.User;
import users.gui.login.LoginController;
import users.gui.profil.ProfilController;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class EditprofilController implements Initializable {

    @FXML
    private TextField firstname_update;
    @FXML
    private TextField lastname_update;
    @FXML
    private AnchorPane pane;
    @FXML
    private Circle c2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user_crud uc = new user_crud();
        User u = uc.getByID(LoginController.userc);
        firstname_update.setText(u.getFirstname());
        lastname_update.setText(u.getName());
        Image imgcer = new Image(u.getImage(),false) ;
        c2.setFill(new ImagePattern(imgcer)); 
        urlImg = u.getImage() ;
    }
    
     String urlImg ; 

    @FXML
    private void ajoutpic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedfile = fileChooser.showOpenDialog(null);
        if (selectedfile != null) {
            urlImg = selectedfile.toURI().toString();
Image imgcer = new Image(urlImg,false) ;
            c2.setFill(new ImagePattern(imgcer));        
        }

    }

    public static boolean estAlphabetique(String chaine) {
    for (int i = 0; i < chaine.length(); i++) {
        if (!Character.isLetter(chaine.charAt(i))) {
            return false;
        }
    }
    return true;
}
    

    public boolean test()
    {
        if(firstname_update.getText().equals("") || lastname_update.getText().equals("") ||urlImg==null)
        return false  ;

        if(!estAlphabetique(firstname_update.getText()) || !estAlphabetique(lastname_update.getText()))
           return false  ;
        return true ; 
    }

    @FXML
    private void update_button(ActionEvent event) {
      
        if(test())
        {
        user_crud uc = new user_crud();
        User u = uc.getByID(LoginController.userc);
        u.setFirstname(firstname_update.getText());
        u.setName(lastname_update.getText());
        u.setImage(urlImg);
        u.setEmail(u.getEmail());
        uc.modifier(u);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Profil updated !");
            alert.showAndWait();
            try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../profil/profil.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(EditprofilController.class.getName()).log(Level.SEVERE, null, ex);
         }}
        else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Verify your informations!");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel_button(ActionEvent event) {
         try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("../profil/profil.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(EditprofilController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

}
