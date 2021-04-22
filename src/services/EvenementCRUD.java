/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Firas
 */
public class EvenementCRUD {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    
      Connection cnx;
    public EvenementCRUD()
    {
        cnx=MyConnection.getInstance().getCnx();
    }
    public void addevent(evenement tp) throws SQLException
    {
        String requete = "INSERT INTO evenement (employeur_event_id,titre,nombre_par,date_start,date_end,description,type_id,photo,updated_at "
                + ") "
                 + "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst =
                    cnx.prepareStatement(requete);

            pst.setInt(1, tp.getId_employeur());
            pst.setString(2, tp.getTitre());
            pst.setInt(3, tp.getNombre_par());
            pst.setDate(4, tp.getDate_start());
            pst.setDate(5, tp.getDate_end());
            pst.setString(6, tp.getDescription());
            pst.setInt(7, tp.getId_type());
            pst.setString(8, tp.getPhoto());
            pst.setDate(9,sqlDate);
            pst.executeUpdate();
            System.out.println("event added!");
            
        
    }
     public List<evenement> afficherPro () throws SQLException {
        List<evenement> ll = new ArrayList<>();
       
            PreparedStatement pt =cnx.prepareStatement("select * from evenement");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) {                
      evenement cl=new evenement();
         cl.setId(rs.getInt("id"));
         TypeEventCRUD sc=new TypeEventCRUD();
         cl.setId_employeur(rs.getInt("employeur_event_id"));
         String c=sc.getnameBy(rs.getInt("employeur_event_id"));
         cl.setNom(c);
         cl.setTitre(rs.getString("titre"));
         cl.setNombre_par(rs.getInt("nombre_par"));
        
         cl.setDate_start(rs.getDate("date_start"));
         cl.setDate_end(rs.getDate("date_end"));
         cl.setId_type(rs.getInt("type_id"));
         String ch=sc.getTpeBy(rs.getInt("type_id"));
         cl.setType(ch);
         cl.setDescription(rs.getString("description"));
         cl.setPhoto(rs.getString("photo"));
         
         ll.add(cl);
     }
      
        return ll;
    }
     public ObservableList<evenement> readAll() throws SQLException {
    ObservableList<evenement> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rs=stm.executeQuery("select id,employeur_event_id,titre,nombre_par,date_start,date_end,type_id,description,photo from evenement");
     while (rs.next()) {                
      evenement cl=new evenement();
         cl.setId(rs.getInt("id"));
         TypeEventCRUD sc=new TypeEventCRUD();
         cl.setId_employeur(rs.getInt("employeur_event_id"));
         String c=sc.getnameBy(rs.getInt("employeur_event_id"));
         cl.setNom(c);
         cl.setTitre(rs.getString("titre"));
         cl.setNombre_par(rs.getInt("nombre_par"));
        
         cl.setDate_start(rs.getDate("date_start"));
         cl.setDate_end(rs.getDate("date_end"));
         cl.setId_type(rs.getInt("type_id"));
         String ch=sc.getTpeBy(rs.getInt("type_id"));
         cl.setType(ch);
         cl.setDescription(rs.getString("description"));
         cl.setPhoto(rs.getString("photo"));
         
         arr.add(cl);
     }
    return arr;
    }
      public void deleteEvenement(int id) throws SQLException {
     Statement stm=cnx.createStatement();
        String query="DELETE FROM `evenement` WHERE `id`='"+id+"'";
        stm.executeUpdate(query); 
      }
       public String gettypeBy(int ID) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT titre FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("titre");
    return arr;
    }
        public String getdescBy(int ID) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT description FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("description");
    return arr;
    }
      
        public Date getDateSBy(int ID) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT date_start FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         java.sql.Date arr = null;
        if(rst.next())
         arr=rst.getDate("date_start");
    return arr;    }
        public Date getDateEBy(int ID) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT date_end FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         java.sql.Date arr = null;
        if(rst.next())
         arr=rst.getDate("date_end");
    return arr;    }
        public Integer getNbPBy(int ID) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT nombre_par FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("nombre_par");
    return arr;    }
         public Integer getIdEPBy(int ID) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT employeur_event_id FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("employeur_event_id");
    return arr;    }
          public Integer getIdTPBy(int ID) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT type_id FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("type_id");
    return arr;    }
       public String getphotoBy(int ID) throws SQLException {
     Statement stm=cnx.createStatement();
        String query="SELECT photo FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("photo");
      return arr;    }
         
         
         public void updateProduct(evenement cl, int id) throws SQLException {
   Statement stm=cnx.createStatement();
    String query="UPDATE `evenement` SET `employeur_event_id`='"+cl.getId_employeur()+"',`titre`='"+cl.getTitre()+"',`nombre_par`='"+cl.getNombre_par()+"',`date_start`='"+cl.getDate_start()+"',`date_end`='"+cl.getDate_end()+"',`description`='"+cl.getDescription()+"',`type_id`='"+cl.getId_type()+"',`photo`='"+cl.getPhoto()+"' WHERE `id`='"+id+"'";
    stm.executeUpdate(query);
    }
          public ObservableList<evenement> readAllBC(String Cat) throws SQLException {
        ObservableList<evenement> arr;
        TypeEventCRUD ss=new TypeEventCRUD();
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    int ID=ss.getIDBy(Cat);
    ResultSet rs=stm.executeQuery("select id,employeur_event_id,titre,nombre_par,date_start,date_end,type_id,description,photo from evenement where type_id='"+ID+"' ");
     while (rs.next()) {                
               evenement cl=new evenement();
         cl.setId(rs.getInt("id"));
         TypeEventCRUD sc=new TypeEventCRUD();
         cl.setId_employeur(rs.getInt("employeur_event_id"));
         String c=sc.getnameBy(rs.getInt("employeur_event_id"));
         cl.setNom(c);
         cl.setTitre(rs.getString("titre"));
         cl.setNombre_par(rs.getInt("nombre_par"));
        
         cl.setDate_start(rs.getDate("date_start"));
         cl.setDate_end(rs.getDate("date_end"));
         cl.setId_type(rs.getInt("type_id"));
         String ch=sc.getTpeBy(rs.getInt("type_id"));
         cl.setType(ch);
         cl.setDescription(rs.getString("description"));
         cl.setPhoto(rs.getString("photo"));
         
         arr.add(cl);
     }
    return arr;
    }
}
