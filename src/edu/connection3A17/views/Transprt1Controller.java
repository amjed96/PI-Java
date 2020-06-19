/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.views;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import java.net.URL;
import java.sql.Date;
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
import services.TransportService;
import entites.Transport;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class Transprt1Controller implements Initializable {

    @FXML
    private TextField recherche;
    @FXML
    private TableView<Transport> table;
    @FXML
    private TableColumn<Transport, String> event_title;
    @FXML
    private TableColumn<Transport, String> event_date;
     @FXML
    private TableColumn<Transport, Date> destination;
       @FXML
    private TableColumn<Transport, String> event_amount;

     TransportService cr = new TransportService();
    ObservableList<Transport> data = FXCollections.observableArrayList(cr.displayTransport());;
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
    private TextField tf_title;
    @FXML
    private TextField tf_desc;
    @FXML
    private DatePicker dp_date;
    @FXML
    private Button drivers;
  
    @FXML
    private Button charts;
    @FXML
    private Button calend;
    @FXML
    private Button rated;
    @FXML
    private TextField amount_text;
 
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
         event_title.setCellValueFactory(new PropertyValueFactory("moyen"));
         destination.setCellValueFactory(new PropertyValueFactory("destination"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
         event_amount.setCellValueFactory(new PropertyValueFactory("amout"));
        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    entites.Transport e = (entites.Transport) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      tf_title.setText(e.getMoyen());
                      tf_desc.setText(e.getDestination());
                      dp_date.setValue(e.getDate().toLocalDate());
                      amount_text.setText(e.getAmout());
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
        TransportService evs = new TransportService();
        ObservableList<Transport> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || newValue == null) {
            table.setItems((ObservableList<Transport>) evs.FindEvent());
        } else {
            table.setItems((ObservableList<Transport>) evs.FindEvent());
            newValue = newValue.toUpperCase();

            for (Transport e : table.getItems()) {

                String filterEventName = e.getDestination();;
               

                if (filterEventName.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }
            }
            table.setItems(filteredList);
        }
    }

    @FXML
    private void event_add(ActionEvent event) {
     if (tf_desc.getText() == null || tf_desc.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
        else if (tf_title.getText()== null || tf_title.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();

        }
        else if (dp_date.getValue()== null ){
		Alert dialogW = new Alert(Alert.AlertType.WARNING);
		dialogW.setTitle("A warning");
 		dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        } 
         else if (amount_text.getText()== null ){
		Alert dialogW = new Alert(Alert.AlertType.WARNING);
		dialogW.setTitle("A warning");
 		dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
        }     
        else 
        {
        Transport e = new entites.Transport(tf_title.getText(), tf_desc.getText(),Date.valueOf(dp_date.getValue()),amount_text.getText());
        cr.ajouterTransport(e);
        }
        data.removeAll(data);
         for (Transport ev : FXCollections.observableArrayList(cr.displayTransport())) {
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
        tf_desc.clear();
        tf_title.clear();
        dp_date.setValue(null);
        amount_text.clear();
        btn_delete.setDisable(true);
        btn_update.setDisable(true);
        btn_add.setDisable(false);
        
    }

    @FXML
    private void event_update(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            cr.modifierTransport(new Transport(tf_title.getText(), tf_desc.getText(),Date.valueOf(dp_date.getValue()),amount_text.getText()), table.getSelectionModel().getSelectedItem().getId());
            data.removeAll(data);
            for (Transport e : FXCollections.observableArrayList(cr.displayTransport())) {
                data.add(e);
            }
            clear();
        }
    }

    @FXML
    private void event_delete(ActionEvent event) {
          ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé ce transport!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Transport Supprimee");
                cr.supprimerTransport(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (Transport e : FXCollections.observableArrayList(cr.displayTransport())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }

    }
     @FXML
    private void drivers(ActionEvent event) {
         try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("Map.fxml"));
            drivers.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void charts(ActionEvent event) {
        try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("Charts.fxml"));
            charts.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void calend(ActionEvent event) {
      

              try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            calend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    

    @FXML
    private void rated(ActionEvent event) {
        try {

            Parent root;
            root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            rated.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
