/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Firas
 */
public class Opportunite {
        private int id;
     private int op_employeur_id ;
     private String titre;
     private String lieu;
     private String description;
     private String nom_entreprise;
     private String taille_entreprise;
     private String poste;
     private String media;
     private int nombre_recrutement;
     private String Logo;

     

   
    public Opportunite() {
    }

    public Opportunite(int id, int op_employeur_id , String titre, String lieu, String description, String nom_entreprise, String taille_entreprise, String poste, String media,int nombre_recrutement,String Logo) {
        this.id = id;
        this.op_employeur_id=op_employeur_id;
        this.titre = titre;
        this.lieu = lieu;
        this.description = description;
        this.nom_entreprise = nom_entreprise;
        this.taille_entreprise = taille_entreprise;
        this.poste = poste;
        this.media = media;
        this.nombre_recrutement = nombre_recrutement;
         this.Logo=Logo;
        
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    public Opportunite(String titre) {
      this.titre = titre;    }

     

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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getTaille_entreprise() {
        return taille_entreprise;
    }

    public void setTaille_entreprise(String taille_entreprise) {
        this.taille_entreprise = taille_entreprise;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getNombre_recrutement() {
        return nombre_recrutement;
    }

    public void setNombre_recrutement(int nombre_recrutement) {
        this.nombre_recrutement = nombre_recrutement;
    }

    public int getOp_employeur_id() {
        return op_employeur_id;
    }

    public void setOp_employeur_id(int op_employeur_id) {
        this.op_employeur_id = op_employeur_id;
    }
    
}
