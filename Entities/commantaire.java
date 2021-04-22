/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Maher
 */
public class commantaire {
    private int id; 
    private int com_pub ;
    String contenu ; 
    
     public commantaire(int com_pub, String contenu) {
        this.com_pub = com_pub;
        this.contenu = contenu;
    }

    public commantaire(int id, int com_pub, String contenu) {
        this.id = id;
        this.com_pub = com_pub;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublication_id() {
        return com_pub;
    }

    public void setPublication_id(int com_pub) {
        this.com_pub = com_pub;
    }

    public String getDescription() {
        return contenu;
    }

    public void setDescription(String contenu) {
        this.contenu = contenu;
    }
    
}
