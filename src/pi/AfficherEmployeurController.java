/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import com.jfoenix.controls.JFXTextField;
import edu.projet.entities.employeur;
import edu.projet.tools.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Session;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherEmployeurController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_mail;
    @FXML
    private TableColumn<?, ?> col_num;
    @FXML
    private TableColumn<?, ?> col_localisation;
    @FXML
    private TableColumn<?, ?> col_image;
    @FXML
    private Button add;
    @FXML
    private JFXTextField idem;
    @FXML
    private Button chercher1;
     ObservableList<employeur> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con =MyConnection.getInstance().getCnx();
            
            
            ResultSet rs;
            
            rs = con.createStatement().executeQuery("SELECT * FROM employeur");
            
            System.out.println("f");
            while (rs.next())
            {  
                employeur r=new employeur(rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6));
              
               
                oblist.add(r);
            }
            
            
             System.out.println("f");
        
            
            col_mail.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_localisation.setCellValueFactory(new PropertyValueFactory<>("loca"));
            col_num.setCellValueFactory(new PropertyValueFactory<>("numero"));
           
            col_image.setCellValueFactory(new PropertyValueFactory<>("logo"));
            
           
            
            System.out.println("ffff");
            
           
            
            System.out.println("ffff");
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }

    }    

    @FXML
    private void recherche(ActionEvent event) {
    }

    @FXML
    private void handleBSupprimerEvent(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
    }

    @FXML
    private void pdfs(ActionEvent event) {
    }
    
    
}
