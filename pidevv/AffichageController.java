/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevv;

import Entities.commantaire;
import Entities.publication;
import Service.commentaireService;
import Service.publicationService;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class AffichageController implements Initializable {

    public int idR = 0;
    public static publication pubActuelle;

    @FXML
    private TextField txrecherche;
    @FXML
    private TableColumn<publication, String> tb_titre;
    @FXML
    private TableColumn<publication , String> tb_des;
    @FXML
    private TableView<publication> table;
    
    private ObservableList<publication> RecData = FXCollections.observableArrayList();
    
    private ObservableList<publication> fedData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        //rechercheA();
        List<publication> listFed= new ArrayList<publication>();
        publicationService rs =  new publicationService();
        listFed = rs.getAll();
        fedData.clear();
        fedData.addAll(listFed);
        table.setItems(fedData);
        
        
        tb_titre.setCellValueFactory(
            new PropertyValueFactory<>("titre")
        );
        tb_des.setCellValueFactory(
            new PropertyValueFactory<>("description")
        );
        
        
        
        
    }


   
    private void rechercheA() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<publication> filteredData = new FilteredList<>(RecData, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(publication -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (publication.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				else if (String.valueOf(publication.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<publication> sortedData = new SortedList<>(filteredData);
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
}
        
        
       // List<publication> listFed = new ArrayList<publication>();
    // publicationService rs = new publicationService();
//        listFed = rs.getAll();
//        for (int i = 0; i < listFed.size(); i++) {
//            creepub(listFed.get(i));
//        }
//
//    }
//
//    public void creepub(publication publication) {
//
//        try {
//            Parent modelePub = FXMLLoader.load(getClass().getResource("/pidevv/modelpub.fxml"));
//            pubvbox.getChildren().add(modelePub);
//            ((Text) ((AnchorPane) modelePub).getChildren().get(0)).setText(publication.getTitre());
//            ((Text) ((AnchorPane) modelePub).getChildren().get(1)).setText(publication.getDescription());
//            ((Button) ((AnchorPane) modelePub).getChildren().get(2)).setOnAction((event) -> {
//                try {
//                    Supprimer(event, publication);
//                } catch (SQLDataException ex) {
//                    Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            });
//            ((Button) ((AnchorPane) modelePub).getChildren().get(3)).setOnAction((event) -> {
//
//                Modifier(event, publication);
//
//            });
//
//            ((Button) ((AnchorPane) modelePub).getChildren().get(6)).setOnAction((event) -> {
//                String textComm = ((TextField) ((AnchorPane) modelePub).getChildren().get(5)).getText();
//                commantaire c = new commantaire(publication.getId(), textComm);
//                commentaireService cs = new commentaireService();
//                cs.AjouterCommentaire(c);
//                Parent modeleComForAjout;
//                try {
//                    modeleComForAjout = FXMLLoader.load(getClass().getResource("/pidevv/modelcom.fxml"));
//                    ((VBox) ((AnchorPane) modelePub).getChildren().get(4)).getChildren().add(modeleComForAjout);
//                    ((Text) ((StackPane) ((AnchorPane) modeleComForAjout).getChildren().get(0)).getChildren().get(0))
//                            .setText(c.getDescription());
//                } catch (IOException ex) {
//                    Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });
//
//            try {
//                List<commantaire> listFed = new ArrayList<commantaire>();
//                commentaireService cs = new commentaireService();
//                listFed = cs.getAllByPubId(publication.getId());
//
//                if (listFed.size() != 0) {
//                    for (int i = 0; i < listFed.size(); i++) {
//                        Parent modeleCom = FXMLLoader.load(getClass().getResource("/pidevv/modelcom.fxml"));
//                        ((VBox) ((AnchorPane) modelePub).getChildren().get(4)).getChildren().add(modeleCom);
//                        ((Text) ((StackPane) ((AnchorPane) modeleCom).getChildren().get(0)).getChildren().get(0))
//                                .setText(listFed.get(0).getDescription());
//                    }
//                }
//
//            } catch (IOException ex) {
//                Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @FXML
//    private void ajouter(ActionEvent event) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/pidevv/AjoutPub.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            System.out.print("Erreur d'affichage : " + e.getMessage() + " " + e.getCause());
//        }
//    }
//
//    private void Modifier(ActionEvent event, publication publication) {
//
//        pubActuelle = publication;
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/pidevv/ModifPub.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            System.out.print("Erreur d'affichage : " + e.getMessage() + " " + e.getCause());
//        }
//
//    }
//
//    private void Supprimer(ActionEvent event, publication publication) throws SQLDataException {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Suppression");
//        alert.setHeaderText(null);
//        alert.setContentText("vous voulez vraiment supprimer cette publication ?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            publicationService ps = new publicationService();
//            ps.SupprimerPublication(publication);
//            pubActuelle = null;
//
//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/pidevv/Accueil.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                System.out.print(ex.getMessage());
//            }
//        }
//    }
//
    
