/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.views;

import entites.Transport;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.TransportService;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class TransportController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> event_title;
    @FXML
    private TableColumn<?, ?> event_date;
    TransportService es = new TransportService();
    ObservableList<Transport> data = FXCollections.observableArrayList(es.FindEvent());
    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_desc;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ImageView iv_pic;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_clear;
    @FXML
    private TextField recherche;
    @FXML
    private Button btn_pic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          event_title.setCellValueFactory(new PropertyValueFactory("event_title"));
        event_date.setCellValueFactory(new PropertyValueFactory("event_date"));
        //table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Transport e = (Transport) table.getSelectionModel().getSelectedItem();
                    System.out.println();
                    tf_title.setText(e.getMoyen());
                    tf_desc.setText(e.getDestination());
                   // dp_date.setValue(e.getDate().toLocalDate());
                   // File file = new File("C:\\wamp64\\www\\ImagesHulk\\" + e.getEvent_pic());
                   // Image image1 = new Image(file.toURI().toString());
                    //iv_pic.setImage(image1);
                    btn_delete.setDisable(false);
                    btn_update.setDisable(false);
                    btn_add.setDisable(true);
                    btn_pic.setDisable(true);
                }
            }
        });
           recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               // filtrerEventList((String) oldValue, (String) newValue);
            }

        });

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
        else 
        {
        //Transport e = new Transport(tf_title.getText(), tf_desc.getText(),Date.valueOf(dp_date.getValue()));
       // es.ajouterTransport(e);
        }
        data.removeAll(data);
         for (Transport ev : FXCollections.observableArrayList(es.FindEvent())) {
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
        btn_delete.setDisable(true);
        btn_update.setDisable(true);
        btn_add.setDisable(false);
        iv_pic.setImage(null);
        btn_pic.setDisable(false);

    }

    @FXML
    private void event_update(ActionEvent event) {
    }

    @FXML
    private void event_delete(ActionEvent event) {
    }

   

    @FXML
    private void upload(ActionEvent event) {
    }
    
}
