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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import users.entity.Commentaire;
import users.services.ServiceAnnonce;
import users.services.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author Amoulette
 */
public class CommentaireAdminController implements Initializable {

    @FXML
    private TableView<Commentaire> tablecom;
    @FXML
    private TableColumn<Commentaire, Integer> colan;
    @FXML
    private TableColumn<Commentaire, String> colnom;
    @FXML
    private TableColumn<Commentaire, String> coltext;
    @FXML
    private Button btnsupp;
     ServiceCommentaire an = new ServiceCommentaire();
    ServiceAnnonce ann = new ServiceAnnonce();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         updateTable();
    }    
     @FXML
    public void updateTable() {
        ObservableList<Commentaire> Events = an.readcomm();
        colan.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coltext.setCellValueFactory(new PropertyValueFactory<>("text"));
       

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
       

    }

    @FXML
    private void SupprimerComma(ActionEvent event) {
         Commentaire e = tablecom.getSelectionModel().getSelectedItem();
        an.Supprimer(e);
        init();
    }
    
}
