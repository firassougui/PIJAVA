/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.PIDev.JavaFx;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author walid
 */
public class AjouterOffre extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        
        
        try {
             Parent root=FXMLLoader.load(getClass().getResource("ajouterOffre.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e){
   if(e.getCause().getClass().equals(AssertionError.class)){
      // handle your exception  1
   } else {
      // handle the rest of the world exception 
   }
} 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           
        launch(args);
      
    }
    
}
