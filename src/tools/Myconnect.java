/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author walid
 */
public class Myconnect {
      public String url="jdbc:mysql://localhost:3306/bd_findjob";
    public String user="root";
    public String pwd="";
Connection cnx;
public static Myconnect instance;

    public static void setInstance(Myconnect instance) {
        Myconnect.instance = instance;
    }

    public static Myconnect getInstance() {
        if (instance ==null){
            instance=new Myconnect();
        }
        
        return instance;
    }

    public Myconnect() {
          try {
            cnx=  DriverManager.getConnection(url,user,pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getcnx() {
return cnx;    }

}
