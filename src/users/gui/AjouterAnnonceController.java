/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui;

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
import users.entity.Annonce;
import users.services.ServiceAnnonce;
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
import java.util.List;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import com.google.zxing.BarcodeFormat;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.util.List;
import javafx.scene.chart.PieChart;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private TextField TFSearch;
    @FXML
    private Button codeQr;
    @FXML
    private ImageView codeqrr;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        TFSearch.textProperty().addListener((observable, oldValue, newValue) -> search());

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

    /* @FXML
    private void ajoutpic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedfile = fileChooser.showOpenDialog(null);
        if (selectedfile != null) {
            urlImg = selectedfile.toURI().toString();
            System.out.println(urlImg);
            Image image = new Image(urlImg);
            imgv2.setImage(image);
        }

    }*/
   

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
           String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; // expression régulière pour l'email
        Pattern pattern = Pattern.compile(emailRegex);
        

        if (email == null || email.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Email doit être saisi");
            alert.setTitle("Problème");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                // La saisie de l'email est valide
                // Ajoutez votre code pour continuer avec la logique de l'application
            } else {
                // La saisie de l'email n'est pas valide
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Email invalide. Veuillez saisir un email valide.");
                alert.setTitle("Problème");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
            
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
                notiff();
                // Envoyer le message WhatsApp avec Twilio
                String ACCOUNT_SID = "ACd7316a9c80d20818ebe259901e2e7a04";
                String AUTH_TOKEN = "d21cddc86436c542ccbd0c49b6ff6474";
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                // Définir les numéros de téléphone de l'expéditeur et du destinataire
                String fromNumber = "whatsapp:+14155238886";
                String toNumber = "whatsapp:+21624447441"; // Le numéro de téléphone du destinataire

                // Créer le message à envoyer
                String messageBody = "Nouvelle annonce de : " + nom + "  ";
                Message message = Message.creator(new PhoneNumber(toNumber), new PhoneNumber(fromNumber), messageBody).create();

                System.out.println("Message envoyé avec succès ! SID : " + message.getSid());

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(" Numéro de téléphone  doit etre un nombres");
                alert.setTitle("Probleme");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

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

    private void search() {
        String query = TFSearch.getText();
        ObservableList<Annonce> filteredList = FXCollections.observableArrayList();
        ServiceAnnonce cp = new ServiceAnnonce();
        List<Annonce> li = cp.readAnnonce();
        ObservableList<Annonce> data = FXCollections.observableArrayList(li);
        for (Annonce annonce : li) {
            if (annonce.getTitre().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(annonce);

            }
        }

        tableannonce.setItems(filteredList);

    }

    @FXML
    private void qrcode(ActionEvent event) {
        Annonce p = tableannonce.getSelectionModel().getSelectedItem();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Nom: " + p.getNom() + "\n" + "Local : " + p.getLocal() + "\n" + "email : " + p.getEmail() + "\n" + "tel : " + p.getTel() + "\n" + "id : " + p.getId();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

            codeqrr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        } catch (WriterException ex) {

        }
    }

    private void notiff() {
        ServiceAnnonce sv = new ServiceAnnonce();
        Annonce RV = new Annonce();
        tray.notification.TrayNotification notification = new tray.notification.TrayNotification();
        AnimationType type = AnimationType.POPUP;
        notification.setAnimationType(type);
        notification.setTitle("Bienvenue à GFL");
        notification.setMessage(" L'Annonce a été ajoutée avec succès.");
        notification.setNotificationType(NotificationType.INFORMATION);
        notification.showAndDismiss(Duration.millis(2000));

    }

    /* @FXML
    private void showStatistics(ActionEvent event) {
        ServiceAnnonce sv = new ServiceAnnonce();

        int disponibleCount = 0;
        int nonDisponibleCount = 0;

        List<Annonce> annonces = sv.listAnnonce();
        for (Annonce annonce : annonces) {
            if (annonce.getEtat().equals("Disponible")) {
                disponibleCount++;
            } else {
                nonDisponibleCount++;
            }
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Statistiques des annonces");
        alert.setHeaderText(null);
        alert.setContentText("Nombre d'annonces disponibles: " + disponibleCount + "\nNombre d'annonces non disponibles: " + nonDisponibleCount);
        alert.showAndWait();
    }
     */
}
