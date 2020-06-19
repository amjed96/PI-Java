/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.entities;

import java.util.Date;

/**
 *
 * @author jihed hajlaoui
 */
public class opportunite {
    private int id ;
    private String addresse ;
    private int nb_place ;
    private String image ;
    private String date ;
    private String description_opportunite ;
    private boolean etat ;
    private Categorie c;
     private String nom_categorie;

  
    public opportunite() {
    }
public opportunite(Categorie c,String addresse, int nb_place,  String description_opportunite,String date,boolean etat,String image) {
        this.c=c;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.date = date;
        this.description_opportunite = description_opportunite;
        this.etat=etat;
        this.image=image;
    }
    public opportunite(int id, String addresse, int nb_place,  String description_opportunite,String date,boolean etat) {
        this.id = id;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.description_opportunite = description_opportunite;
        this.date=date;
        this.etat=etat;
    }
        public opportunite(int id, String addresse, int nb_place,  String description_opportunite,String date,String image) {
        this.id = id;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.description_opportunite = description_opportunite;
        this.date=date;
        this.image=image;
    }
 
    public opportunite(int id, String addresse, int nb_place, String image, String date, String description_opportunite, boolean etat) {
        this.id = id;
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.image = image;
        this.date = date;
        this.description_opportunite = description_opportunite;
        this.etat = etat;
    }
     public opportunite(String addresse, int nb_place, String image, String date, String description_opportunite) {
        this.addresse = addresse;
        this.nb_place = nb_place;
        this.image = image;
        this.date = date;
        this.description_opportunite = description_opportunite;
    }
      public opportunite(String addresse, String image, String date, String description_opportunite) {
        this.addresse = addresse;
        this.image = image;
        this.date = date;
        this.description_opportunite = description_opportunite;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription_opportunite() {
        return description_opportunite;
    }

    public void setDescription_opportunite(String description_opportunite) {
        this.description_opportunite = description_opportunite;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
  public Categorie getC() {
        return c;
    }

    public void setC(Categorie c) {
        this.c = c;
    }

    public String getNomcategorie() {
        return nom_categorie;
    }

    public void setNomcategorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }
    
    
    @Override
    public String toString() {
        return "opportunite{" + "id=" + id + ", addresse=" + addresse + ", nb_place=" + nb_place + ", image=" + image + ", date=" + date + ", description_opportunite=" + description_opportunite + ", etat=" + etat + ", c=" + c + '}';
    }

   

    
}
