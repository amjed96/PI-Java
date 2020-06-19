/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connection3A17.views;

import Maps.gmapsfx.GoogleMapView;
import Maps.gmapsfx.MapComponentInitializedListener;
import Maps.gmapsfx.javascript.object.GoogleMap;
import Maps.gmapsfx.javascript.object.LatLong;
import Maps.gmapsfx.javascript.object.MapOptions;
import Maps.gmapsfx.javascript.object.MapTypeIdEnum;
import Maps.gmapsfx.javascript.object.Marker;
import Maps.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hamza
 */
public class PIDEVFX extends Application  {
    



        @Override
        public void start(Stage primaryStage) throws Exception {

            CalendarView calendarView = new CalendarView(); 
            Transport trans =new Transport(); 

                Calendar birthdays = new Calendar("Transport"); 
                Calendar holidays = new Calendar("Holidays");

                birthdays.setStyle(Style.STYLE1); 
                holidays.setStyle(Style.STYLE2);

                CalendarSource myCalendarSource = new CalendarSource("My Calendars"); 
                
                myCalendarSource.getCalendars().addAll(birthdays, holidays);

                calendarView.getCalendarSources().addAll(myCalendarSource); 

                calendarView.setRequestedTime(LocalTime.now());

                Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                        @Override
                        public void run() {
                                while (true) {
                                        Platform.runLater(() -> {
                                                calendarView.setToday(LocalDate.now());
                                                calendarView.setTime(LocalTime.now());
                                        });

                                        try {
                                                // update every 10 seconds
                                                sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }

                                }
                        };
                };

                updateTimeThread.setPriority(Thread.MIN_PRIORITY);
                updateTimeThread.setDaemon(true);
                updateTimeThread.start();

                Scene scene = new Scene(calendarView);
                primaryStage.setTitle("Calendar");
                primaryStage.setScene(scene);
                primaryStage.setWidth(700);
                primaryStage.setHeight(400);
                primaryStage.centerOnScreen();
                primaryStage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }


  /*  @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
    
 */







 
   



    //Create the JavaFX component and set this as a listener so we know when 
    //the map has been initialized, at which point we can then begin manipulating it.
  /*  mapView = new GoogleMapView();
   
       mapView.addMapInitializedListener(this);

    Scene scene = new Scene(mapView);

    stage.setTitle("JavaFX and Google Maps");
    stage.setScene(scene);
    stage.show();
}
*/

/*@Override
public void mapInitialized() {
    //Set the initial properties of the map.
    MapOptions mapOptions = new MapOptions();

    mapOptions.center(new LatLong(47.6097, -122.3331))
            .mapType(MapTypeIdEnum.ROADMAP)
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(12);

    map = mapView.createMap(mapOptions);

    //Add a marker to the map
    MarkerOptions markerOptions = new MarkerOptions();

    markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);

}

public static void main(String[] args) {
    launch(args);
}*/
}
