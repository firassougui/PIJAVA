/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class employeur {
       private int id ;
    private String nom;
    private String pass;
    private String adresse;
    private int numero ;
    private String loca;
    private String logo;
    
    
       public employeur(){
        
}

    public employeur(int id) {
        this.id = id;
    }

    public employeur(int id, String nom, String pass, String adresse, int numero, String loca, String logo) {
        this.id = id;
        this.nom = nom;
        this.pass = pass;
        this.adresse = adresse;
        this.numero = numero;
        this.loca = loca;
        this.logo = logo;
    }

    public employeur(String nom, int numero, String adresse, String loca, String logo) {
        this.nom = nom;
        this.adresse = adresse;
        this.numero = numero;
        this.loca = loca;
        this.logo = logo;
    }
    

    public employeur(String nom, String pass, String adresse, int numero, String loca, String logo) {
        this.nom = nom;
        this.pass = pass;
        this.adresse = adresse;
        this.numero = numero;
        this.loca = loca;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLoca() {
        return loca;
    }

    public void setLoca(String loca) {
        this.loca = loca;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

       
    
}
