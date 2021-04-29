/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.publication_Service;
import entites.publication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class Item_PubController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button btn_commentaire;
    @FXML
    private Label des;
    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    publication acc = null; 
    static publication pub;
    publication_Service service = new publication_Service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           acc=service.get_publication_affichage(Front_PubsController.indicePub);
             String ImageUrl = "http://localhost/images/";
        Image imag = new Image(ImageUrl + acc.getImg());
             titre.setText(acc.getTitre());
       des.setText(acc.getDescription());
    
        image.setImage(imag);
    }    

    @FXML
    private void commentaire(ActionEvent event) throws IOException {
        pub=acc;
          Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_Commentaire.fxml")));
            stage.setScene(scene);
            stage.show();
    }
    
}
