/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author M'Amine
 */
public class publication {
    
    private int id;
    private int categorie_id;
    private String titre;
    private String description;
    private int vus;
    private int likes;
    private String img;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVus() {
        return vus;
    }

    public void setVus(int vus) {
        this.vus = vus;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public publication() {
    }

    public publication(int id, int categorie_id, String titre, String description, int vus, int likes, String img, Date date) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.titre = titre;
        this.description = description;
        this.vus = vus;
        this.likes = likes;
        this.img = img;
        this.date = date;
    }
    
}
