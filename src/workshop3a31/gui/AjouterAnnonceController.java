/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a31.gui;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import workshop3a31.entities.Annonce;
import workshop3a31.services.ServiceAnnonce;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Amoulette
 */
public class AjouterAnnonceController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tflocal;
    @FXML
    private TextField tftitre;
    @FXML
    private TextArea tfdesc;
    @FXML
    private TextField tfcat;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfimage;

    @FXML
    private TableView<Annonce> tableannonce;

    @FXML
    private TableColumn<Annonce, String> colnom;
    @FXML
    private TableColumn<Annonce, Integer> coltel;
    @FXML
    private TableColumn<Annonce, String> colemail;
    @FXML
    private TableColumn<Annonce, String> collocal;
    @FXML
    private TableColumn<Annonce, String> Coltitre;
    @FXML
    private TableColumn<Annonce, String> coldesc;
    @FXML
    private TableColumn<Annonce, String> colcat;
    @FXML
    private TableColumn<Annonce, String> coletat;
    @FXML
    private TableColumn<Annonce, String> colimage;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmod;
    @FXML
    private Button btnsup;

    ServiceAnnonce an = new ServiceAnnonce();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    public void updateTable() {
        ObservableList<Annonce> Events = an.readAnnonce();
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        collocal.setCellValueFactory(new PropertyValueFactory<>("local"));
        Coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("descreption"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        tableannonce.setItems((ObservableList<Annonce>) Events);
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
        tftel.clear();
        tfemail.clear();
        tflocal.clear();
        tftitre.clear();
        tfdesc.clear();
        tfcat.clear();
        tfetat.clear();
        tfimage.clear();

    }

    @FXML
    private void ajouterAction(ActionEvent event) {

        String nom = tfnom.getText();
        String tel = tftel.getText();
        String email = tfemail.getText();
        String local = tflocal.getText();
        String titre = tftitre.getText();
        String desc = tfdesc.getText();
        String cat = tfcat.getText();
        String etat = tfetat.getText();
        String image = tfimage.getText();

        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("nom doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("email doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (titre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("titre doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (local.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("local doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            try {
                int tell = Integer.parseInt(tel);

                Annonce p = new Annonce(nom, image, desc, titre, tell, email, local, etat, cat);

                ServiceAnnonce sp = new ServiceAnnonce();
                sp.Ajouter(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Annonce inséré avec succès!");
                alert.showAndWait();
                init();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setContentText(" Numéro de téléphone  doit etre un nombres");
                alert.setTitle("Probleme");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void preModSupp(MouseEvent event) {
        Annonce e = tableannonce.getSelectionModel().getSelectedItem();
        System.out.println(e.getId());

        tfnom.setText(e.getNom());
        //   tftel.setText(e.getTel());
        tfemail.setText(e.getEmail());
        tflocal.setText(e.getLocal());
        tftitre.setText(e.getTitre());
        tfdesc.setText(e.getDescreption());
        tfcat.setText(e.getCategorie());
        tfetat.setText(e.getEtat());
        tfimage.setText(e.getImage());
        init();
    }

    @FXML
    private void ModifAnnonce(ActionEvent event) {
        Annonce e = tableannonce.getSelectionModel().getSelectedItem();

        String nom = tfnom.getText();
//        Integer tel = tftel..getSelectionModel().getSelectedItem();
        String email = tfemail.getText();
        String local = tflocal.getText();
        String titre = tftitre.getText();
        String desc = tfdesc.getText();
        String cat = tfcat.getText();
        String etat = tfetat.getText();
        String image = tfimage.getText();

        e.setNom(nom);
        // e.tftel(tel);
        e.setEmail(email);
        e.setLocal(local);
        e.setTitre(titre);
        e.setDescreption(desc);
        e.setCategorie(cat);
        e.setEtat(etat);
        e.setImage(image);

        an.Modifier(e);
        updateTable();
    }

    @FXML
    private void SupprimerAnnonce(ActionEvent event) {
        Annonce e = tableannonce.getSelectionModel().getSelectedItem();
        an.Supprimer(e);
        init();
    }

}
