/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.vues;

import users.entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import users.main;
import users.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author joujo
 */
public class EvenementController implements Initializable {

    @FXML
    private DatePicker datefincb;
    @FXML
    private ComboBox<Integer> cbnbper;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescrip;
    @FXML
    private TableView<Evenement> tableevent;
    @FXML
    private TableColumn<Evenement, String> colnom;
    @FXML
    private TableColumn<Evenement, Date> coldate;
    @FXML
    private TableColumn<Evenement, String> coldescrip;
    @FXML
    private Button btncreate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;
    @FXML
    private DatePicker datecb;
    @FXML
    private TextField tflieu;
    @FXML
    private TableColumn<Evenement, String> collieu;
    @FXML
    private TableColumn<Evenement, Date> coldatefin;
    @FXML
    private TableColumn<Evenement, Integer> colnbrp;
    EvenementService ev = new EvenementService();
    @FXML
    private ImageView bqckbtn;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_lieu;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbrechpar;
    @FXML
    private Button stat;
    @FXML
    private Button btntri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbnbper.setItems((FXCollections.observableArrayList(2,5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,40,50,100)));
        updateTableEvent();
          cbrechpar.setItems(FXCollections.observableArrayList("nom", "lieu", "date", "description", "datefin","nbr_personnes"));
          tableevent.setItems(FXCollections.observableArrayList(ev.readEvent()));
          

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
        updateTableEvent();
        tfnom.clear();
        tflieu.clear();
        datecb.setValue(null);
        tfdescrip.clear();
        datefincb.setValue(null);
        cbnbper.setValue(null);
    }

    @FXML
    private void preModSupp(MouseEvent event) {
        Evenement e = tableevent.getSelectionModel().getSelectedItem();
        System.out.println(e.getId());

        tfnom.setText(e.getNom());
        tflieu.setText(e.getLieu());

        LocalDate d = Instant.ofEpochMilli(e.getDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate d1 = Instant.ofEpochMilli(e.getDatefin().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
datefincb.setValue(d1);
        datecb.setValue(d);
        tfdescrip.setText(e.getDescription());
        datefincb.setValue(d1);
        cbnbper.setValue(e.getNbr_personnes());

    }

    @FXML
    private void ModifEvent(ActionEvent event) {
      
        Evenement e = tableevent.getSelectionModel().getSelectedItem();

        String nom = tfnom.getText();
        String lieu = tflieu.getText();
        Date d = Date.valueOf(datecb.getValue());
        String Description = tfdescrip.getText();
        Date d1 = Date.valueOf(datefincb.getValue());
        Integer nb_personne = cbnbper.getSelectionModel().getSelectedItem();

        e.setNom(nom);
        e.setLieu(lieu);
        e.setDate(d);
        e.setDescription(Description);
        e.setDatefin(d1);
        e.setNbr_personnes(nb_personne);
        

        ev.modifierEvenementPst(e);
        tableevent.refresh();
        tableevent.getSelectionModel().select(e);
        updateTableEvent();
        init();
    }

    @FXML
    private void DeleteEvent(ActionEvent event) {
        Evenement e = tableevent.getSelectionModel().getSelectedItem();
        ev.suppEvenementPst(e);
        init();
    }

    @FXML
    private void CreateEvent(MouseEvent event) {
        String nom = tfnom.getText();
        String lieu = tflieu.getText();
        Date d = Date.valueOf(datecb.getValue());
        Date d1 = Date.valueOf(datefincb.getValue());
        Integer nb_personne = cbnbper.getSelectionModel().getSelectedItem();
        String description = tfdescrip.getText();
         if (nom.isEmpty() ) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le nom est obligatoire");
        alert.showAndWait();
        return;
    }


        Evenement e1 = new Evenement(nom, lieu, d, description, d1, nb_personne);
 if (ev.ajouterEventPst(e1)) {
            AlertWindow("Ajout " , " Ajout avec succés", Alert.AlertType.INFORMATION);
        } else {
            AlertWindow("Ajout " , "Echec d'ajout", Alert.AlertType.ERROR);
        }
       
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
            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backbtnmenu(MouseEvent event) {
         GotoFXML("participation", "Participation", event);
    }

    @FXML
    private void SearchEvent(KeyEvent event) {
         String search = tfrecherche.getText();
        if (search == null) {
            updateTableEvent();
        } else {
            String searchby = cbrechpar.getSelectionModel().getSelectedItem();
            ObservableList<Evenement> Events = ev.recherche(searchby, search);
             colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        collieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coldescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        colnbrp.setCellValueFactory(new PropertyValueFactory<>("nbr_personnes"));
         tableevent.setItems(Events);
        }
    }

    @FXML
    private void OnClickedStatistique(ActionEvent event) {
          try {

            Parent parent = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
 
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void TriDates(ActionEvent event) {
        
 ObservableList<Evenement> Events = FXCollections.observableArrayList(ev.readEvent()
            .stream()
            .sorted(Comparator.comparing(Evenement::getDate))
            .collect(Collectors.toList()));
    tableevent.setItems(Events);
}
    
     
}
