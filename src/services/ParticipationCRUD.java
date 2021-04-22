/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Participation;
import entities.evenement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import tools.MyConnection;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 *
 * @author Firas
 */
public class ParticipationCRUD {
     Connection cnx;
     
     public ParticipationCRUD(){
    
     cnx = MyConnection.getInstance().getCnx();
}
      public void addParticipation(Participation p, int id ) throws SQLException{
         String requete = "INSERT INTO participation (date,id_employer,id_event  ) "
                + "VALUES ('"+p.getDate()+"','"+p.getId_employer()+"','"+id+"')";
         Statement stm =cnx.createStatement();
         stm.executeUpdate(requete);
         System.out.println("Partipation est bien ajoutée !!");
         
        
    } 
        public Integer getIdPBy(int ID,int ID1) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT Id FROM `participation` WHERE `id_event`='"+ID+"' AND `id_employer`='"+ID1+"' ";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("Id");
    return arr;    }
    public Boolean getpar(int ID,int ID1) throws SQLException
    {
        Statement stm=cnx.createStatement();
        String query="SELECT Id FROM `participation` WHERE `id_event`='"+ID+"' AND `id_employer`='"+ID1+"' ";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("Id");
    return arr!=0;
    }
          public void deletepar(int ID,int ID1) throws SQLException {
     Statement stm=cnx.createStatement();
        String query="DELETE FROM `participation` WHERE `id_event`='"+ID+"' AND `id_employer`='"+ID1+"' ";
        stm.executeUpdate(query); 
      }
          
          
           public List<Participation> afficherPar () throws SQLException {
        List<Participation> ll = new ArrayList<>();
       
            PreparedStatement pt =cnx.prepareStatement("select * from participation");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) {                
      Participation par=new Participation();
         
         TypeEventCRUD sc=new TypeEventCRUD();
 
         par.setId_event(rs.getInt("id_event"));
         String ch=sc.geteventBy(rs.getInt("id_event"));
         par.setTitre(ch);
        
         ll.add(par);
     }
      
        return ll;
           }
            public ObservableList<Participation> readAll() throws SQLException {
    ObservableList<Participation> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rs=stm.executeQuery("select id_event,date from participation");
     while (rs.next()) {                
    Participation par=new Participation();
         
         TypeEventCRUD sc=new TypeEventCRUD();
 
         
         String ch=sc.geteventBy(rs.getInt("id_event"));
         System.out.println(ch);
         par.setTitre(ch);
        par.setDate(rs.getString("date"));
       
         
         
         arr.add(par);
     }
    return arr;
    }
              public void pdf(int ID) throws FileNotFoundException, DocumentException
    {
        try {
              java.util.Date date=new java.util.Date();  
        String fileName = new SimpleDateFormat("yyyyMMddHHmm'.pdf'").format(date);
            String file_name = System.getProperty("user.home") + "/Desktop/pdf/participation-"+fileName;
            Document document = new Document();
            
       
            PreparedStatement pt = cnx.prepareStatement("select * from participation WHERE `id`='"+ID+"' ");
            ResultSet rs = pt.executeQuery();
            TypeEventCRUD te=new TypeEventCRUD();
            System.out.println(ID);
            if (rs.next()) { 
                String titre = te.geteventBy(rs.getInt("id_event"));
                String nom = te.getnomBy(rs.getInt("id_employer"));
                System.out.println(nom);
                System.out.println(titre);
                
         ByteArrayOutputStream out = QRCode.from(Integer.toString(ID)).to(ImageType.PNG).withSize(200, 200).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        // SHOW QR CODE

        Image image = new Image(in);
        PdfWriter.getInstance(document, new FileOutputStream(new File(file_name)));
                
                Paragraph para=new Paragraph("Participant : " + nom + " ,Evenement: " + titre + ", Date : " + rs.getString("date") );
                document.open();
                document.add(para);
                document.add(new Paragraph(" "));
               //document.add((Element) image);
              
               
             // Alert alert = new Alert(Alert.AlertType.INFORMATION);
            // alert.setTitle("PDF");
            // alert.setHeaderText("Information");
            // alert.setContentText("Voir votre dossier pdf sur le bureau  ");
             
           // alert.showAndWait();

            }
            TrayNotification tray = new TrayNotification("succès", "Voir votre dossier pdf sur le bureau", SUCCESS);
            tray.showAndWait();  
            
        } catch (SQLException ex) {
            Logger.getLogger(Participation.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Participation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
              
}
