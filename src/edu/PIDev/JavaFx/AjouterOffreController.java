/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.PIDev.JavaFx;


import com.mysql.jdbc.PreparedStatement;

import tools.Myconnect;
import entities.Opportunite;
import services.OpportuniteCrud;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import sample.Main;
 


/**
 * FXML Controller class
 *
 * @author walid
 */
public class AjouterOffreController implements Initializable {

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
    private VBox vb1;
    @FXML
    private TextField iden1;
    @FXML
    private Button Click;
    @FXML
    private TextField Logo;
    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    @FXML
    private TextField description;
    @FXML
    private TextField nom_entreprise;
    @FXML
    private TextField taille_entreprise;
    @FXML
    private TextField poste;
    @FXML
    private TextField media;
    @FXML
    private TextField nombre_recrutement;
    @FXML
    private ComboBox<String> Nom_Employeur;
    @FXML
    private Button ajout;
    private TextField ValeurSelectionner;
    @FXML
    private Button Gevent111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         String Requete="select nom from employeur";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
              
            while(rs.next())
                 Nom_Employeur.getItems().add(rs.getString("nom"));
            
            
  
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

  

    

   @FXML
    private void changer(ActionEvent event) { 
        int x=Nom_Employeur.selectionModelProperty().getValue().getSelectedIndex();
        x=x+1;
 ValeurSelectionner.setText(""+x);
        
    }
    @FXML
    private void Ajouter(ActionEvent event) {
        OpportuniteCrud Op=new OpportuniteCrud();
        Opportunite opp=new Opportunite();
        
                int Nbr_recru=Integer.parseInt(nombre_recrutement.getText());

        
        String Titre=titre.getText();
        String Lieu=lieu.getText();
        String Description=description.getText();
        String Nom_entreprise=nom_entreprise.getText();
        String Taille_entreprise=taille_entreprise.getText();
        String Poste=poste.getText();
        String Media=media.getText();
        String log=Logo.getText();
          int nom_emp = 0; 
   
        nom_emp =(int)Nom_Employeur.getSelectionModel().getSelectedIndex()+1;
         
         opp.setOp_employeur_id(nom_emp);
         opp.setNombre_recrutement(Nbr_recru);
         opp.setTitre(Titre);
         opp.setLieu(Lieu);
         opp.setDescription(Description);
       
         opp.setNom_entreprise(Nom_entreprise);
         opp.setTaille_entreprise(Taille_entreprise);
         opp.setPoste(Poste);
         opp.setMedia(Media);
         opp.setLogo(log);
 

 
         if ((log.length()==0)||(Titre.length()==0)||(Lieu.length()==0)||(Description.length()==0)||(Nom_entreprise.length()==0)||(Taille_entreprise.length()==0)||(Poste.length()==0)||(Media.length()==0)||(Nbr_recru<=0)||(nom_emp==0)) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Dialog");
             alert.setHeaderText("Look, an Error Dialog");
             alert.setContentText("Veuillez verifier les champs!");
             
             alert.showAndWait();
         }
         else{ 
             
             Op.addOpportunite(opp);
                     
                     
                     
                     
                     

    
                   
            
         }
    }
      @FXML
    private void UploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
         
        String filename = f.getAbsolutePath();
         
        Logo.setText(filename);
        
        try{
             File image=new File(filename);
        FileInputStream fis=new FileInputStream(image);
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] buf=new byte[1024];
        for(int readNum;(readNum=fis.read(buf))!=-1;)
        {
            bos.write(buf, 0, readNum);
        }
        
            byte[] photo = bos.toByteArray(); 
        }
       catch(IOException e){
           
       }
        
        
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
    private void typeevent(ActionEvent event) {
    }

    @FXML
    private void Ghome(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }
    
}
