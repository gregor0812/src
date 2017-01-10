package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Koen Hengsdijk
 */
public class submitCase {

    Rootpane rootpane = new Rootpane();

    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();

    private Database db = new Database();

    submitCase() {

    }

    public GridPane MakeSubmitScreen() {

        Button btn;
        Button btn2;
        Button btnS;
        
        
        // ------------------------------
        // this is the main menu button
        btn = new Button(); // button 1
        btn.setText("Main Menu");
        btn.setPrefSize(160, 50);
        btn.setStyle("-fx-base:darkred;-fx-border-color:white");
        btn.setFont(Font.font("Verdana", 12));
        // ------------------------------
        
       ;
        //--------------------------------
        // this button will submit the case
        btnS = new Button(); // button Submit
        btnS.setText("Submit Case");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));
        
        // this pane will contain all the button and labels
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        // the submit button and main menu button are given standard positions
        GridPane.setConstraints(btn, 1, 15);
        GridPane.setConstraints(btnS, 40, 31);

        grid.setStyle("-fx-background-color: white");
        
        // the main menu button will return to the main menu through this action
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                rootpane.addnewpane(homescreen);
            }
        });
        
        // this is the found label
        Label Case = new Label("Found");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);
        
        
        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 30, 22, 15, 1);

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
        TextField airportT = new TextField();
        grid.add(airportT, 20, 19);

        Label labelN = new Label("Label number:");
        grid.add(labelN, 30, 17, 10, 1);
        TextField labelT = new TextField();
        grid.add(labelT, 40, 17);

        Label flightN = new Label("Flight number:");
        grid.add(flightN, 30, 18, 10, 1);
        TextField flightT = new TextField();
        grid.add(flightT, 40, 18);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField();
        grid.add(destinationT, 40, 19);

        Label ownerFirstName = new Label("First name:");
        grid.add(ownerFirstName, 30, 20, 10, 1);
        TextField ownerFirstNameT = new TextField();
        grid.add(ownerFirstNameT, 40, 20);
        
        Label ownerInsertion = new Label("Insertions:");
        grid.add(ownerInsertion, 30, 21, 10, 1);
        

        Label type = new Label("Type:");
        grid.add(type, 30, 23, 10, 1);
        TextField typeT = new TextField();
        grid.add(typeT, 40, 23);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 24, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 40, 24);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 25, 10, 1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 40, 25);

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 26, 10, 1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 40, 26);

        Label CaseId = new Label("The case id is: " + getCaseId());
        grid.add(CaseId, 40, 27, 2, 1);
        CaseId.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);

        grid.add(Calendar, 21, 17);
        
        // an image of the corendon logo is made
        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        // the corendon logo is added to the pane
        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btn, btnS);
        
        // the submit case gets an action here
        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // the case id will be generated here
                int caseid = getCaseId();
                
                // the label nr and flight number have a standard value of null
                Integer labelnr = null;
                Integer flightnr = null;
                
                // if a value is not entered in the labelnr textfield the value will be 0
                if (labelT.getText().isEmpty()) {
                    labelnr = 0;
                 // if a value is entered the labelnr will get the value thats
                 // entered in the textfield   
                } else {
                    labelnr = Integer.parseInt(labelT.getText());
                }
                
                // if a value is not entered in the labelnr textfield the value will be 0
                if (flightT.getText().isEmpty()) {
                    flightnr = 0;
                
                // if a value is entered the labelnr will get the value thats
                // entered in the textfield     
                } else {
                    flightnr = Integer.parseInt(flightT.getText());
                }
                
                // the lugggage info will get the value of their respective fields
                String airportName = airportT.getText();
                String itemname = typeT.getText();
                String Brand = itemBrandT.getText();
                String color = itemColorT.getText();
                String description = addNotesT.getText();
                String dateFound = dateT.getText();
                String destination = destinationT.getText();
                
                // the info will be entered in the the database using the 
                //insert into database method
                insertIntoDatabase(caseid, labelnr, flightnr,
                    airportName, destination, itemname, Brand,
                    color, description, dateFound);
            }
        });

        return grid;

    }

    // this function will get a new caseid 
    public int getCaseId() {

        // the value will be 0 at first
        int newCaseId = 0;
        
        
        try {
            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            // a statement is made
            Statement statement = ReportGenerationConnect.createStatement();
            // a result set is made using a query to get the highest case id
            // by adding a 1 the caseid will always be unique
            ResultSet TableData = statement.executeQuery("select max(foundID) from foundluggage");
            
            // the caseid will be extracted from the resultset
            while (TableData.next()) {
                newCaseId = TableData.getInt(1) + 1;

            }
            
            // if the connection fails the exception will be printed
        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        return newCaseId;
    }
    
    // this method will insert the luggage info into the database
    public void insertIntoDatabase(int caseid, Integer labelnr, Integer flightnr,
        String airportName, String destination, String itemname, String Brand,
        String color, String description, String dateFound) {

        
        try {

            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            // a statement is made
            Statement statement = ReportGenerationConnect.createStatement();
            // 
            String databaseQuery = (" insert into foundluggage (foundID, labelnr,"
                + " flightnr, airport, destination, itemname, brand, colors, description, dateFound, status) "
                + " values( " + caseid + " , " + labelnr + " , " + flightnr + ", "
                + " '" + airportName + "' , '" + destination + "' , ' " + itemname
                + " ' , ' " + Brand + " ' , ' " + color + "', ' "
                + description + "' , ' " + dateFound + "', 'open');");

            System.out.println(databaseQuery);

            statement.executeUpdate(databaseQuery);

            // een resultset met verloren labelnummers
            try {
                
                Statement statement2 = ReportGenerationConnect.createStatement();
                ResultSet knownlabelnr = statement2.executeQuery("select labelnr from lostluggage");
                List rowValues = new ArrayList();
                while (knownlabelnr.next()) {
                    rowValues.add(knownlabelnr.getInt(1));
                }
                
                Statement statement3 = ReportGenerationConnect.createStatement();
                
               if(rowValues.contains(labelnr)){
                   
                    String updatestatus1 = "UPDATE `corendon`.`foundluggage` SET "
                    + "`status`='matched' WHERE labelnr = " + labelnr + ";";
                   
                    statement3.executeUpdate(updatestatus1);
                    
                   String updatestatus2 = "UPDATE `corendon`.`lostluggage` SET "
                       + "`status`='matched' WHERE labelnr = " + labelnr + ";";
                   
                   statement3.executeUpdate(updatestatus2);
                   
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("congrats");
                        alert.setHeaderText("you got a match");
                        alert.setContentText("a match has been found!");
                        alert.showAndWait(); 
               }
                
                
                
                System.out.println(rowValues);
            } catch (Exception ex) {
                System.out.println("failed to check for matches");
                System.err.println(ex.getMessage());
            }

            

        } catch (Exception ex) {
            System.out.println("failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }

}
