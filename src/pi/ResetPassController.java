/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import edu.projet.entities.employer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ResetPassController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private PasswordField pass1;
    @FXML
    private Button Reset;
    @FXML
    private PasswordField pass2;
    Connection con;
    ResultSet rs = null ;
    PreparedStatement pst ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
 /* private void Reset(ActionEvent event) {
         String title = "Succes! ";
         String message = "Le mot de passe est changer avec succés";
        if (pass1.getText()==pass2.getText()){
           try {
        
        employer r = new employer(mail.getText());       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment Rest le Mot de Passe  ?");
                    Optional <ButtonType> result= alert.showAndWait();
                    if (result.get()== ButtonType.OK)
                    {
                            r.modifierpass(mail.getText());
                            System.out.println("555555");
                             Alert a = new Alert(Alert.AlertType.CONFIRMATION,message,ButtonType.OK);
                            a.showAndWait();
                    }
       
        } 
        catch (Exception ex) {
             Alert a = new Alert(Alert.AlertType.ERROR,"Veuillez Verifier les donnees saisises ",ButtonType.OK);
         a.showAndWait();
        }
                
         
        
            
        }
           
        
    }
*/
    public void Reset(){
          String title = "Succes! ";
         String message = "Le mot de passe est changer avec succés";
       
      try {
          
        
        employer r = new employer(mail.getText(),pass1.getText());       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment Modifier le Mot de Passe ?");
                    Optional <ButtonType> result= alert.showAndWait();
                    if (result.get()== ButtonType.OK)
                    {
                            r.modifierpass(mail.getText());
                            
                 TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
                            System.out.println("555555");
                             Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Password Modifié ",ButtonType.OK);
                            a.showAndWait();
                    }
       
        } 
        catch (Exception ex) {
             Alert a = new Alert(Alert.AlertType.ERROR,"Veuillez Verifier les donnees saisises ",ButtonType.OK);
         a.showAndWait();
        }
      }
    }

