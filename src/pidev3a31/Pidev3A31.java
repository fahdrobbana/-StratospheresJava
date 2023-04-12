/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a31;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author joujo
 */
public class Pidev3A31 extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/vues/evenement.fxml"));
        primaryStage.getIcons().add(new Image("/ressources/gfllogo.png"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("GFL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


