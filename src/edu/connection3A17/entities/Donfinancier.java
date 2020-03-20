/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.entities;

/**
 *
 * @author Amjed
 */
public class Donfinancier {
    
    private int id;
    private float montant;
    private int rib;

    public Donfinancier() {
    }

    public Donfinancier(int id, float montant, int rib) {
        this.id = id;
        this.montant = montant;
        this.rib = rib;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getRib() {
        return rib;
    }

    public void setRib(int rib) {
        this.rib = rib;
    }
    
    
    
}
