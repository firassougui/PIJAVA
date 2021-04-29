/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import com.jfoenix.controls.JFXTextField;
import edu.projet.entities.employer;
import edu.projet.services.EmployerCrud;
import edu.projet.tools.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author MSI
 */
public class modifierEmController implements Initializable {
    
    
    @FXML
    private Pane p;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField pass;

    @FXML
    private JFXTextField loc;

    @FXML
    private JFXTextField mail;
     @FXML
    private JFXTextField num;

    @FXML
    private JFXTextField cat;

    @FXML
    private JFXTextField img;

    @FXML
    private JFXTextField Tid_em;

    @FXML
    private Button chercher;

    @FXML
    void Annuler(ActionEvent event) {
        nom.setText("");
     pass.setText("");
     loc.setText("");
     mail.setText("");
     cat.setText("");
     pass.setText("");
     img.setText("");
       num.setText("");
    

    }

    @FXML
    void RechercheEm(ActionEvent event) {
         try {
        Connection con =MyConnection.getInstance().getCnx();

        ResultSet rs;
      

        rs = con.createStatement().executeQuery("SELECT * FROM employer WHERE id=" + Integer.parseInt(Tid_em.getText()));
       
            if (rs.next()) {
                p.setOpacity(1);
                
                
                

                Tid_em.setText(rs.getString(1));
                nom.setText(rs.getString(3));
               pass.setText(rs.getString(4));
                mail.setText(rs.getString(5));
               num.setText(rs.getString(6));
               cat.setText(rs.getString(8));
                loc.setText(rs.getString(7));
               img.setText(rs.getString(9));
              
                
                
            } else {
                System.out.println("non trouvé");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("employe INCONNU");
                alert.setHeaderText(null);
                alert.setContentText("Il n'existe pas un employe avec un tel ID");
                alert.showAndWait();
            }
        } 
catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR," Les informations sont Invalides ou incompletes Veuillez les verifiers ",ButtonType.CLOSE);
            alert.showAndWait();
        }
     

    }

    @FXML
    void modifierEmploye(ActionEvent event) {
         try {
        
        employer r = new employer(Integer.parseInt(Tid_em.getText()),nom.getText(),pass.getText(),mail.getText(), Integer.parseInt(num.getText()),loc.getText(),cat.getText(),img.getText());       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez vous vraiment Modifier l'employe ?");
                    Optional <ButtonType> result= alert.showAndWait();
                    if (result.get()== ButtonType.OK)
                    {
                            r.modifierEmploye(Integer.parseInt(Tid_em.getText()));
                            System.out.println("555555");
                             Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Employe Modifié ",ButtonType.OK);
                            a.showAndWait();
                    }
       
        } 
        catch (Exception ex) {
             Alert a = new Alert(Alert.AlertType.ERROR,"Veuillez Verifier les donnees saisises ",ButtonType.OK);
         a.showAndWait();
        }

    }
     
 
           
             
        
           
    
    
        

    

   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
