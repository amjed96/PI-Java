/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.services;

import edu.connection3A17.entities.Categorie;
import edu.connection3A17.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jihed hajlaoui
 */
public class CategorieService {
    Connection cnx;
    
    public CategorieService(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterCategorie(Categorie c){
        
        try{
            String request = "INSERT INTO categorie(nom_categorie,description)" + "VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, c.getNomcategorie());
             pst.setString(1, c.getDescription());
            pst.executeUpdate();
            System.out.println("categorie ajouté");
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<Categorie> afficherCategorie(){
        List<Categorie> myList = new ArrayList<>();
        
        try {
            
            String request = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setNomcategorie(rs.getString("nom_categorie"));
                c.setDescription(rs.getString("description"));
                myList.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public void supprimerCategorie(int id){
        
        try{
            String request = "DELETE FROM categorie WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, String.valueOf(id));
            pst.executeUpdate();
            System.out.println("categorie supprimé");
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void modifierSecteur(Categorie c){
        
        try{
            String request = "UPDATE categorie SET nom_categorie = ?, description = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, c.getNomcategorie());
            pst.setString(2, c.getDescription());
            pst.setString(4, String.valueOf(c.getId()));
            pst.executeUpdate();
            System.out.println("categorie modifié");
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
