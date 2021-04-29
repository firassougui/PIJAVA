/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author M'Amine
 */
public class Commentaire_Service implements IService<commentaire> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(commentaire u) throws SQLException {
    PreparedStatement ps;

        
        String query = "INSERT INTO `commantaire`( `com_pub`, `contenu`) VALUES (?,?)";
        try {
            ps = c.prepareStatement(query);
              ps.setInt(1, u.getId_pub());
             ps.setString(2, u.getContenu());
        
         
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }  
    
    
    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `commantaire` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }      }

    @Override
    public void Modifier(commentaire u, int id) throws SQLException {
    PreparedStatement ps;
        String query = "UPDATE `commantaire` SET `contenu`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
   
             ps.setString(1, u.getContenu());
          
             ps.setInt(2,id);
             
           
            ps.execute();
    

        } catch (Exception e) {
        }   }

    @Override
    public ObservableList<commentaire> Affichertout() throws SQLException {
      ObservableList<commentaire> list = FXCollections.observableArrayList();
      String requete = "select * from commantaire ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new commentaire(rs.getInt("id"),rs.getString("contenu"),rs.getInt("com_pub")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;    }
       public ObservableList<commentaire> Affichertout_pub(int id) throws SQLException {
      ObservableList<commentaire> list = FXCollections.observableArrayList();
      String requete = "select * from commantaire where com_pub="+id;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new commentaire(rs.getInt("id"),rs.getString("contenu"),rs.getInt("com_pub")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;    }
    
}
