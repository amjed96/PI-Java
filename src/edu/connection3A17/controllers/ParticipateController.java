/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.controllers;

import edu.connection3A17.entities.Events;
import edu.connection3A17.services.EventsService;
import edu.connection3A17.services.ParticipationService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author dazai
 */
public class ParticipateController implements Initializable{
    
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Events> table;
    @FXML
    private TableColumn<Events, String> events_id;
    @FXML
    private TableColumn<Events, String> events_nom;
    @FXML
    private TableColumn<Events, Date> events_dateDebut;
    @FXML
    private TableColumn<Events, Date> events_dateFin;
    @FXML
    private TableColumn<Events, String> events_nbPlaces;
    @FXML
    private TableColumn<Events, String> events_description;
    @FXML
    private TableColumn<Events, String> events_lieu;
    @FXML
    private TableColumn<Events, String> events_photo;
    @FXML
    private TableColumn<Events, String> events_sponsors;

    EventsService sp = new EventsService();
    ObservableList<Events> data;
    //ObservableList<Transport> list = cr.displayTransport();
    @FXML
    private Button btn_participate;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField nbPlaces;
    @FXML
    private TextField description;
    @FXML
    private TextField lieu;
    @FXML
    private TextField photo;
    @FXML
    private TextField sponsors;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
        data = FXCollections.observableArrayList(sp.afficherEvents());
        events_id.setCellValueFactory(new PropertyValueFactory("Id"));
        events_nom.setCellValueFactory(new PropertyValueFactory("Nom"));
        events_dateDebut.setCellValueFactory(new PropertyValueFactory("DateDebut"));
        events_dateFin.setCellValueFactory(new PropertyValueFactory("DateFin"));
        events_nbPlaces.setCellValueFactory(new PropertyValueFactory("NbPlaces"));
        events_description.setCellValueFactory(new PropertyValueFactory("Description"));
        events_lieu.setCellValueFactory(new PropertyValueFactory("Lieu"));
//        events_photo.setCellValueFactory(new PropertyValueFactory("Photo"));
        events_sponsors.setCellValueFactory(new PropertyValueFactory("Sponsors"));
        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Events e = (Events) table.getSelectionModel().getSelectedItem();
                    System.out.println();
                    //id.setText(String.valueOf(e.getId()));
                    nom.setText(e.getNom());
                    dateDebut.setValue(e.getDateDebut().toLocalDate());
                    dateFin.setValue(e.getDateFin().toLocalDate());
                    nbPlaces.setText(String.valueOf(e.getNbPlaces()));
                    description.setText(e.getDescription());
                    lieu.setText(e.getLieu());
//                    photo.setText(e.getPhoto());
                    String nomSponsor = "";
                    for (String s : e.getSponsors()) {
                        nomSponsor += s + " ";
                    }

                    sponsors.setText(nomSponsor);
                    // File file = new File("C:\\wamp64\\www\\ImagesHulk\\" + e.getEvent_pic());
                    // Image image1 = new Image(file.toURI().toString());
                    //iv_pic.setImage(image1);

                }
            }
        });
        recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               filtrerEventList((String) oldValue, (String) newValue);
            }

        });

    }    
    
        
    void filtrerEventList(String oldValue, String newValue) {
        EventsService evs = new EventsService();
        if ("".equals(recherche.getText()) || "".equals(newValue)) {
            ObservableList<Events> filteredList = FXCollections.observableArrayList(evs.afficherEvents());
            table.setItems(filteredList);
        } else {
            ObservableList<Events> filteredList = FXCollections.observableArrayList(evs.rechercheParNom(newValue));
            table.setItems(filteredList);
            newValue = newValue.toUpperCase();
            for (Events e : table.getItems()) {

                String filterEventName = e.getNom();;
               

                if (filterEventName.toUpperCase().contains(newValue) && !filteredList.contains(e)) {
                    filteredList.add(e);
                }
            }
            table.setItems(filteredList);
        }
    }

    
    public void participate(){
        if (Integer.parseInt(nbPlaces.getText()) == 0){
            Alert dialogW = new Alert(Alert.AlertType.INFORMATION);
            dialogW.setTitle("");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("Il n'y a plus de places pour cet evennement");
            dialogW.showAndWait();
        } else {
            ParticipationService participationService = new ParticipationService();
            Events event = table.getSelectionModel().getSelectedItem();
            EventsService evs = new EventsService();
            participationService.participate(event.getId(), event.getNbPlaces()-1);
            ObservableList<Events> filteredList = FXCollections.observableArrayList(evs.afficherEvents());
            table.setItems(filteredList);
            btn_participate.setDisable(true);
        }
        
    }
    
}
