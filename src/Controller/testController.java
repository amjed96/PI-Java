package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static Controller.AjoutCategorieController.showAlert;
import com.jfoenix.controls.JFXTextField;
import edu.connection3A17.entities.Categorie;
import edu.connection3A17.services.CategorieService;
import edu.connection3A17.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


/**
 * FXML Controller class
 *
 * @author DongFeng
 */
public class testController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> nom_categorie;
    @FXML
    private TableColumn<Categorie, String> Description; 
  
    @FXML
    private TableColumn<Categorie, Integer> id;

    @FXML
    private JFXTextField rech;

    @FXML
    private TableView<Categorie> table;
    
        @FXML
    private JFXTextField Nom1;
    @FXML
    private JFXTextField Descr1;
 @FXML
    private JFXTextField ide1;
 
    @FXML
    private Button Valider;

     @FXML
    private Button Delete;
    @FXML
    private Button Update;
    @FXML
    private Button Ajout;

    

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      Ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Ajout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../edu/connection3A17/views/ajout.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(testController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
           Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {
                    if (Nom1.getText().isEmpty()) {
                        Nom1.setText("");
                    }
                    if (Descr1.getText().isEmpty()) {
                        Descr1.setText("");
                    }
                   
                    
                    CategorieService ps = new CategorieService();
    ps.modifierCategorie(new Categorie(Integer.valueOf(ide1.getText()),Nom1.getText(),Descr1.getText()));
            initChamps();
            afficher();
                }
            }
        });
      
       afficher();
       
           table.setRowFactory(tv -> {
            TableRow<Categorie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    Categorie clickedRow = table.getItems().get(myIndex);

                    printRow(clickedRow);
                    

                }
            });
            return row;
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
                            CategorieService ec = new CategorieService();

                    ec.supprimerCategorie(Integer.parseInt(ide1.getText()));
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
    
    
     private void printRow(Categorie item) {
        try {
            Connection cnx;
            cnx = MyConnection.getInstance().getCnx();

            String req = "SELECT `id`, `nom_categorie`, `description` FROM `categorie` where `id`='" + item.getId() + "' ";

            PreparedStatement pstm = cnx.prepareStatement(req);

            ResultSet rs = pstm.executeQuery(req);

            while (rs.next()) {
                item.setId((rs.getInt(1)));
                item.setNomcategorie(rs.getString(2));
                item.setDescription(rs.getString(3));
               
                ide1.setText(String.valueOf(item.getId()));
                Nom1.setText(item.getNomcategorie());
                Descr1.setText(item.getDescription());
                
              

            }
            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(testController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void afficher() {

        ArrayList<Categorie> ev = new ArrayList<>();
        CategorieService ec = new CategorieService();
        ev = (ArrayList<Categorie>) ec.afficherCategorie();

        ObservableList<Categorie> obsl = FXCollections.observableArrayList(ev);

        nom_categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
    
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.setItems(obsl);

        ////////////////////////Recherche dynamique////////////////////////////////////
        FilteredList<Categorie> filterData = new FilteredList<Categorie>(obsl, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Categorie>) Event -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Event.getNomcategorie().contains(newValue)) {
                        return true;
                    } else if (Event.getDescription().toLowerCase().contains(newValue)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Categorie> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);

        });

    }
     
    public void initChamps() {
        ide1.clear();
        Nom1.clear();
        Descr1.clear();
    
    }
    
    private boolean controleDeSaisi() {

        if (Nom1.getText().isEmpty() || Descr1.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } 

        
        return true;
    }


}