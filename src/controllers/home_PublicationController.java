/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import services.category_publication_Service;
import services.publication_Service;
import entities.publication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class home_PublicationController implements Initializable {

    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<publication> tabview;
    @FXML
    private TableColumn<publication, String> col_Titre;
    @FXML
    private TableColumn<publication, Integer> col_categorie;
    @FXML
    private TableColumn<publication, Date> col_Date;
    @FXML
    private TableColumn<publication, String> col_description;
    @FXML
    private TableColumn<publication, Integer> col_vues;
    @FXML
    private TableColumn<publication, Integer> col_likes;
    @FXML
    private TextField txt_Titre;
    @FXML
    private TextField txt_description;
    @FXML
    private ComboBox<Integer> combo_categorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView Image_PUB;
          private TableColumn<publication, String> col_btnDelet;
          private String nomImage = "";
          category_publication_Service serviceCatgorie = new category_publication_Service();
    publication_Service service = new publication_Service();
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
      setCellfromtabletoImage();
        Modifier();
        tabview.setEditable(true);

        try {
            refreche();
        } catch (SQLException ex) {
        }
           combo_categorie.setItems(serviceCatgorie.read_ids());
    
        combo_categorie.getSelectionModel().select(0);
           col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<publication, String>, TableCell<publication, String>> cellFactory
                = new Callback<TableColumn<publication, String>, TableCell<publication, String>>() {
            public TableCell call(final TableColumn<publication, String> param) {
                final TableCell<publication, String> cell = new TableCell<publication, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                publication u = getTableView().getItems().get(getIndex());
 File f = new File("C:/wamp64/www/images/"+u.getImg());
                            
                                System.out.println(f.delete());                             

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                }
                               
                                AlertDialog.showNotification("suppression confirmée!", "suppression a été bien faite", AlertDialog.image_checked);

                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
    }    



    @FXML
    private void handleDragOver(DragEvent event) {
             if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
           List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview.setImage(img);
        nomImage = files.get(0).getName();
    }

    @FXML
    private void ajouter_pub(ActionEvent event) throws SQLException {
           if (txt_Titre.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de txt_Titre", AlertDialog.image_cross);
        } else if (txt_Titre.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur txt_Titre !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        }else if (txt_description.getText().equals("")) {
            AlertDialog.showNotification("Error !", "Champ vide de Description", AlertDialog.image_cross);
        } else if (txt_description.getText().matches("^[0-9]+$")) {
            AlertDialog.showNotification("Erreur Description !", "il faut saisir des caracteres  !", AlertDialog.image_cross);
        } 
           else
        {
              InputStream inStream = null;
    OutputStream outStream = null;

        try{

            File afile =new File("./src/images/"+nomImage);
            File bfile =new File("C:/wamp64/www/images/"+nomImage);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
            publication p =new publication();
            p.setDescription(txt_description.getText());
             p.setTitre(txt_Titre.getText());
             p.setCategorie_id(combo_categorie.getSelectionModel().getSelectedItem());
             p.setImg(nomImage);
             p.setLikes(0);
             p.setVus(0);
             service.Ajouter(p);
                    AlertDialog.showNotification("Ajout", "Ajout", AlertDialog.image_checked);
   refreche();
        }
        
        
        
        
        
    }
      private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            publication ac = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getImg());
        Image_PUB.setImage(image);
        }
        );

    }
          public void refreche() throws SQLException {
  
  

        col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_Titre.setCellFactory(TextFieldTableCell.<publication> forTableColumn());
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_description.setCellFactory(TextFieldTableCell.<publication> forTableColumn());
        col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_vues.setCellValueFactory(new PropertyValueFactory<>("vus"));
        col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

    }
        public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_Titre.setOnEditCommit((TableColumn.CellEditEvent<publication, String> event) -> {
            TablePosition<publication, String> pos = event.getTablePosition();
                
            String libelle_Ac = event.getNewValue();
                 
            int row = pos.getRow();
            publication ac = event.getTableView().getItems().get(row);
           
  
            ac.setTitre(libelle_Ac);
                    try {
                        service.Modifier(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                
                
       
              
              
              
                        col_description.setOnEditCommit((TableColumn.CellEditEvent<publication, String> event) -> {
            TablePosition<publication, String> pos = event.getTablePosition();
           
            String Description_Ab = event.getNewValue();
                  
            int row = pos.getRow();
            publication ab = event.getTableView().getItems().get(row);
          
  
            ab.setDescription(Description_Ab);
                    try {
                        service.Modifier(ab,ab.getId());
                    } catch (SQLException ex) {
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
        
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherevenement.fxml"));
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
