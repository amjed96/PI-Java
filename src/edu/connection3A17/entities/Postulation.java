/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.entities;

/**
 *
 * @author jihed hajlaoui
 */
public class Postulation {
    private int id;
    private int id_user;
    private String date;
    private int opp_id;

    public Postulation(int id_user, String date, int opp_id) {
        this.id_user = id_user;
        this.date = date;
        this.opp_id = opp_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOpp_id() {
        return opp_id;
    }

    public void setOpp_id(int opp_id) {
        this.opp_id = opp_id;
    }

    @Override
    public String toString() {
        return "Postulation{" + "id=" + id + ", id_user=" + id_user + ", date=" + date + ", opp_id=" + opp_id + '}';
    }
    
    
    
}
