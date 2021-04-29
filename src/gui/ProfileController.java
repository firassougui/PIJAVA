/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Config.config;
import gui.LoginController;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import entities.employer;
import services.EmployerCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tools.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProfileController implements Initializable {
    String mail ;
    String mdp ; 
    private TableView<employer> table;

    private TableColumn<employer, String> col_nom;

    private TableColumn<employer, String> col_mail;

    private TableColumn<employer, Integer> col_num;

    private TableColumn<employer, String> col_localisation;

    private TableColumn<employer, String> col_categorie;

    private TableColumn<employer, String> col_image;

    
    Stage primaryStage = new Stage();
    String chemin = "";



       ObservableList<employer> oblist = FXCollections.observableArrayList();
      
    @FXML
    private Button home;
    @FXML
    private Button Participationg;
    @FXML
    private Button opportunite;
    @FXML
    private VBox vbox1;
    @FXML
    private Button events1;
    @FXML
    private Button publication;
    @FXML
    private Button profil;

    
   
    
    
 /*   @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyConnection jdbcDao = new MyConnection();
      
     
        // TODO
         try {
              int k = jdbcDao.getId();
              System.out.println(k);
         
            Connection con =MyConnection.getInstance().getCnx();
            
            
            ResultSet rs;
            
               rs = con.createStatement().executeQuery("SELECT * FROM employer where id='  " + k + "'");
            
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
            col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
            
           
            
            System.out.println("ffff");
            
            table.setItems(oblist);
            System.out.println("ffff");
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }

    }   
*/
     public void initialize(URL url, ResourceBundle rb) {
      List<employer> le = new ArrayList<>();
        EmployerCrud es = new EmployerCrud();
        
  
        ObservableList<String> l = null;
        try {
            le = es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        for (employer evv : le) {
       
                InputStream stream = null;
                Label Name = new Label();
                Label Desc = new Label();
                Label Mail = new Label();
                 Label Num = new Label();
                Label Localisation = new Label();
                Label Categorie = new Label();
                Label nombre_par = new Label();
                
                
          try {
              stream = new FileInputStream("C:\\wamp64\\www\\PIWEB\\public\\assets\\img\\image.JPEG");
          } catch (FileNotFoundException ex) {
              Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
          }
                    Image image = new Image(stream);
                    ImageView photo =new ImageView(image);
                    photo.setFitHeight(150);
                    photo.setFitWidth(150);
                    
                
               
                Name.setText(evv.getName());
                Mail.setText(evv.getMail());
               
                Localisation.setText(evv.getLocalisation());
                Categorie .setText(evv.getCategorie());
                 vbox1.setSpacing(10);
                 vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
                 final Separator sep = new Separator();
                Text ty = new Text("Mail : ");
                Text ty1 = new Text("Localisation :");
                 Text ty2 = new Text("Categorie :");
                  Text ty3 = new Text("Name :");
                
                  HBox h1 = new HBox();
                   HBox h2 = new HBox();
                    VBox rv = new VBox();
                    HBox btn = new HBox();
                    VBox v1 = new VBox();
                    VBox v2 = new VBox();
                    HBox hv1 = new HBox();
                    String nom = null;
                    hv1.getChildren().add(ty3);
                    hv1.getChildren().add(Name);
                     sep.setMaxWidth(Double.MAX_EXPONENT);
                    sep.setStyle(" -fx-text-fill: DARKGREY;");
                    h1.setAlignment(Pos.CENTER);
                    h2.setAlignment(Pos.CENTER);
                    h2.setSpacing(10);
                     h1.getChildren().add(photo);
                    v1.getChildren().add(ty);
                    v1.getChildren().add(Mail);
                    h2.getChildren().add(ty1);
                    h2.getChildren().add(Localisation);
                    h2.getChildren().add(Desc);
                     h2.getChildren().add(ty2);
                    h2.getChildren().add(Categorie);
                    vbox1.getChildren().add(hv1);
                    vbox1.getChildren().add(v1);
                    vbox1.getChildren().add(v2);
                    vbox1.getChildren().add(h1);
                    vbox1.getChildren().add(h2);
                    vbox1.getChildren().add(btn);
                     vbox1.setStyle("-fx-padding: 10;"
                            + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                            + "-fx-border-radius: 5;"  + "-fx-border-height:50");
                   
                    
                  
            
    }    
    }    
 @FXML
    private void Participation(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listePar.fxml"));
            Parent root= loader.load();
  
            Participationg.getScene().setRoot(root);
    }

    @FXML
    private void opportunite(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/StartOffre.fxml"));
            Parent root= loader.load();
  
            opportunite.getScene().setRoot(root);
    }

    @FXML
    private void publica(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Front_Publication.fxml"));
            Parent root= loader.load();
  
            publication.getScene().setRoot(root);
    }

    @FXML
    private void profil(ActionEvent event) throws IOException {
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Profile.fxml"));
            Parent root= loader.load();
  
            profil.getScene().setRoot(root);
        
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherevenement.fxml"));
            Parent root= loader.load();
  
            events1.getScene().setRoot(root);
    }

 
      
    
}