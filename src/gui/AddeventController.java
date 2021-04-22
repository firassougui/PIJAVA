/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.EvenementCRUD;
import entities.evenement;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import services.TypeEventCRUD;
import tools.ControlleSaisie;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AddeventController implements Initializable {
  File f;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField titre;
    @FXML
    private Label amountLabel1;
    @FXML
    private Label amountLabel111;
    @FXML
    private Label amountLabel1111;
    @FXML
    private TextField nombre_par;
    @FXML
    private DatePicker date_end;
    @FXML
    private TextField desc;
    @FXML
    private Label amountLabel1112;
    @FXML
    private Button photo;
    @FXML
    private Label amountLabel12;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Label amountLabel2;
    @FXML
    private ChoiceBox<String> employeur;
    @FXML
    private Label lblAdd;
    @FXML
    private Button ajouter;
    @FXML
    private Button ajouter1;
    @FXML
    private Label amountLabel11111;
    @FXML
    private DatePicker date_start;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         TypeEventCRUD sp = new TypeEventCRUD ();
  
        ObservableList<String> l = null;
        ObservableList<String> ll = null;
    try {
        l = sp.readAll2();
        ll = sp.readAll3();
    } catch (SQLException ex) {
        Logger.getLogger(AddeventController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        type.setItems(l);
    employeur.setItems(ll);}    

       

    @FXML
    private void uploadImage(ActionEvent event) {
          FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        f = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image i = new Image(f.getAbsoluteFile().toURI().toString()) {};
        img.setImage(i);
    }

    @FXML
    private void addCateg(ActionEvent event)  throws SQLException {
        if (!(ControlleSaisie.estVide(titre, "titre "))
            && !(ControlleSaisie.estVide(nombre_par, "nombre_par "))
            && !(ControlleSaisie.estVide(desc, "desc "))
            && !(ControlleSaisie.estVideComboBox(employeur, "employeur "))
            && !(ControlleSaisie.estVideComboBox(type, "type ")))
        {
        EvenementCRUD sc = new EvenementCRUD();
         LocalDate myDate =date_start.getValue();
         LocalDate myDate1 =date_end.getValue();
    java.sql.Date sqlDate = java.sql.Date.valueOf( myDate );
    java.sql.Date sqlDate1 = java.sql.Date.valueOf( myDate1 );
  
   
        evenement cl=new evenement(titre.getText(),Integer.parseInt(nombre_par.getText()),sqlDate,sqlDate1,desc.getText());
        cl.setPhoto(f.getAbsoluteFile().toURI().toString());
        TypeEventCRUD c=new TypeEventCRUD();
        int idc=c.getIDBy(type.getValue());
        cl.setId_type(idc);
         int ide=c.getIDEBy(employeur.getValue());
        cl.setId_employeur(ide);
        
        sc.addevent(cl);
 
        TrayNotification tray = new TrayNotification("succès", " ajouté", SUCCESS);
        tray.showAndWait();
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
         Stage stage = (Stage) ajouter1.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
}
