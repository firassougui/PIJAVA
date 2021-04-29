/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mysql.jdbc.PreparedStatement;
import tools.Myconnect;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
 import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.mysql.jdbc.PreparedStatement;
 
import edu.PIDev.JavaFx.AddOpportController;
import tools.Myconnect;
import java.awt.BorderLayout;
 
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
 
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

 
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class StartOffreController implements Initializable {
     

    /**
     * Initializes the controller class.
     */
    Stage primaryStage;Stage newWindow = new Stage();
    @FXML
    private ListView<?> ListeOffres;
    @FXML
    private Label Label1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            start();
        } catch (SQLException ex) {
            Logger.getLogger(StartOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
     VBox fenetre = new VBox(); Label lb0kC; String Ref;
                     VBox secondaryLayout = new VBox();

    Stage stage;
   Button btn;
    int id;
int rows;    int res=0;  int Row=0,k=0;               int h=0;
int titre=0;Label lb1;String Label;
    public void start() throws SQLException {
          String value=null;  
    BorderPane roo = new BorderPane();
       
        
        
  roo.setPadding(new Insets(80, 70, 50, 50));
       fenetre.setAlignment(Pos.CENTER);
       
 
        Stage stage = new Stage();  
        Group root = new Group();
            final ScrollBar sc = new ScrollBar();
   Scene scene = new Scene(root,400,400);
             DropShadow shadow = new DropShadow();
              
               Label Title=new Label("LISTES DES OFFRES");                
        Title.setMinWidth(100);
            Title.setMinHeight(100);
            Title.setStyle("-fx-font-weight: bold");
             fenetre.getChildren().add(Title);
        scene.setFill(Color.BEIGE);
        stage.setScene(scene);
        stage.setTitle("Scrollbar");
        root.getChildren().addAll(fenetre, sc);
 
        shadow.setColor(Color.GREY);
        shadow.setOffsetX(8);
        shadow.setOffsetY(8);
 
        fenetre.setLayoutX(10);
        fenetre.setSpacing(10);
 
        sc.setLayoutX(scene.getWidth()-sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(1500);
        sc.setMax(800);
         
      sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    fenetre.setLayoutY(-new_val.doubleValue());
            }
        });
        Group root1 = new Group();
            final ScrollBar sc1 = new ScrollBar();
                            Scene secondScene = new Scene(root1, 400,400);

              DropShadow shadow1 = new DropShadow();
              
        secondScene.setFill(Color.BEIGE);
        stage.setScene(secondScene);
        stage.setTitle("Scrollbar");
        root1.getChildren().addAll(secondaryLayout, sc1);
 
        shadow1.setColor(Color.GREY);
        shadow1.setOffsetX(8);
        shadow1.setOffsetY(8);
        

        
        String blackBorder = "-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black";

        /* Left column */
        Button save = new Button("Save");
        Button del = new Button("Delete");
        HBox settings = new HBox(save, del);
        VBox leftCol = new VBox(settings);
        leftCol.setStyle(blackBorder);

        /* Right column */
       

        /* Set up borderpane */
      
        secondaryLayout.setLayoutX(10);
        secondaryLayout.setSpacing(10);
 
        sc1.setLayoutX(scene.getWidth()-sc.getWidth());
        sc1.setMin(0);
        sc1.setOrientation(Orientation.VERTICAL);
        sc1.setPrefHeight(500);
        sc1.setMax(500);
         
      sc1.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    secondaryLayout.setLayoutY(-new_val.doubleValue());
            }
        });
        
                  String Requete="select * from opportunite";
                
            PreparedStatement pst = null;
        try {   k++;
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
               String[] monTableau = null ;
      
               while(rs.next())
            {
 id=rs.getInt("id");
                rows=rs.getRow()-3; 
               
                h++;
 Button[] buttonList = new Button[h];
   Label[] Lb = new Label[h];
             }
   
           
        
            
                
                String Requete2="select * from opportunite  ";
            PreparedStatement pst3 = null;
        try {
            pst3 = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete2);
              ResultSet rs3 = pst3.executeQuery();
      
              
       
               while(rs3.next())
            {     


                Image image=new Image("file:"+rs3.getString("logo")+"");
                ImageView iv= new ImageView();
                iv.setImage(image);
                iv.setFitHeight(50);
                iv.setFitWidth(50);
                fenetre.getChildren().add(iv);
                
                 
                 Label lb=new Label(""+rs3.getString("id"));
                  fenetre.getChildren().addAll(lb);
                  lb.setTextFill(Color.color(1, 0, 0));
                  lb.setStyle("-fx-font-weight: bold;");
                  
                 lb1=new Label("Titre :"+rs3.getString("titre"));
                        lb1.setTextFill(Color.color(1, 0, 0));
                  lb1.setStyle("-fx-font-weight: bold;");
                   fenetre.getChildren().addAll(lb1);
                  Label lb2=new Label("Description :"+rs3.getString("description"));
                         lb2.setTextFill(Color.color(1, 0, 0));
                  lb2.setStyle("-fx-font-weight: bold;");
                  fenetre.getChildren().addAll(lb2);
                   Label lb3=new Label("Lieu :"+rs3.getString("lieu"));
                   
                  fenetre.getChildren().addAll(lb3);
                         lb3.setTextFill(Color.color(1, 0, 0));
                  lb3.setStyle("-fx-font-weight: bold;");
                    Label lb4=new Label("Nom Entreprise :"+rs3.getString("nom_entreprise"));
                  fenetre.getChildren().addAll(lb4);
                         lb4.setTextFill(Color.color(1, 0, 0));
                  lb4.setStyle("-fx-font-weight: bold;");
                   Label lb5=new Label("Poste :"+rs3.getString("poste"));
                  fenetre.getChildren().addAll(lb5);
                         lb5.setTextFill(Color.color(1, 0, 0));
                  lb5.setStyle("-fx-font-weight: bold;");
                    Label lb6=new Label("Nombre Recrutement :"+rs3.getString("nombre_recrutement"));
                  fenetre.getChildren().addAll(lb6);
                         lb6.setTextFill(Color.color(1, 0, 0));
                  lb6.setStyle("-fx-font-weight: bold;");
                  Label lb7=new Label("Taille_Entreprise :"+rs3.getString("taille_entreprise"));
                  fenetre.getChildren().addAll(lb7);
                         lb7.setTextFill(Color.color(1, 0, 0));
                  lb7.setStyle("-fx-font-weight: bold;");
                   Label lb9=new Label("Taille_Entreprise :");
                    
                  btn=new Button("Voir Plus");
                  fenetre.getChildren().addAll(btn);
                  
                   btn.setOnAction((ActionEvent event) -> {          
              

        try {String Requete4="select * from candidature where titre_id='"+lb.getText()+"'  ";
            PreparedStatement pst4 = null;
            pst4 = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete4);
              ResultSet rs4 = pst4.executeQuery();
            secondaryLayout.getChildren().clear();
                        while(rs4.next())
            {
                Row=rs4.getRow(); 
                
             
                Label lkC=new Label("ID :"+rs4.getString("id")); 
               lb0kC=new Label("Nom de candidature :"+rs4.getString("titre_id")); 
                Label lb1kC=new Label("Fonction :"+rs4.getString("fonction")); 
                Label lb2kC=new Label("Type_contrat :"+rs4.getString("Type_Contrat")); 
                Label lb3kC=new Label("Hraires :"+rs4.getString("horaires")); 
                Label lb4kC=new Label("Type_contrat :"+rs4.getString("mode_salaire"));
                Label lb5kC=new Label("Mode Salaire :"+rs4.getString("periode")); 
                Label lb6kC=new Label("Periode :"+rs4.getString("annuel_mois")); 
            
 
     
                  
              
                      

                
                  if(rs4.getString("titre_id").equals(lb.getText()))
                  {Ref=rs4.getString("titre_id");
                      
                 
                    secondaryLayout.getChildren().add(lkC);
                    secondaryLayout.getChildren().add(lb0kC);
                    secondaryLayout.getChildren().add(lb1kC);
                    secondaryLayout.getChildren().add(lb2kC);
                    secondaryLayout.getChildren().add(lb3kC);
                    secondaryLayout.getChildren().add(lb4kC);
                    secondaryLayout.getChildren().add(lb5kC);
                    secondaryLayout.getChildren().add(lb6kC);
                     Button  bt=new Button("Contacter ");
                     secondaryLayout.getChildren().addAll(bt);
                     bt.setOnAction((ActionEvent event1) -> {      
                  
                         // Recipient's email ID needs to be mentioned.
        String to = "loujaga278@gmail.com";;

        // Sender's email ID needs to be mentioned
        String from = "walidgammoudi1989@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable","true"); 
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("walidgammoudi1989@gmail.com", "Walid4400");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f =new File("C:\\pc.jpg");

                attachmentPart.attachFile(f);
                textPart.setText("Veifier Etat de la demande "+Ref);
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }
                   String insert="INSERT INTO mailing (etat,sujet,ref,email) VALUES ('Encours','Demande','"+Ref+"','"+to+"')";
        try {   
            java.sql.PreparedStatement pr=new Myconnect().getcnx().prepareStatement(insert);
           
            

            pr.executeUpdate();
 JOptionPane.showMessageDialog(null, "Le éléemnt est ajoutéé avec succés ");
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
          JOptionPane.showMessageDialog(null, "Le message est envoyé avec succés ");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
                     });
                    
                  }
                                     
                     // New window (Stage)
                    
                    newWindow.setTitle("Second Stage");
                    newWindow.setScene(secondScene);
                     
                    // Set position of second window, related to primary window.
         
    
               
             
         
              
 }
            newWindow.show();
                
 }  catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         });
}                             
}
       catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        
        catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
                 
        stage.setWidth(500);
        stage.setScene(scene);
        stage.show();
         

                      
    }
                 
                 public void send() {
        
       
    }

    

        
}
