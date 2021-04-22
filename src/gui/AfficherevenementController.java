/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entities.Participation;
import entities.evenement;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import services.EvenementCRUD;
import services.ParticipationCRUD;
import services.TypeEventCRUD;
import net.glxn.qrgen.javase.QRCode;
import net.glxn.qrgen.core.image.ImageType;
import static tray.notification.NotificationType.ERROR;
import tray.notification.TrayNotification;






/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AfficherevenementController implements Initializable {

    @FXML
    private VBox vbox1;
    @FXML
    private ChoiceBox<String> catcho;
    @FXML
    private Button filter;
    @FXML
    private Button back;
    @FXML
    private Button Participationg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<evenement> le = new ArrayList<>();
        EvenementCRUD es = new EvenementCRUD();
        TypeEventCRUD sp = new TypeEventCRUD ();
  
        ObservableList<String> l = null;
        try {
          le = es.readAll();
            l = sp.readAll2();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catcho.setItems(l);
        for (evenement evv : le) {
       
                
            
            
                InputStream stream = null;
                try {
                    Date sd = evv.getDate_start();
                    Date sd1 = evv.getDate_end();
                    Label titre = new Label();
                    Label Desc = new Label();
                    Label date_start = new Label();
                    Label date_end = new Label();
                    Label nombre_par = new Label();
                    stream = new FileInputStream("C:\\Users\\Firas\\Documents\\NetBeansProjects\\findjob\\src\\image\\Noimage.PNG");
                    Image image = new Image(stream);
                    ImageView photo =new ImageView(image);
                    photo.setFitHeight(150);
                    photo.setFitWidth(150);
                    if(evv.getPhoto().length()>0)
                    {
                        System.out.println(evv.getPhoto().substring(0, 4));
                    if( "file".equals(evv.getPhoto().substring(0, 4)))
                    {
                        
                     photo = new ImageView(new Image (evv.getPhoto()));
                    photo.setFitHeight(150);
                    photo.setFitWidth(150);
                    }
                    }
                    

                    Text ty = new Text("date_start: ");
                    Text ty1 = new Text("deta_end : ");
                    Text dd = new Text(" | ");
                    ty.setFill(Color.DARKGREY);
                    dd.setFill(Color.DARKGREY);
                    titre.setText(evv.getTitre());
                    Desc.setText(evv.getDescription());
                    date_start.setText(evv.getDate_start().toString());
                    date_end.setText(evv.getDate_end().toString());
                    if (evv.getNombre_par()>0)
                    {
                        nombre_par.setText("Disponible");
                        nombre_par.setStyle("-fx-text-fill: #00ff00");
                        
                    }
                    else {
                        nombre_par.setText("Non disponible");
                        nombre_par.setStyle("-fx-text-fill: #ff0000");
                    }       HBox h2 = new HBox();
                    HBox h1 = new HBox();
                    VBox rv = new VBox();
                    HBox btn = new HBox();
                    VBox v1 = new VBox();
                    VBox v2 = new VBox();
                    HBox hv1 = new HBox();
                    String nom = null;
                        ParticipationCRUD pp = new ParticipationCRUD();
                    try {
                        if(pp.getpar(evv.getId(), 1))
                        {
                            nom="annuler";
                        }
                        else
                        {
                            nom="participer";
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Button bt = new Button(nom);
                    
                    bt.setOnAction(e->{
                        ParticipationCRUD pc = new ParticipationCRUD();
                        Participation P = new Participation();
                   
                    
                        try {
                            if(pc.getpar(evv.getId(), 1))
                            {
                                bt.setText("participer");
                                bt.setStyle("-fx-background-color: #fbb710; -fx-text-fill: black;");
                                pc.deletepar(evv.getId(), 1);
                                
                            }
                            else
                            {bt.setText("annuler");
                                bt.setStyle("-fx-background-color: #c9c4cb; -fx-text-fill: black;");
                             java.util.Date date=new java.util.Date();  
                        P.setDate(date.toString());
                        P.setId_employer(1);
                        try {
                            pc.addParticipation(P, evv.getId() );
                            
                            int x=pc.getIdPBy(evv.getId(), 1);
                            System.out.println(x);
                            qrcode(Integer.toString(x));
                        } catch (SQLException ex) {
                            Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                            }
                        }  catch (SQLException ex) {
                            Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        
                        
                    });     final Separator sep = new Separator();
                    vbox1.setSpacing(10);
                    vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
                    sep.setMaxWidth(Double.MAX_EXPONENT);
                    sep.setStyle(" -fx-text-fill: DARKGREY;");
                    h1.setAlignment(Pos.CENTER);
                    h2.setAlignment(Pos.CENTER);
                    h2.setSpacing(10);
                    hv1.getChildren().add(titre);
                    h1.getChildren().add(photo);
                    v1.getChildren().add(Desc);
                    h2.getChildren().add(ty);
                    h2.getChildren().add(date_start);
                    h2.getChildren().add(ty1);
                    h2.getChildren().add(date_end);
                    h2.getChildren().add(dd);
                    h2.getChildren().add(nombre_par);
                    btn.getChildren().add(bt);
                    btn.setSpacing(10);
                    vbox1.getChildren().add(hv1);
                    vbox1.getChildren().add(v1);
                    vbox1.getChildren().add(v2);
                    vbox1.getChildren().add(h1);
                    vbox1.getChildren().add(h2);
                    vbox1.getChildren().add(btn);
                    vbox1.getChildren().add(sep);
                    btn.setAlignment(Pos.CENTER);
                    titre.setFont(javafx.scene.text.Font.font("Arial", 20));
                    titre.setStyle("-fx-text-fill: #fbb710");
                    Desc.setFont(javafx.scene.text.Font.font("Courrier", 16));
                    Desc.setStyle("-fx-text-fill: #333333");
                    try {
                        if(pp.getpar(evv.getId(), 1))
                        {
                            bt.setStyle("-fx-background-color: #c9c4cb; -fx-text-fill: black;");
                        }
                        else
                        {
                            bt.setStyle("-fx-background-color: #fbb710; -fx-text-fill: black;");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    vbox1.setStyle("-fx-padding: 10;"
                            + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                            + "-fx-border-radius: 5;"  + "-fx-border-height:50");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
    }    
    }    

    @FXML
    private void filter(ActionEvent event) throws SQLException {
           back.setVisible(true);
        vbox1.getChildren().clear();
         List<evenement> le = new ArrayList<>();
        EvenementCRUD es=new EvenementCRUD();
       le= es.readAllBC(catcho.getValue());
              for (evenement evv : le) {
       
                
            
            
                InputStream stream = null;
                try {
                    Date sd = evv.getDate_start();
                    Date sd1 = evv.getDate_end();
                    Label titre = new Label();
                    Label Desc = new Label();
                    Label date_start = new Label();
                    Label date_end = new Label();
                    Label nombre_par = new Label();
                    stream = new FileInputStream("C:\\Users\\Firas\\Documents\\NetBeansProjects\\findjob\\src\\image\\Noimage.PNG");
                    Image image = new Image(stream);
                    ImageView photo =new ImageView(image);
                    photo.setFitHeight(150);
                    photo.setFitWidth(150);
                    if(evv.getPhoto().length()>0)
                    {
                        System.out.println(evv.getPhoto().substring(0, 4));
                    if( "file".equals(evv.getPhoto().substring(0, 4)))
                    {
                        
                     photo = new ImageView(new Image (evv.getPhoto()));
                    photo.setFitHeight(150);
                    photo.setFitWidth(150);
                    }
                    }
                    

                    Text ty = new Text("date_start: ");
                    Text ty1 = new Text("deta_end : ");
                    Text dd = new Text(" | ");
                    ty.setFill(Color.DARKGREY);
                    dd.setFill(Color.DARKGREY);
                    titre.setText(evv.getTitre());
                    Desc.setText(evv.getDescription());
                    date_start.setText(evv.getDate_start().toString());
                    date_end.setText(evv.getDate_end().toString());
                    if (evv.getNombre_par()>0)
                    {
                        nombre_par.setText("Disponible");
                        nombre_par.setStyle("-fx-text-fill: #00ff00");
                        
                    }
                    else {
                        nombre_par.setText("Non disponible");
                        nombre_par.setStyle("-fx-text-fill: #ff0000");
                    }       HBox h2 = new HBox();
                    HBox h1 = new HBox();
                    VBox rv = new VBox();
                    HBox btn = new HBox();
                    VBox v1 = new VBox();
                    VBox v2 = new VBox();
                    HBox hv1 = new HBox();
                    String nom = null;
                        ParticipationCRUD pp = new ParticipationCRUD();
                    try {
                        if(pp.getpar(evv.getId(), 1))
                        {
                            nom="annuler";
                        }
                        else
                        {
                            nom="participer";
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Button bt = new Button(nom);
                    
                    bt.setOnAction(e->{
                        ParticipationCRUD pc = new ParticipationCRUD();
                        Participation P = new Participation();
                   
                    
                        try {
                            if(pc.getpar(evv.getId(), 1))
                            {
                                bt.setText("participer");
                                bt.setStyle("-fx-background-color: #fbb710; -fx-text-fill: black;");
                                pc.deletepar(evv.getId(), 1);
                                
                            }
                            else
                            {bt.setText("annuler");
                                bt.setStyle("-fx-background-color: #c9c4cb; -fx-text-fill: black;");
                             java.util.Date date=new java.util.Date();  
                        P.setDate(date.toString());
                        P.setId_employer(1);
                        try {
                            pc.addParticipation(P, evv.getId() );
                            
                            int x=pc.getIdPBy(evv.getId(), 1);
                            System.out.println(x);
                            qrcode(Integer.toString(x));
                        } catch (SQLException ex) {
                            Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                            }
                        }  catch (SQLException ex) {
                            Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        
                        
                    });     final Separator sep = new Separator();
                    vbox1.setSpacing(10);
                    vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
                    sep.setMaxWidth(Double.MAX_EXPONENT);
                    sep.setStyle(" -fx-text-fill: DARKGREY;");
                    h1.setAlignment(Pos.CENTER);
                    h2.setAlignment(Pos.CENTER);
                    h2.setSpacing(10);
                    hv1.getChildren().add(titre);
                    h1.getChildren().add(photo);
                    v1.getChildren().add(Desc);
                    h2.getChildren().add(ty);
                    h2.getChildren().add(date_start);
                    h2.getChildren().add(ty1);
                    h2.getChildren().add(date_end);
                    h2.getChildren().add(dd);
                    h2.getChildren().add(nombre_par);
                    btn.getChildren().add(bt);
                    btn.setSpacing(10);
                    vbox1.getChildren().add(hv1);
                    vbox1.getChildren().add(v1);
                    vbox1.getChildren().add(v2);
                    vbox1.getChildren().add(h1);
                    vbox1.getChildren().add(h2);
                    vbox1.getChildren().add(btn);
                    vbox1.getChildren().add(sep);
                    btn.setAlignment(Pos.CENTER);
                    titre.setFont(javafx.scene.text.Font.font("Arial", 20));
                    titre.setStyle("-fx-text-fill: #fbb710");
                    Desc.setFont(javafx.scene.text.Font.font("Courrier", 16));
                    Desc.setStyle("-fx-text-fill: #333333");
                    try {
                        if(pp.getpar(evv.getId(), 1))
                        {
                            bt.setStyle("-fx-background-color: #c9c4cb; -fx-text-fill: black;");
                        }
                        else
                        {
                            bt.setStyle("-fx-background-color: #fbb710; -fx-text-fill: black;");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    vbox1.setStyle("-fx-padding: 10;"
                            + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                            + "-fx-border-radius: 5;"  + "-fx-border-height:50");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherevenementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
    } 
       
    }
    public void qrcode( String x)
    {
        
         ByteArrayOutputStream out = QRCode.from(x).to(ImageType.PNG).withSize(200, 200).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        // SHOW QR CODE

        Image image = new Image(in);
        ImageView view = new ImageView(image);
        view.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
        saveToFile(image);

    }
    public static void saveToFile(Image image) {
        java.util.Date date=new java.util.Date();  
        String fileName = new SimpleDateFormat("yyyyMMddHHmm'.png'").format(date);
    File outputFile = new File(System.getProperty("user.home") + "/Desktop/PASS-"+fileName);
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
      ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

    @FXML
    private void back(ActionEvent event)  throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/back.fxml"));
            Parent root= loader.load();
  
            back.getScene().setRoot(root);
    }

    @FXML
    private void Participation(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listePar.fxml"));
            Parent root= loader.load();
  
            Participationg.getScene().setRoot(root);
    }
    
}
