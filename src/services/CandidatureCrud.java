/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Candidature;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author Firas
 */
public class CandidatureCrud {
        public void addCandidature(Candidature C){
        String Requete="INSERT INTO Candidature (titre_id,fonction,type_contrat,horaires,mode_salaire,periode,annuel_mois)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {   
            PreparedStatement pst=new MyConnection().getCnx().prepareStatement(Requete);
           
            pst.setString(1, ""+C.getTitre_id());
             pst.setString(2, C.getFonction());
             pst.setString(3, C.getType_contrat() );
            pst.setString(4, C.getHoraires());
            pst.setString(5, C.getMode_salaire());
            pst.setString(6, C.getPeriode());
            pst.setInt(7, C.getAnnuel_mois());
           
           
        

            pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "L'element est Ajouter avec succeés");

 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
    }
    
   
    
    
}
