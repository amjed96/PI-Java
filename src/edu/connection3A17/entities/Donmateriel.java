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
public class Donmateriel {
    
    private int id;
    private String type;
    private String image = "";
    private int nombre;
    private String title;
    private String description;

    public Donmateriel() {
    }

    public Donmateriel(int id, String type, String image, int nombre, String title, String description) {
        this.id = id;
        this.type = type;
        this.image = image;
        this.nombre = nombre;
        this.title = title;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
}
