/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.TypeEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.TypeEventCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjoutertypeeventController implements Initializable {

    @FXML
    private TextField typetext;
    @FXML
    private TextField desctext;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPCategory(ActionEvent event) throws SQLException {
          TypeEventCRUD sc = new TypeEventCRUD();
        TypeEvent cl=new TypeEvent(1,typetext.getText(),desctext.getText());
        sc.addtypeevent2(cl);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add");
        alert.setHeaderText(null);
        
        alert.setContentText("typeevent Added!");
        alert.showAndWait();
        Stage stage = (Stage) annuler.getScene().getWindow();
   
    stage.close();
    }

    @FXML
    private void exitaction(ActionEvent event) {
        // get a handle to the stage
    Stage stage = (Stage) annuler.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
}
