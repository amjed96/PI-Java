/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AjoutCategorieController.showAlert;
import com.jfoenix.controls.JFXTextField;
import com.teknikindustries.bulksms.SMS;
import edu.connection3A17.entities.opportunite;
import edu.connection3A17.services.OpportuniteService;
import edu.connection3A17.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
 * @author Amine
 */
public class ApproveOpportuniteController implements Initializable {

    @FXML
    private TableColumn<opportunite, String> nom_categorie;
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
    private ImageView photo;
    @FXML
    private Button Clear;
    @FXML
    private Button Approve;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        afficher();
        Descr1.setDisable(true);
        Date1.setDisable(true);
        Adresse1.setDisable(true);
        Nbr1.setDisable(true);
        ide1.setDisable(true);

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
                        Logger.getLogger(ApproveOpportuniteController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });

        Approve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                
                OpportuniteService ps = new OpportuniteService();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String d = Date1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
               
         
            
                ps.modifierOpportuniteApprove(new opportunite(Integer.valueOf(ide1.getText()), Adresse1.getText(), Integer.valueOf(Nbr1.getText()), Descr1.getText(), d,true));
                initChamps();
                Notifications.create()
                        .title("Warning")
                        .text("Opportunité approuvé avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                        .showInformation();
                afficher();

            }
        });

        Clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

               initChamps();

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

                System.out.println("item " + item);
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
        ev = (ArrayList<opportunite>) ec.afficherOpportuniteApprove();

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
        ide1.clear();

        Descr1.clear();
        Adresse1.clear();
        Nbr1.clear();
        Date1.getEditor().clear();

    }

    private boolean controleDeSaisi() {

        if (Nbr1.getText().isEmpty() || Adresse1.getText().isEmpty() || Descr1.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

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
     
}
