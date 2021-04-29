/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Config.config;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ForgetPassController implements Initializable {

    @FXML
    private PasswordField Text;
    @FXML
    private Button sendE;
    @FXML
    private Button verif;
     Stage primaryStage = new Stage();
     String chemin = "";
     int randomCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendE(ActionEvent event) {
           Random rand = new Random();
        randomCode=rand.nextInt(999999);
        String message=" your reset code is "+randomCode;
          Connection con =config.getInstance().getConnection();
            
            
           
            
           
          
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
            m.setSubject("Reset Your password ");
            m.setText(message);

            //send mail

            Transport.send(m);
            //sentBoolValue.setVisible(true);
            System.out.println("Message sent!");
                   chemin = "afficherEm.fxml";
	
            

        }   catch (javax.mail.MessagingException e){
            e.printStackTrace();
        }
        
            
    }

    @FXML
    private void verif(ActionEvent event) {
        if (Integer.valueOf(Text.getText())==randomCode){
            String title = "Succes! ";
            String message = " Le Code est Verifier ";

                 TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
                 chemin = "ResetPass.fxml";
	new OpenWindow2(primaryStage,chemin, "modifier");
                
            
        }
        else {
               TrayNotification tray = new TrayNotification();
                tray.setTitle("WARNING");
                tray.setMessage("Le Code est Incorrect !");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.seconds(5));
            
        }
    }
    
}
