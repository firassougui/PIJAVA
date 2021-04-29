/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.entities;

import edu.projet.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author MSI
 */
public class employer {
    private int id ;
    private int employeur_id ;
    private String name;
    private String mdp;
    private String mail;
    private int num ;
    private String localisation;
    private String categorie;
    private String img;

  
   @Override
    public String toString() {
        return "employer{" + "id=" + id + ", employeur_id=" + employeur_id + ", name=" + name + ", mdp=" + mdp + ", mail=" + mail + ", num=" + num +", localisation=" + localisation +", categorie=" + categorie + ", img=" + img + '}';
    }
    
    public employer(){
        
}

    public employer(int id) {
        this.id = id;
    }
    
    
     

    public employer(String name, String mail, int num, String localisation, String categorie, String img) {
        this.name = name;
        this.mail = mail;
        this.num = num;
        this.localisation = localisation;
        this.categorie = categorie;
        this.img = img;
    }
    

    public employer(int employeur_id, String name, String mdp, String mail, int num, String localisation, String categorie, String img) {
        this.employeur_id = employeur_id;
        this.name = name;
        this.mdp = mdp;
        this.mail = mail;
        this.num = num;
        this.localisation = localisation;
        this.categorie = categorie;
        this.img = img;
    }
    

    public employer(String name, String mdp, String mail, int num, String localisation, String categorie, String img) {
        this.name = name;
        this.mdp = mdp;
        this.mail = mail;
        this.num = num;
        this.localisation = localisation;
        this.categorie = categorie;
        this.img = img;
    }
    
    public employer(int id,int employeur_id, String name, String mdp, String mail, int num, String localisation, String categorie, String img ){
        this.id = id;
        this.employeur_id = employeur_id;
        this.name = name;
        this.mdp = mdp;
        this.mail = mail;
        this.num = num;
        this.localisation = localisation;
        this.categorie = categorie;
        this.img = img; 
}

    public employer(String mail, String mdp) {
        this.mail = mail;
         this.mdp = mdp;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
      public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getEmployeur_id() {
        return employeur_id;
    }

    public void setEmployeur_id(int employeur_id) {
        this.employeur_id = employeur_id;
    }

    
    public void modifierEmploye(int id ) throws SQLException {
        Connection con =MyConnection.getInstance().getCnx();
        try {

            Statement ste = con.createStatement();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String req = "UPDATE employer SET name='"+this.name+"',mdp='"+this.mdp+"',mail='"+this.mail+"',localisation='"+this.localisation+"',img='"+this.img+"',categorie='"+this.categorie+"',num='"+this.num+"' WHERE id='"+ id +"' ";
        PreparedStatement preparedStatement;
        preparedStatement = con.prepareStatement(req);
        preparedStatement.executeUpdate();

    }
          public boolean exist() throws SQLException
    {
        Connection con = MyConnection.getInstance().getCnx();

        ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT * FROM employer WHERE id="+this.id);
        if (rs.next())
            return true;
        else return false;
    }
          
          
           public void Delete() throws SQLException {
        Connection con = MyConnection.getInstance().getCnx();
        try {

            Statement ste = con.createStatement();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String req = "DELETE FROM employer WHERE id="+this.id;
        PreparedStatement preparedStatement;
        preparedStatement = con.prepareStatement(req);
        preparedStatement.executeUpdate();
         Alert a = new Alert(Alert.AlertType.INFORMATION,"employee Supprimé ",ButtonType.OK);
         a.showAndWait();

    }
                   public boolean deleteE (int employer)throws SQLException{
        String sql ="Delete FROM employer where id=?";
        boolean rowDeleted = false ; 
       PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(sql);
            pst2.setInt(1,employer);
           rowDeleted= pst2.executeUpdate()> 0;
           return rowDeleted;
    }
                      public void modifierpass(String mail ) throws SQLException {
        Connection con =MyConnection.getInstance().getCnx();
        try {

            Statement ste = con.createStatement();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        String req = "UPDATE employer SET mdp='"+this.mdp+"' WHERE mail ='"+ mail +"' ";
        PreparedStatement preparedStatement;
        preparedStatement = con.prepareStatement(req);
        preparedStatement.executeUpdate();

    }
 
    
}