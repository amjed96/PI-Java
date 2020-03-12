/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amjed
 */
public class MyConnection {

    private String url = "jdbc:mysql://localhost:3306/esprit";
    private String login = "root";
    private String mdp = "";
    private Connection cnx;
    public static MyConnection instance;

    private MyConnection() {

        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connection établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
