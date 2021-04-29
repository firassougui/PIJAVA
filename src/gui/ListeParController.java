/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import entities.Participation;
import entities.evenement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EvenementCRUD;
import services.ParticipationCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ListeParController implements Initializable {
private ObservableList<Participation> recdata = FXCollections.observableArrayList();
         ParticipationCRUD rs = new ParticipationCRUD();
     public ObservableList<Participation> list;

    @FXML
    private TableView<Participation> table;
    @FXML
    private TableColumn<Participation, String> events;
    @FXML
    private TableColumn<Participation, String> date;
    @FXML
    private Button pdf;
    @FXML
    private Button back;
    @FXML
    private Button home;
    @FXML
    private Button events1;
    @FXML
    private Button Participationg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherpar();
    }    
         private void afficherpar() {
         ParticipationCRUD rs =  new ParticipationCRUD();
        
        recdata.clear();
 
        events.setCellValueFactory(new PropertyValueFactory<>("titre"));
     

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        

       
     
        try {
         
            table.setItems(rs.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(ListeParController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pdfac(ActionEvent event) throws FileNotFoundException, DocumentException {
                  ParticipationCRUD su = new ParticipationCRUD();
        ObservableList<Participation> selectedRows, allClients;
        allClients = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();
 su.pdf(87);
        for (Participation c : selectedRows) {
            su.pdf(87);
    }

}

    @FXML
    private void back(ActionEvent event) throws IOException {
        
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherevenement.fxml"));
            Parent root= loader.load();
  
            back.getScene().setRoot(root);
    }

    @FXML
    private void Participation(ActionEvent event) {
    }
}
