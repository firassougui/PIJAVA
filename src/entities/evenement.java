/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author Firas
 */
public class evenement {
    private int id;
    private int id_employeur;

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", id_employeur=" + id_employeur + ", titre=" + titre + ", nombre_par=" + nombre_par + ", date_start=" + date_start + ", date_end=" + date_end + ", description=" + description + ", id_type=" + id_type + ", nom=" + nom + ", photo=" + photo + ", type=" + type + '}';
    }
    private String titre;
    private int nombre_par ;
    private Date date_start;
    private Date date_end;
    private String description;
    private int id_type;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String photo;
    private String type;

    public evenement() {
    }

    public evenement(int id, int id_employeur, String titre, int nombre_par, Date date_start,String description , Date date_end, int id_type, String photo) {
        this.id = id;
        this.id_employeur = id_employeur;
        this.titre = titre;
        this.nombre_par = nombre_par;
        this.date_start = date_start;
        this.date_end = date_end;
        this.description = description;
        this.id_type = id_type;
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public evenement(int id, String titre, int nombre_par, Date date_start, Date date_end,String description , int id_type, String photo) {
        this.id = id;
        this.titre = titre;
        this.nombre_par = nombre_par;
        this.date_start = date_start;
        this.date_end = date_end;
        this.description =description;
        this.id_type = id_type;
        this.photo = photo;
    }
      public evenement(String titre, int nombre_par, Date date_start, Date date_end,String description) {
        
        this.titre = titre;
        this.nombre_par = nombre_par;
        this.date_start = date_start;
        this.date_end = date_end;
        this.description =description;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_employeur() {
        return id_employeur;
    }

    public void setId_employeur(int id_employeur) {
        this.id_employeur = id_employeur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNombre_par() {
        return nombre_par;
    }

    public void setNombre_par(int nombre_par) {
        this.nombre_par = nombre_par;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setId_employeur(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String gettitre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
    
    
}
