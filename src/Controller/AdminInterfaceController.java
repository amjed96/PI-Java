/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author admin
 */
public class AdminInterfaceController {

    ModifierUserController md = new ModifierUserController();

    @FXML
    private AnchorPane content5;

    @FXML
    private AnchorPane content;

    @FXML
    private AnchorPane list_choix_admin;

    @FXML
    private Button profil;

    @FXML
    private Button manage_pub;

    @FXML
    private Button manage_users;

    @FXML
    private Button manage_class;

    @FXML
    private Button manage_permutation;

    @FXML
    private Button add_admin;

    @FXML
    private Button stat;

    @FXML
    void dashboard_action(ActionEvent event) {
        if (event.getTarget() == profil) {
            try {

                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Modifier_profile_admin.fxml"));
                content.getChildren().clear();
                content.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(EtudiantInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getTarget()==manage_users){
            try {

                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/TableViewAll.fxml"));
                content.getChildren().clear();
                content.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(EtudiantInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    if (event.getTarget() == stat) {
            try {

                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Stats.fxml"));
                content.getChildren().clear();
                content.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(EtudiantInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

}
