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
public class Candidature {
    private int id;
     private int titre_id ;
     private String fonction;
     private String type_contrat;
     private String horaires;
     private String mode_salaire;
     private String periode;
     private int annuel_mois;

    public Candidature(int id, int titre_id, String fonction, String type_contrat, String horaires, String mode_salaire, String periode, int annuel_mois) {
        this.id = id;
        this.titre_id = titre_id;
        this.fonction = fonction;
        this.type_contrat = type_contrat;
        this.horaires = horaires;
        this.mode_salaire = mode_salaire;
        this.periode = periode;
        this.annuel_mois = annuel_mois;
    }

    public Candidature() {
     }

   

     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitre_id() {
        return titre_id;
    }

    public void setTitre_id(int titre_id) {
        this.titre_id = titre_id;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    public String getMode_salaire() {
        return mode_salaire;
    }

    public void setMode_salaire(String mode_salaire) {
        this.mode_salaire = mode_salaire;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public int getAnnuel_mois() {
        return annuel_mois;
    }

    public void setAnnuel_mois(int annuel_mois) {
        this.annuel_mois = annuel_mois;
    }
    
}
