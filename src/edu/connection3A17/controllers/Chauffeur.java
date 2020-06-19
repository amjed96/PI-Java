/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.controllers;

import static java.util.Collections.list;
import java.util.List;


/**
 *
 * @author hamza
 */
public class Chauffeur{
    private int matricule;
    private String nom ;
    private String prenom ;
    private int num_permis;
    private int num_telephone;
    
    
    
    
    public Chauffeur() {
    }

    public Chauffeur(int matricule, String nom, String prenom, int num_permis, int num_telephone) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.num_permis = num_permis;
        this.num_telephone = num_telephone;
    }
    

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum_permis() {
        return num_permis;
    }

    public void setNum_permis(int num_permis) {
        this.num_permis = num_permis;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", num_permis=" + num_permis + ", num_telephone=" + num_telephone + '}';
    }
    
    
}
