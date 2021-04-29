/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Config.config;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.employer;

import tools.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;


public class LoginController {

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    @FXML
    private Hyperlink sendE;
     Stage primaryStage = new Stage();
     String chemin = "";
     int randomCode;
    @FXML
    private AnchorPane login;
    @FXML
    private AnchorPane login1;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton register;

    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String mail = emailIdField.getText();
        String mdp = passwordField.getText();

        MyConnection jdbcDao = new MyConnection();
        boolean flag = jdbcDao.validate(mail,mdp);
        int k = jdbcDao.getId();
        System.out.println(k);
        
     

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
     
              chemin = "afficherevenement.fxml";
	new OpenWindow2(primaryStage,chemin, "modifier");
            
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    @FXML
    public void sendE(ActionEvent event){
       
  
            chemin = "forgetPass.fxml";
	new OpenWindow2(primaryStage,chemin, "verif");
         
   
}

    @FXML
    private void register(ActionEvent event) {
          chemin = "ajoutEm.fxml";
	new OpenWindow2(primaryStage,chemin, "ajouter");
    }

}