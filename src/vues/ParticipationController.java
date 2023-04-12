/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import entities.Evenement;
import entities.Participation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import services.EvenementService;
import services.ParticipationService;

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
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;

    @FXML
    private TableView<Participation> tablepartic;
    @FXML
    private TableColumn<Participation, String> colnom;
    @FXML
    private TableColumn<Participation, String> colprenom;
    @FXML
    private TableColumn<Participation, String> coladresse;
    @FXML
    private TableColumn<Participation, String> colemail;

    @FXML
    private ComboBox<String> cbev;

   

    ObservableList<String> nomevent;

    ParticipationService rv = new ParticipationService();
    EvenementService ev = new EvenementService();

    @FXML
    private ImageView bqckbtn;

    @FXML
    private TableColumn<?, ?> colidevent;
    @FXML
    private Label UserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nomevent = ev.GetNamesEvent();
        cbev.setItems(nomevent);
        updateTable();
    }

    public void updateTable() {
        ObservableList<Participation> Partic = rv.readPraticipation();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colidevent.setCellValueFactory(new PropertyValueFactory<>("event_id"));

        tablepartic.setItems(Partic);

    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);

        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    public void init() {
        updateTable();
        tfnom.clear();
        tfprenom.clear();
        tfadresse.clear();
        tfemail.clear();
        cbev.setValue(null);

    }
    
    private boolean validateFields() {
    String nom = tfnom.getText();
    String prenom = tfprenom.getText();
    String adresse = tfadresse.getText();
    String email = tfemail.getText();
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
    @FXML
    private void ModifPartic(ActionEvent event) {
        if (!validateFields()) {
        return;
    }
        Participation p = tablepartic.getSelectionModel().getSelectedItem();

        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();
        String nomEV = cbev.getSelectionModel().getSelectedItem();
        int event_id = ev.GetIdEvent(nomEV);

        p.setNom(nom);
        p.setPrenom(prenom);
        p.setAdresse(adresse);
        p.setEmail(email);
        p.setEvent_id(event_id);
        rv.modifierParticipationPst(p);
        tablepartic.refresh();
        tablepartic.getSelectionModel().select(p);
        updateTable();
        init();
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
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backbtnmenu(MouseEvent event) {
        GotoFXML("evenement", "Evenement", event);
    }

    @FXML
    private void preModSupp(MouseEvent event) {
        Participation p = tablepartic.getSelectionModel().getSelectedItem();
        System.out.println(p.getId());
        tfnom.setText(p.getNom());
        tfprenom.setText(p.getPrenom());
        tfadresse.setText(p.getAdresse());
        tfemail.setText(p.getEmail());
        String nomEvent = ev.GetNomEventbyId(p.getEvent_id());
        cbev.setValue(nomEvent);
    }

    @FXML
    private void CreatePartic(ActionEvent event) {
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();

        String nomEV = cbev.getSelectionModel().getSelectedItem();
        int event_id = ev.GetIdEvent(nomEV);
        System.out.println(event_id);

        Participation r = new Participation(event_id, nom, prenom, adresse, email);
               if (nom.isEmpty() || prenom.isEmpty()|| adresse.isEmpty() || email.isEmpty() ) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("veuillez remplir le formulaire ");
        alert.showAndWait();
        return;
    }


        if (rv.ajouterParticipation(r)) {
            AlertWindow("Ajout avec succées", "Nouvelle Participation ", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Echec d'ajout", "L'évenement atteint le nombre maximum des personnes  ", Alert.AlertType.ERROR);
        }

        init();
    }

    @FXML
    private void DeletePartic(ActionEvent event) {
        Participation p = tablepartic.getSelectionModel().getSelectedItem();
        rv.suppParticipationPst(p);
        init();

    }

}
