/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.category_publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author M'Amine
 */
public class category_publication_Service implements IService<category_publication> {



    private Connection c = MyConnexion.getInsCon().getcnx();
 public ObservableList<Integer> read_ids()

    {
        ObservableList<Integer> mylist= FXCollections.observableArrayList();


        try {
            PreparedStatement ps = c.prepareStatement("select * from category_publication ");
            ResultSet res = ps.executeQuery();

       
            while (res.next()){
        
                mylist.add(res.getInt("id"));
            }


        } catch (SQLException ex) {
        }
        System.out.print(mylist);
        return mylist;
    }
    @Override
    public void Ajouter(category_publication u) throws SQLException {
      PreparedStatement ps;

        
        String query = "INSERT INTO `category_publication`( `titre`, `description`) VALUES (?,?)";
        try {
            ps = c.prepareStatement(query);
              ps.setString(1, u.getTitre());
             ps.setString(2, u.getDescription());
        
         
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }    }

    @Override
    public void Supprimer(int t) throws SQLException {
       PreparedStatement ps;

        String query = "DELETE FROM `category_publication` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }  
    
    }

    @Override
    public void Modifier(category_publication u, int id) throws SQLException {
   PreparedStatement ps;
        String query = "UPDATE `category_publication` SET `titre`=?,`description`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
   
             ps.setString(1, u.getTitre());
             ps.setString(2, u.getDescription());
             ps.setInt(3,id);
             
           
            ps.execute();
    

        } catch (Exception e) {
        }    }

    @Override
    public ObservableList<category_publication> Affichertout() throws SQLException {
       ObservableList<category_publication> list = FXCollections.observableArrayList();
      String requete = "select * from category_publication ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new category_publication(rs.getInt("id"),rs.getString("titre"),rs.getString("description")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;  }
     public ObservableList<category_publication> serach(String cas) throws SQLException {
                            ObservableList<category_publication> list = FXCollections.observableArrayList();

            String requete = "select * from category_publication where  titre LIKE '%" + cas + "%' or description LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new category_publication(rs.getInt("id"),rs.getString("titre"),rs.getString("description")));

             
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return list; 

    
    
    
    
    
    
    
    }
}
