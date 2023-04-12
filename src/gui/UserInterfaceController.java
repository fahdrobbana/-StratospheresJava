/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

 
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceArticle;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ILYESS LASS
 */
public class UserInterfaceController implements Initializable {

 
    @FXML
    private TableColumn<Article, String> Gcolumn;
     

    @FXML
    private TableColumn<Article, String> Vcolumn;

    @FXML
    private Button btn1;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<Article, String> desccolumn;

   

    @FXML
    private TableColumn<Article, String> liencolumn;
    @FXML
    private TableColumn<Article, String> imagecolumn;
  

    @FXML
    private TableView<Article> table;
    @FXML
    private TextField filtree;
  
    @FXML
    private Button btn11;

    @FXML
    void Creer(ActionEvent event) {
        try {
        	Article selectedLN =  table.getSelectionModel().getSelectedItem();
            if (selectedLN == null) {
                // Afficher un message d'erreur
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible  ");
                alert.setContentText("Veuillez seelectionner un article � reclamer !");
                alert.showAndWait();
            }
            
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ajouterReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeDesReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    @FXML
    void Modifier(ActionEvent event) {
     
    
    }
    @FXML
    void Supprimer(ActionEvent event) {
     
    }     
    ObservableList<Article> listeB = FXCollections.observableArrayList();

    public void show(){
    ServiceArticle bs=new ServiceArticle();
    listeB=bs.afficherTous();
          liencolumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        desccolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
       imagecolumn.setCellValueFactory(new PropertyValueFactory<>("image"));
       

    
         Callback<TableColumn<Article, String>, TableCell<Article, String>> cellFactoryImage
         = //
         new Callback<TableColumn<Article, String>, TableCell<Article, String>>() {
     @Override
     public TableCell<Article, String> call(final TableColumn<Article, String> param) {
         final TableCell<Article, String> cell = new TableCell<Article, String>() {

             @Override
             public void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 if (empty) {
                     setGraphic(null);
                     setText("Aucune image n'existe dans cette liste");
                 } else {
                     ImageView imagev = new ImageView(new Image("file:///"+item));
                     imagev.setFitHeight(120);
                     imagev.setFitWidth(200);
                     setGraphic(imagev);
                     setText(null);
                     //System.out.println(item);
                 }
             }
         };
         return cell;
     }
     };

     imagecolumn.setCellFactory(cellFactoryImage);
     
            table.setItems(listeB);
          
 };

 


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    show();
}

    
 

    
}
