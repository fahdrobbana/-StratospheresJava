/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vues;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author majdi
 */
public class EvenementController implements Initializable {

    @FXML
    private DatePicker datefincb;
    @FXML
    private ComboBox<?> cbnbper;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescrip;
    @FXML
    private TableView<?> tableevent;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> collieu;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> coldatefin;
    @FXML
    private TableColumn<?, ?> colnbrp;
    @FXML
    private TableColumn<?, ?> coldescrip;
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
    private ImageView bqckbtn;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_lieu;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<?> cbrechpar;
    @FXML
    private Button stat;
    @FXML
    private Button btntri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void preModSupp(MouseEvent event) {
    }

    @FXML
    private void CreateEvent(MouseEvent event) {
    }

    @FXML
    private void ModifEvent(ActionEvent event) {
    }

    @FXML
    private void DeleteEvent(ActionEvent event) {
    }

    @FXML
    private void backbtnmenu(MouseEvent event) {
    }

    @FXML
    private void SearchEvent(KeyEvent event) {
    }

    @FXML
    private void OnClickedStatistique(ActionEvent event) {
    }

    @FXML
    private void TriDates(ActionEvent event) {
    }
    
}
