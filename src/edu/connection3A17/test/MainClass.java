/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.test;

import edu.connection3A17.entities.Categorie;
import edu.connection3A17.entities.Personne;
import edu.connection3A17.entities.Secteur;
import edu.connection3A17.services.CategorieService;
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
        //MyConnection mc2 = MyConnection.getInstance();
        // p = new Personne(0,"Ben","Mimi");
        Secteur s = new Secteur(3,"Test","test","test");
        Categorie c = new Categorie(3,"haha","amjed");
        //PersonneService ps = new PersonneService();
        SecteurService ss = new SecteurService();
        CategorieService cc = new CategorieService();
        //System.out.println(cc.afficherCategorie());
        //cc.ajouterCategorie(c);
        //cc.supprimerCategorie(21);
        cc.modifierCategorie(c);
        //ps.ajouterPersonne();
        //ss.modifierSecteur(s);
        
        //System.out.println(ss.afficherSecteur());
        //ss.supprimerSecteur(4);
        
        //System.out.println(ps.afficher());
        
        /*PersonneService ps = new PersonneService();
        ps.ajouterPersonne();*/
    }
    
}
