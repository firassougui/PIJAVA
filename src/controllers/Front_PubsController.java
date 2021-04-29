/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.publication_Service;
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
    @FXML
    private Button btn_pubs;
    publication_Service service_Pub = new publication_Service();
     static int indicePub = 0;
      private int taillePub =0;
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

                nodes_formations[indicePub] = FXMLLoader.load(getClass().getResource("/GUI/Item_Publication.fxml"));

                hboxpubs.getChildren().add(nodes_formations[indicePub]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         
    }
    
}
