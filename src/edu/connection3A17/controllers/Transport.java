/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.controllers;
import entites.Chauffeur;

import java.sql.Date;

/**
 *
 * @author hamza
 */
public class Transport {
    private int id ;
    private String moyen;
    private String destination;
    private Date Date;
    private String amout;

    public Transport(int id,String moyen, String destination, Date Date, String amout) {
       this.id = id;
        this.moyen = moyen;
        this.destination = destination;
        this.Date = Date;
        this.amout = amout;
         
    }

    public Transport(String moyen, String destination, Date Date, String amout) {
        this.moyen = moyen;
        this.destination = destination;
        this.Date = Date;
        this.amout = amout;
    }

   

   
   

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout;
    }
   



  


    public Transport() {
         //To change body of generated methods, choose Tools | Templates.
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoyen() {
        return moyen;
    }

    public void setMoyen(String moyen) {
        this.moyen = moyen;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", moyen=" + moyen + ", destination=" + destination + ", Date=" + Date + ", amout=" + amout + '}';
    }

    
          
   
   

   

    
}
