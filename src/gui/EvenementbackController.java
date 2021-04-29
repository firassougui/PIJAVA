/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.EvenementCRUD;
import entities.evenement;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.TypeEventCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class EvenementbackController implements Initializable {
    
private ObservableList<evenement> recdata = FXCollections.observableArrayList();
         EvenementCRUD rs = new EvenementCRUD();
     public ObservableList<evenement> list;

    @FXML
    private TableView<evenement> table;
    @FXML
    private TableColumn<evenement, Integer> id;
    @FXML
    private TableColumn<evenement, String> employeur;
    @FXML
    private TableColumn<evenement, String> type;
    @FXML
    private TableColumn<evenement,String> titre;
    @FXML
    private TableColumn<evenement, Integer> nombre_par;
    @FXML
    private TableColumn<evenement, Date> date_start;
    @FXML
    private TableColumn<evenement, Date> date_end;
    @FXML
    private TableColumn<evenement, String> dsec;
    @FXML
    private TableColumn<evenement, String> image;
    @FXML
    private Button Gtypeevent;
    @FXML
    private Button Ghome;
    @FXML
    private Button Gevent;
    @FXML
    private Button ajouter;
    @FXML
    private Button sup;
    static evenement Recup;
    public static  Integer idxx;
    @FXML
    private Button exit;
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

    public EvenementbackController() throws SQLException {
        this.list = FXCollections.observableArrayList(rs.afficherPro());
    }

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       afficherProduits();
       
        
        
    }    

     private void afficherProduits() {
         EvenementCRUD rs =  new EvenementCRUD();
        
        recdata.clear();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeur.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        nombre_par.setCellValueFactory(new PropertyValueFactory<>("nombre_par"));
        date_start.setCellValueFactory(new PropertyValueFactory<>("date_start"));
        date_end.setCellValueFactory(new PropertyValueFactory<>("date_end"));
        dsec.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("photo"));
        

       
     
        try {
         
            table.setItems(rs.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(TypeeventbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void addCateg(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("addevent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
        afficherProduits();
        });
    }

    @FXML
    private void delCateg(ActionEvent event) throws SQLException {
          EvenementCRUD su = new EvenementCRUD();
        ObservableList<evenement> selectedRows, allClients;
        allClients = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();

        for (evenement c : selectedRows) {
            su.deleteEvenement(c.getId());
           
        }
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("event's Deleted!");
        alert.showAndWait();
        afficherProduits();
    }
     private void addButtonUpdateToTable() {
        TableColumn<evenement, Void> colBtn = new TableColumn("Modifier ");
 Callback<TableColumn<evenement, Void>, TableCell<evenement, Void>> cellFactory = new Callback<TableColumn<evenement, Void>, TableCell<evenement, Void>>() {
            @Override
            public TableCell<evenement, Void> call(final TableColumn<evenement, Void> param) {
                final TableCell<evenement, Void> cell = new TableCell<evenement, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Recup = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("updevent.fxml"));
                                Parent root = loader.load();
                                UpdeventController rc = loader.getController();
                                btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }

    @FXML
    private void modif(ActionEvent event) throws IOException {
              if (table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!  Selctionnez un evenement du tableau !!!");
            alert.showAndWait();
        } else {
         idxx=table.getSelectionModel().getSelectedItem().getId();
               
        FXMLLoader loader = new FXMLLoader();
        exit.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("updevent.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }}

    @FXML
    private void exit(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/back.fxml"));
            Parent root= loader.load();
  
            exit.getScene().setRoot(root);
    }

   @FXML
    private void typeevent(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/typeeventback.fxml"));
            Parent root= loader.load();
  
            Gtypeevent.getScene().setRoot(root);
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
