package users.gui.login;

import static users.gui.login.ForgetPaswordController.Ssemail2;
import users.entity.User;
import users.services.user_crud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.client.repackaged.org.apache.commons.codec.EncoderException;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;

import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static javax.mail.Message.RecipientType.TO;

public class Identifier_votre_compteController implements Initializable {

    @FXML
    private Label ssemails;
    @FXML
    private ListView<User> lvUser;
    @FXML
    private user_crud userService;
    @FXML
    private static final String TEST_EMAIL = "aymen.zouaoui@esprit.tn";
    @FXML
    private Gmail service;
   
    static String  emailS ;

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        userService = new user_crud();
        lvUser.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getName() + " " + user.getFirstname());
                }
            }
        });

        lvUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Display an alert to confirm password reset
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de la réinitialisation de mot de passe");
                alert.setHeaderText("Vous êtes sur le point de réinitialiser le mot de passe de l'utilisateur " + newValue.getName() + " " + newValue.getFirstname());
                alert.setContentText("Êtes-vous sûr de vouloir continuer ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User confirmed password reset
                    // Generate a token and send a password reset email
                    String token = generateToken();
                   
                    String emailAddress = newValue.getEmail();
                    try {
                        System.out.println("1  :"+token);
                        User u = userService.getUserByEmai(emailAddress);
                        System.out.println(u);
                        u.setReset_token(token);
                        userService.modifierbymail(u);
                        System.out.println(u);
                        userService.sendMail(u, token, "Reset Password!");
                        sendMail(emailAddress, token);
                    } catch (Exception ex) {
                        Logger.getLogger(Identifier_votre_compteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void EnvoyerToken(MouseEvent event
    ) {
        String email = ssemails.getText();
       emailS =email;
        try {
            List<User> users = userService.getUsersByEmail(email);
            ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
            lvUser.setItems(observableUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        return token;
    }

    public void setUserInformation(String email) {
        ssemails.setText(email);
    }

    public void GMailer() throws Exception {
        System.out.println("gmail");
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("GFL")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        Collection<String> gm = new ArrayList<>();
        System.out.println("cred");
        List<String> scopes = Arrays.asList(GMAIL_SEND);
        System.out.println();
        gm.add(GMAIL_SEND);
        String json = "{\"installed\":{\"client_id\":\"661650265715-uoc8ipu1blmem4g08h7rgujd7jbgiake.apps.googleusercontent.com\",\"project_id\":\"bubbly-journey-308421\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost\"]}}";
        StringReader reader = new StringReader(json);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, reader);
        //GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(Identifier_votre_compteController.class.getResourceAsStream("‪C:\\Users\\aymen\\Desktop\\code_secret_client.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, scopes)
                //.setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setDataStoreFactory(new FileDataStoreFactory(new File("/path/to/credential-store")))
                .setAccessType("offline")
                .build();
        

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void sendMail(String subject, String message) throws Exception, EncoderException {
/*
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL));
        email.addRecipient(TO, new InternetAddress(Ssemail2));// static variable globale static
        System.out.println(Ssemail2);
        email.setSubject(subject);

        email.setText(message);
        /*email.setText("Bonjour,\n\n"
                + "Voici votre lien de réinitialisation de mot de passe : "
                + "http://localhost:8080/reset_password?token=" + "token");
         */
    /*    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = new String(Base64.encodeBase64(rawMessageBytes));
        String safeString = encodedEmail.replace('+', '-').replace('/', '_');
        //String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            GMailer();
            System.out.println(msg);

            System.out.println("service");
            System.out.println(service);
            Message sentMessage = service.users().messages().send("me", msg).execute();
            System.out.println(sentMessage);
            System.out.println("Message id: " + msg.getId());
            System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
*/
    
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivationC.fxml"));
        Parent root = loader.load();
        //  ResetPasswordController dc = loader.getController();

        //dc.setUserInformation(email);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

}
