/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import com.jfoenix.controls.JFXTextField;


import edu.projet.entities.employer;
import edu.projet.services.EmployerCrud;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Config.config;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.scene.input.TransferMode;



import javax.mail.Session;




/**
 *
 * @author MSI
 */
public class ajoutEmController implements Initializable {
     
      @FXML
    private JFXTextField num;
    private JFXTextField emId;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField pass;

    @FXML
    private JFXTextField loc;

    @FXML
    private JFXTextField mail;

    @FXML
    private JFXTextField cat;

    @FXML
    private JFXTextField img;
    
     Stage primaryStage = new Stage();
    String chemin = "";
    private String nomImage= "";
    
    @FXML
    private ImageView imageview;

    @FXML
    void Annuler(ActionEvent event) {

    }
      public void sendEmail() throws SQLException, AddressException, javax.mail.MessagingException{
                    Connection con =config.getInstance().getConnection();
            
            
            ResultSet rs;
            
            rs = con.createStatement().executeQuery("SELECT mail FROM employer where id ='" + this.emId + "' ");
            String email = null;
            if(rs.next())
            {
                email = rs.getString("email");
                        
            }
        String to="masseoudi.amine99@gmail.com";
        String from = "artdomeproject@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "masseoudi.amine99@gmail.com";
        final String password = "181JMT2332amine";

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
         @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Inscription");
            m.setText("Inscription etablie");

            //send mail

            Transport.send(m);
            //sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (javax.mail.MessagingException e){
            e.printStackTrace();
        }
        
            

    }
    
     @FXML
    void afficher(ActionEvent event) {
       chemin = "afficherEm.fxml";
	new OpenWindow2(primaryStage,chemin, "modifier");
           
             
        
           
    }
    @FXML
    private void handleDragOver(DragEvent event) {
             if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
           List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview.setImage(img);
        nomImage = files.get(0).getName();
    }
    

    @FXML
    void ajout(ActionEvent event)  throws SQLException, javax.mail.MessagingException {
        InputStream inStream = null;
       OutputStream outStream = null;
        
        try{

            File afile =new File("./src/image/"+nomImage);
            File bfile =new File("C:/wamp64/www/image/"+nomImage);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException t){
            t.printStackTrace();
        }
        
            employer r = new employer(); //    Logger.getLogger(evenementController.class.getName()).log(Level.SEVERE, null, ex);
            r.setName(nom.getText());
            r.setMdp(pass.getText());
            r.setMail(mail.getText());
            r.setNum(Integer.parseInt(num.getText()));
            r.setLocalisation(loc.getText());
            r.setCategorie(cat.getText());
            r.setImg(nomImage);
            EmployerCrud e = new EmployerCrud();
            e.add(r);
                
        System.out.println("aaaa");
                
    }
    
  
        

    

   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
