/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class Modifier_profile_enseignantController implements Initializable {

  
      @FXML
    private JFXTextField change_first;

    @FXML
    private JFXTextField change_last;

    @FXML
    private JFXTextField change_name;

    @FXML
    private JFXTextField change_email;

       @FXML
    private JFXPasswordField change_password;

    @FXML
    private JFXPasswordField password_repeter;

    @FXML
    private JFXTextField change_number;

    @FXML
    private JFXTextField change_cin;

    @FXML
    private JFXTextField change_age;

    @FXML
    private JFXButton modifier_user;

    @FXML
    private ImageView image_etudiant;
      @FXML
    private AnchorPane content;
         @FXML
    private FontAwesomeIconView image_user;

    @FXML
    private Label upload_image;

    

    public static UserService userService = new UserService();
    @FXML
    void modifier_user(ActionEvent event) throws SQLException, IOException {
        LoginController.user.setFirst_Name(change_first.getText());
        LoginController.user.setLast_Name(change_last.getText());
        LoginController.user.setUser_Name(change_name.getText());
        LoginController.user.setAge(Integer.parseInt(change_age.getText()));
        LoginController.user.setCIN(Integer.parseInt(change_cin.getText()));
        LoginController.user.setEmail(change_email.getText());
        LoginController.user.setPassword(change_password.getText());
        LoginController.user.setPhone_number(Integer.parseInt(change_number.getText()));
        userService.modifierUser(LoginController.user);
            content.getChildren().clear();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
change_first.setText(LoginController.user.getFirst_Name());
        change_last.setText(LoginController.user.getLast_Name());
        change_email.setText(LoginController.user.getEmail());
        change_name.setText(LoginController.user.getUser_Name());
        change_cin.setText(String.valueOf(LoginController.user.getCIN()));
        change_number.setText(String.valueOf(LoginController.user.getPhone_number()));
        Image im = new Image("/Images/"+LoginController.user.getImage_user());
        image_etudiant.setImage(im);
        change_password.setText(LoginController.user.getPassword());
change_age.setText(String.valueOf(LoginController.user.getAge()));
password_repeter.setText(LoginController.user.getPassword());  
    }
@FXML
    void btnLoadEventListener(MouseEvent event) throws IOException {
  FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        System.out.println(file.getName());
        LoginController.user.setImage_user(file.getName());
        image_etudiant.setImage(image);
    }
}
