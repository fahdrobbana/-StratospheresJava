package users.gui;

 
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import users.entity.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
 import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import users.services.ServiceReclamation;
 

public class ListeDesReclamationController implements Initializable{
     
	  
  

    @FXML
    private Button btn1;
    @FXML
    private TableColumn<Reclamation, String> commentaire;

    @FXML
    private TableColumn<Reclamation, String> emailcolumn;

    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> respond;

 

    @FXML
    private TableView<Reclamation> table;
 
   
 
    @FXML
    private Button btn11;
    
    @FXML
    private TextField recherche;
    
    

    
     
    
    
     
    
    ObservableList<Reclamation> listeB = FXCollections.observableArrayList();
    @FXML
    public void show(){
    ServiceReclamation bs=new ServiceReclamation();
    listeB=bs.afficherTous();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        respond.setCellValueFactory(new PropertyValueFactory<>("respond"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
 
    
 
        table.setItems(listeB);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    show();
}

 
 
    
    @FXML
    void Creer(ActionEvent event) {

    }

   public void search() {
        FilteredList<Reclamation> filteredList;
        filteredList = new FilteredList<>(listeB, (e -> true));
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(predicateRec -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateRec.getNom().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateRec.getCommentaire().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateRec.getRespond().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateRec.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Reclamation> sortList = new SortedList<>(filteredList);
        sortList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortList);
    }


}