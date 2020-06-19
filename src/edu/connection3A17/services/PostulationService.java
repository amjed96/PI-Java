/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.services;

import edu.connection3A17.entities.Categorie;
import edu.connection3A17.entities.opportunite;
import edu.connection3A17.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 *
 * @author jihed hajlaoui
 */
public class PostulationService {
    
     Connection cnx;

    public PostulationService() {
        cnx = MyConnection.getInstance().getCnx();
    }
      public void ajouterPostulation(opportunite o,String d,int id) {

        try {
            String request = "INSERT INTO `postulation`(`opportunite_id`, `date`, `user_id`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, o.getId());
            pst.setString(2,  d);
            pst.setInt(3,id);
            if (o.getNb_place()>0)
            {
                modifierPostulation(o);
                  Notifications.create()
                            .title("Warning")
                            .text("Postuler avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                              pst.executeUpdate();

            }
            else {
                 Notifications.create()
                            .title("Warning")
                            .text("Pas de place disponible!!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
            }

            
            System.out.println("opportunite ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
      public void modifierPostulation(opportunite o){
        
        try{
            String request = "UPDATE `opportunite` SET `nb_place`=? WHERE id= ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1,o.getNb_place()-1);
           pst.setInt(2,o.getId());
            pst.executeUpdate();
        }
        
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }

}
