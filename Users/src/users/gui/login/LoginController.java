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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import users.gui.admin.DashAdminController;
import users.services.user_crud;

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
        if(userc!=0)
        {
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
    
}
