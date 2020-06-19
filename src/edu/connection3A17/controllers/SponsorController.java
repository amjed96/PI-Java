/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.controllers ;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dto.SponsorDto;
import edu.connection3A17.entities.Sponsor;
import edu.connection3A17.services.SponsorService;
import edu.connection3A17.utils.MyConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class SponsorController implements Initializable {

    @FXML
    private TextField recherche;
    @FXML
    private TableView<SponsorDto> table;
    @FXML
    private TableColumn<SponsorDto, String> sponsor_id;
    @FXML
    private TableColumn<SponsorDto, String> sponsor_nom;
     @FXML
    private TableColumn<SponsorDto, String> sponsor_description;

     SponsorService sp = new SponsorService();
    ObservableList<SponsorDto> data;
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
    private TextField description;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data= FXCollections.observableArrayList(sp.afficherSponsor());
         sponsor_id.setCellValueFactory(new PropertyValueFactory("Id"));
         sponsor_nom.setCellValueFactory(new PropertyValueFactory("Nom"));
        sponsor_description.setCellValueFactory(new PropertyValueFactory("Description"));
        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    SponsorDto e = (SponsorDto) table.getSelectionModel().getSelectedItem();
                    System.out.println();
                    //id.setText(String.valueOf(e.getId()));
                    nom.setText(e.getNom());
                   description.setText(e.getDescription());
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
    @FXML
    private void convertirEnPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
    String file="C:\\Users\\zakib\\Desktop\\CHAIMA LINUX\\sponsor.pdf";
    Document document =new Document();
      Alert a2 = new Alert(Alert.AlertType.CONFIRMATION);
        a2.setTitle("Conversion PDF !");
        a2.setContentText("PDF telecharge avec succés!");
        a2.show();

     try{
           Font f = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.UNDERLINE));
            f.setColor(0, 153, 255);
      

            Font f2 = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD));
            f2.setColor(0, 0, 0);

            PdfWriter.getInstance(document, new FileOutputStream(new File(file)));
         document.open();
         Paragraph p =new Paragraph("LISTE  DES  Sponsores  " ,f);
       
                    

     //  document.add(Image.getInstance("E:\\don.png"));
  Paragraph pm =new Paragraph();
         pm.add("   \n  ");
         p.setAlignment(Element.ALIGN_CENTER);
        
         document.add(p);
            document.add(pm);

document.add(pm);
Paragraph posss= new Paragraph("__________________________________________________");
document.add(posss);
Paragraph pos= new Paragraph("id"+"      "+"nom "+"      "+" Description",f2);
document.add(pos);
document.add(posss);
         Connection cn2 = MyConnection.getInstance().getCnx();
         String req ="select * from sponsor  ";
            Statement pst = cn2.createStatement();
            ResultSet rs = pst.executeQuery(req);
      while (rs.next()) {
              
Paragraph p1= new Paragraph( "   ");
Paragraph po= new Paragraph(rs.getInt("id")+"                      "+rs.getString("nom")+"                     "+rs.getString("description"));

               


document.add(p1);
document.add(po);



                
           
            }
         document.close();
         System.out.println("Done");
         
     }catch(Exception e){
         e.printStackTrace();
     }
     
     
    }
        
    void filtrerEventList(String oldValue, String newValue) {
        SponsorService evs = new SponsorService();
        if ("".equals(recherche.getText()) || "".equals(newValue)) {
            ObservableList<SponsorDto> filteredList = FXCollections.observableArrayList(evs.afficherSponsor());
            table.setItems(filteredList);
        } else {
            ObservableList<SponsorDto> filteredList = FXCollections.observableArrayList(evs.rechercheParNom(newValue));
            table.setItems(filteredList);
            newValue = newValue.toUpperCase();
            for (SponsorDto e : table.getItems()) {

                String filterEventName = e.getNom();;
               

                if (filterEventName.toUpperCase().contains(newValue) && !filteredList.contains(e)) {
                    filteredList.add(e);
                }
            }
            table.setItems(filteredList);
        }
    }

    @FXML
    private void addSponsor(ActionEvent event) {
     if (nom.getText() == null || nom.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
        else if (description.getText()== null || description.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();

        }
           
        else 
        {
        Sponsor e = new Sponsor(0,nom.getText(),null,null, description.getText());
        sp.ajouterSponsor(e);
        }
        data.removeAll(data);
         for (SponsorDto ev : FXCollections.observableArrayList(sp.afficherSponsor())) {
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
        description.clear();
        btn_delete.setDisable(true);
        btn_update.setDisable(true);
        btn_add.setDisable(false);
        
    }

    @FXML
    private void updateSponsor(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            SponsorDto s = table.getSelectionModel().getSelectedItem();
            sp.modifierSponsor(new Sponsor(s.getId(),nom.getText(), null, null, description.getText()));
            data.removeAll(data);
            for (SponsorDto e : FXCollections.observableArrayList(sp.afficherSponsor())) {
                data.add(e);
            }
            clear();
        }
    }

    @FXML
    private void deleteSponsor(ActionEvent event) {
          ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé ce sponsor!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Sponsor Supprimee");
                sp.supprimerSponsor(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (SponsorDto e : FXCollections.observableArrayList(sp.afficherSponsor())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }

    }

    
}