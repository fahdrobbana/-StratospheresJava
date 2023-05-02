/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui;


import users.entity.Article;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import users.services.ServiceArticle;
import users.services.ServiceReclamation;


public class StatFXMLController implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setCenter(bulidBarChart());
    }    

    @FXML
    private void handleShowBarChart(ActionEvent event) {
       borderPane.setCenter(bulidBarChart());
       
    }
    /**
     *  builds up
     * @return
     */
    private BarChart  bulidBarChart(){
         CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Articles");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Reclamations");
        BarChart barChart =new BarChart(xAxis, yAxis);
        XYChart.Series data = new XYChart.Series();
       
        ServiceArticle serviceArticle = new ServiceArticle();
        ServiceReclamation serviceReclamation=new ServiceReclamation();
        for (Article art : serviceArticle.afficherTous()) {
             data.getData().add(new XYChart.Data(art.getType(),serviceReclamation.countRecArt(art.getId_article())));
             System.out.println(serviceReclamation.countRecArt(art.getId_article()));
        }
//       data.getData().add(new XYChart.Data("product A",3000));
//       data.getData().add(new XYChart.Data("product B",1500));
//      data.getData().add(new XYChart.Data("product C",500));
        barChart.getData().add(data);
        barChart.setLegendVisible(false);
       
        return barChart;
     
     
       
    }
   

    @FXML
    private void handleOieChart(ActionEvent event) {
        
        ObservableList<PieChart.Data> PieCharData=FXCollections.observableArrayList(
                
        new PieChart.Data("Product A", 3000),
        new PieChart.Data("Product B", 1500),
        new PieChart.Data("Product C", 500)      
        );
        PieChart pieChart= new PieChart(PieCharData);
        pieChart.setTitle("Product sold");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        borderPane.setCenter(pieChart);
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleUpdateData(ActionEvent event) {
        Node node = borderPane.getCenter();
       
        if (node instanceof PieChart){
            PieChart pc =(PieChart) node;
            pc.getData().get(2).setPieValue(2000);
        }
    }
   
}