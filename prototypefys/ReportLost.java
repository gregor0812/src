package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import cataloog.FoundLuggage;
import cataloog.LostLuggage;
import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;


/**
 *
 * @author IS-109-2
 */
public class ReportLost {

    private Database db = new Database();

    Rootpane rootpane = new Rootpane();

    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();

    ReportLost() {

    }

    /**
     *
     * @return
     */
    public GridPane MakeLostReport() {

        Button btn;
        Button btnS;

        HBox Menu = new HBox();
        // ------------------------------
        btn = new Button(); // button 1
        btn.setText("Main Menu");
        btn.setPrefSize(110, 50);
        btn.setStyle("-fx-base:darkred;-fx-border-color:white");
        btn.setFont(Font.font("Verdana", 12));
        //--------------------------------
        btnS = new Button(); // button Submit
        btnS.setText("Submit Case");
        btnS.setPrefSize(100, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btn, 1, 15);

       


        GridPane.setConstraints(btnS, 39, 30, 2, 2);
        Label caseid = new Label();
        caseid.setText("The case id is: " + getCaseId());
        caseid.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        
        Label OwnerId = new Label("The owner id is: " + getOwnerId());
        grid.add(OwnerId, 39, 28, 2, 1);
        OwnerId.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        GridPane.setConstraints(caseid, 39, 29, 2, 1);

        grid.setStyle("-fx-background-color: white");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                rootpane.addnewpane(homescreen);
            }
        });

        Label Case = new Label("Destination");
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

        Label labelNumber = new Label("Label number:");
        grid.add(labelNumber, 30, 17, 10, 1);
        TextField labelNumberT = new TextField();
        grid.add(labelNumberT, 40, 17);

        Label flight = new Label("Flight number:");
        grid.add(flight, 30, 18, 10, 1);
        TextField flightT = new TextField();
        grid.add(flightT, 40, 18);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField();
        grid.add(destinationT, 40, 19);

        Label OwnerInfo = new Label("Owner information: ");
        OwnerInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(OwnerInfo, 10, 20, 12, 1);

        Label naamReiziger = new Label("First Name:");
        grid.add(naamReiziger, 10, 21, 10, 1);
        TextField naamReizigerT = new TextField();
        grid.add(naamReizigerT, 20, 21);

        Label NameInsertion = new Label("Insertion:");
        grid.add(NameInsertion, 10, 22, 10, 1);
        TextField NameInsertionT = new TextField();
        grid.add(NameInsertionT, 20, 22);

        Label lastName = new Label("Last name:");
        grid.add(lastName, 10, 23, 10, 1);
        TextField LastNameT = new TextField();
        grid.add(LastNameT, 20, 23);

        Label phone1 = new Label("Phone number 1:");
        grid.add(phone1, 10, 24, 10, 1);
        TextField phone1T = new TextField();
        grid.add(phone1T, 20, 24);

        Label phone2L = new Label("Phone number 2:");
        grid.add(phone2L, 10, 25, 10, 1);
        TextField phone2T = new TextField();
        grid.add(phone2T, 20, 25);

        Label emailL = new Label("E-mail: ");
        grid.add(emailL, 10, 26, 10, 1);
        TextField emailT = new TextField();
        grid.add(emailT, 20, 26);

        Label addOwnerNotes = new Label("Additional notes:");
        grid.add(addOwnerNotes, 10, 27, 10, 1);
        TextField addOwnerNotesT = new TextField();
        grid.add(addOwnerNotesT, 20, 27, 1, 1);

        Label ownerAdd = new Label("Address:");
        grid.add(ownerAdd, 10, 28, 10, 1);
        TextField ownerAddT = new TextField();
        grid.add(ownerAddT, 20, 28);

        Label ownerCity = new Label("City:");
        grid.add(ownerCity, 10, 29, 10, 1);
        TextField ownerCityT = new TextField();
        grid.add(ownerCityT, 20, 29);

        Label ownerZip = new Label("Zipcode:");
        grid.add(ownerZip, 10, 30, 10, 1);
        TextField ownerZipT = new TextField();
        grid.add(ownerZipT, 20, 30);

        Label ownerCountry = new Label("Country:");
        grid.add(ownerCountry, 10, 31, 10, 1);
        TextField ownerCountryT = new TextField();
        grid.add(ownerCountryT, 20, 31);

        Label itemType = new Label("Type:");
        grid.add(itemType, 30, 24, 10, 1);
        TextField itemTypeT = new TextField();
        grid.add(itemTypeT, 40, 24);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 25, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 40, 25);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 26, 10, 1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 40, 26);

//        Label lostandfound = new Label("Lost-and-found ID");
//        grid.add(lostandfound, 10, 20, 10, 1);
//        TextField lostandfoundT = new TextField();
//        grid.add(lostandfoundT, 20, 20);
        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 27, 10, 1);
        TextField addNotesT = new TextField();
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
        grid.getChildren().addAll(btn, btnS, caseid);

        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int ownerid = getOwnerId();
                String firstname = naamReizigerT.getText();
              
                
                String insertion = NameInsertionT.getText();

                String Lastname = LastNameT.getText();
                int phone1 = Integer.parseInt(phone1T.getText());
                
                // because phone number 2 is optional the value turn into 
                // null if nothing is entered
                Integer phone2 = null;
                if (phone2T.getText().isEmpty()){
                    phone2 = null;
                }
                else{
                phone2 = Integer.parseInt(phone2T.getText());
                }
                String email = emailT.getText();
                String notes = addOwnerNotesT.getText();
                int caseid = getCaseId();
                int labelnr = Integer.parseInt(labelNumberT.getText());
                int flightnr = Integer.parseInt(flightT.getText());
                String destination = destinationT.getText();
                String airportName = airportT.getText();

                String itemname = itemTypeT.getText();
                String Brand = itemBrandT.getText();
                String color = itemColorT.getText();
                String description = addNotesT.getText();
                String dateFound = datePicker.getValue().toString();
                
                String address = ownerAddT.getText();
                String city = ownerCityT.getText();
                String zipcode = ownerZipT.getText();
                String country = ownerCountryT.getText();
                
                insertIntoDatabase(ownerid, firstname, insertion,
                    Lastname, phone1, phone2, email, notes,
                    caseid, labelnr, flightnr,destination, airportName,
                    itemname, Brand, color, description, dateFound, address,
                    city, zipcode, country);
                
                Alert alertS = new Alert(Alert.AlertType.WARNING);
                        alertS.setTitle("Submit");
                        alertS.setHeaderText("Submit Case");
                        alertS.setContentText("The case is submitted.");
                        
                           alertS.showAndWait(); 
            }
        });
        
        
         
        

        return grid;

    }

    /**
     *
     * @return
     */
    public int getCaseId() {

        int newCaseId = 0;

        try {

            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery("select max(lostID) from lostluggage");

            while (TableData.next()) {
                newCaseId = TableData.getInt(1) + 1;

            }

        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        return newCaseId;
    }

    /**
     *
     * @return
     */
    public int getOwnerId() {

        int newOwnerId = 0;

        try {

            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery("select max(ownerid) from luggageowner");

            while (TableData.next()) {
                newOwnerId = TableData.getInt(1) + 1;

            }

        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        return newOwnerId;
    }

    /**
     *
     * @param ownerid the id of the luggage owner
     * @param firstname the first name of the luggage owner
     * @param insertion the insertion of the luggage owner
     * @param Lastname the last name of the luggage owner
     * @param phone1 the first phonenumber of the luggage owner
     * @param phone2 the second phonenumber of the luggage owner
     * @param email the email of the luggage owner
     * @param notes the notes about  the luggage owner
     * @param caseid the case id of the luggage
     * @param labelnr the labelnr of the luggage
     * @param flightnr the flightnr of the luggage
     * @param destination the destination of the luggagee
     * @param airportName the name of the airport the luggage was registrated
     * @param itemname the item name of the luggage
     * @param Brand the brand of the luggage
     * @param color the color of the luggage
     * @param description the description of the luggage
     * @param dateLost the date the luggage was lost
     * @param address the owner address
     * @param city the city the luggage owner lives in
     * @param zipcode the zipcode of the luggage owner
     * @param country the country the luggage owner lives in
     */
    public void insertIntoDatabase(int ownerid, String firstname, String insertion,
        String Lastname, int phone1, Integer phone2, String email, String notes,
        int caseid, int labelnr, int flightnr, String destination, String airportName,
        String itemname, String Brand, String color, String description, String dateLost, 
        String address, String city, String zipcode, String country) {

        try {

            // a connection is made
            Connection matchCheckConnection = db.getConnection();
            Statement statement = matchCheckConnection.createStatement();

            String databaseQuery = ("insert into luggageowner (ownerid, firstname, insertion, lastname, phone1, phone2, email, notes)"
                + " values(" + ownerid + ", '" + firstname + "', '" + insertion + "', '"
                + Lastname + "' , " + phone1 + ", " + phone2 + " ,  '" + email + "', '" + notes + "');");
                
                String addressQuery = ("insert into address values (" + ownerid
                    + ",  '" + address + "' ,  '" + zipcode + "'"
                    + ",  '" + city + "' ,  '" + country + "')");
                
                String query2 = (" insert into lostluggage (lostID, ownerid, labelnr,"
                + " flightr, destination, airport, itemname, brand, colors, description, `date lost`, status) "
                + " values( " + caseid + " , " + ownerid + " , " + labelnr + ", " 
                + flightnr + " , '" + destination + "', '" + airportName + "' , '" + itemname 
                + "' , '" + Brand + "' , '" + color + "', '" 
                + description + "' , '" + dateLost + "', 'open');");

                                
            statement.executeUpdate(databaseQuery);
            statement.executeUpdate(query2);
            statement.executeUpdate(addressQuery);


            // this string is used to make a lostluggage object which is used to
            // make a pdf and potentially a matchview
            String LostLuggageInfoQuery = ("select lostluggage.lostID, lostluggage.ownerid, "
                + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
                + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
                + "lostluggage.airport, lostluggage.itemname,"
                + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
                + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
                + "inner join luggageowner"
                + " on lostluggage.ownerid = luggageowner.ownerid "
                + "where labelnr = " + labelnr);
            // a LostLuggage object is made using the query
            LostLuggage LostLuggageInfo = lostLuggageMatchInfo(LostLuggageInfoQuery);
            
            // a pdf is made using the lostluggage object
            GenerateSignedPdf pdfmaker = new GenerateSignedPdf();
            pdfmaker.MakePdf(LostLuggageInfo);


            try {
                
                Statement statement2 = matchCheckConnection.createStatement();
                ResultSet knownlabelnr = statement2.executeQuery("select labelnr from foundluggage " +
                "where labelnr != 0");
                List rowValues = new ArrayList();
                while (knownlabelnr.next()) {
                    rowValues.add(knownlabelnr.getInt(1));
                }


                Statement statement3 = matchCheckConnection.createStatement();

                if (rowValues.contains(labelnr)) {

                    Statement owneridStatement = matchCheckConnection.createStatement();

                    // this resultset will contain the ownerid of the luggageowner
                    ResultSet LostLuggageOwnerId
                        = owneridStatement.executeQuery("select ownerid from lostluggage "
                            + "where labelnr = " + labelnr + " ;");

                    int knownOwnerID = 0;

                    while (LostLuggageOwnerId.next()) {
                        knownOwnerID = LostLuggageOwnerId.getInt(1);
                    }

                    String updatestatus1 = "UPDATE `corendon`.`foundluggage` SET "
                        + "`status`='matched', ownerid = " + knownOwnerID + " WHERE labelnr = " + labelnr + ";";

                    statement3.executeUpdate(updatestatus1);

                    String updatestatus2 = "UPDATE `corendon`.`lostluggage` SET "
                        + "`status`='matched' WHERE labelnr = " + labelnr + ";";

                    statement3.executeUpdate(updatestatus2);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Congratulations");
                    alert.setHeaderText("you got a match");
                    alert.setContentText("a match has been found!");

                    ButtonType okButton = new ButtonType("ok", ButtonData.CANCEL_CLOSE);
                    ButtonType ViewMatchBtn = new ButtonType("View match");
                    alert.getButtonTypes().setAll(okButton, ViewMatchBtn);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ViewMatchBtn) {

                        String FoundLuggageInfo = ("select * from foundluggage "
                            + "where labelnr = " + labelnr);

                        matchInformatie matchinfo = new matchInformatie();
                        GridPane infoScherm = matchinfo.matchInfo(LostLuggageInfo,
                            foundLuggageMatchInfo(FoundLuggageInfo));
                        rootpane.addnewpane(infoScherm);

                    }

                }
   
                
                
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
                
                
                

                
            } catch (Exception ex) {
                System.out.println("failed to check for matches");
                System.err.println(ex.getMessage());
            }
                

            // dit heb ik gecomment omdat ik een error kreeg dat ik niet iets
            // kon invoeren nadat de connectie is gesloten
            //matchCheckConnection.close();

        } catch (Exception ex) {
            System.out.println("Failed to insert data into the database ");
            System.err.println(ex.getMessage());
        }

    }

    /**
     *
     * @param query the query that will retrieve the required info from the
     * database
     * @return this will return a instance of the lostluggage class
     */
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

        // matchCheckConnection.close();


        } catch (Exception ex) {
            System.out.println("failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }


        // this method makes a pdf with all the user info
        return LostInfo;
    }

    /**
     *
     * @param query  the query that will retrieve the required info from the
     * database
     * @return this will return a instance of the foundluggage class
     */
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




