/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import services.category_publication_Service;
import entities.category_publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class home_CategorieController implements Initializable {

    @FXML
    private Pane pnl_cat;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<category_publication> tabview;
    @FXML
    private TableColumn<category_publication, String> col_libelle;
    @FXML
    private TableColumn<category_publication, String> col_description;
    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_description;
    @FXML
    private Button btn_ajout;
    category_publication_Service service = new category_publication_Service();
        private TableColumn<category_publication, String> col_btnDelet;
    @FXML
    private Button Gtypeevent;
    @FXML
    private Button Ghome;
    @FXML
    private Button Gevent;
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
      tabview.setEditable(true);
        Modifier();
           search();
            try {
                                        refreche();
                                    } catch (SQLException ex) {
                                       }
                
           col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<category_publication, String>, TableCell<category_publication, String>> cellFactory
                = (final TableColumn<category_publication, String> param) -> {
                    final TableCell<category_publication, String> cell = new TableCell<category_publication, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    category_publication u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
                                    try {
                                        service.Supprimer(u.getId());
                                    } catch (SQLException ex) {
                                     }
                                    
                                    
                                    AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);
                                    
                                    
                                    try {
                                        refreche();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
           };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
    }    


    @FXML
    private void ajouter_categorie(ActionEvent event) throws SQLException {
                           if (txt_titre.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de txt_titre", AlertDialog.image_cross);
        } else if (txt_titre.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_titre !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }  else if (txt_description.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Description", AlertDialog.image_cross);
        } else if (txt_description.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Description !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } 

        else {
 
           category_publication Categorieajouter = new category_publication(txt_titre.getText(), txt_description.getText());
            service.Ajouter(Categorieajouter);
            refreche();
        }
    }
    public void refreche() throws SQLException {
        col_libelle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_libelle.setCellFactory(TextFieldTableCell.<category_publication> forTableColumn());
        col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col_description.setCellFactory(TextFieldTableCell.<category_publication> forTableColumn());
     

        tabview.setItems(service.Affichertout());
    }
     public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("") ) {

                try {
                    refreche();
                } catch (SQLException ex) {
                  }

            } else {

                try {
            col_libelle.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_libelle.setCellFactory(TextFieldTableCell.<category_publication> forTableColumn());
        col_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col_description.setCellFactory(TextFieldTableCell.<category_publication> forTableColumn());
     
        tabview.getItems().clear();



                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                 }
        

            }
        }
        );

    }
     public void Modifier()
    {
               
                col_libelle.setOnEditCommit((TableColumn.CellEditEvent<category_publication, String> event) -> {
            TablePosition<category_publication, String> pos = event.getTablePosition();
                System.out.println(pos);
            String libelle_Categorie = event.getNewValue();
                    System.out.println(libelle_Categorie);
            int row = pos.getRow();
            category_publication categorie = event.getTableView().getItems().get(row);
            System.out.println(categorie);
  
            categorie.setTitre(libelle_Categorie);
                    try {
                        service.Modifier(categorie,categorie.getId());
                    } catch (SQLException ex) {
                    }
        });
                
                
            col_description.setOnEditCommit((TableColumn.CellEditEvent<category_publication, String> event) -> {
            TablePosition<category_publication, String> pos = event.getTablePosition();
                System.out.println(pos);
            String Description_Categorie = event.getNewValue();
                    System.out.println(Description_Categorie);
            int row = pos.getRow();
            category_publication categorie = event.getTableView().getItems().get(row);
            System.out.println(categorie);
  
            categorie.setDescription(Description_Categorie);
                    try {
                        service.Modifier(categorie,categorie.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });      
                
    
                
                
                
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
    private void cattpub(ActionEvent event) {
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
