package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author M'Amine
 */
public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
          Parent root = FXMLLoader.load(getClass().getResource("/pidevv/Accueil.fxml"));
          //    Parent root = FXMLLoader.load(getClass().getResource("/pidevv/Stati.fxml"));
          
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
