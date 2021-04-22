/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.TypeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Firas
 */
public class TypeEventCRUD {
    Connection cnx;
    public TypeEventCRUD()
    {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void addtypeevent(TypeEvent tp) throws SQLException{
         String requete = "INSERT INTO type_event (type,description) "
                + "VALUES ('"+tp.getType()+"','"+tp.getDescription()+"')";
         Statement stm =cnx.createStatement();
         stm.executeUpdate(requete);
         System.out.println("TYPE Event est bien ajoutée !!");
         
        
    }
    public void addtypeevent2(TypeEvent tp) throws SQLException
    {
        String requete = "INSERT INTO type_event (type,description) "
                 + "VALUES (?,?)";
        PreparedStatement pst =
                    cnx.prepareStatement(requete);
            pst.setString(1, tp.getType());
            pst.setString(2, tp.getDescription());
            pst.executeUpdate();
            System.out.println("typeevent added!");
            
        
    }


    public List<TypeEvent> gettypeevent1(){
        
        List<TypeEvent> myList = new ArrayList();
        PreparedStatement pt = null;
               try {
            pt = cnx.prepareStatement("select * from type_event");
            ResultSet rs = null;
            rs = pt.executeQuery();
            
            while(rs.next()){
                TypeEvent p = new TypeEvent();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setDescription("description");
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return myList; 
        
        
    }
    public ObservableList<TypeEvent> triCatsByLabel()throws SQLException
    {
        ObservableList<TypeEvent> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select * from type_event order by type");
     while (rst.next()) {                
      TypeEvent cl=new TypeEvent();
         cl.setId(rst.getInt("id"));
         cl.setType(rst.getString("type"));
         cl.setDescription(rst.getString("description"));
        
         
         arr.add(cl);
     }
    return arr;
    }

      public ObservableList<TypeEvent> readAll() throws SQLException {
    ObservableList<TypeEvent> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select * from type_event");
     while (rst.next()) {                
      TypeEvent cl=new TypeEvent();
         cl.setId(rst.getInt("id"));
        
         cl.setType(rst.getString("type"));
         cl.setDescription(rst.getString("description"));
         
         arr.add(cl);
     }
    return arr;
    }
      
    public void deleteCategory(int id) throws SQLException {
     Statement stm=cnx.createStatement();
        String query="DELETE FROM `type_event` WHERE `id`='"+id+"'";
        stm.executeUpdate(query); 
    }
         
    
    
     public void updateCategory(TypeEvent cl, int id) throws SQLException {
   Statement stm=cnx.createStatement();
    String query="UPDATE `type_event` SET `type`='"+cl.getType()+"' , `description`='"+cl.getDescription()+"' WHERE `id`='"+id+"'";
    stm.executeUpdate(query);
    }
     
      public String getTpeBy(int ID)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT type FROM `type_event` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("type");
    return arr;
    }
      
             public String getnameBy(int ID)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT nom FROM `employeur` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("nom");
    return arr;
    }
             
     public ObservableList<String> readAll2() throws SQLException {
    ObservableList<String> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select type from type_event");
     while (rst.next()) {                
         
        
         
         arr.add(rst.getString("type"));
     }
    return arr;
    }
     
    public ObservableList<String> readAll3() throws SQLException {
    ObservableList<String> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select nom from employeur");
     while (rst.next()) {                
         
        
         
         arr.add(rst.getString("nom"));
     }
    return arr;
    }
     public int getIDBy(String type)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT id FROM `type_event` WHERE `type`='"+type+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("id");
    return arr;
    }
      public int getIDEBy(String nom)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT id FROM `employeur` WHERE `nom`='"+nom+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("id");
    return arr;
    }
      public String getnomBy(int ID)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT name FROM `employer` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("name");
    return arr;
    }
            public String geteventBy(int ID)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT titre FROM `evenement` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("titre");
    return arr;
    }
        
      
        
    
}
