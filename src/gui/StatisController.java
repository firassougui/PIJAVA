/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mysql.jdbc.PreparedStatement;
import tools.Myconnect;
import entities.Opportunite;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
 

/**
 * FXML Controller class
 *
 * @author walid
 */

public class StatisController implements Initializable {

    @FXML
    private PieChart pieChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
         int i=0,j=0,k=0,Tot=0;Label lb;
        Stage primaryStage = new Stage(); 
                  String Requete="select * from mailing";
                
            PreparedStatement pst = null;
         
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
        
              ResultSet rs = null;
         
            rs = pst.executeQuery();
         
          String name = null;
               String[] monTableau = null ;
     
        
            while(rs.next())
            {
                 
                if(rs.getString("etat").equals("Accepter"))
                {
                    i++;
                }
                if(rs.getString("etat").equals("Refuser"))
                {
                    j++;
                }
                  if(rs.getString("etat").equals("Encours"))
                {
                    k++;
                } 
                   int res=i+j+k;
                ObservableList<PieChart.Data> pieData=FXCollections.observableArrayList(
                        
                        new PieChart.Data("Candidats Refusé"+j,j),
                        new PieChart.Data("Candidats EnCours"+k,k),
                       
                        
                        new PieChart.Data("Candidats Acceptée"+i,i)
                        
                        
                        
                );
                 
                    
             
                
                PieChart pieChart=new PieChart(pieData);
                
//Ajouter pierchart to sceneroot
Group root = new Group(pieChart);
Scene scene = new Scene(root,1200,800);




primaryStage.setTitle("Charte Demo");
primaryStage.setScene(scene);
primaryStage.show();
            }
        }catch (SQLException ex) {
            Logger.getLogger(StatisController.class.getName()).log(Level.SEVERE, null, ex);
        } 

        
    }    

   
    
    
}
