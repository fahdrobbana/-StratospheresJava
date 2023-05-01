/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui.login;

import users.entity.User;
import users.services.user_crud;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.services.user_crud;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class ForgetPaswordController implements Initializable {

    @FXML
    private TextField fxAddresseRec;
    @FXML
    private Button btnRechercher;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnconnecter;
    @FXML
    private TextField fxmotpasse;
    @FXML
    private TextField fxadresseC;
       @FXML
    private user_crud userService;
     
    public static String Ssemail2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Rechercher(ActionEvent event) throws SQLException, IOException {
        // Récupérer l'adresse e-mail entrée par l'utilisateur

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Identifier_votre_compte.fxml"));

        Parent root = loader.load();
        Identifier_votre_compteController dc = loader.getController();
        String email = fxAddresseRec.getText();
          Ssemail2= fxAddresseRec.getText();
        dc.setUserInformation(email);

        // Create a new scene with the loaded FXML file and show it
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnRechercher.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Main(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    

    /**
     * Validate user input and show error messages if necessary.
     */
    private boolean validateInput() throws SQLException, NoSuchAlgorithmException {
        boolean isValid = true;
        String email = fxadresseC.getText();
        String password = fxmotpasse.getText();
        password = hashPassword(password);

        user_crud user = new user_crud();

        // Validate email address
        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
            isValid = false;
            fxadresseC.setStyle("-fx-border-color: red;");
        } else {
            fxadresseC.setStyle("");
        }

        // Validate password
        if (password.isEmpty()) {
            isValid = false;
            fxmotpasse.setStyle("-fx-border-color: red;");
        } else {
            fxmotpasse.setStyle("");
        }
        if (!user.FoundUser(email, password)) {
            isValid = false;

        }

        return isValid;
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedhash);
    }
}
