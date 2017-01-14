package prototypefys;

import cataloog.FoundLuggage;
import cataloog.LostLuggage;
import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;

/**
 *
 * @author Koen Hengsdijk
 *
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

        // List with items for foundOwners combobox
        ObservableList<String> foundOwnersList
                = FXCollections.observableArrayList(
                        "New"
                );

        // this is the found label
        Label Case = new Label("Found");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 30, 24, 15, 1);

        Label date = new Label("Date:");
        grid.add(date, 10, 17, 10, 1);

        // this datepicker will be used to select dates
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate date = datePicker.getValue();
                System.err.println("Selected date: " + date);
            }
        });

        // this pattern is the data format used
        String pattern = "yyyy-MM-dd";

        datePicker.setPromptText(pattern.toLowerCase());

        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        grid.add(datePicker, 20, 17);

        Label airport = new Label("Airport:");
        grid.add(airport, 10, 18, 10, 1);
        TextField airportT = new TextField();
        grid.add(airportT, 20, 18);

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

        // Label and textfield for owner firstname
        Label ownerFirstName = new Label("First name:");
        grid.add(ownerFirstName, 30, 20, 10, 1);
        TextField ownerFirstNameT = new TextField();
        grid.add(ownerFirstNameT, 40, 20);
        ownerFirstNameT.textProperty().addListener((listener) -> {
            listFoundOwners(foundOwnersList, ownerFirstNameT.getText(), "", "");
        });

        // Label and textfield for owner insertions
        Label ownerInsertion = new Label("Insertion(s):");
        grid.add(ownerInsertion, 30, 21, 10, 1);
        TextField ownerInsertionT = new TextField();
        grid.add(ownerInsertionT, 40, 21);
        ownerInsertionT.textProperty().addListener((listener) -> {
            listFoundOwners(foundOwnersList, ownerFirstNameT.getText(),
                    ownerInsertionT.getText(), "");
        });

        // Label and textfield for owner lastname
        Label ownerLastName = new Label("Last name: ");
        grid.add(ownerLastName, 30, 22, 10, 1);
        TextField ownerLastNameT = new TextField();
        grid.add(ownerLastNameT, 40, 22);
        ownerLastNameT.textProperty().addListener((listener) -> {
            listFoundOwners(foundOwnersList, ownerFirstNameT.getText(),
                    ownerInsertionT.getText(), ownerLastNameT.getText());
        });

        // Combobox to display all owners that exist with the same name
        ComboBox foundCustomers = new ComboBox(foundOwnersList);
        foundCustomers.setMinSize(160, 20);
        foundCustomers.setMaxWidth(160);
        grid.add(foundCustomers, 40, 23);
        foundCustomers.valueProperty().addListener((listener) -> {
            String selectedItem = foundCustomers.getValue().toString();
            String[] test = selectedItem.replace(",", "").split(" ");
            ownerFirstNameT.setText(test[1]);
            ownerInsertionT.setText(test[2]);
            ownerLastNameT.setText(test[3]);
        });

        Label type = new Label("Type:");
        grid.add(type, 30, 25, 10, 1);
        TextField typeT = new TextField();
        grid.add(typeT, 40, 25);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 26, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 40, 26);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 27, 10, 1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 40, 27);

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 28, 10, 1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 40, 28);

        Label CaseId = new Label("The case id is: " + getCaseId());
        grid.add(CaseId, 40, 29, 2, 1);
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
        btnS.setOnAction((ActionEvent event) -> {
            // the case id will be generated here
            int caseid = getCaseId();

            // the label nr and flight number have a standard value of null
            Integer labelnr;
            Integer flightnr;
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
            int ownerID = -1;
            if (foundCustomers.getValue() != "New") {
                String item = foundCustomers.getValue().toString();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < item.length(); i++) {
                    if (Character.isDigit(item.charAt(i))) {
                        sb.append(item.charAt(i));
                        ownerID = Integer.parseInt(sb.toString());
                    } else {
                        break;
                    }
                }
            } else {
                ownerID = -1;
            }
            // the lugggage info will get the value of their respective fields
            String airportName = airportT.getText();
            String itemname = typeT.getText();
            String Brand = itemBrandT.getText();
            String color = itemColorT.getText();
            String description = addNotesT.getText();
            String dateFound = datePicker.getValue().toString();
            String destination1 = destinationT.getText();
            String firstname = ownerFirstNameT.getText();
            String insertion = ownerInsertionT.getText();
            String lastname = ownerLastNameT.getText();
            // the info will be entered in the the database using the
            //insert into database method
            if (insertIntoDatabase(caseid, labelnr, flightnr, airportName,
                    destination1, itemname, Brand, color, description,
                    dateFound, ownerID, firstname, insertion, lastname)) {
                // Tell user the form has been submitted succesfully
                Alert successMessage = new Alert(Alert.AlertType.CONFIRMATION);
                successMessage.setHeaderText("Operation completed successfully");
                successMessage.setContentText("All data has been added to the "
                        + "database");
                successMessage.showAndWait();

                // Clear all form fields
                datePicker.setValue(LocalDate.now());
                //dateT.setText("");
                airportT.setText("");
                labelT.setText("");
                flightT.setText("");
                destinationT.setText("");
                ownerFirstNameT.setText("");
                ownerInsertionT.setText("");
                ownerLastNameT.setText("");
                typeT.setText("");
                itemBrandT.setText("");
                itemColorT.setText("");
                addNotesT.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("An error accured while submitting "
                        + "the form, please try again later.");
                alert.showAndWait();
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
        } catch (SQLException ex) {
            System.out.println("exception 2 ");
        }

        return newCaseId;
    }

    /**
     * Get owner id From database if the owner exists
     *
     * @param firstname The firstname of the owner
     * @param insertion The insertions of the owner
     * @param lastname The lastname name of the owner
     * @return The integer that represents the owner
     */
    public int getOwnerID(String firstname, String insertion, String lastname) {

        String query = "SELECT ownerid "
                + "FROM luggageowner "
                + "WHERE firstname='" + firstname + "' "
                + "AND insertion='" + insertion + "' "
                + "AND lastname='" + lastname + "' "
                + "ORDER BY ownerid DESC "
                + "LIMIT 1";

        try {
            Connection selectOwnerId = db.getConnection();
            Statement statement = selectOwnerId.createStatement();
            ResultSet tableData = statement.executeQuery(query);

            while (tableData.next()) {
                return tableData.getInt(1);
            }

            return 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

    /**
     * List all existing owner with the same name that the user enterd
     *
     * @param foundOwnersList The list with all found owners
     * @param firstname The first name to search for
     * @param insertion The insertion to search for
     * @param lastname The lastname to search for
     */
    public void listFoundOwners(ObservableList foundOwnersList,
            String firstname, String insertion, String lastname) {

        // Remove alle current items from combobox
        foundOwnersList.clear();

        // Add option to create a new owner to the list
        foundOwnersList.add("New");

        try {

            // Query to search all existing owners
            String query = "SELECT L.ownerid, firstname, "
                    + "insertion, lastname, address, zipcode, city, "
                    + "country "
                    + "FROM luggageowner L "
                    + "LEFT JOIN address A "
                    + "ON L.ownerid=A.ownerid "
                    + "WHERE firstname LIKE '%" + firstname + "%' "
                    + "AND insertion LIKE '%" + insertion + "%' "
                    + "AND lastname LIKE '%" + lastname + "%';";

            // Run the query to find all owners with the same name
            Connection findOwners = db.getConnection();
            Statement statement = findOwners.createStatement();
            ResultSet rs = statement.executeQuery(query);

            System.out.println(query);

            // Loop trough all results
            while (rs.next()) {

                // Create StrinBuilder to display all owners
                StringBuilder sb = new StringBuilder();
                sb.append(rs.getInt("ownerid")).append(" ")
                        .append(rs.getString("firstname")).append(" ")
                        .append(rs.getString("insertion")).append(" ")
                        .append(rs.getString("lastname")).append(", ")
                        .append(rs.getString("address")).append(" ")
                        .append(rs.getString("zipcode")).append(" ")
                        .append(rs.getString("city")).append(", ")
                        .append(rs.getString("country"));

                // Add owner to the list
                foundOwnersList.add(sb.toString());
            }

            // Handle the errors
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Insert the form data into the database
     *
     * @param caseid The identifier of the luggage
     * @param labelnr The labelnr on the luggage
     * @param flightnr The flightnr the luggage is from
     * @param airportName The name of the airport where the luggage was found
     * @param destination The destination of the luggage
     * @param itemname A name to describe the luggage
     * @param Brand The brand of the luggage
     * @param color The color of the luggage
     * @param description A brief description of the luggage
     * @param dateFound The data the luggage was reported
     * @param ownerId The id of the owner of the luggage, if known
     * @param firstname The firstname of the luggage owner
     * @param insertion The name insertion of the luggage owner
     * @param lastname The lastname of the luggage owner
     * @return Boolean, true if no errors accured
     */
    public boolean insertIntoDatabase(int caseid, Integer labelnr, Integer flightnr,
            String airportName, String destination, String itemname, String Brand,
            String color, String description, String dateFound, int ownerId,
            String firstname, String insertion, String lastname) {

        String time = new SimpleDateFormat("hh:mm:ss").format(new Date());

        if (ownerId == -1) {
            try {

                String sql = "INSERT INTO luggageowner (firstname, insertion, "
                        + "lastname, phone1) "
                        + "VALUES('" + firstname + "', '" + insertion + "',"
                        + "'" + lastname + "', 0)";

                Connection insertNewOwner = db.getConnection();
                Statement stmt = insertNewOwner.createStatement();

                stmt.executeUpdate(sql);

                ownerId = getOwnerID(firstname, insertion, lastname);

            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }

        try {

            // a connection is made
            Connection matchCheckConnection = db.getConnection();
            // a statement is made
            Statement statement = matchCheckConnection.createStatement();
            // 
            String databaseQuery = ("INSERT INTO foundluggage ( labelnr, "
                    + "ownerid, flightnr, ownerName, insertion, lastname, "
                    + "airport, destination, itemname, brand, colors, "
                    + "description, dateFound, timeFound, status) "
                    + "VALUES( "
                    + labelnr + ", "
                    + ownerId + ", "
                    + flightnr + ", "
                    + "'" + firstname + "', "
                    + "'" + insertion + "', "
                    + "'" + lastname + "', "
                    + "'" + airportName + "', "
                    + "'" + destination + "', "
                    + "'" + itemname + "', "
                    + "'" + Brand + "', "
                    + "'" + color + "', "
                    + "'" + description + "', "
                    + "'" + dateFound + "', "
                    + "'" + time + "', "
                    + "'open'"
                    + ");");

            // For debugging
            System.out.println(databaseQuery);

            statement.executeUpdate(databaseQuery);

            // een resultset met verloren labelnummers
            try {

                Statement statement2 = matchCheckConnection.createStatement();
                ResultSet knownlabelnr = statement2.executeQuery("select labelnr from lostluggage");
                List rowValues = new ArrayList();
                while (knownlabelnr.next()) {
                    rowValues.add(knownlabelnr.getInt(1));
                }

                Statement statement3 = matchCheckConnection.createStatement();

                if (rowValues.contains(labelnr)) {

                    Statement owneridStatement = matchCheckConnection.createStatement();

                    // this resultset will contain the ownerid of the luggageowner
                    ResultSet LostLuggageOwnerId
                            = owneridStatement.executeQuery("select ownerid from foundluggage "
                                    + "where labelnr = " + labelnr + " ;");

                    int ownerid = 0;

                    while (LostLuggageOwnerId.next()) {
                        ownerid = LostLuggageOwnerId.getInt(1);
                    }

                    // this will update the status from the foundluggage
                    String updatestatus1 = "UPDATE `corendon`.`foundluggage` SET "
                            + "`status`='matched', ownerid = " + ownerid + " WHERE labelnr = " + labelnr + ";";

                    statement3.executeUpdate(updatestatus1);
                    // this will update the status of the lostluggage
                    String updatestatus2 = "UPDATE `corendon`.`lostluggage` SET "
                            + "`status`='matched' WHERE labelnr = " + labelnr + ";";

                    statement3.executeUpdate(updatestatus2);

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("congrats");
                    alert.setHeaderText("you got a match");
                    alert.setContentText("a match has been found!");
                    ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.CANCEL_CLOSE);
                    ButtonType ViewMatchBtn = new ButtonType("View match");
                    alert.getButtonTypes().setAll(okButton, ViewMatchBtn);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ViewMatchBtn) {

                        String LostLuggageInfo = ("select lostluggage.lostID, lostluggage.ownerid, "
                                + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
                                + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
                                + "lostluggage.airport, lostluggage.itemname,"
                                + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
                                + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
                                + "inner join luggageowner"
                                + " on lostluggage.ownerid = luggageowner.ownerid "
                                + "where labelnr = " + labelnr);

                        String FoundLuggageInfo = ("select * from foundluggage "
                                + "where labelnr = " + labelnr);

                        matchInformatie matchinfo = new matchInformatie();
                        GridPane infoScherm = matchinfo.matchInfo(lostLuggageMatchInfo(LostLuggageInfo),
                                foundLuggageMatchInfo(FoundLuggageInfo));
                        rootpane.addnewpane(infoScherm);

                    }

                }

                System.out.println(rowValues);
            } catch (SQLException ex) {
                System.out.println("failed to check for matches");
                System.err.println(ex.getMessage());
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("failed to insert data in to the database ");
            System.err.println(ex.getMessage());
            return false;
        }

        return true;

    }

    public LostLuggage lostLuggageMatchInfo(String query) {

        LostLuggage LostInfo = null;

        try {

            Connection matchCheckConnection = db.getConnection();
            Statement statement = matchCheckConnection.createStatement();

            ResultSet LostLuggageResult = statement.executeQuery(query);

            while (LostLuggageResult.next()) {

                LostInfo = (new LostLuggage(LostLuggageResult.getInt(1), LostLuggageResult.getInt(2),
                        LostLuggageResult.getString(3), LostLuggageResult.getString(4),
                        LostLuggageResult.getString(5), LostLuggageResult.getInt(6),
                        LostLuggageResult.getInt(7), LostLuggageResult.getString(8),
                        LostLuggageResult.getString(9),
                        LostLuggageResult.getString(10), LostLuggageResult.getString(11),
                        LostLuggageResult.getString(12), LostLuggageResult.getString(13),
                        LostLuggageResult.getString(14),
                        LostLuggageResult.getString(15), LostLuggageResult.getString(16)));
            }
        } catch (Exception ex) {
            System.out.println("Failed to retrieve matchinfo ");
            System.err.println(ex.getMessage());
        }

        return LostInfo;
    }

    public FoundLuggage foundLuggageMatchInfo(String query) {

        FoundLuggage FoundInfo = null;
        try {

            Connection matchCheckConnection = db.getConnection();
            Statement statement = matchCheckConnection.createStatement();

            ResultSet TableData = statement.executeQuery(query);

            while (TableData.next()) {
                FoundInfo = (new FoundLuggage(TableData.getInt(1), TableData.getInt(2), TableData.getInt(3),
                        TableData.getInt(4), TableData.getString(5), TableData.getString(6),
                        TableData.getString(7), TableData.getString(9),
                        TableData.getString(8), TableData.getString(10),
                        TableData.getString(11), TableData.getString(12),
                        TableData.getString(13), TableData.getString(14),
                        TableData.getString(15), TableData.getString(16)));
            }

        } catch (Exception ex) {
            System.out.println("Failed to retrieve matchinfo ");
            System.err.println(ex.getMessage());
        }

        return FoundInfo;
    }

}
