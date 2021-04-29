/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import services.Commentaire_Service;
import services.publication_Service;
import entities.BadWords;
import entities.commentaire;
import entities.publication;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class FrontCommentaireController implements Initializable {

    @FXML
    private TableColumn<commentaire, String> col_Commentaire;
    @FXML
    private TextArea comentaire_text;
    @FXML
    private Label username;
    @FXML
    private Pane pnl_commentaire;
    @FXML
    private TableView<commentaire> tabcommentaire;
    private Button btn_back;
    Commentaire_Service service = new Commentaire_Service();
 
            publication_Service pub_service = new publication_Service();
               private TableColumn<commentaire, String> col_btnDelet;
    @FXML
    private Button home;
    @FXML
    private Button Participationg;
    @FXML
    private Button opportunite;
    @FXML
    private Button publication;
    @FXML
    private Button events1;
    @FXML
    private Button profil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           Modifier();
                   col_Commentaire.setCellValueFactory(new PropertyValueFactory<>("contenu"));
          col_Commentaire.setCellFactory(TextFieldTableCell.<commentaire> forTableColumn());
      
        try {
            tabcommentaire.setItems(service.Affichertout_pub(Item_PubController.pub.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(FrontCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
           col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<commentaire, String>, TableCell<commentaire, String>> cellFactory
                = (final TableColumn<commentaire, String> param) -> {
                    final TableCell<commentaire, String> cell = new TableCell<commentaire, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    commentaire u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
                                    try {
                                        service.Supprimer(u.getId());
                                    } catch (SQLException ex) {
                                     }
                                    
                                    
                                    AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);
                                    
                                    
                                      col_Commentaire.setCellValueFactory(new PropertyValueFactory<>("contenu"));
      col_Commentaire.setCellFactory(TextFieldTableCell.<commentaire> forTableColumn());
      
                                    try {
                                        tabcommentaire.setItems(service.Affichertout_pub(Item_PubController.pub.getId()));
                                    } catch (SQLException ex) {
                                        Logger.getLogger(FrontCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
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
        tabcommentaire.getColumns().add(col_btnDelet);
    }    

    @FXML
    private void Add_Commentaire(ActionEvent event) throws SQLException {
                BadWords.loadConfigs();

        {
         if (comentaire_text.getText().equals("")) {
                          AlertDialog.showNotification("Error !", "champ vide de comentaire_text", AlertDialog.image_cross);

         } else if (comentaire_text.getText().matches("^[0-9]+$")) {
                     AlertDialog.showNotification("Error !", "comentaire_text", AlertDialog.image_cross);

        } 
         else if (BadWords.filterText(comentaire_text.getText())) {

                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            }
         else
         {
             commentaire c = new commentaire();
             c.setContenu(comentaire_text.getText());
             c.setId_pub(Item_PubController.pub.getId());
        service.Ajouter(c);
             col_Commentaire.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        col_Commentaire.setCellFactory(TextFieldTableCell.<commentaire> forTableColumn());
      
        tabcommentaire.setItems(service.Affichertout_pub(Item_PubController.pub.getId())); 
                      AlertDialog.showNotification("Ajout", "Ajout", AlertDialog.image_checked);

         }
        }
        
    }

    private void handleClicks(ActionEvent event) throws IOException {
                   if(event.getSource()==btn_back)
        {                 
           
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_Publication.fxml")));
            stage.setScene(scene);
            stage.show();

        }
    }
         public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_Commentaire.setOnEditCommit((TableColumn.CellEditEvent<commentaire, String> event) -> {
            TablePosition<commentaire, String> pos = event.getTablePosition();
                
            String nom = event.getNewValue();
                 
            int row = pos.getRow();
            commentaire ac = event.getTableView().getItems().get(row);
           
  
            ac.setContenu(nom);
                    try {
                        service.Modifier(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                

              
              
              
      
              
    
     
     
                
    }

  
 

    @FXML
    private void Participation(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listePar.fxml"));
            Parent root= loader.load();
  
            Participationg.getScene().setRoot(root);
    }

    @FXML
    private void opportunite(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/StartOffre.fxml"));
            Parent root= loader.load();
  
            opportunite.getScene().setRoot(root);
    }

    @FXML
    private void publica(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Front_Publication.fxml"));
            Parent root= loader.load();
  
            publication.getScene().setRoot(root);
    }

     @FXML
    private void profil(ActionEvent event) throws IOException {
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Profile.fxml"));
            Parent root= loader.load();
  
            profil.getScene().setRoot(root);
        
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherevenement.fxml"));
            Parent root= loader.load();
  
            events1.getScene().setRoot(root);
    }
}
