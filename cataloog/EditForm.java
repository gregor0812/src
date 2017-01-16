/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cataloog;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
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
import prototypefys.Rootpane;

/**
 *
 * @author Koen Hengsdijk
 */
public class EditForm {

    private Rootpane rootpane = new Rootpane();
    private Database db = new Database();

    EditForm() {

    }

    public GridPane MakeEdit(LostLuggage person) {

        Button btnmainmenu;
        Button btn2;
        Button btnS;

        HBox Menu = new HBox();
        // ------------------------------
        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("Return to catalogue");
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

        Label caseid = new Label();
        caseid.setText("The case id is: " + person.getCaseid());
        caseid.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btnmainmenu, 1, 15);
        grid.add(caseid, 40, 30, 1, 1);
        // GridPane.setConstraints(btn2, 2, 15);
        GridPane.setConstraints(btnS, 40, 28, 2, 2);

        grid.setStyle("-fx-background-color: white");

        btnmainmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                BagageCatalogue scherm2 = new BagageCatalogue();
                GridPane cataloog = scherm2.MaakCatalogue();
                rootpane.addnewpane(cataloog);

            }
        });

        Label Case = new Label("Lost");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 30, 23, 15, 1);

        Label date = new Label("Date:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField(person.getDateLost());
        grid.add(dateT, 20, 17);
        dateT.setPromptText("yyyy-mm-dd");

        Label airport = new Label("Airport:");
        grid.add(airport, 10, 18, 10, 1);
        TextField airportT = new TextField(person.getAirport());
        grid.add(airportT, 20, 18);

        Label labelNumber = new Label("Label number:");
        grid.add(labelNumber, 30, 17, 10, 1);
        TextField labelNumberT = new TextField(Integer.toString(person.getLabelnr()));
        grid.add(labelNumberT, 40, 17);

        Label flight = new Label("Flight number:");
        grid.add(flight, 30, 18, 10, 1);
        TextField flightT = new TextField(Integer.toString(person.getFlightnr()));
        grid.add(flightT, 40, 18);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField(person.getDestination());
        grid.add(destinationT, 40, 19);

        Label OwnerInfo = new Label("Owner information: ");
        OwnerInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(OwnerInfo, 10, 20, 12, 1);

        Label naamReiziger = new Label("First Name:");
        grid.add(naamReiziger, 10, 21, 10, 1);
        TextField naamReizigerT = new TextField(getOwnerinfo(person.getOwnerid(), 2));
        grid.add(naamReizigerT, 20, 21);

        Label NameInsertion = new Label("Insertion:");
        grid.add(NameInsertion, 10, 22, 10, 1);
        TextField NameInsertionT = new TextField(getOwnerinfo(person.getOwnerid(), 3));
        grid.add(NameInsertionT, 20, 22);

        Label lastName = new Label("Last name:");
        grid.add(lastName, 10, 23, 10, 1);
        TextField LastNameT = new TextField(getOwnerinfo(person.getOwnerid(), 4));
        grid.add(LastNameT, 20, 23);

        Label phone1 = new Label("Phone number 1:");
        grid.add(phone1, 10, 24, 10, 1);
        TextField phone1T = new TextField(getOwnerinfo(person.getOwnerid(), 5));
        grid.add(phone1T, 20, 24);

        Label phone2L = new Label("Phone number 2:");
        grid.add(phone2L, 10, 25, 10, 1);
        TextField phone2T = new TextField(getOwnerinfo(person.getOwnerid(), 6));
        grid.add(phone2T, 20, 25);

        Label emailL = new Label("E-mail: ");
        grid.add(emailL, 10, 26, 10, 1);
        TextField emailT = new TextField(getOwnerinfo(person.getOwnerid(), 7));
        grid.add(emailT, 20, 26);

        Label addOwnerNotes = new Label("Additional notes:");
        grid.add(addOwnerNotes, 10, 27, 10, 1);
        TextField addOwnerNotesT = new TextField(getOwnerinfo(person.getOwnerid(), 8));
        grid.add(addOwnerNotesT, 20, 27, 1, 1);

        Label ownerAdd = new Label("Address:");
        grid.add(ownerAdd, 10, 28, 10, 1);
        TextField ownerAddT = new TextField(getAddressInfo(person.getOwnerid(), 2));
        grid.add(ownerAddT, 20, 28);

        Label ownerCity = new Label("City:");
        grid.add(ownerCity, 10, 29, 10, 1);
        TextField ownerCityT = new TextField(getAddressInfo(person.getOwnerid(), 4));
        grid.add(ownerCityT, 20, 29);

        Label ownerZip = new Label("Zipcode:");
        grid.add(ownerZip, 10, 30, 10, 1);
        TextField ownerZipT = new TextField(getAddressInfo(person.getOwnerid(), 3));
        grid.add(ownerZipT, 20, 30);

        Label ownerCountry = new Label("Country:");
        grid.add(ownerCountry, 10, 31, 10, 1);
        TextField ownerCountryT = new TextField(getAddressInfo(person.getOwnerid(), 5));
        grid.add(ownerCountryT, 20, 31);

        Label itemType = new Label("Type:");
        grid.add(itemType, 30, 24, 10, 1);
        TextField itemTypeT = new TextField(person.getItemname());
        grid.add(itemTypeT, 40, 24);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 25, 10, 1);
        TextField itemBrandT = new TextField(person.getBrand());
        grid.add(itemBrandT, 40, 25);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 26, 10, 1);
        TextField itemColorT = new TextField(person.getColors());
        grid.add(itemColorT, 40, 26);

//        Label lostandfound = new Label("Lost-and-found ID");
//        grid.add(lostandfound, 10, 20, 10, 1);
//        TextField lostandfoundT = new TextField();
//        grid.add(lostandfoundT, 20, 20);
        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 27, 10, 1);
        TextField addNotesT = new TextField(person.getDescription());
        grid.add(addNotesT, 40, 27);

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

        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int ownerid = person.getOwnerid();
                String firstname = naamReizigerT.getText();

                String insertion = NameInsertionT.getText();

                String Lastname = LastNameT.getText();
                int phone1 = Integer.parseInt(phone1T.getText());

                // because phone number 2 is optional the value turn into 
                // null if nothing is entered
                Integer phone2 = null;
                if (phone2T.getText().isEmpty()) {
                    phone2 = null;
                } else {
                    phone2 = Integer.parseInt(phone2T.getText());
                }

                String email = emailT.getText();
                String notes = addOwnerNotesT.getText();
                int caseid = person.getCaseid();
                int labelnr = Integer.parseInt(labelNumberT.getText());
                int flightnr = Integer.parseInt(flightT.getText());
                String destination = destinationT.getText();
                String airportName = airportT.getText();

                String itemname = itemTypeT.getText();
                String Brand = itemBrandT.getText();
                String color = itemColorT.getText();
                String description = addNotesT.getText();
                String dateLost = dateT.getText();

                String address = ownerAddT.getText();
                String city = ownerCityT.getText();
                String zipcode = ownerZipT.getText();
                String country = ownerCountryT.getText();

                insertEditIntoDatabase(ownerid, firstname, insertion,
                    Lastname, phone1, phone2, email, notes,
                    caseid, labelnr, flightnr, destination, airportName,
                    itemname, Brand, color, description, dateLost, address,
                    city, zipcode, country);

            }
        });

        return grid;

    }
    
    // this method will get the owner information
    public String getOwnerinfo(int ownerid, int infoNumber) {

        String info = "";

        try {

            Connection userinfoConnection = db.getConnection();
            Statement statement = userinfoConnection.createStatement();

            ResultSet ownerinfo = statement.executeQuery("select * from luggageowner "
                + "where ownerid = " + ownerid + "");

            while (ownerinfo.next()) {
                info = ownerinfo.getString(infoNumber);
            }

        } catch (Exception ex) {
            System.out.println("Failed to get user information");
            System.err.println(ex.getMessage());
        }

        return info;
    }

    public String getAddressInfo(int ownerid, int infoNumber) {

        String info = "";

        try {

            Connection userinfoConnection = db.getConnection();
            Statement statement = userinfoConnection.createStatement();

            ResultSet ownerinfo = statement.executeQuery("select * from address "
                + "where ownerid = " + ownerid + "");

            while (ownerinfo.next()) {
                info = ownerinfo.getString(infoNumber);
            }

        } catch (Exception ex) {
            System.out.println("Failed to get user information");
            System.err.println(ex.getMessage());
        }

        return info;
    }

    public void insertEditIntoDatabase(int ownerid, String firstname, String insertion,
        String Lastname, int phone1, Integer phone2, String email, String notes,
        int caseid, int labelnr, int flightnr, String destination, String airportName,
        String itemname, String Brand, String color, String description, String dateLost,
        String address, String city, String zipcode, String country) {

        try {

            // a connection is made
            Connection newConnection = db.getConnection();
            Statement statement = newConnection.createStatement();

            // this query will update the lugguowner info with the information provided
            String databaseQuery = ("UPDATE `corendon`.`luggageowner` SET "
                + "`firstname`='" + firstname + "', `insertion`='" + insertion
                + "', `lastname`='" + Lastname + "',"
                + " `phone1`='" + phone1 + "', `phone2`='" + phone2 + "', "
                + "`email`='" + email + "',"
                + " `notes`='" + notes + "' WHERE `ownerid`='" + ownerid + "';");

            // this query will update the address info with with the information provided
            String addressQuery = ("update address SET address = '" + address + "' , "
                + " zipcode = '" + zipcode
                + "' , city = '" + city + "', country = '" + country
                + "' WHERE ownerid = " + ownerid + ";");

            // this query will update the luggageinfo with the information provided
            String query2 = (" UPDATE `corendon`.`lostluggage` SET `labelnr`='" + labelnr + ""
                + "', `flightr`=" + flightnr + ", "
                + "`destination`='" + destination + "', `airport`='" + airportName
                + "', `itemname`='" + itemname + "', "
                + "`brand`='" + Brand + "', `colors`='" + color + "', `description`='" + description + "',"
                + " `date lost`='" + dateLost + "' WHERE `lostID`=" + caseid + ";");

            System.out.println(addressQuery);

            // the querys are executed here
            statement.executeUpdate(databaseQuery);
            statement.executeUpdate(query2);
            statement.executeUpdate(addressQuery);

            /**
             * after the edit is made the programm will check for matches using
             * the labelnr
             */
            try {

                // a new statement is made
                Statement statement2 = newConnection.createStatement();
                // a resultset is made using a query to select all the labelnumbers
                // from the foundluggage
                ResultSet knownlabelnr = statement2.executeQuery("select labelnr from foundluggage "
                    + "where labelnr != 0");

                // an arraylist is made to contain the labelnumbers
                List rowValues = new ArrayList();
                // this loop will add labelnumbers to the arraylist until there
                // is no more data in the resultset
                while (knownlabelnr.next()) {
                    rowValues.add(knownlabelnr.getInt(1));
                }
                // a new statement is made
                Statement statement3 = newConnection.createStatement();
                // this if function will check if there are matching labelnumbers
                if (rowValues.contains(labelnr)) {

                    // if an match has been found the status of the  found luggage will
                    // be updated to "matched"
                    String updatestatus1 = "UPDATE `corendon`.`foundluggage` SET "
                        + "`status`='matched' WHERE labelnr = " + labelnr + ";";

                    // the query is executed
                    statement3.executeUpdate(updatestatus1);

                    // if an match has been found the status of the  lost luggage will
                    // be updated to "matched"
                    String updatestatus2 = "UPDATE `corendon`.`lostluggage` SET "
                        + "`status`='matched' WHERE labelnr = " + labelnr + ";";
                    // the query is executed
                    statement3.executeUpdate(updatestatus2);

                    // an alert is displayed to notify the user of the match
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Congrats");
                    alert.setHeaderText("You got a match");
                    alert.setContentText("A match has been found!");

                    alert.showAndWait();
                }

                System.out.println(rowValues);

            } catch (Exception ex) {
                System.out.println("Failed to check for matches");
                System.err.println(ex.getMessage());
            }
            // the connection is closed
            newConnection.close();

        } catch (Exception ex) {
            System.out.println("Failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }

// this is an edit form for the found luggage
    public GridPane MakeEdit(FoundLuggage person) {

        // the buttons are made here
        Button backBtn;
        Button btnS;

        // this button will return the user to the catalogue
        backBtn = new Button();
        backBtn.setText("Back to catalogue");
        backBtn.setPrefSize(160, 50);
        backBtn.setStyle("-fx-base:darkred;-fx-border-color:white");
        backBtn.setFont(Font.font("Verdana", 12));
        // ------------------------------

        // this button will submit the edits
        btnS = new Button();
        btnS.setText("Submit Edits");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(backBtn, 1, 15);

        GridPane.setConstraints(btnS, 40, 31);

        grid.setStyle("-fx-background-color: white");

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
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
        grid.add(luggage, 30, 23, 15, 1);

        Label date = new Label("Date:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField(person.getDateFound());
        grid.add(dateT, 20, 17);
        dateT.setPromptText("yyyy/mm/dd");

        Label time = new Label("Time:");
        grid.add(time, 10, 18, 10, 1);
        TextField timeT = new TextField();
        grid.add(timeT, 20, 18);
        Label airport = new Label("Airport:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airportT = new TextField(person.getAirport());
        grid.add(airportT, 20, 19);

        Label labelN = new Label("Label number:");
        grid.add(labelN, 30, 17, 10, 1);
        TextField labelT = new TextField(Integer.toString(person.getLabelnr()));
        grid.add(labelT, 40, 17);

        Label flightN = new Label("Flight number:");
        grid.add(flightN, 30, 18, 10, 1);
        TextField flightT = new TextField(Integer.toString(person.getLabelnr()));
        grid.add(flightT, 40, 18);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField(person.getDestination());
        grid.add(destinationT, 40, 19);

        Label firstNameL = new Label("First name traveler:");
        grid.add(firstNameL, 30, 20, 10, 1);
        TextField firstNameT = new TextField(person.getFirstName());
        grid.add(firstNameT, 40, 20);

        Label insertionL = new Label("Insertion:");
        grid.add(insertionL, 30, 21, 10, 1);
        TextField insertionT = new TextField(person.getInsertion());
        grid.add(insertionT, 40, 21);

        Label lastNameL = new Label("Last name traveler:");
        grid.add(lastNameL, 30, 22, 10, 1);
        TextField lastNameT = new TextField(person.getLastName());
        grid.add(lastNameT, 40, 22);

        Label type = new Label("Type:");
        grid.add(type, 30, 24, 10, 1);
        TextField typeT = new TextField(person.getItemname());
        grid.add(typeT, 40, 24);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 25, 10, 1);
        TextField itemBrandT = new TextField(person.getBrand());
        grid.add(itemBrandT, 40, 25);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 26, 10, 1);
        TextField itemColorT = new TextField(person.getColors());
        grid.add(itemColorT, 40, 26);

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 27, 10, 1);
        TextField addNotesT = new TextField(person.getDescription());
        grid.add(addNotesT, 40, 27);

        Label CaseId = new Label("The found id is: " + person.getCaseid());
        grid.add(CaseId, 40, 28, 2, 1);
        CaseId.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);

        grid.add(Calendar, 21, 17);

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(backBtn, btnS);

        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // the case is gotten from the person object
                int foundId = person.getCaseid();

                // the info from the textfields are out in these variables
                int labelnr = Integer.parseInt(labelT.getText());
                int flightnr = Integer.parseInt(flightT.getText());
                String firstName = firstNameT.getText();
                String insertion = insertionT.getText();
                String lastName = lastNameT.getText();
                String airport = airportT.getText();
                String destination = destinationT.getText();
                String itemname = typeT.getText();
                String brand = itemBrandT.getText();
                String colors = itemColorT.getText();
                String description = addNotesT.getText();
                String dateFound = dateT.getText();

                UpdateFoundLuggage(foundId, labelnr, flightnr,
                    firstName, insertion, lastName, airport,
                    destination, itemname, brand, colors,
                    description, dateFound);

            }
        });

        return grid;

    }

    // this method will insert the updates made to the found luggage in the database
    public void UpdateFoundLuggage(int foundId, int labelnr, int flightnr,
        String firstName, String insertion, String lastName, String airport,
        String destination, String itemname, String brand, String colors,
        String description, String dateFound) {

        try {

            Connection newConnection = db.getConnection();
            Statement statement = newConnection.createStatement();

            String query = "UPDATE `corendon`.`foundluggage` SET `labelnr`= " + labelnr + ","
                + "`flightnr`= " + flightnr + ", `ownerName`='" + firstName + "', "
                + "`insertion`='" + insertion + "', `lastname`='" + lastName + "', "
                + "`destination`='" + destination + "', `airport`='" + airport + "', "
                + "`itemname`='" + itemname + "', `brand`='" + brand + "', `colors`='" + colors + "', "
                + "`description`='" + description + "', "
                + "`dateFound`='" + dateFound + "' WHERE `foundID`= " + foundId + ";";

            statement.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("Failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }
}
