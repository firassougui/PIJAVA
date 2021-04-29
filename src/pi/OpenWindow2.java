
package pi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpenWindow2 {
	Stage primaryStage;
	String chemin;
	String title;
	Pane sPane;

	public OpenWindow2(Stage primaryStage, String chemin, String title) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(chemin));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
                
	}
        
	
	

}
