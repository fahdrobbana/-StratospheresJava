/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import utils.DataSource;

/**
 *
 * @author joujo
 */
public class StatevenementController implements Initializable {
     private BarChart<String, Integer> barchart;
    Connection conn=DataSource.getInstance().getCnx();
    ObservableList<XYChart.Data> data;

    public StatevenementController() {
        this.data = FXCollections.observableArrayList();
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        statistiqueEvenement();//#7f9db9
        // TODO
    }    
   public void statistiqueEvenement(){
       
        try {
            String query= "SELECT evenement.nom, COUNT(participation.id) AS nbr FROM evenement LEFT JOIN participation ON evenement.id=participation.event_id GROUP BY evenement.nom";
            Statement statement=conn.createStatement();
            ResultSet resulset=statement.executeQuery(query);
            
            while(resulset.next()){
                data.add(new XYChart.Data("evenement: "+resulset.getString("nom"), resulset.getInt("nbr")));
                
            }
            XYChart.Series series=new XYChart.Series();
            series.getData().addAll(data);
            
            
            barchart.getData().add(series);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatevenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
