package users.gui.login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import users.entity.User;
import users.services.user_crud;

public class ResetPasswordController {

    @FXML
    private PasswordField fxmotpasse;

    @FXML
    private PasswordField fxCmotpasse;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @FXML
    void Comfirmer(ActionEvent event) throws SQLException, IOException {
        String motdepasse = fxmotpasse.getText();
        String confirmer = fxCmotpasse.getText();

        if (motdepasse.isEmpty() || confirmer.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else if (!motdepasse.equals(confirmer)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Les deux mots de passe ne correspondent pas.");
            alert.showAndWait();
        } else {
            user_crud  u = new user_crud ();
            currentUser. setPassword(motdepasse);

            u.modifier(currentUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mot de passe réinitialisé");
            alert.setHeaderText(null);
            alert.setContentText("Votre mot de passe a été réinitialisé avec succès.");
            alert.showAndWait();

            Stage stage = (Stage) fxmotpasse.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.show();
        }
    }

}
