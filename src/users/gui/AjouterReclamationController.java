/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.services.ServiceReclamation;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.regex.Pattern;

import users.entity.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class AjouterReclamationController implements Initializable {

  

 

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         
    }
    

    @FXML 
    private DatePicker date_rep;
    @FXML
    private TextField email;
    @FXML
    private TextArea message_rep;
    @FXML
    private TextArea message_rep1;
    @FXML
    private TextField nom;
    @FXML
    private Button btnajouter;

    private static final String EMAIL_REGEX = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Za-z]{2,4}$";

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
          //recuperation de donne dans interface
        if (date_rep.getValue() == null || message_rep.getText().isEmpty()||nom.getText().isEmpty()||message_rep1.getText().isEmpty() ) {
            // Affichage d'une alerte en cas de champ vide
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }else if (!isValidEmail(email.getText())) {
            // Affichage d'une alerte en cas de saisie d'email invalide
            
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir une adresse email valide!");
            alert.showAndWait();
        } else {
            String dater = date_rep.getValue().toString();
            String message1 = message_rep.getText();
            String message2 = message_rep1.getText();
            String nom1 =nom.getText();
            String mail = email.getText();
            ServiceReclamation b = new ServiceReclamation();
            Reclamation r1 = new Reclamation(nom1, message1,dater,message2,mail,UserInterfaceController.idArt);
            b.ajouter(r1);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("La reclamation est ajoutée avec succes");
            alert.showAndWait();
            Alert alert2 = new Alert(AlertType.CONFIRMATION);
			alert2.setTitle("Information Dialog");
			alert2.setHeaderText(null);
			alert2.setContentText("reponse ins�r�e avec succ�s!");
/*
			Optional<ButtonType> result = alert2.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
			    try {
		        	
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
		            Parent root = loader.load();

		            // Show the Modif.fxml interface
		            Scene scene = new Scene(root);
		            Stage stage = new Stage();
		            stage.setScene(scene);
		            stage.show();
		            } catch (IOException ex) {
		                ex.printStackTrace();

		             }*/
			}
			}
     

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

 
}
