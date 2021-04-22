/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevv;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class StatiController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private Button load;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadchar(ActionEvent event) {
        
        
           XYChart.Series set1 = new XYChart.Series<>();

         try {
            
            String requete = "SELECT  publication.titre, publication.likes AS Nombre_de_Fois FROM publication GROUP BY publication.titre";
          
            
            XYChart.Series<String,Integer> series=new XYChart.Series<>();
            
            //execute querry and store it in resulttest
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data<>( rs.getString(1),rs.getInt(2)));
                chart.getData().add(series);
                
            }
                // TODO
            } catch (SQLException ex) {    
            Logger.getLogger(StatiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
        
        
        
        
        
    }
    
}
