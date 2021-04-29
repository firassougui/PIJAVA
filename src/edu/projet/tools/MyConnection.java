/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.tools;
import edu.projet.entities.employer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MSI
 */
public class MyConnection {
    public String url ="jdbc:mysql://localhost:3306/bd_findjob"; 
    public String user= "root" ; 
    public String pwd ="";
      private static final String SELECT_QUERY = "SELECT * FROM employer WHERE mail = ? and mdp = ?";
         private static final String SELECT_QUERYY = "SELECT * FROM employer";
      
     
    public static MyConnection instance;
    Connection cnx ; 
   public MyConnection(){
        
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion établie !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());   
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
     public boolean validate(String mail, String mdp ) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager.getConnection(url,user,pwd);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, mdp);
             
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
       String mail ;
       String mdp;
   public int getId() throws SQLException {
  
             boolean flag = validate(mail,mdp);
        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager.getConnection(url,user,pwd);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERYY)) {
            
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if (resultSet.next()) {
                 return resultSet.getInt(1);
             
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return 0;
    }
}



    

