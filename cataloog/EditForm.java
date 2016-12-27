/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cataloog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prototypefys.Rootpane;

/**
 *
 * @author Koen Hengsdijk
 */
public class EditForm {
    
    private Rootpane rootpane = new Rootpane();
    
    EditForm(){
        
    }
    
    public GridPane MakeLostReport(LostLuggage person){
        
        Button btnmainmenu;
        Button btn2;
        Button btnS;
        
         HBox Menu = new HBox();
        // ------------------------------
        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("return to catalogue");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));
        // ------------------------------
//        btn2 = new Button(); // button 2
//        btn2.setText("Options");
//        btn2.setPrefSize(160, 50);
//        btn2.setStyle("-fx-base:darkred;-fx-border-color:white");
//        btn2.setFont(Font.font("Verdana", 12));
        //--------------------------------
        btnS = new Button(); // button Submit
        btnS.setText("Submit Case");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        GridPane.setConstraints(btnmainmenu, 1, 15);
        
       // GridPane.setConstraints(btn2, 2, 15);
        
        GridPane.setConstraints(btnS, 40, 28);

        
        grid.setStyle("-fx-background-color: white");
        
        btnmainmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
              BagageCatalogue scherm2 = new BagageCatalogue();
                GridPane cataloog = scherm2.MaakCatalogue();
                
                rootpane.addnewpane(cataloog);
            }
            });
        
        Label Case = new Label("Found");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 10, 23, 15, 1);

        Label date = new Label("Date:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField();
        grid.add(dateT, 20, 17);
        dateT.setPromptText("dd/mm/yyyy");

        Label time = new Label("Time:");
        grid.add(time, 10, 18, 10, 1);
        TextField timeT = new TextField();
        grid.add(timeT, 20, 18);

        Label airport = new Label("Airport:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airtportT = new TextField(person.getAirport());
        grid.add(airtportT, 20, 19);

        Label labelNumber = new Label("Label number:");
        grid.add(labelNumber, 30, 17, 10, 1);
        TextField labelNumberT = new TextField(Integer.toString(person.getLabelnr()));
        grid.add(labelNumberT, 40, 17);
        labelNumberT.setEditable(false);

        Label flight = new Label("Flight number:");
        grid.add(flight, 30, 18, 10, 1);
        TextField flightT = new TextField();
        grid.add(flightT, 40, 18);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField();
        grid.add(destinationT, 40, 19);
        
        Label naamReiziger = new Label("Name traveler:");
        grid.add(naamReiziger, 30, 20, 10, 1);
        TextField naamReizigerT = new TextField();
        grid.add(naamReizigerT, 40, 20);

        Label itemType = new Label("Type:");
        grid.add(itemType, 10, 24, 10, 1);
        TextField itemTypeT = new TextField(person.getItemname());
        grid.add(itemTypeT, 20, 24);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 10, 25, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 20, 25);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 10, 26, 10, 1);
        TextField itemColorT = new TextField(person.getColors());
        grid.add(itemColorT, 20, 26);

        Label lostandfound = new Label("case ID");
        grid.add(lostandfound, 10, 20, 10, 1);
        TextField lostandfoundT = new TextField(Integer.toString(person.getCaseid()));
        grid.add(lostandfoundT, 20, 20);

        

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 10, 27, 10, 1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 20, 27);
        
        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);
        
        grid.add(Calendar, 21, 17);
        
        
        
        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);
        
        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);
        
        return grid;
     
    }
    
    
}
