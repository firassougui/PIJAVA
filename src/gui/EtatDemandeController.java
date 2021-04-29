/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mysql.jdbc.PreparedStatement;
import tools.Myconnect;
import entities.Opportunite;
import entities.mailing;
import services.OpportuniteCrud;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sample.Main;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class EtatDemandeController implements Initializable {

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
    private Button Gevent111;
    @FXML
    private TableView<mailing> tableMailing;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private TableColumn<?, ?> sujet1;
    @FXML
    private TableColumn<?, ?> etat1;
    @FXML
    private TableColumn<?, ?> ref1;
    @FXML
    private TableColumn<?, ?> Email1;
    @FXML
    private VBox vb1;
    @FXML
    private TextField iden1;
    @FXML
    private TextField sujet;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private TextField ref;
    @FXML
    private TextField ValeurSelectionner;
    @FXML
    private TextField mail;
    @FXML
    private Button Modifier;
    @FXML
    private Button Delete;
    int index=-1;
 ObservableList<mailing> MailingList=FXCollections.observableArrayList();
    @FXML
    private Button Gevent112;
    @FXML
    private Button catpub;
    @FXML
    private Button pub;
    @FXML
    private Button employer1;
    @FXML
    private Button employeur1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          etat.getItems().add("Accepter");
          etat.getItems().add("Refuser");
          etat.getItems().add("Encours");
 loading();
 Actualiser();
 
    } 
       private void loading() {
       
         id1.setCellValueFactory(new PropertyValueFactory<>("id"));
         sujet1.setCellValueFactory(new PropertyValueFactory<>("sujet"));  
         etat1.setCellValueFactory(new PropertyValueFactory<>("etat"));
         Email1.setCellValueFactory(new PropertyValueFactory<>("email"));
         ref1.setCellValueFactory(new PropertyValueFactory<>("ref"));
 
                         
     
        
    }
    
 
     private void Actualiser()
    {
         
       String Requete="select * from mailing " ;
                
            PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              ResultSet rs = pst.executeQuery();
          String name = null;
            
            while(rs.next())
            {
  MailingList.add(new mailing(
  
  rs.getInt("id"), 
  rs.getString("sujet"),
  rs.getString("etat"),
  rs.getString("ref"),
  rs.getString("email")
  

  ));
  tableMailing.setItems(MailingList);
     
        

         }
        }
        catch (SQLException ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
         
    }

     
       
        
         
    

   
     @FXML
    private void GestionOffre(ActionEvent event) 
  
          
             throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("AddOpport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
       loading();
        });
           
   
        
    
    }
   
  
 

    @FXML
    private void Contact(ActionEvent event) {
        
        // Recipient's email ID needs to be mentioned.
        String to = mail.getText();

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
                

                
  String Requete1="select * from opportunite " ;
                
            PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete1);
              ResultSet rs1 = ps.executeQuery();
          String name = null;
            
            while(rs1.next())
            {
                File f =new File(rs1.getString("logo"));
            
                attachmentPart.attachFile(f);
                textPart.setText("Votre demande est :"+etat.getValue()+" Sous la Reference :"+ref.getText());
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

           }}catch (SQLException ex) {
                    Logger.getLogger(EtatDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                } } catch (IOException e) {

                e.printStackTrace();

            }   

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
          JOptionPane.showMessageDialog(null, "Le message est envoyé avec succés ");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    @FXML
    private void changer(ActionEvent event) {
         int x=etat.selectionModelProperty().getValue().getSelectedIndex();
         String select = null;
         
if(x==0)
{
    select="Accepter";
    
}
if(x==1)
{
    select="Refuser";
    
}
if(x==2)
{
    select="Encours";
    
}
x=x+1;
 ValeurSelectionner.setText(select);
    }

    @FXML
    private void Update(ActionEvent event) {
        try {
             PreparedStatement pst = null;
         OpportuniteCrud Op=new OpportuniteCrud();
        Opportunite opp=new Opportunite();
        
        String id=iden1.getText();
        String Sujet=sujet.getText();
        String Ref=ref.getText();
        String Mail=mail.getText();
       
  
         String Etat=etat.getValue();
                
      
     
      
              
          
         String Requete="update mailing set sujet='"+Sujet+"',ref='"+Ref+"',email='"+Mail+"',etat='"+ValeurSelectionner.getText()+"' where id='"+id+"'";
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
                  
        try {
             PreparedStatement pst = null;
         OpportuniteCrud Op=new OpportuniteCrud();
        Opportunite opp=new Opportunite();
        
        String id=iden1.getText();
        
                
              
                
           
        String Requete="delete  from mailing where id='"+id+"'";
        pst = (PreparedStatement) new Myconnect().getcnx().prepareStatement(Requete);
              boolean rs = pst.execute();
             JOptionPane.showMessageDialog(null, "L'element est supprimé avec succés");
        }
         catch (Exception ex) {
            Logger.getLogger(AddOpportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index=tableMailing.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
            
        } 
        
       
         iden1.setText(id1.getCellData(index).toString());
         
       String et=etat1.getCellData(index).toString();
     int y=0;  
     if(et.equals("Encours"))
       {
           y=3;
           
       }
      if(et.equals("Accepter"))
       {
           y=1;
           
       }
       if(et.equals("Refuser"))
       {
           y=2;
           
       }
        
         etat.getSelectionModel().select(y-1);
        
        iden1.setText(id1.getCellData(index).toString());   
        sujet.setText(sujet1.getCellData(index).toString());
        ref.setText(ref1.getCellData(index).toString());
        mail.setText(Email1.getCellData(index).toString());
        
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
