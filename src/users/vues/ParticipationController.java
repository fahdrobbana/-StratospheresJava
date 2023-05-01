/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.vues;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import users.entity.Participation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import users.entity.Evenement;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import users.services.EvenementService;
import users.services.ParticipationService;
import sun.misc.IOUtils;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.File;
import java.util.Comparator;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import users.services.MyListener;


/**
 *
 * @author joujo
 */
public class ParticipationController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfemail;
    @FXML
    private Button btncreate;

    private TableView<Participation> tablepartic;
  

    @FXML
    private ComboBox<String> cbev;

    ObservableList<String> nomevent;

    ParticipationService rv = new ParticipationService();
    EvenementService ev = new EvenementService();

    @FXML
    private ImageView bqckbtn;

    private TableColumn<?, ?> colidevent;

    @FXML
    private Button pdf;
   @FXML
    private TableView<Evenement> tableevent;
    @FXML
    private TableColumn<Evenement, String> collieu;
    @FXML
    private TableColumn<Evenement, Date> coldate;
    @FXML
    private TableColumn<?, ?> coldatefin;
    @FXML
    private TableColumn<?, ?> colnbrp;
    @FXML
    private TableColumn<?, ?> coldescrip;
    @FXML
    private TextField tfrecherche;
    @FXML
    private TableColumn<Evenement, String> colnom;
    private Label idgetter;
    @FXML
    private TextField tfnum;
    @FXML
    private Button btntri;
    @FXML
    private Label UserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nomevent = ev.GetNamesEvent();
        cbev.setItems(nomevent);
        updateTableEvent();
        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherEvent();
        });
       
    }

   
    public void updateTableEvent() {
        ObservableList<Evenement> Events = ev.readEvent();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        collieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coldescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        colnbrp.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));

        tableevent.setItems(Events);
       
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);

        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    public void init() {
      
        tfnom.clear();
        tfprenom.clear();
        tfadresse.clear();
        tfemail.clear();
        tfnum.clear();
        cbev.setValue(null);
        
        updateTableEvent();

    }

   private boolean validateFields() {
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();
         int numero = Integer.valueOf(tfnum.getText());
        String nomEV = cbev.getSelectionModel().getSelectedItem();

        if (nom.isEmpty()) {
            showAlert("Nom est vide");
            return false;
        }

        if (prenom.isEmpty()) {
            showAlert("Prénom est vide");
            return false;
        }

        if (adresse.isEmpty()) {
            showAlert("Adresse est vide");
            return false;
        }

        if (email.isEmpty()) {
            showAlert("Email est vide");
             System.out.println("here");
            return false;
           
        }
         if (numero==0) {
            showAlert("Numero est vide");
            return false;
        }

        if (nomEV == null) {
            showAlert("Événement non sélectionné");
            return false;
        }

        return true;
    }


    private void showAlert(String message) {
        // Créer un boîte de dialogue d'alerte
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Afficher la boîte de dialogue
        alert.showAndWait();
    }

   

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ParticipationBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backbtnmenu(MouseEvent event) {
        GotoFXML("participationback", "Gestion Participation", event);
    }



    @FXML
    private void CreatePartic(ActionEvent event) {
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();
         int numero = Integer.valueOf(tfnum.getText());
        String nomEV = cbev.getSelectionModel().getSelectedItem();
        int event_id = ev.GetIdEvent(nomEV);
        System.out.println(event_id);

        Participation r = new Participation(event_id, nom, prenom, adresse, email,numero);
        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || email.isEmpty()||numero==0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir le formulaire ");
            alert.showAndWait();
            
            return;
        }

        if (rv.ajouterParticipation(r)) {
            AlertWindow("Ajout avec succées", "Nouvelle Participation ", Alert.AlertType.INFORMATION);
            EnvoyerSMS sms = new EnvoyerSMS();
                        String toPhoneNumber = "+21627839355"; // Numéro de téléphone 
                        String message= "Votre participation est ajoute"; // Contenu du SMS à envoyer
                        sms.send(toPhoneNumber, message);
        } else {
            AlertWindow("Echec d'ajout", "L'évenement atteint le nombre maximum des personnes  ", Alert.AlertType.ERROR);
        }

        init();
    }

   

 
    
     
      public void selectedEvenement(Evenement e){
        idgetter.setText(e.getId()+"");
    }
       MyListener myListener;
    public void recherche_avance(){
     
               ObservableList<Evenement> Events = FXCollections.observableArrayList(ev.readEvent());
    FilteredList<Evenement> filteredData = new FilteredList<>(Events, e -> true);
   tableevent.setItems(filteredData);
  
            myListener=new MyListener() {
                @Override
                public void onclickListener(Evenement e) {
                    selectedEvenement(e);
                  
                    
                }
            };
    tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(event -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String newValuelowercase = newValue.toLowerCase();
            if (event.getNom().toLowerCase().contains(newValuelowercase)) {
                return true;
            }
            else if (event.getLieu().toLowerCase().contains(newValuelowercase)) {
                return true;
            }
          
            else if (String.valueOf(event.getDate()).toLowerCase().contains(newValuelowercase)) {
                return true;
            }
            else {
                return false;
            }
        });
        tableevent.setItems(filteredData);
    });
    
}

    @FXML
    private void TriDates(ActionEvent event) {
               
 ObservableList<Evenement> Events = FXCollections.observableArrayList(ev.readEvent()
            .stream()
            .sorted(Comparator.comparing(Evenement::getDate))
            .collect(Collectors.toList()));
    tableevent.setItems(Events);
    }
    public void rechercherEvent() {
        String keyword = tfrecherche.getText();
        ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
        ObservableList<TableColumn<Evenement, ?>> columns = tableevent.getColumns();
        for (int i = 0; i <ev.readEvent().size(); i++) {
            Evenement event = ev.readEvent().get(i);
            for (int j = 0; j < columns.size(); j++) {
                TableColumn<Evenement, ?> column = columns.get(j);
                String cellValue = column.getCellData(event).toString();
                if (cellValue.contains(keyword)) {
                    filteredList.add(event);
                    break;
                }
            }
        }
        tableevent.setItems(filteredList);
    }

    @FXML
    private void Pdf(ActionEvent event) {
    }

}
