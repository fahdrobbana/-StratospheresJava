/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import users.entity.Annonce;
import users.services.ServiceAnnonce;

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
    @FXML
    private Button imprimerrec;
    @FXML
    private Button statButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
         imprimerrec.setOnAction(event -> {
          exportToPdf();
        });
        
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

    public void exportToPdf(){
     try {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
        document.open();
        PdfPTable table = new PdfPTable(6);
        PdfPCell c1 = new PdfPCell(new Phrase("Id"));
        table.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Nom"));
        table.addCell(c2);  
        PdfPCell c3 = new PdfPCell(new Phrase("Titre"));
        table.addCell(c3);
         PdfPCell c4 = new PdfPCell(new Phrase("Description"));
        table.addCell(c4);
        PdfPCell c5 = new PdfPCell(new Phrase("Etat"));
        table.addCell(c5);
        PdfPCell c6 = new PdfPCell(new Phrase("Catégorie"));
        table.addCell(c6);
       
        table.setHeaderRows(6);
        ServiceAnnonce pcrud = new ServiceAnnonce();
        List <Annonce> listeProduits =  pcrud.listAnnonce();
        for (Annonce p : listeProduits) {
            table.addCell(Integer.toString(p.getId()));
            table.addCell(String.format(p.getNom()));
            table.addCell(p.getNom());
            table.addCell(String.format(p.getDescreption()));
            table.addCell(String.format(p.getEtat()));
            table.addCell(String.format(p.getCategorie()));
           
        }
        document.add(table);
        document.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier a été exporté avec succès !");
        alert.showAndWait();}
    } catch (FileNotFoundException | DocumentException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Export PDF");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'exportation du fichier PDF !");
        alert.showAndWait();
    }}

    @FXML
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

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Disponible", disponibleCount),
                        new PieChart.Data("Non disponible", nonDisponibleCount)
                );

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistiques  des etats des annonces");

        Stage stage = new Stage();
        Scene scene = new Scene(chart);
        stage.setScene(scene);
        stage.show();
    }

    
    
}
