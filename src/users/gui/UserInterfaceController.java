/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package users.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.oschrenk.utils.Systems.USER_NAME;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import users.entity.Article;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import users.services.ServiceArticle;
import javafx.scene.image.ImageView;


public class UserInterfaceController implements Initializable {

    public static int idArt;

    @FXML
    private Button btn1;


    @FXML
    private TableColumn<Article, String> desccolumn;

    @FXML
    private TableColumn<Article, String> liencolumn;
    @FXML
    private TableColumn<Article, String> imagecolumn;

    @FXML
    private TableView<Article> table;

    @FXML
    void Creer(ActionEvent event) {
        try {
            Article selectedLN = table.getSelectionModel().getSelectedItem();
            if (selectedLN == null) {
                // Afficher un message d'erreur
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible  ");
                alert.setContentText("Veuillez seelectionner un article � reclamer !");
                alert.showAndWait();
            }
            idArt=selectedLN.getId_article();

            Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ajouterReclamation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeDesReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void Modifier(ActionEvent event) {

    }

    void Supprimer(ActionEvent event) {

    }
    ObservableList<Article> listeB = FXCollections.observableArrayList();

    public void show() {
        ServiceArticle bs = new ServiceArticle();
        listeB = bs.afficherTous();
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
                            ImageView imagev = new ImageView(new javafx.scene.image.Image("file:///" + item));
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

    }

    ;

 
    @FXML
    public void generatePDF() {
        try {
            ServiceArticle bs = new ServiceArticle();
            listeB = bs.afficherTous();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Liste des Article" + ".pdf"));

            // Définir le style de police et de couleur pour le titre
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(0, 102, 204));
            Paragraph title = new Paragraph("Liste des Article", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(150);

            // Ajouter une image au document
            // Ajouter les informations de l'attestation de travail dans un tableau
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            Font infoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(0, 0, 0));
            PdfPCell cell1 = new PdfPCell(new Phrase("TYPE :", infoFont));
            PdfPCell cell2 = new PdfPCell(new Phrase("Description,", infoFont));
            PdfPCell cell3 = new PdfPCell(new Phrase("Image :", infoFont));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            for (Article art : listeB) {
                PdfPCell cell4 = new PdfPCell(new Phrase(art.getType(), infoFont));
                PdfPCell cell5 = new PdfPCell(new Phrase(art.getDescription(), infoFont));
                 Image img = Image.getInstance(art.getImage());
                PdfPCell cell6 = new PdfPCell(img);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(img);
            }

            // Ajouter la date et la signature
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateFormatee = date.format(formatter);
            Paragraph dateInfo = new Paragraph("Fait à Tunis, le " + dateFormatee, infoFont);
            Paragraph signature = new Paragraph("[Nom et signature du responsable de l'entreprise]", infoFont);
            signature.setAlignment(Element.ALIGN_RIGHT);

            // Ajouter les éléments au document
            document.open();

            document.add(title);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(dateInfo);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.close();

            Desktop.getDesktop().open(new File("Liste des Article" + ".pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        show();
    }

}
