/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.services;

import edu.connection3A17.entities.Secteur;
import edu.connection3A17.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amjed
 */
public class SecteurService {
    
    Connection cnx;
    
    public SecteurService(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterSecteur(Secteur s){
        
        try{
            String request2 = "INSERT INTO secteur(nom,adresse,pays)" + "VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request2);
            pst.setString(1, s.getNom());
            pst.setString(2, s.getAdresse());
            pst.setString(3, s.getPays());
            pst.executeUpdate();
            System.out.println("Secteur ajouté");
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<Secteur> afficher(){
        List<Secteur> myList = new ArrayList<>();
        
        try {
            
            String request3 = "SELECT * FROM secteur";
            PreparedStatement pst = cnx.prepareStatement(request3);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Secteur s = new Secteur();
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("nom"));
                s.setAdresse(rs.getString("adresse"));
                s.setPays(rs.getString("pays"));
                myList.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public void supprimerSecteur(int id){
        
        try{
            String request2 = "DELETE FROM secteur WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request2);
            pst.setString(1, String.valueOf(id));
            pst.executeUpdate();
            System.out.println("Secteur ajouté");
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void modifierSecteur(Secteur s){
        
        try{
            String request = "UPDATE secteur SET nom = ?, adresse = ?, pays = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, s.getNom());
            pst.setString(2, s.getAdresse());
            pst.setString(3, s.getPays());
            pst.setString(4, String.valueOf(s.getId()));
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
