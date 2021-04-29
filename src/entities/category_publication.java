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
public class category_publication {
    private int id;
    private String titre;
     private String Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public category_publication() {
    }

    public category_publication(int id, String titre, String Description) {
        this.id = id;
        this.titre = titre;
        this.Description = Description;
    }

    public category_publication(String titre, String Description) {
        this.titre = titre;
        this.Description = Description;
    }
    
}
