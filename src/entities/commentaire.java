/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author M'Amine
 */
public class commentaire {
    private int id;
    private String contenu;
    private int id_pub;

    public commentaire() {
    }

    public commentaire(int id, String contenu, int id_pub) {
        this.id = id;
        this.contenu = contenu;
        this.id_pub = id_pub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }
    
    
}
