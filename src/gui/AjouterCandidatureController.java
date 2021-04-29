/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mysql.jdbc.PreparedStatement;
import tools.Myconnect;
import entities.Candidature;
import entities.Opportunite;
import java.io.IOException;
import services.CandidatureCrud;
import services.OpportuniteCrud;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class AjouterCandidatureController implements Initializable {

   
     
     
 
    @FXML
    private Button Gtypeevent;
    @FXML
    private Button Ghome;
    @FXML
    private Button Gevent;
    @FXML
    private Button Offre;
    @FXML
    private Button Gevent1;
    @FXML
    private Button Gevent11;
    @FXML
    private Button ajout;
    @FXML
    private TextField fonction;
    @FXML
    private ComboBox<String> type_contrat;
    @FXML
    private ComboBox<String> horaires;
    @FXML
    private TextField Mode_salaire;
    
    @FXML
    private TextField annuel_mois;
    @FXML
    private ComboBox<String> titre;
     
    @FXML
    private TextField period;
    @FXML
    private VBox periode;
    @FXML
    private TextField iden1;
   
int titr = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         type_contrat.getItems().add("CDD");
         type_contrat.getItems().add("CDI");
         
          horaires.getItems().add("TempPlein");
          horaires.getItems().add("TempPartiel");
          
             
        String Requete="select titre from Opportunite";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
              
            while(rs.next())
                 titre.getItems().add(rs.getString("titre"));
            
           
  
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    

    @FXML
    private void typeevent(ActionEvent event) {
    }

    @FXML
    private void Ghome(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

   @FXML
    private void GestionOffre(ActionEvent event) throws IOException {
  

           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/AddOpport.fxml"));
            Parent root= loader.load();
  
            Offre.getScene().setRoot(root);
        
    
    }
    @FXML
    private void GestionCandidature(ActionEvent event) throws IOException {
    

              FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/MiseAjourCand.fxml"));
            Parent root= loader.load();
  
            Gevent1.getScene().setRoot(root);

        }
        
    
    
 @FXML
    private void Contact(ActionEvent event)throws Exception {  
    
        
 
       
 
try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("EtatDemande.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout, 600, 500);
             Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
        
        }
    @FXML
    private void Statistique(ActionEvent event)throws Exception {  
    
        
 
       
 
try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Statis.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

          

        } catch(Exception e) {
            e.printStackTrace();
        }
        
        }

    @FXML
    private void Ajouter(ActionEvent event) {
           CandidatureCrud Cand=new CandidatureCrud();
           Candidature cand=new Candidature();
        
 
        String Fonction=fonction.getText();
         
         String mode_salaire=Mode_salaire.getText();
        String Periode=period.getText();
         int Annuel_mois=Integer.parseInt(annuel_mois.getText());
           
        
          int titr = 0; 
   if(titre.getSelectionModel().getSelectedIndex()!=-1)
   {
 String Requete="select * from Opportunite  where titre='"+titre.getSelectionModel().getSelectedItem().toString()+"'";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
               int i=0;
            while(rs.next())
           
                 titr=rs.getInt("id");
           
       
                
            
  
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }          
        
        
   }
           
   
      
          
       
        
       
        
        
        
         int Type_contrat = 0; 
   
        Type_contrat =(int)type_contrat.getSelectionModel().getSelectedIndex()+1;
        int Horaires = 0; 
   
        Horaires =(int)horaires.getSelectionModel().getSelectedIndex()+1;
         
         
        
         cand.setTitre_id(titr);
         cand.setFonction(Fonction);
         cand.setType_contrat(""+Type_contrat);
         cand.setHoraires(""+Horaires);
         cand.setMode_salaire(mode_salaire);
       
             cand.setPeriode(Periode);   
              cand.setAnnuel_mois(Annuel_mois);

 
         if ((Fonction.length() ==0)|(Periode.length()==0)||(Horaires==0)||(Annuel_mois==0)||(titre.getSelectionModel().getSelectedIndex()==-1)||(mode_salaire.length()==0)||(Type_contrat==-1)) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Dialog");
             alert.setHeaderText("Look, an Error Dialog");
             alert.setContentText("Veuillez verifier les champs!");
             
             alert.showAndWait();
         }
         else{ 
             
             Cand.addCandidature(cand);
                     
                     
                     
                     
                     

    
                   
            
         }
    }
    
}
