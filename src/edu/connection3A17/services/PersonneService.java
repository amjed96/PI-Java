/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.services;

import edu.connection3A17.entities.Personne;
import edu.connection3A17.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amjed
 */
public class PersonneService {

    Connection cnx;

    public PersonneService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterPersonne() {
        String request = "INSERT INTO personne (nom,prenom)"
                + "VALUES ('Bouallegui','Amjed')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterPersonne2(Personne p) {
        try {
            String request2 = "INSERT INTO personne(nom,prenom)" + "VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(request2);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.executeUpdate();
            System.out.println("Personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Personne> afficher(){
        List<Personne> myList = new ArrayList<>();
        
        try {
            
            String request3 = "SELECT * FROM personne";
            PreparedStatement pst = cnx.prepareStatement(request3);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString("prenom"));
                myList.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
