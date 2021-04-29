/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.employer;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author MSI
 */
public class EmployerCrud {
     Connection cnx;
     private Statement ste;
    private PreparedStatement pre;
     private String nomImage= "";
     
     public EmployerCrud(){
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
     
     public void add (employer p){
       
        try {
             
             pre=cnx.prepareStatement("INSERT INTO `bd_findjob`.`employer` ( `name`, `mdp`, `mail`, `num`,`localisation`,`categorie`,`img`) VALUES (?,?,?,?,?,?,?);");
          
            pre.setString(1,p.getName());
            pre.setString(2,p.getMdp());
            pre.setString(3, p.getMail());
            pre.setInt(4, p.getNum());
            pre.setString(5, p.getLocalisation());
            pre.setString(6, p.getCategorie());
            pre.setString(7, p.getImg());
            pre.executeUpdate(); 
            System.out.println("Employer added!");
        } catch (SQLException ex) { 
            System.out.println("erruer");
        }
 
         
  }
            public List<employer> getemployer(){
         List<employer> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM employer";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                employer p = new employer();
                p.setId(rs.getInt(1));
                p.setEmployeur_id(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setMdp(rs.getString("mdp"));
                p.setMail(rs.getString("mail"));
        
                p.setNum(rs.getInt("num"));
                p.setLocalisation(rs.getString("localisation"));
                p.setCategorie(rs.getString("categorie"));
                p.setImg(rs.getString("img"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return myList; 
    }
             public boolean delete (int employer)throws SQLException{
        String sql ="Delete FROM employer where id=?";
        boolean rowDeleted = false ; 
       PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst2.setInt(1,employer);
           rowDeleted= pst2.executeUpdate()> 0;
           return rowDeleted;
    }
             
              public void Update ( employer p2){
       
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

             
             
         
  }
              public ObservableList<employer> readAll() throws SQLException {
                   MyConnection jdbcDao = new MyConnection();
                  int k = jdbcDao.getId();
    ObservableList<employer> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rs=stm.executeQuery("select id,name,mail,num,localisation,categorie,img from employer where id='  " + k + "'");
     while (rs.next()) {                
      employer cl=new employer();
         cl.setId(rs.getInt("id"));
         EmployerCrud sc=new EmployerCrud();
       
      
         cl.setName(rs.getString("name"));
         cl.setNum(rs.getInt("num"));
        
         cl.setMail(rs.getString("mail"));
         cl.setLocalisation(rs.getString("localisation"));
      
         cl.setCategorie(rs.getString("categorie"));
         cl.setImg(rs.getString("Img"));
         
         arr.add(cl);
     }
    return arr;
    }
}
       
             
    
            
        
        
   
