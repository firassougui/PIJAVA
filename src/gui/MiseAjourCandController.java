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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sample.Main;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class MiseAjourCandController implements Initializable {

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
    private VBox periode;
    @FXML
    private TextField iden1;
    @FXML
    private TextField fonction;
    @FXML
    private ComboBox<String> type_contrat;
    @FXML
    private ComboBox<String> horaires;
    @FXML
    private TextField Mode_salaire;
    @FXML
    private TextField period;
    @FXML
    private TextField annuel_mois;
    @FXML
    private ComboBox<String> titre;
    @FXML
    private TableColumn<?, ?> fonction1;
    @FXML
    private TableColumn<?, ?> type_contrat1;
    @FXML
    private TableColumn<?, ?> horaires1;
    @FXML
    private TableColumn<?, ?> mde_salaire1;
    @FXML
    private TableColumn<?, ?> periode1;
    @FXML
    private TableColumn<?, ?> annuel_mois1;
    @FXML
    private TableColumn<?, ?> titre1;
    @FXML
    private Button Modifier;
    @FXML
    private Button Delete;
    @FXML
    private TableView<Candidature> CandidatureTable;
int index=-1;
private ListView<Candidature> list;
    ObservableList<Candidature> CandidatureList=FXCollections.observableArrayList();

    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private TextField ValeurSelectionner;
    @FXML
    private Button Gevent111;
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
        loading();
        Actualiser();
    }    
int etat=0;
 
     private void Actualiser()
    {
         if(etat==0)
         {
       String Requete="select * from candidature " ;
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
            
            while(rs.next())
            {
  CandidatureList.add(new Candidature(
  
  rs.getInt("id"), 
  rs.getInt("titre_id"),
  rs.getString("fonction"),
  rs.getString("type_contrat"),
  rs.getString("horaires"),
  rs.getString("mode_salaire"),
  rs.getString("periode"),
  rs.getInt("annuel_mois")

  ));
  CandidatureTable.setItems(CandidatureList);
     
        

         }
        }
        catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
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
    private void GestionOffre(ActionEvent event) {
    try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddOpport.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout, 800, 800);
             Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
        
    
    }
    @FXML
    private void GestionCandidature(ActionEvent event) {
    try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MiseAjourCand.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout, 800, 800);
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
    private void Contact(ActionEvent event) {
         
       
 
try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Mail.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout, 600, 500);
             Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 private void loading() {
       
         id1.setCellValueFactory(new PropertyValueFactory<>("id"));
         fonction1.setCellValueFactory(new PropertyValueFactory<>("fonction"));  
         type_contrat1.setCellValueFactory(new PropertyValueFactory<>("type_contrat"));
         titre1.setCellValueFactory(new PropertyValueFactory<>("titre_id"));
         horaires1.setCellValueFactory(new PropertyValueFactory<>("horaires"));
         mde_salaire1.setCellValueFactory(new PropertyValueFactory<>("mode_salaire"));
         periode1.setCellValueFactory(new PropertyValueFactory<>("periode"));
         annuel_mois1.setCellValueFactory(new PropertyValueFactory<>("annuel_mois"));
       
                         
     
        
    }
    @FXML
    private void Update(ActionEvent event) {
         try {
             PreparedStatement pst = null;
          CandidatureCrud Op=new CandidatureCrud();
          Candidature cand=new Candidature();
        
         String Fonction=fonction.getText();
         
         int Type_contrat = 0; 
         Type_contrat =(int)type_contrat.getSelectionModel().getSelectedIndex()+1;
         int Horaires = 0; 
         Horaires =(int)horaires.getSelectionModel().getSelectedIndex()+1;
         
         String Mode_Salaire=Mode_salaire.getText();
         String Periode=period.getText();
         int Annuel_mois=Integer.parseInt(annuel_mois.getText());
        int titr = 0; 
         titr =(int)titre.getSelectionModel().getSelectedIndex()+1;
        
                String id=iden1.getText();
      
                
        String Requete1="select * from Opportunite  where titre='"+titre.getSelectionModel().getSelectedItem().toString()+"'";
                
            PreparedStatement pst1 = null;
        try {
            pst1 = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete1);
              ResultSet rs1 = pst1.executeQuery();
          String name = null;
               int i=0;
            while(rs1.next())
           
                 titr=rs1.getInt("id");
           
       
                
            
  
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
              
          
         String Requete="update candidature set titre_id='"+titr+"',type_contrat='"+Type_contrat+"',horaires='"+Horaires+"',mode_salaire='"+Mode_Salaire+"',periode='"+Periode+"',annuel_mois='"+Annuel_mois+"' where id='"+id+"'";
        pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              boolean rs = pst.execute();
             JOptionPane.showMessageDialog(null, "L'element est modifé avec succeés");
        }
         catch (Exception ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void getselect(MouseEvent event) {
          index=CandidatureTable.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
            
        } 

            iden1.setText(id1.getCellData(index).toString());
          String x=type_contrat1.getCellData(index).toString();
           int y=Integer.parseInt(x);
            type_contrat.getSelectionModel().select(y-1);
        
        
          String x1=horaires1.getCellData(index).toString();
        int y1=Integer.parseInt(x1);
        horaires.getSelectionModel().select(y1-1);
   
         
          fonction.setText(fonction1.getCellData(index).toString());
          Mode_salaire.setText(mde_salaire1.getCellData(index).toString());
        
        
          period.setText(periode1.getCellData(index).toString());
          annuel_mois.setText(annuel_mois1.getCellData(index).toString());
  String tit = null; 
        String Requete="select * from Opportunite ";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
               int i=0;
            while(rs.next())
            if(i<=rs.getRow()-1)
              {
                  if(rs.getString("id").equals(titre1.getCellData(index).toString()))
 
             
                  titre.getSelectionModel().select(i);
                i++;
              }
           
       
                
            
  
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changer(ActionEvent event) {
        int x=type_contrat.selectionModelProperty().getValue().getSelectedIndex();
        x=x+1;
 ValeurSelectionner.setText(""+x);
    int x1=horaires.selectionModelProperty().getValue().getSelectedIndex();
        x1=x1+1;
 ValeurSelectionner.setText(""+x1);
    }

    @FXML
    private void AddCand(ActionEvent event)  throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("AjouterCandidature.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
       loading();
        });
    }
         
       
 

    
}
