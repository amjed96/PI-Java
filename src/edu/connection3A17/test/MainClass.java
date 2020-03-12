/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.test;

import edu.connection3A17.entities.Personne;
import edu.connection3A17.services.PersonneService;
import edu.connection3A17.utils.MyConnection;

/**
 *
 * @author Amjed
 */
public class MainClass {
    
    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        Personne p = new Personne(0,"Ben Kbayer","Mimi");
        
        PersonneService ps = new PersonneService();
        //ps.ajouterPersonne();
        
        System.out.println(ps.afficher());
        
        /*PersonneService ps = new PersonneService();
        ps.ajouterPersonne();*/
    }
    
}
