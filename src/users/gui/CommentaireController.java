/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import users.services.ServiceCommentaire;
import users.entity.Commentaire;
import users.services.ServiceAnnonce;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author Amoulette
 */
public class CommentaireController implements Initializable {

    private TextField tfannonce;
    @FXML
    private TextArea tftext;
    @FXML
    private TextField tfnom;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Commentaire> tablecom;
    @FXML
    private TableColumn<Commentaire, Integer> colan;
    @FXML
    private TableColumn<Commentaire, String> colnom;
    @FXML
    private TableColumn<Commentaire, String> coltext;
    @FXML
    private TableColumn<Commentaire, Date> coldate;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnmodifcom;
    @FXML
    private Button btnsupp;
    
     @FXML
    private ComboBox<String> cmbtitre;
     
    ObservableList<String> noman;
     @FXML
    private ComboBox<String> cmbemail;
         ObservableList<String> emailn;
    
    ServiceCommentaire an = new ServiceCommentaire();
    ServiceAnnonce ann = new ServiceAnnonce();
   user_crud annn= new user_crud();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.noman = ann.GetNamesAnnonce();
        cmbtitre.setItems(noman);
        this.emailn= annn.GetEmailUsers();
         cmbemail.setItems(emailn);
        
        
        updateTable();
    }

    public void updateTable() {
        ObservableList<Commentaire> Events = an.readcomm();
        colan.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coltext.setCellValueFactory(new PropertyValueFactory<>("text"));
         coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Créer un filtre de mauvais mots
    //BadWordFilter filter = new BadWordFilter();
    
    // Parcourir chaque commentaire et filtrer le texte
   /* for (Commentaire c : Events) {
        String filteredText = filter.filterBadWords(c.getText());
        c.setText(filteredText);
    }
        */
        tablecom.setItems((ObservableList<Commentaire>) Events);
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
//       tfannonce.clear();
        tfnom.clear();
        tftext.clear();
          date.setValue(null);

    }

    private void preModSupp(MouseEvent event) {
        Commentaire e = tablecom.getSelectionModel().getSelectedItem();
        System.out.println(e.getId());
        tfnom.setText(e.getNom());
        tftext.setText(e.getText());
        LocalDate d = Instant.ofEpochMilli(e.getDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        date.setValue(d);
        init();
    }

    @FXML
    private void ModifierComm(ActionEvent event) {
        Commentaire e = tablecom.getSelectionModel().getSelectedItem();

        String nom = tfnom.getText();
        String text = tftext.getText();
         //Date d = Date.valueOf(date.getValue());
        e.setNom(nom);
        e.setText(text);
         //e.setDate(d);
        an.Modifier(e);
        updateTable();
    }

    @FXML
    private void SupprimerComm(ActionEvent event) {
        Commentaire e = tablecom.getSelectionModel().getSelectedItem();
        an.Supprimer(e);
        init();
    }
   

    @FXML
    private void CreateComm(ActionEvent event) {
        String nomAn = cmbtitre.getSelectionModel().getSelectedItem();
         String emailu = cmbemail.getSelectionModel().getSelectedItem();
        //String id_annonce = tfannonce.getText();
        String nom = tfnom.getText();
        String text = tftext.getText();
        Date d = Date.valueOf(date.getValue());
        int annonce_id = ann.GetIdAnnone(nomAn);
        int user_id = annn.GetIdUser(emailu);
        System.out.println("id annonce="+annonce_id);
        System.out.println("id user="+user_id);

        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("nom doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (text.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("text doit etre saisi");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();

        } else {
            try {
               // int id_annoncec = Integer.parseInt(id_annonce);
                Commentaire R1 = new Commentaire(annonce_id, user_id,nom, text,d);
                ServiceCommentaire cr = new ServiceCommentaire();
                cr.Ajouter(R1);
                System.out.println(R1);
                init();
        updateTable();
        AlertWindow("Commentaire ajouté", "Le commentaire a été ajouté avec succès", Alert.AlertType.INFORMATION);
    

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setContentText(" annonce doivent etre un nombres");
                alert.setTitle("Probleme");
                alert.setHeaderText(null);
                alert.showAndWait();
               // System.out.println("");
            }

        }
    }
       

}