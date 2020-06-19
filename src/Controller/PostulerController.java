/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTextField;
import edu.connection3A17.entities.opportunite;
import edu.connection3A17.services.OpportuniteService;
import edu.connection3A17.services.PostulationService;
import edu.connection3A17.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author jihed hajlaoui
 */
public class PostulerController implements Initializable{

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
    private Button Postuler;

     @FXML
    private Button Retour;
      @FXML
    private ImageView photo;
          static int idd=7;
          private static opportunite o;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Postuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Postuler(o);
                    afficher();
                } catch (IOException ex) {
                    Logger.getLogger(PostulerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
                }
            
        });

        Retour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Retour.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/test/mainOpportunite.fxml")));
        
                } catch (IOException ex) {
                    Logger.getLogger(PostulerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        afficher();
       
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
               
                getImg(item.getId());
o=item;                
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
        ev = (ArrayList<opportunite>) ec.afficherListeOpportunite(idd);
    
        ObservableList<opportunite> obsl = FXCollections.observableArrayList(ev);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
       // nom_categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
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
       
        photo.setImage(null);
    
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
       
      
    void Postuler(opportunite o) throws IOException {
       
        PostulationService ps = new PostulationService();
        Date dd=new Date() ;
                            String d =dd.toString() ;

        ps.ajouterPostulation(o,d,idd);
                photo.setImage(null);

    }
}
