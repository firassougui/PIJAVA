/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;



import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import static tray.notification.NotificationType.ERROR;
import tray.notification.TrayNotification;

/**
 *
 * @author Firas
 */
public class ControlleSaisie {
        public static boolean estVide(TextField txtField, String nomField){
        if(txtField.getText().equals("")){
            txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Précisez votre "+ nomField  , ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return true; 
        } else {
            txtField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return false; 
        }
    }
  public static boolean estVideComboBox(ChoiceBox cb, String nomField){
        if(cb.getSelectionModel().isEmpty()){
            cb.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Précisez votre "+ nomField , ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return true; 
        } else {
            cb.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return false; 
        }
    }
    
}
