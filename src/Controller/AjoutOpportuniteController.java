/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.connection3A17.entities.Categorie;
import edu.connection3A17.services.CategorieService;
import edu.connection3A17.services.OpportuniteService;
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
import javafx.scene.control.DatePicker;
import edu.connection3A17.entities.opportunite;
import edu.connection3A17.utils.EmailSend;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;

/**
 *
 * @author jihed hajlaoui
 */
public class AjoutOpportuniteController implements Initializable {

    @FXML
    private JFXButton Valider;
    @FXML
    private JFXButton Annuler;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextArea Descr;
    @FXML
    private JFXTextArea Nbr;
    @FXML
    private DatePicker Date;
    @FXML
    private JFXComboBox Cat;
    private String content;
     @FXML
    private Button ajouterPhoto;
    @FXML
    private ImageView photo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {

                    if (Descr.getText().isEmpty()) {
                        Descr.setText("");
                    }
                    if (Adresse.getText().isEmpty()) {
                        Adresse.setText("");
                    }
                    if (Nbr.getText().isEmpty()) {
                        Nbr.setText("");
                    }

                    OpportuniteService ps = new OpportuniteService();
                    CategorieService pss = new CategorieService();
                    System.out.println("test" + Cat.getSelectionModel().getSelectedItem());

                    Categorie value = pss.afficherCategorie().stream()
                            .filter(customer -> Cat.getSelectionModel().getSelectedItem().equals(customer.getNomcategorie()))
                            .findAny()
                            .orElse(null);
                    Categorie c = new Categorie(value.getId(), value.getNomcategorie(), value.getDescription());

                    String d = Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    ps.ajouterOpportunite(new opportunite(c, Adresse.getText(), Integer.valueOf(Nbr.getText()), Descr.getText(), d, false,content));
                    initChamps();
                    EmailSend em = new EmailSend();
                    em.sendmail("karray.gargouri@esprit.tn");
                    Notifications.create()
                            .title("Warning")
                            .text("Opportunité ajouter avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                }
            }
        });

        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Annuler.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/views/test.fxml")));
                    Annuler.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/test/mainOpportunite.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(AjoutOpportuniteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean controleDeSaisi() {

        if (Adresse.getText().isEmpty() || Descr.getText().isEmpty()) {
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
        Adresse.clear();
        Nbr.clear();
        Descr.clear();

    }

    public void display() {
        CategorieService ps = new CategorieService();

        System.out.println("cat " + ps.afficherNomCategorie());

        Cat.getItems().removeAll(Cat.getItems());
        Cat.getItems().addAll(ps.afficherNomCategorie().stream().map(Categorie::getNomcategorie).collect(Collectors.toList()));
        //id.setText((String) Cat.getSelectionModel().getSelectedItem());
        System.out.println("caaat " + ps.afficherNomCategorie().stream().map(Categorie::getNomcategorie).collect(Collectors.toList()));

    }
 @FXML
    String uploadPhoto(ActionEvent event) throws IOException {
        FileChooser file = new FileChooser(); //pour choisir la photo
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.bmp"));
        file.setTitle("Choisir une phoo de l'opportunite");

        File selected_photo = file.showOpenDialog((Stage) ajouterPhoto.getScene().getWindow());
        if (selected_photo != null) {
            if ((selected_photo.length() / 1024) / 1024 < 2.0) {
                String path = selected_photo.getAbsolutePath();
                BufferedImage bufferedImage = ImageIO.read(selected_photo);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo.setImage(image);

                File img = new File(path);

                content = img.getPath();
                copyFile();

            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Taile trop grande !", "Veuillez choisir une photo de profil avec une taille < 2 Mo");
            }
        }
        return content;

    }

   
    public void copyFile() throws IOException {
        File srcd = Paths.get(content).toFile();
        File sd = Paths.get("C:/xampp/htdocs/Jihed/PI-Java/src/edu/connection3A17/assets").toFile();

        //copy source to target using Files Class
   FileUtils.copyFileToDirectory(srcd,sd);

   
    }
}
