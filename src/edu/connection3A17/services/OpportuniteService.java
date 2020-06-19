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
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jihed hajlaoui
 */
public class OpportuniteService {

    Connection cnx;

    public OpportuniteService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterOpportunite(opportunite o) {

        try {
            String request = "INSERT INTO opportunite(categorie_id,addresse,nb_place,description_opportunite,date,etat,image)" + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, o.getC().getId());
            pst.setString(2, o.getAddresse());
            pst.setString(3, String.valueOf(o.getNb_place()));

            //  pst.setString(3, String.valueOf(o.getDate()));
            pst.setString(4, o.getDescription_opportunite());
            pst.setString(5, String.valueOf(o.getDate()));
            pst.setBoolean(6, o.isEtat());
            pst.setString(7, o.getImage());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<opportunite> afficherOpportunite(int idd) {
        List<opportunite> myList = new ArrayList<>();

        try {

            //  String request = "SELECT * FROM opportunite";
            String request = "SELECT opportunite.id,opportunite.categorie_id, opportunite.addresse, opportunite.nb_place, opportunite.image, opportunite.date, opportunite.description_opportunite, opportunite.etat,categorie.id,categorie.nom_categorie,categorie.description  FROM `opportunite` join categorie where (opportunite.categorie_id=categorie.id and opportunite.etat=1  and id_user='" + idd + "')";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                opportunite c = new opportunite();
                Categorie cc = new Categorie();
                c.setId(rs.getInt("opportunite.id"));
                c.setAddresse(rs.getString("opportunite.addresse"));
                c.setNb_place(rs.getInt("opportunite.nb_place"));
                c.setImage(rs.getString("opportunite.image"));
                c.setDate(rs.getString("opportunite.date"));
                cc.setDescription(rs.getString("categorie.description"));
                cc.setId(rs.getInt("categorie.id"));
                cc.setNomcategorie(rs.getString("categorie.nom_categorie"));
                c.setNomcategorie(rs.getString("categorie.nom_categorie"));

                c.setC(cc);
                System.out.println(c);
                c.setDescription_opportunite(rs.getString("opportunite.description_opportunite"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<opportunite> afficherListeOpportunite(int idd) {
        List<opportunite> myList = new ArrayList<>();

        try {

            //  String request = "SELECT * FROM opportunite";
            String request = "SELECT opportunite.id,opportunite.categorie_id, opportunite.addresse, opportunite.nb_place, opportunite.image, opportunite.date, opportunite.description_opportunite, opportunite.etat,categorie.id,categorie.nom_categorie,categorie.description  FROM `opportunite` join categorie where (opportunite.categorie_id=categorie.id and opportunite.etat=1  and id_user<>'" + idd + "')";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                opportunite c = new opportunite();
                Categorie cc = new Categorie();
                c.setId(rs.getInt("opportunite.id"));
                c.setAddresse(rs.getString("opportunite.addresse"));
                c.setNb_place(rs.getInt("opportunite.nb_place"));
                c.setImage(rs.getString("opportunite.image"));
                c.setDate(rs.getString("opportunite.date"));
                cc.setDescription(rs.getString("categorie.description"));
                cc.setId(rs.getInt("categorie.id"));
                cc.setNomcategorie(rs.getString("categorie.nom_categorie"));
                c.setC(cc);

                c.setDescription_opportunite(rs.getString("opportunite.description_opportunite"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<opportunite> afficherOpportuniteApprove() {
        List<opportunite> myList = new ArrayList<>();

        try {

            //  String request = "SELECT * FROM opportunite";
            String request = "SELECT opportunite.id,opportunite.categorie_id, opportunite.addresse, opportunite.nb_place, opportunite.image, opportunite.date, opportunite.description_opportunite, opportunite.etat,categorie.id,categorie.nom_categorie,categorie.description  FROM `opportunite` join categorie where (opportunite.categorie_id=categorie.id and opportunite.etat=0)";
            PreparedStatement pst = cnx.prepareStatement(request);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                opportunite c = new opportunite();
                Categorie cc = new Categorie();
                c.setId(rs.getInt("opportunite.id"));
                c.setAddresse(rs.getString("opportunite.addresse"));
                c.setNb_place(rs.getInt("opportunite.nb_place"));
                c.setImage(rs.getString("opportunite.image"));
                c.setDate(rs.getString("opportunite.date"));
                cc.setDescription(rs.getString("categorie.description"));
                cc.setId(rs.getInt("categorie.id"));
                cc.setNomcategorie(rs.getString("categorie.nom_categorie"));
                c.setC(cc);

                c.setDescription_opportunite(rs.getString("opportunite.description_opportunite"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerOpportunite(int id) {

        try {
            String request = "DELETE FROM opportunite WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, String.valueOf(id));
            pst.executeUpdate();
            System.out.println("opportunite supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierOpportunite(opportunite o) {

        try {
            String request = "UPDATE opportunite SET addresse = ?, nb_place = ?, description_opportunite = ?, date = ?, image = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, o.getAddresse());
            pst.setString(2, String.valueOf(o.getNb_place()));
            // pst.setString(3,String.valueOf(o.getDate()));
            pst.setString(3, String.valueOf(o.getDescription_opportunite()));
            pst.setString(4, String.valueOf(o.getDate()));
            pst.setString(6, String.valueOf(o.getId()));
            pst.setString(5, String.valueOf(o.getImage()));

            pst.executeUpdate();
            System.out.println("opportunite modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierOpportuniteApprove(opportunite o) {

        try {
            String request = "UPDATE opportunite SET addresse = ?, nb_place = ?, description_opportunite = ?, date = ?, etat = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, o.getAddresse());
            pst.setString(2, String.valueOf(o.getNb_place()));
            // pst.setString(3,String.valueOf(o.getDate()));
            pst.setString(3, String.valueOf(o.getDescription_opportunite()));
            pst.setString(4, String.valueOf(o.getDate()));
            pst.setBoolean(5, (o.isEtat()));
            pst.setString(6, String.valueOf(o.getId()));

            pst.executeUpdate();
            System.out.println("opportunite modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
