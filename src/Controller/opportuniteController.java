package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static Controller.AjoutCategorieController.showAlert;
import static Controller.AjoutOpportuniteController.showAlert;
import com.jfoenix.controls.JFXTextField;
import edu.connection3A17.entities.Categorie;
import edu.connection3A17.entities.opportunite;
import edu.connection3A17.services.CategorieService;
import edu.connection3A17.services.OpportuniteService;
import edu.connection3A17.utils.MyConnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DongFeng
 */
public class opportuniteController implements Initializable {

    @FXML
    private TableColumn<opportunite, String> Categorie;
    @FXML
    private TableColumn<opportunite, String> Descr; 
    @FXML
    private TableColumn<opportunite, String> Nbr; 
    @FXML
    private TableColumn<opportunite, String> Date; 
   @FXML
    private TableColumn<opportunite, String> Adresse; 
    @FXML
    private TableColumn<opportunite, Integer> id;

    @FXML
    private JFXTextField rech;

    @FXML
    private TableView<opportunite> table;
    

    @FXML
    private JFXTextField Descr1;
       @FXML
    private DatePicker Date1;
    @FXML
    private JFXTextField Adresse1;
       @FXML
    private JFXTextField Nbr1;
   
    @FXML
    private JFXTextField ide1;
 
    @FXML
    private Button Valider;

     @FXML
    private Button Delete;
       @FXML
    private Button liste;
    @FXML
    private Button Update;
    @FXML
    private Button Ajout;

     private String content;
     @FXML
    private Button ajouterPhoto;
          @FXML
    private Button stat;
    @FXML
    private ImageView photo;
    static int idd=7;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficher();
         
          stat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage s = new Stage();
                try {
                    s.setScene(StatController.loadStat());
                } catch (SQLException ex) {
                    Logger.getLogger(opportuniteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.showAndWait();

            }

        });
       
           table.setRowFactory(tv -> {
            TableRow<opportunite> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    opportunite clickedRow = table.getItems().get(myIndex);

                    try {
                        printRow(clickedRow);
                    } catch (ParseException ex) {
                        Logger.getLogger(opportuniteController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(opportuniteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    

                }
            });
            return row;
        });
           
           
      Ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Ajout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/views/ajouterOpportunite.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
       liste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Ajout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/views/listeOpportunite.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
           Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {
                    if (Nbr1.getText().isEmpty()) {
                        Nbr1.setText("");
                    }
                     if (Adresse1.getText().isEmpty()) {
                        Adresse1.setText("");
                    }
                    if (Descr1.getText().isEmpty()) {
                        Descr1.setText("");
                    }
                }
                    OpportuniteService ps = new OpportuniteService();
                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String d = Date1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    ps.modifierOpportunite(new opportunite(Integer.valueOf(ide1.getText()),Adresse1.getText(),Integer.valueOf(Nbr1.getText()),Descr1.getText(),d,content));
            initChamps();
             Notifications.create()
                            .title("Warning")
                            .text("Opportunité modifier avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
            afficher();
                
            }
        });
      
      
            Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

              
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("A confirmation dialog-box");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Voulez vous vraiment supprimer cette categorie?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                            OpportuniteService ec = new OpportuniteService();

                    ec.supprimerOpportunite(Integer.parseInt(ide1.getText()));
                      Notifications.create()
                            .title("Warning")
                            .text("Opportunité supprimer avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                    initChamps();
                   /* Notifications.create()
                            .title("Amiticia")
                            .text("Evenement supprimer avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();*/
                } else {
                    System.out.println("User chose Cancel or closed the dialog-box");
                }
                afficher();

            }
        });
    

    }
    
    
     private void printRow(opportunite item) throws ParseException, IOException {
        try {
            Connection cnx;
            cnx = MyConnection.getInstance().getCnx();

            String req = "SELECT `id`, `addresse`, `nb_place`, `date`, `description_opportunite` FROM `opportunite` WHERE id='" + item.getId() + "' ";

            PreparedStatement pstm = cnx.prepareStatement(req);

            ResultSet rs = pstm.executeQuery(req);

            while (rs.next()) {
                item.setId((rs.getInt(1)));
                
                item.setAddresse(rs.getString(2));
                item.setNb_place(rs.getInt(3));
                item.setDescription_opportunite(rs.getString(5));
                item.setDate(rs.getString(4));
                ide1.setText(String.valueOf(item.getId()));
                Descr1.setText(item.getDescription_opportunite());
                Adresse1.setText(item.getAddresse());
                Nbr1.setText(String.valueOf(item.getNb_place()));
                getImg(item.getId());
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(item.getDate(), formatter);
                Date1.setValue(d1);
                
                System.out.println("item "+ item);
                 /*  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse( item.getDate(), formatter);
                Date1.setValue(d1);*/
 
       // LocalDate localDate = LocalDate.parse(item.getDate() , formatter);
                       // Date1.setValue(LocalDate.parse(formatter.format(item.getDate().toLocalDate())));
     }
        
            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void afficher() {

        ArrayList<opportunite> ev = new ArrayList<>();
       OpportuniteService ec = new OpportuniteService();
        ev = (ArrayList<opportunite>) ec.afficherOpportunite(idd);
    
        ObservableList<opportunite> obsl = FXCollections.observableArrayList(ev);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        Descr.setCellValueFactory(new PropertyValueFactory<>("description_opportunite"));
        Nbr.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("addresse")); 
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        table.setItems(obsl);
        
        ////////////////////////Recherche dynamique////////////////////////////////////
        FilteredList<opportunite> filterData = new FilteredList<opportunite>(obsl, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super opportunite>) Event -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Event.getAddresse().contains(newValue)) {
                        return true;
                    } else if (Event.getDescription_opportunite().toLowerCase().contains(newValue)) {
                        return true;
                    }
                   
                    return false;
                });
            });
            SortedList<opportunite> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);

        });

    }
     
    public void initChamps() {
        ide1.clear();
       
        Descr1.clear();
        Adresse1.clear();
        Nbr1.clear();
        Date1.getEditor().clear();
        photo.setImage(null);
    
    }
    
    private boolean controleDeSaisi() {

        if ( Nbr1.getText().isEmpty() || Adresse1.getText().isEmpty() || Descr1.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        }   else {

            if (!Pattern.matches("[0-9]*", Nbr1.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de place ! ");
                Nbr1.requestFocus();
                Nbr1.selectEnd();
                return false;
            }

        
        return true;
    }
    }
    
     public void getImg(int idd) throws SQLException, IOException {
        Connection cnx = MyConnection.getInstance().getCnx();

        String q = "SELECT `image` FROM `opportunite` WHERE id='"+idd+"'";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(q);

        if (rs.next()) {

            String imagePath = "file:/" + rs.getString("image");
            Image image = new Image(imagePath);

            photo.setImage(image);
        }
    }
     
 @FXML
    String uploadPhotoedit(ActionEvent event) throws IOException {
        FileChooser file = new FileChooser(); //pour choisir la photo
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.bmp"));
        file.setTitle("Choisir une photo du produit");

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
  public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
   
    public void copyFile() throws IOException {
        File srcd = Paths.get(content).toFile();
        File sd = Paths.get("C:/xampp/htdocs/Jihed/PI-Java/src/edu/connection3A17/assets").toFile();

        //copy source to target using Files Class
   FileUtils.copyFileToDirectory(srcd,sd);

   
    }


}