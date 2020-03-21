/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.services;

import edu.connection3A17.entities.Donmateriel;
import edu.connection3A17.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Amjed
 */
public class DonmaterielService {
    
    Connection cnx;

    public DonmaterielService(Connection cnx) {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterDonmateriel(Donmateriel dm){
        
        try{
            String request = "INSER INTO donmateriel"
                    + "(id, secteur_id, donneur_id, type, image, nombre, title,description)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, String.valueOf(dm.getId()));
            //pst.setString(2, String.valueOf(dm.getSecteur()));
            //pst.setString(3, String.valueOf(dm.getDonneur()));
            pst.setString(4, dm.getType());
            pst.setString(5, dm.getImage());
            pst.setString(6, String.valueOf(dm.getNombre()));
            pst.setString(7, dm.getTitle());
            pst.setString(8, dm.getDescription());
            
            pst.executeUpdate();
            System.out.println("Don materiel ajouté");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
