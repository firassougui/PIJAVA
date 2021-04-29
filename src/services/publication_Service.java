/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IService.IService;
import tools.MyConnexion;
import entities.publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author M'Amine
 */
public class publication_Service implements IService<publication> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    public void Ajouter(publication u) throws SQLException {
       PreparedStatement ps;

        
        String query = "INSERT INTO `publication`( `categorie_id`, `titre`, `description`, `vus`, `likes`, `date`, `img`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
              ps.setInt(1, u.getCategorie_id());
             ps.setString(2, u.getTitre());
        ps.setString(3, u.getDescription());
        ps.setInt(4, u.getVus());
            ps.setInt(5, u.getLikes());
             ps.setDate(6,  new java.sql.Date(new Date().getTime()));
          ps.setString(7, u.getImg());
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
    }

    @Override
    public void Supprimer(int t) throws SQLException {
   PreparedStatement ps;

        String query = "DELETE FROM `publication` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }     }

    @Override
    public void Modifier(publication t, int id) throws SQLException {
   PreparedStatement ps;
        String query = "UPDATE `publication` SET `titre`=?,`description`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
   
             ps.setString(1, t.getTitre());
             ps.setString(2, t.getDescription());
             ps.setInt(3,id);
             
           
            ps.execute();
    

        } catch (Exception e) {
        } 
    }

    @Override
    public ObservableList<publication> Affichertout() throws SQLException {
    ObservableList<publication> list = FXCollections.observableArrayList();
      String requete = "select * from publication ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new publication(rs.getInt("id"),rs.getInt("categorie_id"),rs.getString("titre"),rs.getString("description"),rs.getInt("vus"),rs.getInt("likes"),rs.getString("img"),rs.getDate("date")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;    }
    
    public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr from publication  ";

        try {
               PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }
 public publication get_publication_affichage(int i) {
        publication pub = null;
        int nombre = 0;
      String requete = "SELECT *  FROM  publication "  ;
                       
         try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (i == nombre) {
                    System.out.println("dqsdsq");
  
              pub=new publication(rs.getInt("id"),rs.getInt("categorie_id"),rs.getString("titre"),rs.getString("description"),rs.getInt("vus"),rs.getInt("likes"),rs.getString("img"),rs.getDate("date"));

                }
                nombre++;
                         }

        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return pub;

    }
}
