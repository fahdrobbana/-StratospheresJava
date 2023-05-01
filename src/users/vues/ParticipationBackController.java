/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.vues;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import users.entity.Participation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javax.swing.JFileChooser;
import users.services.EvenementService;
import users.services.ParticipationService;
import sun.misc.IOUtils;


/**
 *
 * @author joujo
 */
public class ParticipationBackController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfemail;
   
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
    private Button pdf;
    @FXML
    private Label UserName;
    @FXML
    private TableColumn<?, ?> colnum;
    @FXML
    private TextField tfnum;


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
          colnum.setCellValueFactory(new PropertyValueFactory<>("numero"));
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
         tfnum.clear();
        cbev.setValue(null);

    }

    private boolean validateFields() {
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        String email = tfemail.getText();
          int numero= Integer.valueOf(tfnum.getText());
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
         if (numero==0) {
            showAlert("Numero est vide");
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
         int numero= Integer.valueOf(tfnum.getText());
        String nomEV = cbev.getSelectionModel().getSelectedItem();
        int event_id = ev.GetIdEvent(nomEV);

        p.setNom(nom);
        p.setPrenom(prenom);
        p.setAdresse(adresse);
        p.setEmail(email);
        p.setNumero(numero);
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
        GotoFXML("evenement", "Gestion Evenement", event);
    }

    @FXML
    private void preModSupp(MouseEvent event) {
        Participation p = tablepartic.getSelectionModel().getSelectedItem();
        System.out.println(p.getId());
        tfnom.setText(p.getNom());
        tfprenom.setText(p.getPrenom());
        tfadresse.setText(p.getAdresse());
        tfemail.setText(p.getEmail());
          tfnum.setText(String.valueOf(p.getNumero()));
        String nomEvent = ev.GetNomEventbyId(p.getEvent_id());
        cbev.setValue(nomEvent);
    }


    @FXML
    private void DeletePartic(ActionEvent event) {
        Participation p = tablepartic.getSelectionModel().getSelectedItem();
        rv.suppParticipationPst(p);
        init();

    }

    @FXML
    private void Pdf(ActionEvent event) {
         String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(null);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "/Participation.pdf"));
            doc.open();

            // Ajout du titre
            doc.add(new Paragraph("  "));
            doc.add(new Paragraph("  "));
            Paragraph title = new Paragraph("Liste des Participations", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.DARK_GRAY));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);

            // Ajout de la date de création
            LocalDate currentDate = LocalDate.now();
            Paragraph date = new Paragraph("Date: " + currentDate.toString(), FontFactory.getFont(FontFactory.HELVETICA, 10, new BaseColor(0, 0, 255)));
            date.setAlignment(Element.ALIGN_RIGHT);
            doc.add(date);

            // Ajout du logo
            String imagePath = getClass().getResource("/ressources/gfllogo.png").getPath();
            Image logo = Image.getInstance(imagePath);
            float pageWidth = doc.getPageSize().getWidth();
            float logoWidth = 250; // Largeur du logo en pixels
            float logoHeight = 100; // Hauteur du logo en pixels
            float logoX = (pageWidth - logoWidth) / 2; // Position horizontale du logo
            float logoY = doc.getPageSize().getHeight() - 100; // Position verticale du logo (au-dessus du contenu)
            logo.setAbsolutePosition(logoX, logoY);
            logo.scaleAbsolute(logoWidth, logoHeight);
            doc.add(logo);

            PdfPTable table = new PdfPTable(6); // Modification du nombre de colonnes
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            float[] columnWidths = {2f, 2f, 3f, 3f, 2f,3f};
            table.setWidths(columnWidths);

            // Ajout de couleurs de fond et de police différente pour l'en-tête de table
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.GREEN);
            headerCell.setPadding(5);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            headerCell.setPhrase(new Phrase("Nom", headerFont));
            table.addCell(headerCell);

            headerCell.setPhrase(new Phrase("Prénom", headerFont));
            table.addCell(headerCell);

            headerCell.setPhrase(new Phrase("Adresse", headerFont));
            table.addCell(headerCell);

            headerCell.setPhrase(new Phrase("Email", headerFont));
            table.addCell(headerCell);
            
             headerCell.setPhrase(new Phrase("Numero", headerFont));
            table.addCell(headerCell);

            headerCell.setPhrase(new Phrase("ID événement", headerFont));
            table.addCell(headerCell);

            ParticipationService p = new ParticipationService();
            for (int i = 0; i < p.rowPartic(); i++) {

                String nom = tablepartic.getColumns().get(0).getCellObservableValue(i).getValue().toString();
                String prenom = tablepartic.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                String adresse = tablepartic.getColumns().get(2).getCellObservableValue(i).getValue().toString();
                String email = tablepartic.getColumns().get(3).getCellObservableValue(i).getValue().toString();
                  String numero = tablepartic.getColumns().get(4).getCellObservableValue(i).getValue().toString();
                String event_id = tablepartic.getColumns().get(5).getCellObservableValue(i).getValue().toString();

                // Ajout de couleurs de fond et de police différente pour les cellules de données
                PdfPCell dataCell = new PdfPCell();
                dataCell.setPadding(5);
                dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.DARK_GRAY);
                dataCell.setPhrase(new Phrase(nom, dataFont));
                table.addCell(dataCell);

                dataCell.setPhrase(new Phrase(prenom, dataFont));
                table.addCell(dataCell);

                dataCell.setPhrase(new Phrase(adresse, dataFont));
                table.addCell(dataCell);

                dataCell.setPhrase(new Phrase(email, dataFont));
                table.addCell(dataCell);
                
                 dataCell.setPhrase(new Phrase(numero, dataFont));
                table.addCell(dataCell);

                dataCell.setPhrase(new Phrase(event_id, dataFont));
                table.addCell(dataCell);
            }
            doc.add(table);
            doc.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
