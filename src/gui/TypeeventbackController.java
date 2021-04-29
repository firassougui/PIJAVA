/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.TypeEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale.Category;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import services.TypeEventCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class TypeeventbackController implements Initializable {

     private ObservableList<TypeEvent> recdata = FXCollections.observableArrayList();
      TypeEventCRUD rss = new TypeEventCRUD();
     public ObservableList<TypeEvent> list = FXCollections.observableArrayList(rss.gettypeevent1());
    @FXML
    private TableView<TypeEvent> table;
    
    
    @FXML
    private TableColumn<TypeEvent,Integer> id;
    @FXML
    private TableColumn<TypeEvent,String> type;
    @FXML
    private TableColumn<TypeEvent,String> description;
    @FXML
    private Button Gtypeevent;
    @FXML
    private Button Ghome;
    @FXML
    private Button Gevent;
    @FXML
    private Button trier;
    @FXML
    private Button ajouter;
    @FXML
    private Button sup;
    @FXML
    private Button retour;
    @FXML
    private Button Offre;
    @FXML
    private Button Gevent1;
    @FXML
    private Button Gevent111;
    @FXML
    private Button Gevent11;
    @FXML
    private Button catpub;
    @FXML
    private Button pub;
    @FXML
    private Button employer1;
    @FXML
    private Button employeur1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherCategories();
       table.setEditable(true);
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        
       
    }    

 

    @FXML
    private void typeevent(ActionEvent event) {
    }

   


    private ObservableList<TypeEvent> getSortedCategory() throws SQLException {
        TypeEventCRUD su = new TypeEventCRUD();
        return su.triCatsByLabel();
    }
     public void afficherCategories() {
         TypeEventCRUD rs =  new TypeEventCRUD();
       
        recdata.clear();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new  PropertyValueFactory<>("description"));
        try {
            
            table.setItems(rs.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(TypeeventbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changelabel(TableColumn.CellEditEvent event)throws SQLException {
        TypeEvent clientSelected = table.getSelectionModel().getSelectedItem();
        clientSelected.setType(event.getNewValue().toString());
        TypeEventCRUD su = new TypeEventCRUD();
        su.updateCategory(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Category Updated!");
        alert.showAndWait();
        afficherCategories();
    }

   @FXML
    private void addCateg(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ajoutertypeevent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
        afficherCategories();
        });
    }

    @FXML
   private void delCateg(ActionEvent event) throws SQLException {
         TypeEventCRUD su = new TypeEventCRUD();
        ObservableList<TypeEvent> selectedRows, allClients;
        allClients = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();

        for (TypeEvent c : selectedRows) {
            su.deleteCategory(c.getId());
           
        }
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Category's Deleted!");
        alert.showAndWait();
        afficherCategories();
    }

    @FXML
    private void changelabel1(TableColumn.CellEditEvent event) throws SQLException {
            TypeEvent clientSelected = table.getSelectionModel().getSelectedItem();
        
        clientSelected.setDescription(event.getNewValue().toString());
        TypeEventCRUD su = new TypeEventCRUD();
        su.updateCategory(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Category Updated!");
        alert.showAndWait();
        afficherCategories();
    }

    @FXML
    private void retour(ActionEvent event)  throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/back.fxml"));
            Parent root= loader.load();
  
            retour.getScene().setRoot(root);
        
    }


    @FXML
    private void Ghome(ActionEvent event) throws IOException {
        
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
        
    }

    @FXML
    private void event(ActionEvent event)  throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/evenementback.fxml"));
            Parent root= loader.load();
  
            Gevent.getScene().setRoot(root);
        
        
    }

    @FXML
    private void GestionOffre(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/AddOpport.fxml"));
            Parent root= loader.load();
  
            Offre.getScene().setRoot(root);
    }

    @FXML
    private void GestionCandidature1(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/MiseAjourCand.fxml"));
            Parent root= loader.load();
  
            Gevent1.getScene().setRoot(root);
        

    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Statis.fxml"));
            Parent root= loader.load();
  
            Gevent11.getScene().setRoot(root);
    }

    @FXML
    private void Contact(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/EtatDemande.fxml"));
            Parent root= loader.load();
  
            Gevent11.getScene().setRoot(root);
    }

    @FXML
    private void cattpub(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Home_Categorie.fxml"));
            Parent root= loader.load();
  
            catpub.getScene().setRoot(root);
    }

    @FXML
    private void pub1(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Home_Publication.fxml"));
            Parent root= loader.load();
  
            pub.getScene().setRoot(root);
        
    }

    @FXML
    private void employerlis(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherEm.fxml"));
            Parent root= loader.load();
  
            employer1.getScene().setRoot(root);
    }

    @FXML
    private void employeurlis(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficheEmployeur.fxml"));
            Parent root= loader.load();
  
            employeur1.getScene().setRoot(root);
    }
    



  
    
}
