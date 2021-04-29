/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import services.publication_Service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author M'Amine
 */
public class Front_PubsController implements Initializable {

    @FXML
    private Pane pnl_pubs;
    @FXML
    private ScrollPane scrollpaneProduit;
    @FXML
    private HBox hboxpubs;
    @FXML
    private Label username;
    publication_Service service_Pub = new publication_Service();
     static int indicePub = 0;
      private int taillePub =0;
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
    
        taillePub = service_Pub.nombre();
       
          Node[] nodes_formations= new Node[taillePub];
           scrollpaneProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indicePub = 0; indicePub < taillePub; indicePub++) {
            try {

                nodes_formations[indicePub] = FXMLLoader.load(getClass().getResource("/gui/Item_Publication.fxml"));

                hboxpubs.getChildren().add(nodes_formations[indicePub]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
