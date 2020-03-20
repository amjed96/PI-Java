/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.test;

import edu.connection3A17.entities.Personne;
import edu.connection3A17.entities.Secteur;
import edu.connection3A17.services.PersonneService;
import edu.connection3A17.services.SecteurService;
import edu.connection3A17.utils.MyConnection;

/**
 *
 * @author Amjed
 */
public class MainClass {
    
    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        // p = new Personne(0,"Ben","Mimi");
        Secteur s = new Secteur(3,"Ennour","Kasserin","Tunisi");
        
        //PersonneService ps = new PersonneService();
        SecteurService ss = new SecteurService();
        //ps.ajouterPersonne();
        ss.modifierSecteur(s);
        //System.out.println(ss.afficherSecteur());
        //ss.supprimerSecteur(1);
        
        //System.out.println(ps.afficher());
        
        /*PersonneService ps = new PersonneService();
        ps.ajouterPersonne();*/
    }
    
}
