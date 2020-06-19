/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.controllers;

import dto.SponsorDto;
import edu.connection3A17.entities.Events;
import edu.connection3A17.entities.Sponsor;
import edu.connection3A17.services.EventsService;
import edu.connection3A17.services.SponsorService;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class EventsController implements Initializable {

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
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_clear;
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
                    btn_delete.setDisable(false);
                    btn_update.setDisable(false);
                    btn_add.setDisable(true);

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

    @FXML
    private void addEvents(ActionEvent event) {
        if (nom.getText() == null || nom.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (dateDebut.getValue() == null) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (dateFin.getValue() == null) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (nbPlaces.getText() == null || nbPlaces.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (description.getText() == null || description.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (lieu.getText() == null || lieu.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (photo.getText() == null || photo.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } else if (sponsors.getText() == null || sponsors.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();

        } else {
            Events e = new Events();
            e.setDateDebut(Date.valueOf(dateDebut.getValue()));
            e.setDateFin(Date.valueOf(dateFin.getValue()));
            e.setDescription(description.getText());
            e.setId(0);
            e.setLieu(lieu.getText());
            int places = 0;
            try{
                places = Integer.parseInt(nbPlaces.getText());
                if(Integer.parseInt(nbPlaces.getText()) <= 0){
                    Alert dialogW = new Alert(Alert.AlertType.WARNING);
                    dialogW.setTitle("A warning dialog-box");
                    dialogW.setHeaderText(null); // No header
                    dialogW.setContentText("Nombre de places doit être un entier positif");
                    dialogW.showAndWait();
                    return;
                }
            }catch(Exception exception){
                Alert dialogW = new Alert(Alert.AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null); // No header
                dialogW.setContentText("Nombre de places doit être un entier positif");
                dialogW.showAndWait();
                return;
            }
            e.setNbPlaces(places);
            e.setNom(nom.getText());
            e.setPhoto(photo.getText());
            List<String> list = new ArrayList<>();
            for(int i = 0; i < sponsors.getText().split("[ ]+").length; i++){
                list.add(sponsors.getText().split("[ ]+")[i]);
            }
            if(Date.valueOf(dateFin.getValue()).compareTo(Date.valueOf(dateDebut.getValue())) < 0){
                Alert dialogW = new Alert(Alert.AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null); // No header
                dialogW.setContentText("Date de fin doit etre apres la date de debut");
                dialogW.showAndWait();
                return;
            }
            if(sponsorExists(list)){
                e.setSponsors(list);
                sp.ajouterEvents(e);
            } else {
                Alert dialogW = new Alert(Alert.AlertType.WARNING);
                dialogW.setTitle("A warning dialog-box");
                dialogW.setHeaderText(null); // No header
                dialogW.setContentText("Un sponsor parmis ceux que tu as entré n'existe pas");
                dialogW.showAndWait();
                return;
            }
            
        }
        data.removeAll(data);
        for (Events ev : FXCollections.observableArrayList(sp.afficherEvents())) {
            data.add(ev);

        }
        clear();

    }

    @FXML
    private void clear(ActionEvent event) {
        clear();
    }

    private void clear() {
        table.getSelectionModel().clearSelection();
        nom.clear();
        dateDebut.setValue(null);
        dateFin.setValue(null);
        nbPlaces.clear();
        description.clear();
        lieu.clear();
//        photo.clear();
        sponsors.clear();
        btn_delete.setDisable(true);
        btn_update.setDisable(true);
        btn_add.setDisable(false);

    }

    @FXML
    private void updateEvents(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Events s = table.getSelectionModel().getSelectedItem();
            Events e = new Events();
            e.setId(s.getId());
            e.setDateDebut(Date.valueOf(dateDebut.getValue()));
            e.setDateFin(Date.valueOf(dateFin.getValue()));
            e.setDescription(description.getText());
            e.setLieu(lieu.getText());
            e.setNbPlaces(Integer.parseInt(nbPlaces.getText()));
            e.setNom(nom.getText());
//            e.setPhoto(photo.getText());
            sp.modifierEvents(e);
            data.removeAll(data);
            for (Events ev : FXCollections.observableArrayList(sp.afficherEvents())) {
                data.add(ev);
            }
            clear();
        }
    }

    @FXML
    private void deleteEvents(ActionEvent event) {
        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous supprimé cet évènement!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Event Supprimee");
                sp.supprimerEvents(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (Events e : FXCollections.observableArrayList(sp.afficherEvents())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }

    }

    private boolean sponsorExists(List<String> sponsors) {
        SponsorService ss = new SponsorService();
        List<SponsorDto> s = ss.afficherSponsor();
        List<String> sponsorNames = new ArrayList<>();
        for(SponsorDto e : s){
            sponsorNames.add(e.getNom());
        }
        
        for(String e : sponsors){
            if (! sponsorNames.contains(e)){
                return false;
            }
        }
        return true;
    }

}
