/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author M'Amine
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // admin pub
      Parent root = FXMLLoader.load(getClass().getResource("Home_Publication.fxml"));
          // admin
      // Parent root = FXMLLoader.load(getClass().getResource("Home_Categorie.fxml"));
        
        //user
      //Parent root = FXMLLoader.load(getClass().getResource("Front_Publication.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
