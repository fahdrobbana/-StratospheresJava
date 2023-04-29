/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui.login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import users.entity.User;
import users.gui.admin.DashAdminController;
import users.services.user_crud;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class LoginController implements Initializable {

  public static int userc ;  
    
    @FXML
    private TextField Email_id;
    @FXML
    private PasswordField Password_id;
    @FXML
    private AnchorPane pane;
    String message = "You're Logged In!";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signin_button(ActionEvent event) throws SQLException, IOException {
        String eml = Email_id.getText();
        String mdp = Password_id.getText();
        user_crud uc = new user_crud();
        userc = uc.login(mdp, eml) ; 
        User u = uc.getByID(userc);
        if(u.isBlocked())
         {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("you are blocked by an admin!");
            alert.showAndWait();
            return ;
        }
        if(userc!=0)
        {
            notifyUser(message);
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../profil/profil.fxml"));
                    Parent root2 = loader2.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root2));
                    stage.show();
        }
         
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Password !");
            alert.showAndWait();
        }
    }

    @FXML
    private void create_buuton(ActionEvent event) throws IOException {
          try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("../inscription/inscription.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void forgetbtn(MouseEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("ForgetPasword.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void notifyUser(String message) {
         if (SystemTray.isSupported()) {
             try {
                 // Initialiser SystemTray
                 SystemTray tray = SystemTray.getSystemTray();

                 // Créer une icône pour la notification
                 Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                 TrayIcon trayIcon = new TrayIcon(image, "Notification");

                 // Ajouter l'icône au SystemTray
                 tray.add(trayIcon);

                 // Afficher la notification
                 trayIcon.displayMessage("Notification", message, TrayIcon.MessageType.INFO);
             } catch (AWTException e) {
                 System.err.println("Erreur lors de l'initialisation du SystemTray: " + e);
             }
         } else {
             System.out.println("SystemTray n'est pas pris en charge");
         }
     }
}
