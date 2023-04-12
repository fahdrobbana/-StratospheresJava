/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a31.gui;

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
import workshop3a31.entities.Annonce;
import workshop3a31.services.ServiceAnnonce;

/**
 * FXML Controller class
 *
 * @author Amoulette
 */
public class AnnonceAdminController implements Initializable {

    @FXML
    private TableView<Annonce> tableannonce;
    @FXML
    private TableColumn<Annonce, String> colnom;
    @FXML
    private TableColumn<Annonce, Integer> coltel;
    @FXML
    private TableColumn<Annonce, String> colemail;
    @FXML
    private TableColumn<Annonce, String> colloc;
    @FXML
    private TableColumn<Annonce, String> coltitre;
    @FXML
    private TableColumn<Annonce, String> coldes;
    @FXML
    private TableColumn<Annonce, String> colcat;
    @FXML
    private TableColumn<Annonce, String> coletat;
            @FXML
    private TableColumn<Annonce, String> colimage;
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
        colloc.setCellValueFactory(new PropertyValueFactory<>("local"));
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("descreption"));
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
    }


   
    @FXML
    private void SupprimerAnnoncea(ActionEvent event) {
          Annonce e = tableannonce.getSelectionModel().getSelectedItem();
        an.Supprimer(e);
        init();
    }
    
}
