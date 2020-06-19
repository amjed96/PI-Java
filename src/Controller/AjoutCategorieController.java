/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.connection3A17.entities.Categorie;
import edu.connection3A17.services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author jihed hajlaoui
 */
public class AjoutCategorieController implements Initializable{
 
    
    @FXML
    private JFXButton Valider;
    @FXML
    private JFXButton Annuler;
    @FXML
    private JFXTextField Nom;
   
    @FXML
    private JFXTextArea Descr;
   

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {
                    if (Nom.getText().isEmpty()) {
                        Nom.setText("");
                    }
                    if (Descr.getText().isEmpty()) {
                        Descr.setText("");
                    }
                   
                    
                    CategorieService ps = new CategorieService();
    ps.ajouterCategorie(new Categorie(Nom.getText(),Descr.getText()));
            initChamps();
                }
            }
        });
        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                      Annuler.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/test/mainCategorie.fxml")));
        
                } catch (IOException ex) {
                    Logger.getLogger(AjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean controleDeSaisi() {

        if (Nom.getText().isEmpty() || Descr.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } 

        
        return true;
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }



    public void initChamps() {
        Nom.clear();
       
        Descr.clear();
      
       
    }

}
