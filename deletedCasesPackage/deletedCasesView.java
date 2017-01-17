/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletedCasesPackage;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import prototypefys.HomeScreen;
import prototypefys.Rootpane;
import prototypefys.adminScherm;
import prototypefys.matchInformatie;

/**
 *
 * @author Koen Hengsdijk
 */
public class deletedCasesView {

    
    
    
    public deletedCasesView() {    
    }
    
    private static final Rootpane basisPane = new Rootpane();

    private static final HomeScreen thuisScherm = new HomeScreen();
    private static final HBox mainmenu = thuisScherm.maakhomescreen();
    private static final matchInformatie matchinfo = new matchInformatie();
    private Database db = new Database();

    private ObservableList<deletedLost> data;
    private TableView<deletedLost> catalogue = new TableView();

    private ObservableList<deletedFound> dataFound;
    private TableView<deletedFound> catalogueFound = new TableView();
    
      public Database CatalogueDatabase = new Database();

    // this boolean checks wether the lost or found luggage is displayed
    private boolean lostOrFound = true;
    
    public GridPane makeTableView() {

        // this stackpane contains the tableviews
        StackPane TablePane = new StackPane();

        GridPane root = new GridPane();
    
        root.getColumnConstraints().add(new ColumnConstraints(200));
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: white");
        
        GridPane Zoekscherm = new GridPane();
        Zoekscherm.setPadding(new Insets(25, 25, 25, 25));
        Zoekscherm.setHgap(10);
        Zoekscherm.setVgap(10);
        Zoekscherm.setStyle("-fx-base:darkred;-fx-border-color:darkred");
        Zoekscherm.setAlignment(Pos.CENTER);
        Zoekscherm.setPrefSize(250, 280);
        Zoekscherm.setMaxSize(250, 280);


        StackPane EmptyPane = new StackPane();
        EmptyPane.setPrefSize(250, 150);

        StackPane EmptyPane2 = new StackPane();
        EmptyPane2.setPrefSize(50, 50);
        TextField tekst = new TextField();
        tekst.setStyle("-fx-background-color: white;-fx-border-color:darkred");
        tekst.setPromptText("Search...");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 12, 5, 12));
        hbox.setSpacing(10);

        ImageView Corendon = new ImageView("/resources/CorendonAirlines.png");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);
        
        LostLuggageTable("select lostluggage.lostID, lostluggage.ownerid, "
            + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
            + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
            + "lostluggage.airport, lostluggage.itemname,"
            + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
            + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
            + "inner join luggageowner "
            + "on lostluggage.ownerid = luggageowner.ownerid where destroyed = 1");
        
        catalogue.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        
        catalogueFound.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TablePane.getChildren().addAll(catalogue, catalogueFound);
        catalogueFound.setVisible(false);

        root.add(TablePane, 2, 3, 2, 3);
        
        
        root.add(Corendon, 0, 1, 10, 1);
        
         // this observable list contains search options for a combobo
        ObservableList<String> LostOptions = FXCollections.observableArrayList(
            "lostid",
            "ownerid",
            "first name",
            "insertion",
            "last name",
            "labelnr",
            "flightnumber",
            "destination",
            "airportname",
            "item name",
            "brand",
            "color",
            "description",
            "date lost",
            "time lost",
            "status"
        );

        ObservableList<String> FoundOptions = FXCollections.observableArrayList(
            "foundID",
            "labelnr",
            "ownerID",
            "flightnumber",
            "first name",
            "insertion",
            "last name",
            "airport name",
            "destination",
            "item name",
            "brand",
            "color",
            "description",
            "date found",
            "time found",
            "status");

        // this combobox is used to select a search condition in the table
        ComboBox comboBox = new ComboBox(LostOptions);
        comboBox.setMinSize(150, 20);
        comboBox.setStyle("-fx-background-color: white; "
            + "-fx-base:white; -fx-border-color:darkred"
        );
        comboBox.getSelectionModel().selectFirst();
        
        ComboBox comboBoxFound = new ComboBox(FoundOptions);
        comboBoxFound.setMinSize(150, 20);
        comboBoxFound.setStyle("-fx-background-color: white; "
            + "-fx-border-color:darkred; -fx-base:white;");
        comboBoxFound.getSelectionModel().selectFirst();

        StackPane comboContainer = new StackPane();
        comboContainer.setPrefSize(150, 25);
        comboContainer.getChildren().addAll(comboBox, comboBoxFound);
        comboBoxFound.setVisible(false);

        // this button will search through the table
        Button zoekTabel = new Button("Search");
        zoekTabel.setMinSize(70, 20);
        zoekTabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (lostOrFound) {
                    // the selected value in the combobox will be converted to a string
                    String output = (String) comboBox.getValue();
                    // the entered search condition will be converted to a string
                    String zoekConditie = (String) tekst.getText();
                    
                    
                    if ("ownerid".equals(output)) {
                        output = "lostluggage.ownerid";
                    }
                    
                    if ("first name".equals(output)) {
                        output = "firstname";
                    }
                    if ("last name".equals(output)) {
                        output = "lastname";
                    }
                    
                    if ("flightnumber".equals(output)) {
                        output = "flightr";
                    }
                    
                    if ("airportname".equals(output)) {
                        output = "airport";
                    }
                    
                    if ("item name".equals(output)) {
                        output = "itemname";
                    }
                    if ("date lost".equals(output)) {
                        output = "`date lost`";
                    }
                    if ("time lost".equals(output)) {
                        output = "timeLost";
                    }
                    
                    
                    // if the color option is selected it will be changed here
                    // because the name in de database is different
                    if ("color".equals(output)) {
                        output = "colors";
                    }
                    

                    catalogue.getItems().clear();
                    catalogue.getColumns().clear();

                    LostLuggageTable(
                        "select lostluggage.lostID, lostluggage.ownerid, "
                        + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
                        + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
                        + "lostluggage.airport, lostluggage.itemname,"
                        + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
                        + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
                        + "inner join luggageowner "
                        + "on lostluggage.ownerid = luggageowner.ownerid "
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "%' and destroyed = 1");

                } else {

                    // the selected value in the combobox will be converted to a string
                    String output = (String) comboBoxFound.getValue();
                    // the entered search condition will be converted to a string
                    String zoekConditie = (String) tekst.getText();
                   
                    if ("flightnumber".equals(output)) {
                        output = "flightnr";
                    }
                    
                    if ("first name".equals(output)) {
                        output = "ownerName";
                    }
                    
                    if ("last name".equals(output)) {
                        output = "lastname";
                    }
                    
                    if ("airport name".equals(output)) {
                        output = "airport";
                    }
                    
                    if ("item name".equals(output)) {
                        output = "itemname";
                    }
                    
                                   

                    // if the color option is selected it will be changed here
                    // because the name in de database is different
                    if ("color".equals(output)) {
                        output = "colors";
                    }
                    
                    if ("date found".equals(output)) {
                        output = "dateFound";
                    }
                    
                    if ("time found".equals(output)) {
                        output = "timeFound";
                    }
                    
                    

                    catalogue.getItems().clear();
                    catalogue.getColumns().clear();
                    FoundLuggageTable("SELECT * FROM foundluggage "
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "% and destroyed = 1'");

                }
            }
        });
        
        
        
        // this button will display the table with found luggage
        Button showFound = new Button("Show deleted cases \nthat were found");
        showFound.setMinSize(150, 40);
        showFound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                // the table with found luggage will be made visible
                catalogueFound.setVisible(true);
                // the table with lost luggage wil be made invisible
                catalogue.setVisible(false);
                // the boolean will be set to false so that the rest of the 
                // programm knows the found luggage is displayed
                lostOrFound = false;
                // this query will select all the data from the database
                FoundLuggageTable("Select * FROM foundluggage where destroyed = 1");
               
                comboBoxFound.setVisible(true);
                comboBox.setVisible(false);
            }
        });
        
        

        Button showLost = new Button("Show deleted cases \n that were lost");
        showLost.setMinSize(150, 40);
        showLost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                catalogue.setVisible(true);
                catalogueFound.setVisible(false);
                LostLuggageTable("select lostluggage.lostID, lostluggage.ownerid, "
                    + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
                    + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
                    + "lostluggage.airport, lostluggage.itemname,"
                    + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
                    + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
                    + "inner join luggageowner"
                    + " on lostluggage.ownerid = luggageowner.ownerid where destroyed = 1");
                // LostLuggageTable("Select * FROM lostluggage");
                lostOrFound = true;
                comboBoxFound.setVisible(false);
                comboBox.setVisible(true);

            }
        });
        
         // this button will return to the main meny
        Button buttonMainMenu = new Button("return to \nadminscreen");
        //buttonCurrent.setPrefSize(90, 50);
        buttonMainMenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                 adminScherm scherm6 = new adminScherm();
                StackPane adminScherm = scherm6.maakAdminScherm();
                basisPane.addnewpane(adminScherm);
            }
        });
        buttonMainMenu.setPrefSize(120, 60);
        
        
         hbox.getChildren().addAll(buttonMainMenu);

        // all the buttons are added here
        HBox tabelKnoppen = new HBox();
        tabelKnoppen.getChildren().addAll(zoekTabel, showFound, showLost);
        tabelKnoppen.setSpacing(10);
        Zoekscherm.add(tekst, 1, 1);
        Zoekscherm.add(comboContainer, 1, 0);
   
        Zoekscherm.add(showFound, 1, 4);
     
        Zoekscherm.add(showLost, 1, 6);
     
        Zoekscherm.add(tabelKnoppen, 1, 2);
        root.add(EmptyPane, 0, 1);
        root.add(Zoekscherm, 0, 3);
        root.add(hbox, 0, 2);
        root.add(EmptyPane2, 1, 1);
        
        
        return root;
    }
    
     // this is the table with the found luggage
    public void FoundLuggageTable(String query) {
        catalogueFound.setEditable(true);
        catalogueFound.setMinWidth(1500);
        // de table colums are made here
        TableColumn<deletedFound, Integer> caseidColumn = new TableColumn<>("foundID");
        caseidColumn.setCellValueFactory(new PropertyValueFactory<>("caseid"));

        TableColumn<deletedFound, Integer> labelnrColumn = new TableColumn<>("labelnr");
        labelnrColumn.setCellValueFactory(new PropertyValueFactory<>("labelnr"));

        TableColumn<deletedFound, Integer> owneridColumn = new TableColumn<>("ownerID");
        owneridColumn.setCellValueFactory(new PropertyValueFactory<>("ownerid"));

        TableColumn<deletedFound, Integer> flightnrColumn = new TableColumn<>("flightnumber");
        flightnrColumn.setCellValueFactory(new PropertyValueFactory<>("flightnr"));

        TableColumn<deletedFound, String> firstNameColumn = new TableColumn<>("first name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<deletedFound, String> insertionColumn = new TableColumn<>("insertion");
        insertionColumn.setCellValueFactory(new PropertyValueFactory<>("insertion"));

        TableColumn<deletedFound, String> lastNameColumn = new TableColumn<>("last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<deletedFound, String> airportColumn = new TableColumn<>("airport name");
        airportColumn.setCellValueFactory(new PropertyValueFactory<>("airport"));

        TableColumn<deletedFound, String> destinationColumn = new TableColumn<>("destination");
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<deletedFound, String> itemnameColumn = new TableColumn<>("item name");
        itemnameColumn.setCellValueFactory(new PropertyValueFactory<>("itemname"));

        TableColumn<deletedFound, String> brandColumn = new TableColumn<>("brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<deletedFound, String> colorsColumn = new TableColumn<>("colors");
        colorsColumn.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<deletedFound, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<deletedFound, String> dateFoundColumn = new TableColumn<>("date found");
        dateFoundColumn.setCellValueFactory(new PropertyValueFactory<>("dateFound"));
        
        TableColumn<deletedFound, String> timeFoundColumn = new TableColumn<>("time found");
        timeFoundColumn.setCellValueFactory(new PropertyValueFactory<>("timeFound"));

        TableColumn<deletedFound, String> statusColumn = new TableColumn<>("status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {

            // a connection is made
            Connection catalogueConnect = db.getConnection();
            Statement statement = catalogueConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            // this while loop gets data in the ovservable list
            dataFound = FXCollections.observableArrayList();
            while (TableData.next()) {
                //Iterate Row
                // the database data is added to the observable list
                dataFound.add(new deletedFound(TableData.getInt(1), TableData.getInt(2), TableData.getInt(3),
                    TableData.getInt(4), TableData.getString(5), TableData.getString(6),
                    TableData.getString(7), TableData.getString(9),
                    TableData.getString(8), TableData.getString(10),
                    TableData.getString(11), TableData.getString(12),
                    TableData.getString(13), TableData.getString(14), TableData.getString(15), TableData.getString(16)));

            }
           
            System.out.println(query);
            catalogueFound.setFixedCellSize(30.0);
            // the table is cleared
            catalogueFound.getItems().clear();
            catalogueFound.getColumns().clear();
            // the data is added to the table
            catalogueFound.setItems(dataFound);
            //the columns are added to the table
            catalogueFound.getColumns().addAll(caseidColumn, labelnrColumn, owneridColumn,
                flightnrColumn, firstNameColumn, insertionColumn, lastNameColumn, 
                destinationColumn, airportColumn, itemnameColumn,
                brandColumn, colorsColumn, descriptionColumn, dateFoundColumn, 
                timeFoundColumn, statusColumn);
        } catch (Exception ex) {
            System.out.println("Exception 2 ");
            System.out.println(ex);
        }

    }
    
    public void LostLuggageTable(String query) {

        // de table colums are made here
        TableColumn<deletedLost, Integer> caseidColumn = new TableColumn<>("lostID");
        caseidColumn.setCellValueFactory(new PropertyValueFactory<>("caseid"));
        //caseidColumn.setMaxWidth(100);
        
        TableColumn<deletedLost, Integer> owneridColumn = new TableColumn<>("ownerid");
        owneridColumn.setCellValueFactory(new PropertyValueFactory<>("ownerid"));

        TableColumn<deletedLost, String> firstNameColumn = new TableColumn<>("first name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<deletedLost, String> insertionColumn = new TableColumn<>("insertion");
        insertionColumn.setCellValueFactory(new PropertyValueFactory<>("insertion"));

        TableColumn<deletedLost, String> lastNameColumn = new TableColumn<>("last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<deletedLost, Integer> labelnrColumn = new TableColumn<>("labelnr");
        labelnrColumn.setCellValueFactory(new PropertyValueFactory<>("labelnr"));

        TableColumn<deletedLost, Integer> flightnrColumn = new TableColumn<>("flightnumber");
        flightnrColumn.setCellValueFactory(new PropertyValueFactory<>("flightnr"));

        TableColumn<deletedLost, String> destinationColumn = new TableColumn<>("destination");
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<deletedLost, String> airportColumn = new TableColumn<>("airport name");
        airportColumn.setCellValueFactory(new PropertyValueFactory<>("airport"));
        airportColumn.setPrefWidth(80);
        TableColumn<deletedLost, String> itemnameColumn = new TableColumn<>("item name");
        itemnameColumn.setCellValueFactory(new PropertyValueFactory<>("itemname"));

        TableColumn<deletedLost, String> brandColumn = new TableColumn<>("brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<deletedLost, String> colorsColumn = new TableColumn<>("colour");
        colorsColumn.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<deletedLost, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<deletedLost, String> dateLostColumn = new TableColumn<>("date lost");
        dateLostColumn.setCellValueFactory(new PropertyValueFactory<>("dateLost"));
        
         TableColumn<deletedLost, String> timeLostColumn = new TableColumn<>("time lost");
        timeLostColumn.setCellValueFactory(new PropertyValueFactory<>("timeLost"));

        
        TableColumn<deletedLost, String> statusColumn = new TableColumn<>("status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {

            // a connection is made
            Connection catalogueConnect = db.getConnection();
            Statement statement = catalogueConnect.createStatement();
            System.out.println(query);
            ResultSet TableData = statement.executeQuery(query);

            // this while loop gets data in the ovservable list
            data = FXCollections.observableArrayList();
            while (TableData.next()) {
                //Iterate Row
                // the data will be added to the arraylist
                data.add(new deletedLost(TableData.getInt(1), TableData.getInt(2),
                    TableData.getString(3), TableData.getString(4), TableData.getString(5), TableData.getInt(6),
                    TableData.getInt(7), TableData.getString(8), TableData.getString(9),
                    TableData.getString(10), TableData.getString(11),
                    TableData.getString(12), TableData.getString(13), TableData.getString(14),
                    TableData.getString(15), TableData.getString(16)));

            }
        
            // the tableview will be emptied
            catalogue.setFixedCellSize(30.0);
            catalogue.getItems().clear();
            catalogue.getColumns().clear();

            //System.out.println(data);
            catalogue.setItems(data);
            catalogue.getColumns().addAll(caseidColumn, owneridColumn,
                firstNameColumn, insertionColumn, lastNameColumn, labelnrColumn,
                destinationColumn, flightnrColumn, airportColumn, itemnameColumn, brandColumn,
                colorsColumn, descriptionColumn, dateLostColumn, timeLostColumn, statusColumn);
        } catch (Exception ex) {
            System.out.println("exception 2 ");
            System.out.println(ex);
        }

    }

    
    
    
}
