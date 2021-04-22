/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author M'Amine
 */
public class publication {

    int id;
    int categorie_id;
    String description;
    String titre;
    Date date;
    int vus;
    int likes ;
    String img ;  

    public publication() {
    }

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

    public int likes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public publication(int id, int categorie_id, String description, int vus, int likes, Date date, String titre) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.description = description;
        this.vus = vus;
        this.likes = likes;
        this.date = date;
        this.titre = titre;
    }

    public publication(int id, String titre,String description) {
         this.id = id;
           this.titre = titre;
        this.description = description;
      
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
