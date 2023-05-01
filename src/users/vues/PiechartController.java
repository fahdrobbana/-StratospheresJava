/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.vues;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import users.utils.MyConnectionn;

/**
 *
 * @author joujo
 */
public class PiechartController  implements Initializable{

    @FXML
    private PieChart piechart;
     private Statement st;
    private ResultSet rs;
    private Connection cnx;
    
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx=MyConnectionn.getInstance().getConn();
        stat();
    }    
     private void stat()
    {
          
           
      try {
           
        String query = "SELECT evenement.nom , nbr_personnes FROM evenement ORDER BY nbr_personnes DESC";
            PreparedStatement statement = cnx.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString("evenement.nom"), rs.getInt("nbr_personnes")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        piechart.setTitle("**Statistiques nombres participants**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }
    
    
    
}
