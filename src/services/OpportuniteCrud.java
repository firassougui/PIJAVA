/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Candidature;
import entities.Opportunite;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author Firas
 */
public class OpportuniteCrud {
        
    public void addOpportunite(Opportunite O){
        String Requete="INSERT INTO Opportunite (op_employeur_id,titre,lieu,description,nom_entreprise,taille_entreprise,poste,media,nombre_recrutement,logo)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {   
            PreparedStatement pst=new MyConnection().getCnx().prepareStatement(Requete);
           
            pst.setString(1, ""+O.getOp_employeur_id());
            pst.setString(2, O.getTitre());
            pst.setString(3, O.getLieu());
            pst.setString(4, O.getDescription());
            pst.setString(5, O.getNom_entreprise());
            pst.setString(6, O.getTaille_entreprise());
            pst.setString(7, O.getPoste());
            pst.setString(8, O.getMedia());
            pst.setInt   (9, O.getNombre_recrutement());
            pst.setString(10, O.getLogo());

            pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "L'element est Ajouter avec succeés");

 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
    }
    

}
