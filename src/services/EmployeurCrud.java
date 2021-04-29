/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.employeur;
import tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author MSI
 */
public class EmployeurCrud {
     Connection cnx;
     private Statement ste;
    private PreparedStatement pre;
     
     public EmployeurCrud(){
    cnx = MyConnection.getInstance().getCnx();
    }
    
      /* public void addEmployer (employer p){
            
            try {
            String requete = "INSERT INTO employer (name,mdp,mail,num,localisation,categorie,img) " + "VALUES ('"+p.getName()+"','"+p.getMdp()+"','"+p.getMail()+"','"+p.getNum()+"','"+p.getLocalisation()+"','"+p.getCategorie()+"','"+p.getImg()+"')"; 
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
           System.out.println("Personne ajoutée!");
    } catch (SQLException ex) {
            System.out.println("msssssssg");
        }
        }*/
     public void add (employeur p){
       
        try {
             pre=cnx.prepareStatement("INSERT INTO `bd_findjob`.`employeur` ( `nom`, `pass`, `adresse`, `numero`,`loca`,`logo`) VALUES (?,?,?,?,?,?);");
          //  String requete = "INSERT INTO employer (name,mdp,mail,num,localisation,categorie,img)"
            //        + "VALUES (?,?,?,?,?,?,?)";
          //  PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
           // pre.setInt(1, 13);
            pre.setString(1,p.getNom());// i stoled from category id mel base de donnee aale khaterha clé etranger
            pre.setString(2,p.getPass());
           pre.setString(3, p.getAdresse());
            pre.setInt(4, p.getNumero());
            pre.setString(5, p.getLoca());
       
            pre.setString(6, p.getLogo());
            pre.executeUpdate(); 
            System.out.println("Employeur added!");
        } catch (SQLException ex) { 
            System.out.println("erruer");
        }
 
         
  }
            public List<employeur> getemployeur(){
         List<employeur> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM employeur";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                employeur p = new employeur();
                p.setId(rs.getInt(1));
              
                p.setNom(rs.getString("nom"));
                p.setPass(rs.getString("pass"));
                p.setAdresse(rs.getString("adresse"));
        
                p.setNumero(rs.getInt("numero"));
                p.setLoca(rs.getString("loca"));
               
                p.setLogo(rs.getString("logo"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return myList; 
    }
             public boolean delete (int employeur)throws SQLException{
        String sql ="Delete FROM employeur where id=?";
        boolean rowDeleted = false ; 
       PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst2.setInt(1,employeur);
           rowDeleted= pst2.executeUpdate()> 0;
           return rowDeleted;
    }
             
     /*          public void Update ( int idd,employer p2){
       
        try {
            String requeteGet ="select * from employer where id=?" ; 
            PreparedStatement pst1 = MyConnection.getInstance().getCnx().prepareStatement( requeteGet);
            ResultSet rs;
            rs = pst1.executeQuery( requeteGet);
            pst1.executeUpdate(); 
            employer p1 = new employer();
            p1.setEmployeur_id(p2.getEmployeur_id()); 
            p1.setName(p2.getName());
            p1.setMdp(p2.getMdp());
            p1.setMail(p2.getMail());
            p1.setNum(p2.getNum());
            p1.setLocalisation(p2.getLocalisation());
            p1.setCategorie(p2.getCategorie());
            p1.setImg(p2.getImg());
            String requete = "UPDATE employer SET employeur_id=?, name=?, mdp=?, mail=?, num=?, localisation=? ,categorie=? ,img=? WHERE id=idd" ; 
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst2.executeUpdate(); 
            System.out.println("employer updated!");
        } catch (SQLException ex) { 
            System.out.println("erruerddd");
        }
*/
             
             
         
  }
       
             
    
            
        
        
   
