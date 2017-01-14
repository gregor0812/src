package cataloog;

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
import javafx.scene.control.Alert;
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

/**
 *
 * @author Koen Hengsdijk
 */
public class BagageCatalogue {

    private static final Rootpane basisPane = new Rootpane();

    private static final HomeScreen thuisScherm = new HomeScreen();
    private static final HBox mainmenu = thuisScherm.maakhomescreen();
    private static final EditForm LostLuggageEdit = new EditForm();
    private Database db = new Database();

    private ObservableList<LostLuggage> data;
    private TableView<LostLuggage> catalogue = new TableView();

    private ObservableList<FoundLuggage> dataFound;
    private TableView<FoundLuggage> catalogueFound = new TableView();

    public BagageCatalogue() {
    }

    public Database CatalogueDatabase = new Database();

    // this boolean checks wether the lost or found luggage is displayed
    private boolean lostOrFound = true;
    
 

    public GridPane MaakCatalogue() {

        // this stackpane contains the tableviews
        StackPane TablePane = new StackPane();

        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_LEFT);
        root.getColumnConstraints().add(new ColumnConstraints(200));
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: #baf9ff");
        
        GridPane Zoekscherm = new GridPane();
        Zoekscherm.setPadding(new Insets(25, 25, 25, 25));
        Zoekscherm.setHgap(10);
        Zoekscherm.setVgap(10);
        Zoekscherm.setStyle("-fx-base:darkred;-fx-border-color:darkred");
        Zoekscherm.setAlignment(Pos.CENTER);
        Zoekscherm.setPrefSize(250, 430);
        Zoekscherm.setMaxSize(250, 430);


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

        root.add(Corendon, 0, 1, 10, 1);

        // the standard tableview is added here
        LostLuggageTable("select lostluggage.lostID, lostluggage.ownerid, "
            + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
            + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
            + "lostluggage.airport, lostluggage.itemname,"
            + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
            + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
            + "inner join luggageowner "
            + "on lostluggage.ownerid = luggageowner.ownerid");
        //root.add(catalogue, 2, 3, 2, 3);

        catalogue.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        FoundLuggageTable("Select * FROM foundluggage");
        catalogueFound.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TablePane.getChildren().addAll(catalogue, catalogueFound);
        catalogueFound.setVisible(false);

        root.add(TablePane, 2, 3, 2, 3);

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
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "%'");

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
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "%'");

                }
            }
        });
        
        Button FoundWithouthLabel = new Button("show found luggage \n with no label");
        FoundWithouthLabel.setMinSize(150, 40);
        FoundWithouthLabel.setOnAction(new EventHandler<ActionEvent>() {
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
                FoundLuggageTable("Select * FROM foundluggage where labelnr = 0");
               
                comboBoxFound.setVisible(true);
                comboBox.setVisible(false);
            }
        });
        
        // this button will display the table with found luggage
        Button showFound = new Button("show found luggage");
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
                FoundLuggageTable("Select * FROM foundluggage");
               
                comboBoxFound.setVisible(true);
                comboBox.setVisible(false);
            }
        });
        
        

        Button showLost = new Button("Show lost luggage");
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
                    + " on lostluggage.ownerid = luggageowner.ownerid");
                // LostLuggageTable("Select * FROM lostluggage");
                lostOrFound = true;
                comboBoxFound.setVisible(false);
                comboBox.setVisible(true);

            }
        });

        Button deleteCase = new Button("Delete case");
        deleteCase.setMinSize(150, 40);
        deleteCase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (lostOrFound) {

                    try {

                        Integer person = catalogue.getSelectionModel().getSelectedItem().getCaseid();
                        String lost = "lostluggage";
                        String ID = "lostID";
                        DeleteCase(person, lost, ID);
                        LostLuggageTable("select lostluggage.lostID, lostluggage.ownerid, "
                    + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
                    + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
                    + "lostluggage.airport, lostluggage.itemname,"
                    + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
                    + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
                    + "inner join luggageowner"
                    + " on lostluggage.ownerid = luggageowner.ownerid");

                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("delete case");
                        alert.setContentText("Select a case to delete");

                        alert.showAndWait();
                    }

                } else {

                    try {

                        Integer person = catalogueFound.getSelectionModel().getSelectedItem().getCaseid();
                        String found = "foundluggage";
                        String ID = "foundID";
                        DeleteCase(person, found, ID);
                        FoundLuggageTable("Select * FROM foundluggage");

                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("Delete case");
                        alert.setContentText("Select a case to delete");

                        alert.showAndWait();
                    }
                }
            }
        });

        // this button will return to the main meny
        Button buttonMainMenu = new Button("Main Menu");
        //buttonCurrent.setPrefSize(90, 50);
        buttonMainMenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonMainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                basisPane.addnewpane(mainmenu);
            }
        });
        buttonMainMenu.setPrefSize(100, 20);

        // this button will edit the case
        Button buttonViewCase = new Button("Edit Case");
        buttonViewCase.setMinSize(150, 40);
        buttonViewCase.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonViewCase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // the boolean will determine wether the lost or found luggage is selected
                if (lostOrFound) {
                    // a object of the person class wil be made with the selected values
                    LostLuggage person = catalogue.getSelectionModel().getSelectedItem();

                    if (person != null) {
                        // an editform will be made with the person class and the selected values
                        GridPane Editform = LostLuggageEdit.MakeEdit(person);
                        // the editform is added to the screen
                        basisPane.addnewpane(Editform);
                    } else {
                        // an alert that will be shown if nothing is selected
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("edit case");
                        alert.setContentText("Select a case to edit");

                        alert.showAndWait();
                    }

                } else {
                    // a object of the person class wil be made with the selected values
                    FoundLuggage EditFound = catalogueFound.getSelectionModel().getSelectedItem();

                    // if no value is selected then a warning will be displayed
                    if (EditFound != null) {
                        // an editform will be made with the person class and the selected values
                        GridPane Editform = LostLuggageEdit.MakeEdit(EditFound);
                        // the editform is added to the screen
                        basisPane.addnewpane(Editform);
                    } else {
                        // an alert that will be shown if nothing is selected
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("edit case");
                        alert.setContentText("Select a case to edit");

                        alert.showAndWait();
                    }

                }

            }
        });
        
        

        hbox.getChildren().addAll(buttonMainMenu);

        // all the buttons are added here
        HBox tabelKnoppen = new HBox();
        tabelKnoppen.getChildren().addAll(zoekTabel, showFound, showLost, deleteCase);
        tabelKnoppen.setSpacing(10);
        Zoekscherm.add(tekst, 1, 1);
        Zoekscherm.add(comboContainer, 1, 0);
        Zoekscherm.add(buttonViewCase, 1, 3);
        Zoekscherm.add(showFound, 1, 4);
        Zoekscherm.add(FoundWithouthLabel, 1, 5);
        Zoekscherm.add(showLost, 1, 6);
        Zoekscherm.add(deleteCase, 1, 7 );
        Zoekscherm.add(tabelKnoppen, 1, 2);
        root.add(EmptyPane, 0, 1);
        root.add(Zoekscherm, 0, 3);
        root.add(hbox, 0, 2);
        root.add(EmptyPane2, 1, 1);

        //root.add(catalogue, 2, 3, 2, 3);
        return root;

    }

    public void LostLuggageTable(String query) {

        // de table colums are made here
        TableColumn<LostLuggage, Integer> caseidColumn = new TableColumn<>("lostID");
        caseidColumn.setCellValueFactory(new PropertyValueFactory<>("caseid"));
        //caseidColumn.setMaxWidth(100);
        
        TableColumn<LostLuggage, Integer> owneridColumn = new TableColumn<>("ownerid");
        owneridColumn.setCellValueFactory(new PropertyValueFactory<>("ownerid"));

        TableColumn<LostLuggage, String> firstNameColumn = new TableColumn<>("first name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<LostLuggage, String> insertionColumn = new TableColumn<>("insertion");
        insertionColumn.setCellValueFactory(new PropertyValueFactory<>("insertion"));

        TableColumn<LostLuggage, String> lastNameColumn = new TableColumn<>("last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<LostLuggage, Integer> labelnrColumn = new TableColumn<>("labelnr");
        labelnrColumn.setCellValueFactory(new PropertyValueFactory<>("labelnr"));

        TableColumn<LostLuggage, Integer> flightnrColumn = new TableColumn<>("flightnumber");
        flightnrColumn.setCellValueFactory(new PropertyValueFactory<>("flightnr"));

        TableColumn<LostLuggage, String> destinationColumn = new TableColumn<>("destination");
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<LostLuggage, String> airportColumn = new TableColumn<>("airport name");
        airportColumn.setCellValueFactory(new PropertyValueFactory<>("airport"));
        airportColumn.setPrefWidth(80);
        TableColumn<LostLuggage, String> itemnameColumn = new TableColumn<>("item name");
        itemnameColumn.setCellValueFactory(new PropertyValueFactory<>("itemname"));

        TableColumn<LostLuggage, String> brandColumn = new TableColumn<>("brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<LostLuggage, String> colorsColumn = new TableColumn<>("colour");
        colorsColumn.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<LostLuggage, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<LostLuggage, String> dateLostColumn = new TableColumn<>("date lost");
        dateLostColumn.setCellValueFactory(new PropertyValueFactory<>("dateLost"));
        
         TableColumn<LostLuggage, String> timeLostColumn = new TableColumn<>("time lost");
        timeLostColumn.setCellValueFactory(new PropertyValueFactory<>("timeLost"));

        
        TableColumn<LostLuggage, String> statusColumn = new TableColumn<>("status");
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
                data.add(new LostLuggage(TableData.getInt(1), TableData.getInt(2),
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

    // this is the table with the found luggage
    public void FoundLuggageTable(String query) {
        catalogueFound.setEditable(true);
        catalogueFound.setMinWidth(1500);
        // de table colums are made here
        TableColumn<FoundLuggage, Integer> caseidColumn = new TableColumn<>("foundID");
        caseidColumn.setCellValueFactory(new PropertyValueFactory<>("caseid"));

        TableColumn<FoundLuggage, Integer> labelnrColumn = new TableColumn<>("labelnr");
        labelnrColumn.setCellValueFactory(new PropertyValueFactory<>("labelnr"));

        TableColumn<FoundLuggage, Integer> owneridColumn = new TableColumn<>("ownerID");
        owneridColumn.setCellValueFactory(new PropertyValueFactory<>("ownerid"));

        TableColumn<FoundLuggage, Integer> flightnrColumn = new TableColumn<>("flightnumber");
        flightnrColumn.setCellValueFactory(new PropertyValueFactory<>("flightnr"));

        TableColumn<FoundLuggage, String> firstNameColumn = new TableColumn<>("first name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<FoundLuggage, String> insertionColumn = new TableColumn<>("insertion");
        insertionColumn.setCellValueFactory(new PropertyValueFactory<>("insertion"));

        TableColumn<FoundLuggage, String> lastNameColumn = new TableColumn<>("last name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<FoundLuggage, String> airportColumn = new TableColumn<>("airport name");
        airportColumn.setCellValueFactory(new PropertyValueFactory<>("airport"));

        TableColumn<FoundLuggage, String> destinationColumn = new TableColumn<>("destination");
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<FoundLuggage, String> itemnameColumn = new TableColumn<>("item name");
        itemnameColumn.setCellValueFactory(new PropertyValueFactory<>("itemname"));

        TableColumn<FoundLuggage, String> brandColumn = new TableColumn<>("brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<FoundLuggage, String> colorsColumn = new TableColumn<>("colors");
        colorsColumn.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<FoundLuggage, String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<FoundLuggage, String> dateFoundColumn = new TableColumn<>("date found");
        dateFoundColumn.setCellValueFactory(new PropertyValueFactory<>("dateFound"));
        
        TableColumn<FoundLuggage, String> timeFoundColumn = new TableColumn<>("time found");
        timeFoundColumn.setCellValueFactory(new PropertyValueFactory<>("timeFound"));

        TableColumn<FoundLuggage, String> statusColumn = new TableColumn<>("status");
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
                dataFound.add(new FoundLuggage(TableData.getInt(1), TableData.getInt(2), TableData.getInt(3),
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
            System.out.println("exception 2 ");
            System.out.println(ex);
        }

    }

    // a method to delete a case
    public void DeleteCase(int id, String table, String whichID) {

        try {
            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            // this query will delete the selected row from the database
            statement.executeUpdate("DELETE FROM `corendon`. " + table + " WHERE " + whichID
                + " ='" + id + "';");

        } catch (Exception ex) {
            System.out.println("failed to delete case ");
            System.out.println(ex);
        }

    }

}
