/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.TypeEvent;

import services.TypeEventCRUD;
import tools.MyConnection;

/**
 *
 * @author Firas
 */
public class MainClass {
    public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
        TypeEventCRUD Te =new TypeEventCRUD();
        TypeEvent te =new TypeEvent();
        
       // Te.addtypeevent2(te);
       System.out.println(Te.gettypeevent1());
        
        
    }
    
    
}
