/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui;

import com.sun.mail.smtp.SMTPTransport;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import users.entity.Reclamation;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import users.services.ServiceReclamation;

public class ModifierReclamationController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	private ServiceReclamation serviceReclamation;
	private ObservableList<String> ReclamtionList;
	@FXML
	private ComboBox<Reclamation> comboBoxReclamation;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		serviceReclamation = new ServiceReclamation();
		ReclamtionList = FXCollections.observableArrayList();
		setComboBoxItems();
	}

	private void setComboBoxItems() {
		// Retrieve all reclamations and their corresponding users
		ObservableList<Reclamation> reclamations = serviceReclamation.afficherTous();

		// Add the user names to the list

		// Set the items of the combo box
		comboBoxReclamation.setItems(reclamations);
		
		comboBoxReclamation.setConverter(new StringConverter<Reclamation>() {
            @Override
            public String toString(Reclamation object) {
                if (object != null) {
                    return object.getNom();
                } else return "";
            }

            @Override
            public Reclamation fromString(String string) {
                return comboBoxReclamation.getSelectionModel().getSelectedItem();
            }
        });
	}

	@FXML
	private TextArea message;

	@FXML
    public void modifier  (ActionEvent event) { 
    	if (message.getText().isEmpty()) {
    	    Alert alert = new Alert(AlertType.ERROR);
    	    alert.setTitle("Erreur de saisie");
    	    alert.setHeaderText(null);
    	    alert.setContentText("Veuillez saisir un message valide.");
    	    alert.showAndWait();
    	}else {
         String message1 = message.getText()  ; 
 
     //    Reclamation c = new Reclamation (message1) ;  // instanciation 
         ServiceReclamation b  = new ServiceReclamation(); 

        
        b.modifier(comboBoxReclamation.getSelectionModel().getSelectedItem().getId_reclamation(),message1); 
       Reclamation c =b. rechercherReclamationParId(comboBoxReclamation.getSelectionModel().getSelectedItem().getId_reclamation());
       //sendMail(message1,c.getEmail());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation modifier avec succ�s!");
        alert.showAndWait();
    	}
        
    }
public void sendMail(String reponse, String email) {
        try {

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp"); //SMTP protocol
            props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props, null);

       
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("Reclamation Reponse <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("Body Rock  : Reset Password ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            String txt = "voici notre reponse pour votre reclamation: \n " + reponse ;

            msg.setText(txt);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");

            st.connect("smtp.gmail.com", 465, "majdi.jendoubi@esprit.tn", "181JMT1795");

            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : " + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
