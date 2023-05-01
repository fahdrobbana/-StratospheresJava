/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amoulette
 */
public class MenuController implements Initializable {

    @FXML
    private Button userannonce;
    @FXML
    private Button adminann;
    @FXML
    private Button admincomm;
    @FXML
    private Button usercomm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         userannonce.setOnAction( e ->{ 
             try {
                Parent root = FXMLLoader.load(getClass().getResource("AjouterAnnonce.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Gestion des annonces");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
         });
          usercomm.setOnAction( e ->{ 
             try {
                Parent root = FXMLLoader.load(getClass().getResource("Commentaire.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Gestion des commentaires");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
         });
           adminann.setOnAction( e ->{ 
             try {
                Parent root = FXMLLoader.load(getClass().getResource("AnnonceAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Consultation des annonces");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
         });
           
             admincomm.setOnAction( e ->{ 
             try {
                Parent root = FXMLLoader.load(getClass().getResource("CommentaireAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Consultation des commentaires");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
         });
         
    }    
    
}
