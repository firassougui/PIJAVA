/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Config.config;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import entities.employer;
import services.EmployerCrud;
import tools.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.text.Document;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author MSI
 */
public class afficherEmController implements Initializable {
     
   @FXML
    private TableView<employer> table;

    @FXML
    private TableColumn<employer, String> col_nom;

    @FXML
    private TableColumn<employer, String> col_mail;

    @FXML
    private TableColumn<employer, Integer> col_num;

    @FXML
    private TableColumn<employer, String> col_localisation;

    @FXML
    private TableColumn<employer, String> col_categorie;

    private TableColumn<employer, String> col_image;

    @FXML
    private JFXTextField idemployer;

    @FXML
    private Button add;
    
    Stage primaryStage = new Stage();
    String chemin = "";

    @FXML
    private JFXTextField idem;
    

       ObservableList<employer> oblist = FXCollections.observableArrayList();
    @FXML
    private Button chercher1;
    @FXML
    private ImageView image_em;
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
    private Button Gevent111;
    @FXML
    private Button Gevent11;
    @FXML
    private Button catpub;
    @FXML
    private Button pub;
    @FXML
    private Button employer1;
    @FXML
    private Button employeur1;

    
   
    
    private void setCellfromtabletoImage() {
        table.setOnMouseClicked(e -> {

            employer ac = table.getItems().get(table.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getImg());
        image_em.setImage(image);
        }
        );

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            Connection con =MyConnection.getInstance().getCnx();
            
            
            ResultSet rs;
            
            rs = con.createStatement().executeQuery("SELECT * FROM employer");
            
            System.out.println("f");
            while (rs.next())
            {  
                employer r=new employer(rs.getString(3), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
               
                oblist.add(r);
            }
            
            
             System.out.println("f");
        
            
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            col_num.setCellValueFactory(new PropertyValueFactory<>("num"));
            col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
              table.getItems().clear();

                
            
            
           
            
            System.out.println("ffff");
            
            table.setItems(oblist);
            System.out.println("ffff");
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }

    }    
    
    
    @FXML
    void handleAddAction(ActionEvent event) {
        chemin = "ajoutEm.fxml";
	new OpenWindow2(primaryStage,chemin, "");

    }

    @FXML
    void handleBSupprimerEvent(ActionEvent event) throws SQLException {
          employer r = new employer(Integer.parseInt(idemployer.getText()));
        if (r.exist()==true)
        {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer cette employer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
        {
            r.Delete();
        }
        }
        else 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"employer inexistante",ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    @FXML
    void modifier(ActionEvent event) {
          chemin = "modifierEm.fxml";
	new OpenWindow2(primaryStage,chemin, "modifier");

    }

    @FXML
     void recherche(ActionEvent event) {
        String s=idem.getText();
        if (s.isEmpty())
        {
           try {
            Connection con =MyConnection.getInstance().getCnx();
            
            
            ResultSet rs;
            
            rs = con.createStatement().executeQuery("SELECT * FROM employer");
            
            System.out.println("f");
            while (rs.next())
            {   employer r=new employer(rs.getString(3),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
                oblist.add(r);
            }
            
            
             System.out.println("f");
        
            
            
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            col_num.setCellValueFactory(new PropertyValueFactory<>("num"));
            col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
            
            
           
            
            System.out.println("ffff");
            
            table.setItems(oblist);
            System.out.println("ffff");
            
        } catch (SQLException ex) {
           
            System.out.println(ex);
        }
        }
        else 
        {
        table.getItems().clear();
         
            
            
            oblist.clear();
              try {
            Connection con =MyConnection.getInstance().getCnx();
            
            
            ResultSet rs;
            
            rs = con.createStatement().executeQuery("SELECT * FROM employer where id='  " + idem.getText() + "'");
            
            System.out.println("f");
            while (rs.next())
            {    employer r=new employer(rs.getString(3), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
                oblist.add(r);
                System.out.println("nnnnnn");
            }
            
            
             System.out.println("f");
        
            
            col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            col_num.setCellValueFactory(new PropertyValueFactory<>("num"));
            col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
            
           
            
            System.out.println("cccc");
            
            table.setItems(oblist);
            System.out.println("ffff");
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }

    }
        
  }
      @FXML
    void pdfs (ActionEvent event) throws SQLException, DocumentException {
        try {
                    Connection con = config.getInstance().getConnection();

        ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT e.id,e.name,e.mail,e.num , e.localisation, e.categorie  FROM employer e");
                    /* Step-2: Initialize PDF documents - logical objects */
                    com.itextpdf.text.Document my_pdf_report = new com.itextpdf.text.Document();
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Liste Employers.pdf"));
                    my_pdf_report.open();            
                    //we have four columns in our table
                    PdfPTable my_report_table = new PdfPTable(5);
                    //create a cell object
                    PdfPCell table_cell;

                    while (rs.next()) {                
                                    String dept_id = rs.getString("id");
                                    table_cell=new PdfPCell(new Phrase(dept_id));
                                    my_report_table.addCell(table_cell);
                                   
                                    String id_event=rs.getString("name");
                                    table_cell=new PdfPCell(new Phrase(id_event));
                                    my_report_table.addCell(table_cell);
                                    
                                    String id_client=rs.getString("num");
                                    table_cell=new PdfPCell(new Phrase(id_client));
                                    my_report_table.addCell(table_cell);
                                    
                                    String dept_name=rs.getString("localisation");
                                    table_cell=new PdfPCell(new Phrase(dept_name));
                                    my_report_table.addCell(table_cell);
                                    
                                    String manager_id=rs.getString("categorie");
                                    table_cell=new PdfPCell(new Phrase(manager_id));
                                    my_report_table.addCell(table_cell);
                                    
                                    
                                    }
                    /* Attach report table to PDF */
                    my_pdf_report.add(my_report_table);                       
                    my_pdf_report.close();

                    /* Close all DB related objects */
                    rs.close();
                    
                           



    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
        
    }
         String title = "Succes! ";
        String message = "Le fichier PDF est generé";

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));

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
    
    
}
