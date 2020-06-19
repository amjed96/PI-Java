/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Administrateur;
import Entities.User;
import Entities.Refuge;
import Services.UserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author admin
 */
public class LoginController implements Initializable {
@FXML
    private AnchorPane content5;
   @FXML
    private AnchorPane content;
    @FXML
    private JFXTextField username_login;

    @FXML
    private JFXPasswordField password_login;

    @FXML
    private JFXButton btn_login;

    @FXML
    private Label sign_up;

    @FXML
    private JFXButton signup1;
    
    //etudiant
     @FXML
    private AnchorPane content3;
    @FXML
    private JFXTextField change_first;
    @FXML
    private JFXTextField change_last;
    @FXML
    private JFXTextField change_name;
    @FXML
    private JFXTextField change_email;
    @FXML
    private AnchorPane list_choix_etudiant;
    @FXML
    private Button profil;
    @FXML
    private Button see_marks;
    @FXML
    private Button student_absences;
    @FXML
    private Button publier;
    @FXML
    private Button reclamation;
    @FXML
    private Button stage;
    @FXML
    private JFXTextField password_repeter;
    @FXML
    private JFXTextField change_password;
    @FXML
    private JFXTextField change_number;
    @FXML
    private JFXTextField change_cin;
    @FXML
    private JFXTextField change_age;
    @FXML
    private JFXTextField change_photo;
    @FXML
    private JFXButton modifier_user;
    
    //enseignant
    @FXML
    private AnchorPane content4;
    
    //parent
     @FXML
    private AnchorPane content6;


    @FXML
    private void profil(ActionEvent event) {
    }

    @FXML
    private void manage_marks(ActionEvent event) {
    }

    @FXML
    private void manage_absences(ActionEvent event) {
    }

    @FXML
    private void publier(ActionEvent event) {
    }

    @FXML
    private void modifier_user(ActionEvent event) {
    }


    @FXML
    private void see_marks(ActionEvent event) {
    }

    @FXML
    private void see_absences(ActionEvent event) {
    }

    @FXML
    private void publication(ActionEvent event) {
    }

    @FXML
    private void reclamer(ActionEvent event) {
    }

    @FXML
    private void demande_stage(ActionEvent event) {
    }
    

    @FXML
    void gotosignup(MouseEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/FirstRegistrationFXML.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);

    }
        public static User user = new User();
        public static Administrateur admin = new Administrateur();
        public static UserService userService = new UserService();

    @FXML
    void login(ActionEvent event) throws IOException, SQLException {
      user=userService.FindUserByLoginAndPassword(username_login.getText(), password_login.getText());

        if (user!=null){

          //   user.setId_user(user.getId_user());
        if (user.getRole().equals("Refuge")){
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/EtudiantInterface.fxml"));
            content.getChildren().clear();

            content.getChildren().add(newLoadedPane);
            }
        else if (user.getRole().equals("volentaire")){
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/EnseignantInterface.fxml"));
            content.getChildren().clear();

            content.getChildren().add(newLoadedPane);
            
            
        }
        else if (user.getRole().equals("admin")){
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/AdminInterface.fxml"));
            content.getChildren().clear();

            content.getChildren().add(newLoadedPane);
            
        }
            else {
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/FXML/ParentInterface.fxml"));
            content.getChildren().clear();

            content.getChildren().add(newLoadedPane);
        }}
        else{
            System.out.println("ERROR");
        }
    }
    @FXML
    void close_app(MouseEvent event) {
    System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
