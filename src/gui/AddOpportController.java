/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package gui;

import com.mysql.jdbc.PreparedStatement;

import tools.MyConnection;
import entities.Opportunite;
import java.awt.HeadlessException;
import services.OpportuniteCrud;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SortEvent;
import javafx.scene.layout.BorderPane;
import javax.swing.JFileChooser;
import sample.Main;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class AddOpportController implements Initializable {
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
    private ComboBox<String> NoRechercheEntreprise;
    private ComboBox<String>TitreEntreprise;
    private TableColumn<Opportunite, String> id; 
    private TableColumn<Opportunite, String> nombre_recr;
    @FXML
    private TableColumn<Opportunite, String> titre1;
    @FXML
    private TableColumn<Opportunite, String>lieu1;
    @FXML
    private TableColumn<Opportunite, String>descrip;
    @FXML
    private TableColumn<Opportunite, String>nom_entre;
    @FXML
    private TableColumn<Opportunite, String>taille_entre;
    @FXML
    private TableColumn<Opportunite, String> poste1;
    @FXML
    private TableColumn<Opportunite, String> media1;
       private TableColumn<Opportunite, String> Nom;


    ObservableList<Opportunite> OpportuniteList=FXCollections.observableArrayList();
    
        int index=-1;
          @FXML
    private TableView<Opportunite> OpportuniteTable;
    @FXML
    private TableColumn<?, ?> recru;
    private TableColumn<?, ?> recru1;
    @FXML
    private TextField iden1;
    @FXML
    private Button Modifier;
    @FXML
    private TableColumn<?, ?> recru2;
    @FXML
    private TableColumn<?, ?> recru3;
    private TextField nombre_recrutement1;
    @FXML
    private TextField ValeurSelectionner;
    @FXML
    private Button Delete;
    @FXML
    private ComboBox<String> RechTitre;
    private VBox vbox1;
    
     
 
  
    int i=5 ; String filename="Abcd"; byte[] photo;
    private ListView<Opportunite> list;
    @FXML
    private VBox vb1;
    @FXML
    private Button Gtypeevent;
    @FXML
    private Button Ghome;
    @FXML
    private Button Gevent;
    @FXML
    private Button Gevent1;
    @FXML
    private Button Offre;
    @FXML
    private Button Gevent11;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button Click;
    @FXML
    private TextField Logo;
    @FXML
    private TableColumn<?, ?> Photo;
    @FXML
    private Button AjOff;
    @FXML
    private BorderPane mainpain;
    @FXML
    private Button Gevent111;
    @FXML
    private Button catpub;
    @FXML
    private Button pub;
    @FXML
    private Button employer1;
    @FXML
    private Button employeur1;

    public AddOpportController() {
         
    }
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
        
        String Requete="select nom from employeur";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
              
            while(rs.next())
                 Nom_Employeur.getItems().add(rs.getString("nom"));
            
           
  
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
           loading();
           RechercheEntreprise();
           Actualiser();
           SearchTiltle();
            
    }
    
   
    
    private void loading() {
       
         recru2.setCellValueFactory(new PropertyValueFactory<>("id")); 
         recru.setCellValueFactory(new PropertyValueFactory<>("op_employeur_id"));  
         recru3.setCellValueFactory(new PropertyValueFactory<>("nombre_recrutement"));
        
           
       titre1.setCellValueFactory(new PropertyValueFactory<>("titre"));
       lieu1.setCellValueFactory(new PropertyValueFactory<>("lieu"));
       descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
       nom_entre.setCellValueFactory(new PropertyValueFactory<>("nom_entreprise"));
       taille_entre.setCellValueFactory(new PropertyValueFactory<>("taille_entreprise"));
       poste1.setCellValueFactory(new PropertyValueFactory<>("poste"));   
       media1.setCellValueFactory(new PropertyValueFactory<>("media"));
        Photo.setCellValueFactory(new PropertyValueFactory<>("logo"));
 
                         
     
        
    }
    int etat=0;
 
     private void Actualiser()
    {
         if(etat==0)
         {
       String Requete="select * from opportunite " ;
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
            
            while(rs.next())
            {
  OpportuniteList.add(new Opportunite(
  
  rs.getInt("id"), 
  rs.getInt("op_employeur_id"),
 
  rs.getString("titre"),
  rs.getString("lieu"),
  rs.getString("description"),
  rs.getString("nom_entreprise"),
  rs.getString("taille_entreprise"),
  rs.getString("poste"),
  rs.getString("media"),
  rs.getInt("Nombre_recrutement"),
  rs.getString("logo")

  ));
  OpportuniteTable.setItems(OpportuniteList);
     
        

         }
        }
        catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
         }
    }
@FXML
    private void GetSelect(javafx.scene.input.MouseEvent event) {
         index=OpportuniteTable.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
            
        } String x=recru.getCellData(index).toString();
        int y=Integer.parseInt(x);
        Nom_Employeur.getSelectionModel().select(y-1);
        System.out.println(y);
        iden1.setText(recru2.getCellData(index).toString());   
  
                
            
             
        nombre_recrutement.setText(recru3.getCellData(index).toString());
        titre.setText(titre1.getCellData(index));
        lieu.setText(lieu1.getCellData(index));
        description.setText(descrip.getCellData(index));
        nom_entreprise.setText(nom_entre.getCellData(index));
        taille_entreprise.setText(taille_entre.getCellData(index));
        poste.setText(poste1.getCellData(index));
        media.setText(media1.getCellData(index));
        Logo.setText(Photo.getCellData(index).toString());
 
    }

    @FXML
    private void Update(ActionEvent event) {
         
        try {
             PreparedStatement pst = null;
         OpportuniteCrud Op=new OpportuniteCrud();
        Opportunite opp=new Opportunite();
        
        String id=iden1.getText();
        String Titre=titre.getText();
        String Lieu=lieu.getText();
        String Description=description.getText();
        String Nom_entreprise=nom_entreprise.getText();
        String Taille_entreprise=taille_entreprise.getText();
        String Poste=poste.getText();
        String Media=media.getText();
        int Nbr_recru=Integer.parseInt(nombre_recrutement.getText());
         String Nom_Employeu=Nom_Employeur.getValue();
                
      
     
       String log=Logo.getText();
              
          
         String Requete="update opportunite set titre='"+Titre+"',lieu='"+Lieu+"',description='"+Description+"',nom_entreprise='"+Nom_entreprise+"',taille_entreprise='"+Taille_entreprise+"',poste='"+Poste+"',media='"+Media+"',nombre_recrutement='"+Nbr_recru+"' ,op_employeur_id='"+ValeurSelectionner.getText()+"',logo='"+log+"' where id='"+id+"'";
        pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              boolean rs = pst.execute();
             JOptionPane.showMessageDialog(null, "L'element est modifé avec succeés");
        }
         catch (NumberFormatException | SQLException | HeadlessException ex) {
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
    private void Delete(ActionEvent event) {
        
        
               
        try {
             PreparedStatement pst = null;
         OpportuniteCrud Op=new OpportuniteCrud();
        Opportunite opp=new Opportunite();
        
        String id1=iden1.getText();
        
                
              
                
           
        String Requete="delete  from opportunite where id='"+id1+"'";
        pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              boolean rs = pst.execute();
             JOptionPane.showMessageDialog(null, "L'element est supprimé avec succés");
        }
         catch (SQLException | HeadlessException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


        private void RechercheEntreprise() {
        String Requete="select distinct(nom_entreprise) from opportunite";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
              
            while(rs.next())
                 NoRechercheEntreprise.getItems().add(rs.getString("nom_entreprise"));
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
           private void SearchTiltle() {
        String Requete="select distinct(titre) from opportunite";
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
              
            while(rs.next())
                 RechTitre.getItems().add(rs.getString("titre"));
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
  

    private void search(ActionEvent event) {
         ObservableList<Opportunite> filterList=FXCollections.observableArrayList();
         
      String   RechEntr =NoRechercheEntreprise.getSelectionModel().getSelectedItem();
     String   RechTitr =RechTitre.getSelectionModel().getSelectedItem();   
        String Requete="select * from opportunite where nom_entreprise='"+RechEntr+"' OR titre='"+RechTitr+"' ";
             PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
            while(rs.next())
            {
          
              OpportuniteTable.getItems().stream().map((Opportunite op) -> {
                  String filter=op.getNom_entreprise();
                  String filter1=op.getTitre();
                  
                  if(filter.equals(RechEntr))
                  {
                      OpportuniteList.setAll(op);
                      
                      
                      
                      
                      
                      
                  }
                  
                  
                  return op;
              }).forEachOrdered((_item) -> {
     
            });
              
           }  
                 

         OpportuniteTable.setItems(OpportuniteList);
        } catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        OpportuniteList.clear(); 
        etat=1;
               
      String   RechEntr =NoRechercheEntreprise.getSelectionModel().getSelectedItem();
        String Requete="select * from opportunite where nom_entreprise='"+RechEntr+"'" ;
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
            
            while(rs.next())
            {
  OpportuniteList.add(new Opportunite(
  
  rs.getInt("id"), 
  rs.getInt("op_employeur_id"),
 
  rs.getString("titre"),
  rs.getString("lieu"),
  rs.getString("description"),
  rs.getString("nom_entreprise"),
  rs.getString("taille_entreprise"),
  rs.getString("poste"),
  rs.getString("media"),
  rs.getInt("Nombre_recrutement"),
           rs.getString("logo")
  ));
  OpportuniteTable.setItems(OpportuniteList);
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @FXML
    private void clear1(ActionEvent event) {
        OpportuniteList.clear(); 
        etat=1;
          String   RechTit =RechTitre.getSelectionModel().getSelectedItem();
           String   RechEntr =NoRechercheEntreprise.getSelectionModel().getSelectedItem();
        String Requete="select * from opportunite where titre='"+RechTit+"' and nom_entreprise='"+RechEntr+"'" ;
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new MyConnection().getCnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
            
            while(rs.next())
            {
  OpportuniteList.add(new Opportunite(
  
  rs.getInt("id"), 
  rs.getInt("op_employeur_id"),
 
  rs.getString("titre"),
  rs.getString("lieu"),
  rs.getString("description"),
  rs.getString("nom_entreprise"),
  rs.getString("taille_entreprise"),
  rs.getString("poste"),
  rs.getString("media"),
  rs.getInt("Nombre_recrutement"),
  rs.getString("logo")
  ));
  OpportuniteTable.setItems(OpportuniteList);
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
        
        
    
        
    

    private void ListOffre(ActionEvent event) {
        OpportuniteCrud Op=new OpportuniteCrud();
        Opportunite opp=new Opportunite();
        
      JOptionPane.showMessageDialog(null, list.getSelectionModel().getSelectedItem());

    }

    
        
 
       
 

    @FXML
    private void UploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
         
         filename=f.getAbsolutePath();
         
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
        
       photo = bos.toByteArray(); 
        }
       catch(IOException e){
           
       }
        
        
     }
 

   

    @FXML
    private void AjOffres(ActionEvent event) 
      throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("ajouterOffre.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
       loading();
        });
    }

    private void EtatDemande(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(Main.class.getResource("EtatDemande.fxml"));
            AnchorPane rootLayout = null;        long mTime = System.currentTimeMillis();
long end = mTime + 100; // 5 seconds 

while (mTime < end) 
{
    mTime = System.currentTimeMillis();
} 
        try {
            rootLayout = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }

            Scene scene = new Scene(rootLayout, 600, 500);
             Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
   
stage.close();
    }

    @FXML
    private void typeevent(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/typeeventback.fxml"));
            Parent root= loader.load();
  
            Gtypeevent.getScene().setRoot(root);
    }

    @FXML
    private void Ghome(ActionEvent event) throws IOException {
        
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
        
    }

    @FXML
    private void event(ActionEvent event)  throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/evenementback.fxml"));
            Parent root= loader.load();
  
            Gevent.getScene().setRoot(root);
        
        
    }

    @FXML
    private void GestionOffre(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/AddOpport.fxml"));
            Parent root= loader.load();
  
            Offre.getScene().setRoot(root);
    }

    @FXML
    private void GestionCandidature1(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/MiseAjourCand.fxml"));
            Parent root= loader.load();
  
            Gevent1.getScene().setRoot(root);
        

    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Statis.fxml"));
            Parent root= loader.load();
  
            Gevent11.getScene().setRoot(root);
    }

    @FXML
    private void Contact(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/EtatDemande.fxml"));
            Parent root= loader.load();
  
            Gevent11.getScene().setRoot(root);
    }

    @FXML
    private void cattpub(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Home_Categorie.fxml"));
            Parent root= loader.load();
  
            catpub.getScene().setRoot(root);
    }

    @FXML
    private void pub1(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Home_Publication.fxml"));
            Parent root= loader.load();
  
            pub.getScene().setRoot(root);
        
    }

    @FXML
    private void employerlis(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherEm.fxml"));
            Parent root= loader.load();
  
            employer1.getScene().setRoot(root);
    }

    @FXML
    private void employeurlis(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficheEmployeur.fxml"));
            Parent root= loader.load();
  
            employeur1.getScene().setRoot(root);
    }

    @FXML
    private void GestionCandidature(SortEvent event) {
    }
     
}