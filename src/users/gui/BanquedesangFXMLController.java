/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import users.entity.banquedesang;
import users.services.ServiceBanqueDeSang;

public class BanquedesangFXMLController implements Initializable {

    banquedesang b = new banquedesang();
    ServiceBanqueDeSang sb = new ServiceBanqueDeSang();
    private ObservableList<banquedesang> banqueData = FXCollections.observableArrayList();

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tflongitude;
    @FXML
    private TextField tfaltitude;
    @FXML
    private Button ajouter;
    @FXML
    private Label listlabel;
    @FXML
    private TableView<banquedesang> tablebanquedesang;
    @FXML
    private TableColumn<banquedesang, String> nameColumn;
    @FXML
    private TableColumn<banquedesang, String> addressColumn;
    @FXML
    private TableColumn<banquedesang, Integer> telColumn;
    @FXML
    private TableColumn<banquedesang, Float> longitudeColumn;
    @FXML
    private TableColumn<banquedesang, Float> latitudeColumn;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button bb;
    @FXML
    private TextField filterInput;
    @FXML
    private Button pdfButton;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));

        // Load the data from the database and add it to the table
        List<banquedesang> banque = sb.afficher();
        banqueData.addAll(banque);
        tablebanquedesang.setItems(banqueData);

        this.filterInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filterLivreList(oldValue, newValue);
        });
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (tfnom.getText().isEmpty() || tfadresse.getText().isEmpty() || tftel.getText().isEmpty() || tflongitude.getText().isEmpty() || tfaltitude.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplir le formulaire");
            alert.showAndWait();
        } else if (!tftel.getText().matches("\\d{8}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Numéro de téléphone invalide");
            alert.setContentText("Le numéro de téléphone doit contenir 8 chiffres.");
            alert.showAndWait();
        } else if (Float.parseFloat(tflongitude.getText()) < 0 || Float.parseFloat(tfaltitude.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Coordonnées géographiques invalides");
            alert.setContentText("La longitude doit être positive");
            alert.showAndWait();
        } else if (!tfnom.getText().matches("[a-zA-Z0-9' -]{1,50}") || !tfadresse.getText().matches("[a-zA-Z0-9' -]{1,100}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Nom ou adresse invalide");
            alert.setContentText("Le nom ou l'adresse doit contenir uniquement des lettres, des chiffres et les caractères spéciaux suivants : - '");
            alert.showAndWait();
        } else {
            b.setNom(tfnom.getText());
            b.setAdresse(tfadresse.getText());
            b.setTel(Integer.parseInt(tftel.getText()));
            b.setLongitude(Float.parseFloat(tflongitude.getText()));
            b.setLatitude(Float.parseFloat(tfaltitude.getText()));
            sb.ajouter(b);
            reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("msg");
            alert.setHeaderText("succés");
            alert.showAndWait();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        banquedesang crt = tablebanquedesang.getSelectionModel().getSelectedItem();

        if (crt == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une banque de sang dans le tableau!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette banque de sang : " + crt.getNom() + " ?");
            alert.setHeaderText(null);
            // Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            // Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ServiceBanqueDeSang sbds = new ServiceBanqueDeSang();
                sbds.supprimer(crt.getId(), crt);
                //updating user data after closing popup
                banqueData = FXCollections.observableList(sbds.afficher());
                tablebanquedesang.setItems(banqueData);

            }

        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        banquedesang selectedBanque = tablebanquedesang.getSelectionModel().getSelectedItem();

        if (selectedBanque == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une banque de sang dans le tableau!");
            alert.showAndWait();
        } else {
            Dialog<banquedesang> dialog = new Dialog<>();
            dialog.setTitle("Modifier la banque de sang");
            dialog.setHeaderText("Modifier les informations de la banque de sang");

            // Set the button types.
            ButtonType modifierButtonType = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField nomField = new TextField();
            nomField.setText(selectedBanque.getNom());
            TextField adresseField = new TextField();
            adresseField.setText(selectedBanque.getAdresse());
            TextField telField = new TextField();
            telField.setText(Integer.toString(selectedBanque.getTel()));
            TextField longitudeField = new TextField();
            longitudeField.setText(Float.toString(selectedBanque.getLongitude()));
            TextField latitudeField = new TextField();
            latitudeField.setText(Float.toString(selectedBanque.getLatitude()));

            grid.add(new Label("Nom:"), 0, 0);
            grid.add(nomField, 1, 0);
            grid.add(new Label("Adresse:"), 0, 1);
            grid.add(adresseField, 1, 1);
            grid.add(new Label("Téléphone:"), 0, 2);
            grid.add(telField, 1, 2);
            grid.add(new Label("Longitude:"), 0, 3);
            grid.add(longitudeField, 1, 3);
            grid.add(new Label("Latitude:"), 0, 4);
            grid.add(latitudeField, 1, 4);

            dialog.getDialogPane().setContent(grid);

            // Request focus on the username field by default.
            Platform.runLater(() -> nomField.requestFocus());

            // Convert the result to a banquedesang object when the modifier button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == modifierButtonType) {
                    banquedesang banque = new banquedesang();
                    banque.setId(selectedBanque.getId());
                    banque.setNom(nomField.getText());
                    banque.setAdresse(adresseField.getText());
                    banque.setTel(Integer.parseInt(telField.getText()));
                    banque.setLongitude(Float.parseFloat(longitudeField.getText()));
                    banque.setLatitude(Float.parseFloat(latitudeField.getText()));
                    return banque;
                }
                return null;
            });

            Optional<banquedesang> result = dialog.showAndWait();
            if (result.isPresent()) {
                banquedesang banque = result.get();
                System.out.println(banque);
                sb.modifierBanque(banque);
                banqueData.setAll(sb.afficher());
            }

        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("maininterfaces.fxml"));
            root = loader.load();
            bb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void filterLivreList(String oldValue, String newValue) {
        ObservableList<banquedesang> filteredList = FXCollections.observableArrayList();
        if (newValue != null && (newValue.length() < oldValue.length() || oldValue == null)) {
            this.tablebanquedesang.setItems(this.banqueData);
        } else {
            String filterValue = newValue.toUpperCase();
            for (banquedesang livres : this.banqueData) {
                String filterLibelle = livres.getNom().toUpperCase();
                if (filterLibelle.contains(filterValue)) {
                    filteredList.add(livres);
                }
            }
            this.tablebanquedesang.setItems(filteredList);
        }
    }

    @FXML
    private void DownloadPDF(ActionEvent event) throws DocumentException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("banques_de_sang.pdf"));
            document.open();
            document.add(new Paragraph("Liste des banques de sang :"));
            document.add(new Paragraph("\n"));
            for (banquedesang banque : banqueData) {
                document.add(new Paragraph("Nom : " + banque.getNom()));
                document.add(new Paragraph("Adresse : " + banque.getAdresse()));
                document.add(new Paragraph("Téléphone : " + banque.getTel()));
                document.add(new Paragraph("Longitude : " + banque.getLongitude()));
                document.add(new Paragraph("Latitude : " + banque.getLatitude()));
                document.add(new Paragraph("\n"));
            }
            document.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF généré");
            alert.setHeaderText(null);
            alert.setContentText("Le PDF a été généré avec succès !");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de la génération du PDF");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la génération du PDF : " + e.getMessage());
            alert.showAndWait();
        }
    }
}
