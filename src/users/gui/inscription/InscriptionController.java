/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.inscription;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import users.entity.User;
import users.gui.admin.DashAdminController;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmp;
    @FXML
    private ImageView imgv2;

    /**
     * Initializes the controller class.
     */
    
    String urlImg;
    @FXML
    private AnchorPane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
    private void ajoutpic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedfile = fileChooser.showOpenDialog(null);
        if (selectedfile != null) {
            urlImg = selectedfile.toURI().toString();
            System.out.println(urlImg);
            Image image = new Image(urlImg);
            imgv2.setImage(image);
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
    
    public static boolean estAdresseEmailValide(String email) {
    // expression régulière pour vérifier si l'adresse email est valide
    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}
    public boolean test()
    {
        System.out.println("1");
        if(name.getText().equals("") || lastname.getText().equals("") || email.getText().equals("") || password.getText().equals("") || confirmp.getText().equals("")||urlImg==null)
        return false  ;
             System.out.println("2");

        if(!estAlphabetique(name.getText()) || !estAlphabetique(lastname.getText()))
           return false  ;
             System.out.println("3");

        if(!estAdresseEmailValide(email.getText()))
          return false ; 
              System.out.println("4");

        if(password.getText().length()<6)
          return false ;
              System.out.println("5");

        if(!password.getText().equals(confirmp.getText()))
          return false ;      

        return true ; 
    }
    
    @FXML
    private void signup(ActionEvent event) throws IOException, SQLException {
        user_crud uc = new user_crud() ; 
        if(test())
        {
       uc.ajouter(new User(email.getText(),"",password.getText(),name.getText(),lastname.getText(),urlImg,false));
       User u =  new User(email.getText(),"",password.getText(),name.getText(),lastname.getText(),urlImg,false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("user added !");
            alert.showAndWait();
            
            uc.sendMail(u, "Congrats you just create a new account !", "Congratulations");
            
          try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../login/login.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        }
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
    private void bck(ActionEvent event) throws IOException {
          try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../login/login.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}}
