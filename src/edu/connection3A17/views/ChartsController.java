/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class ChartsController implements Initializable {

    @FXML
    private Button but_load;
    @FXML
    private BarChart<String,Double> BarCharts;
    private Connection conx; 
    @FXML
    private Button but_back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadCharts(ActionEvent event) {
        
         String querry= "select destination,amout FROM transport ORDER BY destination ASC";
         
         XYChart.Series<String,Double> series = new XYChart.Series<>();
         try {
             
             conx =connectBD();
             ResultSet rs = conx.createStatement().executeQuery(querry);
             while(rs.next()){
             series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2))); 
                     }
             BarCharts.getData().add(series);
            
        } catch (Exception e) {
        }
        //select amount from transport where destination="Bizerte";
        //select amount*100/sum(amount) from transport;
    
    }
    
    private Connection connectBD (){
        
        try {
            String url = "jdbc:mysql://localhost:3306/esprit";
            String login = "root";
            String mdp = "";
            
            Connection conn= DriverManager.getConnection(url, login, mdp);
            System.out.println("Connection");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       return null ;

}

    @FXML
    private void Back(ActionEvent event) {
          try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("Transprt1.fxml"));
             but_back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
