package users.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.GapContent;
 
import users.entity.Article;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import users.services.ServiceArticle;
 

public class AjouterArticleController implements Initializable {

	@FXML
	private Label nom;
	@FXML
	private Button btnAjouter;
	@FXML
	private TextField urlTF;
  
	private File file;

 
 

	@FXML
	private Button btnChoisir;
	@FXML
	private ImageView imageview;
	@FXML
	private TextField textdesc;
	 

 
	@FXML
	private TextField txtnom;

	@FXML
	private TextField txtnumF;

	@FXML
	private TextField txtnumT;
	
	@FXML
	private Stage stage;

	@FXML
	void ajouter(ActionEvent event) {
		boolean test = true;
		String type;
		String desc ;
		type = txtnom.getText();
 
		desc = textdesc.getText();
	 
 

		if ((txtnom.getText().isEmpty()) || (textdesc.getText().isEmpty()) ) {
		 
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("oops");
				alert1.setHeaderText(null);
				alert1.setContentText("remplir tous les champs");
				alert1.showAndWait();
				return;

			}
		 

 
	 

		else {

			Article nouvelleArticle = new Article();
			nouvelleArticle.setType(type);
 
			nouvelleArticle.setImage(file.getPath());
			 
			nouvelleArticle.setDescription(textdesc.getText());
			ServiceArticle bs = new ServiceArticle();
			bs.ajouter(nouvelleArticle );
			
			   Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Confirmation");
	            alert.setHeaderText(null);
	            alert.setContentText("Ajouter Article est avec succer   " );
	            alert.showAndWait();

			 
			}
		}

 
	 

	@Override
	public void initialize(URL location, ResourceBundle resources) {

 
		};
		
		
		
 
	@FXML
	private void importer(ActionEvent event) {
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("S�lectionnez un fichier PNG");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File fichierSelectionne = fileChooser.showOpenDialog(stage);

        if (fichierSelectionne != null) {
        	urlTF.setText(fichierSelectionne.getName());
            file = fichierSelectionne;
        }
		
	}

}
