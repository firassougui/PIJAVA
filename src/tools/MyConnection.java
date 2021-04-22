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
 * @author Firas
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/bd_findjob";
    public String user="root";
    public String pwd="";
    public static MyConnection instance;
     Connection cnx;
    @SuppressWarnings("empty-statement")
    public MyConnection()
    {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance() {
         if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
