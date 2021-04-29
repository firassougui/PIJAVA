/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import com.jfoenix.controls.JFXTextField;
import edu.projet.entities.employeur;
import edu.projet.services.EmployeurCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjoutEmployeurController implements Initializable {

    @FXML
    private JFXTextField num;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXTextField loc;
    @FXML
    private JFXTextField mail;
   
    @FXML
    private JFXTextField img;
 Stage primaryStage = new Stage();
    String chemin = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) {
             EmployeurCrud sp = new EmployeurCrud();
        employeur r = new employeur(nom.getText(),pass.getText(),mail.getText(),Integer.parseInt(num.getText()),loc.getText(),img.getText()); //    Logger.getLogger(evenementController.class.getName()).log(Level.SEVERE, null, ex);
        sp.add(r);
        String title = "Succes! ";
                 String message = "L'employeur est ajouté avec succés";

                 TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
               
        System.out.println("aaaa");
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }

    @FXML
    private void afficher(ActionEvent event) {
    }
    
}
