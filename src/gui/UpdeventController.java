/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EvenementCRUD;
import services.TypeEventCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class UpdeventController implements Initializable {
    
     public static int x;
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
    private Button modifier;
    @FXML
    private Button ajouter1;
    @FXML
    private Label amountLabel11111;
    @FXML
    private DatePicker date_start;
    @FXML
    private TextField id;
    @FXML
    private ImageView img;
    File file;
    String images;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         TypeEventCRUD sp = new TypeEventCRUD ();
        EvenementCRUD ss = new EvenementCRUD();
       
        
            try {
                  FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("evenementback.fxml"));
            Stage prStage = new Stage();
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            EvenementbackController irc = loader.getController();
            
            int id1 = Integer.parseInt(irc.idxx.toString());
            System.out.println(id1);
                id.setText(Integer.toString(x));
                id.setDisable(true);
                titre.setText(ss.gettypeBy(id1));
                desc.setText(ss.getdescBy(id1));
                
                nombre_par.setText(ss.getNbPBy(id1).toString());
                date_start.setValue(ss.getDateSBy(id1).toLocalDate());
                date_end.setValue(ss.getDateEBy(id1).toLocalDate());
                int emp =ss.getIdEPBy(id1);
                
                employeur.setValue(sp.getnameBy(emp));
                int tp = ss.getIdTPBy(id1);
                type.setValue(sp.getTpeBy(tp));
                 InputStream stream = new FileInputStream("C:\\Users\\Firas\\Documents\\NetBeansProjects\\findjob\\src\\image\\Noimage.PNG");
                Image image = new Image(stream);
                if(ss.getphotoBy(id1).length()>0)
                {
                if("file".equals(ss.getphotoBy(id1).substring(0, 4)))
                {
            File filex = new File(ss.getphotoBy(id1));
             image =  new Image(filex.toString());
            System.out.println(filex.toURI().toString());
                }
                }
      
                
                
            img.setImage(image);
               
            } catch (SQLException ex) {
               
            } catch (IOException ex) {
             Logger.getLogger(UpdeventController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
        ObservableList<String> l = null;
        ObservableList<String> ll = null;
    try {
        l = sp.readAll2();
        ll=sp.readAll3();
    } catch (SQLException ex) {

    }
        
        type.setItems(l);
        employeur.setItems(ll);
            
    }    

    @FXML
    private void uploadImage(ActionEvent event) {
        evenement p = new evenement();
          FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select PDF files");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image image = new Image(file.getAbsoluteFile().toURI().toString());
        img.setImage(image);
        images = file.toURI().toString();
         p.setPhoto(images);
    }

    @FXML
    private void addCateg(ActionEvent event)  throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("evenementback.fxml"));
            Stage prStage = new Stage();
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            

            EvenementbackController irc = loader.getController();
            EvenementCRUD sp = new EvenementCRUD();
           
            int idl = Integer.parseInt(irc.idxx.toString());
           System.out.println("llllllllllllllllll"+idl);
           LocalDate myDate =date_start.getValue();
         LocalDate myDate1 =date_end.getValue();
          java.sql.Date sqlDate = java.sql.Date.valueOf( myDate );
    java.sql.Date sqlDate1 = java.sql.Date.valueOf( myDate1 );
        evenement p =new evenement();
        p.setTitre(titre.getText());
        p.setDate_start(sqlDate);
        p.setDate_end(sqlDate1);
        p.setDescription(desc.getText());
    
        p.setNombre_par(Integer.parseInt(nombre_par.getText()));
        p.setPhoto(sp.getphotoBy(idl));
        
       
         TypeEventCRUD c=new TypeEventCRUD();
        int idc=c.getIDBy(type.getValue());
       p.setId_type(idc);
         int ide=c.getIDEBy(employeur.getValue());
        p.setId_employeur(ide);
        sp.updateProduct(p,idl);
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
   alert.setHeaderText("!!! Modification effectuer avec suucces !!!");
   alert.setContentText("succes");
   alert.showAndWait();
        
        modifier.getScene().getWindow().hide();  
              
                loader.setLocation(getClass().getResource("evenementback.fxml"));
                
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
        
    }

    @FXML
    private void annuler(ActionEvent event) {
    }
    
}
