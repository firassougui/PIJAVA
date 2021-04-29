/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.tests;
import edu.projet.entities.employer;
import edu.projet.tools.MyConnection;
import edu.projet.services.EmployerCrud;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class MainClass {
    public static void main(String[] args )throws SQLException{
         MyConnection mc = new MyConnection();
        EmployerCrud em = new EmployerCrud();
         employer p = new employer(13, 13, "fouleeen", "benfouleeeen", "aaaa", 2344, "amineeee", "aaaa", "bbbbbbbbb");
        em.add(p);  
        em.delete(50);
        employer p2 = new employer(60,14,"omar","ahahahahahah","bbbbbb",5099,"AZIZZZZZZZZZ","img","AMINEEEEEEE");
        //em.Update(60,p2);
        
    }
    
  }
    

