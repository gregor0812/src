package cataloog;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
import prototypefys.matchInformatie;

/**
 *
 * @author IS-109-2
 */
public class BagageCatalogue {

    private static final Rootpane basisPane = new Rootpane();

    private static final HomeScreen thuisScherm = new HomeScreen();
    private static final HBox mainmenu = thuisScherm.maakhomescreen();
    private static final EditForm LostLuggageEdit = new EditForm();
    private static final matchInformatie matchinfo = new matchInformatie();
    private Database db = new Database();

    private ObservableList<LostLuggage> data;
    private TableView<LostLuggage> catalogue = new TableView();

    private ObservableList<FoundLuggage> dataFound;
    private TableView<FoundLuggage> catalogueFound = new TableView();

    private ObservableList<LostLuggage> potentialDataLost
        = FXCollections.observableArrayList();
    private TableView<LostLuggage> potentialMatchLost = new TableView();

    private ObservableList<FoundLuggage> potentialDataFound
        = FXCollections.observableArrayList();
    private TableView<FoundLuggage> potentialMatchFound = new TableView();

    /**
     * the standard constructor
     */
    public BagageCatalogue() {
    }

    /**
     * a database instance is made
     */
    public Database CatalogueDatabase = new Database();

    // this boolean checks wether the lost or found luggage is displayed
    private boolean lostOrFound = true;

    /**
     *
     * @return this method will return a gridpane that can be added to 
     * the primary stage
     */
    public GridPane MaakCatalogue() {

        // this stackpane contains the tableviews
        StackPane TablePane = new StackPane();

        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_LEFT);
        root.getColumnConstraints().add(new ColumnConstraints(200));
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setStyle("-fx-background-color: white");

        GridPane Zoekscherm = new GridPane();
        Zoekscherm.setPadding(new Insets(25, 25, 25, 25));
        Zoekscherm.setHgap(10);
        Zoekscherm.setVgap(10);
        Zoekscherm.setStyle("-fx-base:darkred;-fx-border-color:darkred");
        Zoekscherm.setAlignment(Pos.CENTER);
        Zoekscherm.setPrefSize(250, 460);
        Zoekscherm.setMaxSize(250, 460);

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
            + "on lostluggage.ownerid = luggageowner.ownerid where destroyed = 0");
        //root.add(catalogue, 2, 3, 2, 3);

        catalogue.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        FoundLuggageTable("Select * FROM foundluggage where destroyed = 0");
        catalogueFound.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TablePane.getChildren().addAll(catalogue, catalogueFound);
        catalogueFound.setVisible(false);

        TablePane.setAlignment(Pos.TOP_CENTER);
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
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "%' and destroyed = 0");

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
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "% and destroyed = 0'");

                }
            }
        });

        Button FoundWithouthLabel = new Button("Show found luggage\nwith no label");
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
                FoundLuggageTable("Select * FROM foundluggage where labelnr = 0 and destroyed = 0");

                comboBoxFound.setVisible(true);
                comboBox.setVisible(false);
            }
        });

        // this button will display the table with found luggage
        Button showFound = new Button("Show found luggage");
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
                FoundLuggageTable("Select * FROM foundluggage where destroyed = 0");

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
                    + " on lostluggage.ownerid = luggageowner.ownerid where destroyed = 0");
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
                            + " on lostluggage.ownerid = luggageowner.ownerid where destroyed = 0");

                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setHeaderText("Delete case");
                        alert.setContentText("Select a case to delete");

                        alert.showAndWait();
                    }

                } else {

                    try {

                        Integer person = catalogueFound.getSelectionModel().getSelectedItem().getCaseid();
                        String found = "foundluggage";
                        String ID = "foundID";
                        DeleteCase(person, found, ID);
                        FoundLuggageTable("Select * FROM foundluggage where destroyed = 0");

                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
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
                        alert.setTitle("Error");
                        alert.setHeaderText("Edit case");
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
                        alert.setTitle("Error");
                        alert.setHeaderText("Edit case");
                        alert.setContentText("Select a case to edit");

                        alert.showAndWait();
                    }

                }

            }
        });

        Button viewMatch = new Button("view match \n information");
        viewMatch.setMinSize(150, 40);
        viewMatch.setStyle("-fx-base:darkred;-fx-border-color:white");
        viewMatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // the boolean will determine wether the lost or found luggage is selected
                if (lostOrFound) {

                    // a object of the person class wil be made with the selected values
                    LostLuggage person = catalogue.getSelectionModel().getSelectedItem();

                    if (person != null) {

                        if ("matched".equals(catalogue.getSelectionModel()
                            .getSelectedItem().getStatus())) {

                            // an viewmatch form will be made with the person
                            //class and the selected values
                            FoundLuggage FoundLuggageMatch = foundLuggageMatchInfo(catalogue.getSelectionModel()
                                .getSelectedItem().getLabelnr());

                            GridPane infoScherm = matchinfo.matchInfo(person, FoundLuggageMatch);
                            // the editform is added to the screen
                            basisPane.addnewpane(infoScherm);
                        } else {
                            // an alert that will be shown if nothing is selected
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("error");
                            alert.setHeaderText("view match");
                            alert.setContentText("this case is not yet matched");

                            alert.showAndWait();
                        }

                    } else {
                        // an alert that will be shown if nothing is selected
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("view match");
                        alert.setContentText("Select a matched case to view");

                        alert.showAndWait();
                    }

                } else {

                    // a object of the person class wil be made with the selected values
                    FoundLuggage FoundMatchInfo
                        = catalogueFound.getSelectionModel().getSelectedItem();

                    // if no value is selected then a warning will be displayed
                    if (FoundMatchInfo != null) {

                        if ("matched".equals(catalogueFound.getSelectionModel()
                            .getSelectedItem().getStatus())) {

                            LostLuggage LostLuggageMatch = lostLuggageMatchInfo(catalogueFound.getSelectionModel()
                                .getSelectedItem().getLabelnr());

                            // an view match form will be made with the person class and 
                            //the selected values
                            GridPane infoScherm = matchinfo.matchInfo(LostLuggageMatch, FoundMatchInfo);
                            // the editform is added to the screen
                            basisPane.addnewpane(infoScherm);

                        } else {
                            // an alert that will be shown if nothing is selected
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("error");
                            alert.setHeaderText("view match");
                            alert.setContentText("this case is not yet matched");

                            alert.showAndWait();
                        }

                    } else {
                        // an alert that will be shown if nothing is selected
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("edit case");
                        alert.setContentText("Select a matched case to view");

                        alert.showAndWait();
                    }

                }

            }
        });

        Button clearPotential = new Button("clear the potential \nmatch tables");
        clearPotential.setPrefSize(180, 60);
        clearPotential.setStyle("-fx-border-color:white;-fx-base:darkred;");
        root.add(clearPotential, 3, 4, 1, 2);
        clearPotential.setTranslateX(350);
        clearPotential.setTranslateY(150);
        clearPotential.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                potentialDataLost.clear();
                potentialDataFound.clear();
            }
        });

        Button CreateMatch = new Button("create a new match");
        CreateMatch.setPrefSize(180, 60);
        CreateMatch.setStyle("-fx-border-color:white;-fx-base:darkred;");
        root.add(CreateMatch, 3, 4, 1, 2);
        CreateMatch.setTranslateX(750);
        CreateMatch.setTranslateY(150);
        CreateMatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (potentialDataLost.isEmpty() || potentialDataFound.isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("there is no luggage in one of the \n"
                        + "potential match tables");
                    alert.showAndWait();
                } else {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("are you sure you want to "
                        + " create this match?");

                    ButtonType confirm = new ButtonType("yes", ButtonBar.ButtonData.CANCEL_CLOSE);
                    ButtonType cancel = new ButtonType("cancel");
                    alert.getButtonTypes().setAll(confirm, cancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == confirm) {

                        LostLuggage lostMatch = potentialMatchLost.getItems().get(0);
                        FoundLuggage foundMatch = potentialMatchFound.getItems().get(0);

                        createMatch(lostMatch, foundMatch);
                    }

                }

            }
        });

        Button SelectPotentialMatch = new Button("select potential match");
        SelectPotentialMatch.setPrefSize(180, 60);
        SelectPotentialMatch.setStyle("-fx-border-color:white;-fx-base:darkred;");
        root.add(SelectPotentialMatch, 3, 4, 1, 2);
        SelectPotentialMatch.setTranslateX(550);
        SelectPotentialMatch.setTranslateY(150);
        SelectPotentialMatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (lostOrFound) {
                    if (potentialDataLost.isEmpty()) {

                        LostLuggage person = catalogue.getSelectionModel().getSelectedItem();
                        data.remove(data.get(catalogue.getSelectionModel().getSelectedIndex()));
                        potentialDataLost.add(person);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("you have already added a potential match\n "
                            + "please clear the potential match table if you wish to add \n"
                            + "a different case");
                        alert.showAndWait();
                    }
                } else if (potentialDataFound.isEmpty()) {

                    FoundLuggage person = catalogueFound.getSelectionModel().getSelectedItem();
                    if (person.getLabelnr() != 0) {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("this case had a labelnr \n  "
                            + "are you sure you want to manually match this case?");

                        ButtonType okButton = new ButtonType("add to table", ButtonBar.ButtonData.CANCEL_CLOSE);
                        ButtonType cancel = new ButtonType("cancel");
                        alert.getButtonTypes().setAll(okButton, cancel);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == okButton) {
                            dataFound.remove(dataFound.get(catalogueFound.getSelectionModel().getSelectedIndex()));
                            potentialDataFound.add(person);
                        }

                    } else {

                        dataFound.remove(dataFound.get(catalogueFound.getSelectionModel().getSelectedIndex()));
                        potentialDataFound.add(person);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("you have already added a potential match\n "
                        + "please clear the potential match table if you wish to add \n"
                        + "a different case");
                    alert.showAndWait();
                }
            }
        });
        
          // the colums for the potential match table are made here
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

        potentialMatchLost.getColumns().addAll(caseidColumn, owneridColumn,
            firstNameColumn, insertionColumn, lastNameColumn, labelnrColumn,
            destinationColumn, flightnrColumn, airportColumn, itemnameColumn, brandColumn,
            colorsColumn, descriptionColumn, dateLostColumn, timeLostColumn, statusColumn);

        StackPane PotentialLostMatchContainer = new StackPane();
        root.add(PotentialLostMatchContainer, 2, 5, 2, 3);
        PotentialLostMatchContainer.setStyle("-fx-border-color:black");
        PotentialLostMatchContainer.setMinHeight(60);
        PotentialLostMatchContainer.setAlignment(Pos.CENTER);
        PotentialLostMatchContainer.getChildren().add(potentialMatchLost);
        potentialMatchLost.setItems(potentialDataLost);
        potentialMatchLost.setMaxHeight(60);
        potentialMatchLost.setFixedCellSize(30.0);
        potentialMatchLost.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // the colums for the potential match table are made here
        TableColumn<FoundLuggage, Integer> caseidColumnFound = new TableColumn<>("foundID");
        caseidColumnFound.setCellValueFactory(new PropertyValueFactory<>("caseid"));

        TableColumn<FoundLuggage, Integer> labelnrColumnFound = new TableColumn<>("labelnr");
        labelnrColumn.setCellValueFactory(new PropertyValueFactory<>("labelnr"));

        TableColumn<FoundLuggage, Integer> owneridColumnFound = new TableColumn<>("ownerID");
        owneridColumnFound.setCellValueFactory(new PropertyValueFactory<>("ownerid"));

        TableColumn<FoundLuggage, Integer> flightnrColumnFound = new TableColumn<>("flightnumber");
        flightnrColumnFound.setCellValueFactory(new PropertyValueFactory<>("flightnr"));

        TableColumn<FoundLuggage, String> firstNameColumnFound = new TableColumn<>("first name");
        firstNameColumnFound.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<FoundLuggage, String> insertionColumnFound = new TableColumn<>("insertion");
        insertionColumnFound.setCellValueFactory(new PropertyValueFactory<>("insertion"));

        TableColumn<FoundLuggage, String> lastNameColumnFound = new TableColumn<>("last name");
        lastNameColumnFound.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<FoundLuggage, String> airportColumnFound = new TableColumn<>("airport name");
        airportColumnFound.setCellValueFactory(new PropertyValueFactory<>("airport"));

        TableColumn<FoundLuggage, String> destinationColumnFound = new TableColumn<>("destination");
        destinationColumnFound.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<FoundLuggage, String> itemnameColumnFound = new TableColumn<>("item name");
        itemnameColumnFound.setCellValueFactory(new PropertyValueFactory<>("itemname"));

        TableColumn<FoundLuggage, String> brandColumnFound = new TableColumn<>("brand");
        brandColumnFound.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<FoundLuggage, String> colorsColumnFound = new TableColumn<>("colors");
        colorsColumnFound.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<FoundLuggage, String> descriptionColumnFound = new TableColumn<>("description");
        descriptionColumnFound.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<FoundLuggage, String> dateFoundColumnFound = new TableColumn<>("date found");
        dateFoundColumnFound.setCellValueFactory(new PropertyValueFactory<>("dateFound"));

        TableColumn<FoundLuggage, String> timeFoundColumnFound = new TableColumn<>("time found");
        timeFoundColumnFound.setCellValueFactory(new PropertyValueFactory<>("timeFound"));

        TableColumn<FoundLuggage, String> statusColumnFound = new TableColumn<>("status");
        statusColumnFound.setCellValueFactory(new PropertyValueFactory<>("status"));

        potentialMatchFound.getColumns().addAll(caseidColumnFound, labelnrColumnFound, owneridColumnFound,
            flightnrColumnFound, firstNameColumnFound, insertionColumnFound, lastNameColumnFound,
            destinationColumnFound, airportColumnFound, itemnameColumnFound,
            brandColumnFound, colorsColumnFound, descriptionColumnFound, dateFoundColumnFound,
            timeFoundColumnFound, statusColumnFound);

        StackPane PotentialFoundMatchContainer = new StackPane();
        root.add(PotentialFoundMatchContainer, 2, 7, 2, 3);
        PotentialFoundMatchContainer.setStyle("-fx-border-color:blue");
        PotentialFoundMatchContainer.setMinHeight(60);
        PotentialFoundMatchContainer.setAlignment(Pos.BOTTOM_CENTER);
        PotentialFoundMatchContainer.setTranslateY(100);

        PotentialFoundMatchContainer.getChildren().add(potentialMatchFound);
        potentialMatchFound.setItems(potentialDataFound);
        potentialMatchFound.setMaxHeight(60);
        potentialMatchFound.setFixedCellSize(30.0);
        potentialMatchFound.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        hbox.getChildren().addAll(buttonMainMenu);

        // all the buttons are added here
        HBox tabelKnoppen = new HBox();
        tabelKnoppen.getChildren().addAll(zoekTabel);
        tabelKnoppen.setSpacing(10);
        Zoekscherm.add(tekst, 1, 1);
        Zoekscherm.add(comboContainer, 1, 0);
        Zoekscherm.add(buttonViewCase, 1, 3);
        Zoekscherm.add(showFound, 1, 4);
        Zoekscherm.add(FoundWithouthLabel, 1, 5);
        Zoekscherm.add(showLost, 1, 6);
        Zoekscherm.add(deleteCase, 1, 7);
        Zoekscherm.add(viewMatch, 1, 8);
        Zoekscherm.add(tabelKnoppen, 1, 2);
        root.add(EmptyPane, 0, 1);
        root.add(Zoekscherm, 0, 3, 1, 2);
        root.add(hbox, 0, 2);
        root.add(EmptyPane2, 1, 1);

        return root;

    }

    /**
     *
     * @param query this query will select all the info out of the database
     */
    public void LostLuggageTable(String query) {

        catalogue.setEditable(true);
        catalogue.setMinWidth(1500);
        catalogue.setMaxHeight(300);

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

    /**
     *
     * @param query this query will select all the info out of the database
     */
    public void FoundLuggageTable(String query) {
        catalogueFound.setEditable(true);
        catalogueFound.setMinWidth(1500);
        catalogueFound.setMaxHeight(300);
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


    /**
     *
     * @param id the number of the lost or found id
     * @param table the table that will be updated
     * @param whichID the lost or the found id
     */
    public void DeleteCase(int id, String table, String whichID) {

        try {
            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            // this query will delete the selected row from the database
            statement.executeUpdate("update " + table + " set destroyed = 1 "
                + "where " + whichID + " = " + id);

        } catch (Exception ex) {
            System.out.println("Failed to delete case ");
            System.out.println(ex);
        }

    }

    /**
     *
     * @param labelnr the labelnr of the selected case 
     * @return a lostlugga instance wil be returned containing the info of the 
     * selected case
     */
    public FoundLuggage foundLuggageMatchInfo(int labelnr) {

        String query = "select * from foundluggage where labelnr = " + labelnr;

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

    /**
     *
     * @param labelnr the labelnr of the selected case
     * @return a lostlugga instance wil be returned containing the info of the 
     * selected case
     */
    public LostLuggage lostLuggageMatchInfo(int labelnr) {

        String query = ("select lostluggage.lostID, lostluggage.ownerid, "
            + "luggageowner.firstname, luggageowner.insertion, luggageowner.lastname,"
            + "lostluggage.labelnr, lostluggage.flightr, lostluggage.destination, "
            + "lostluggage.airport, lostluggage.itemname,"
            + "lostluggage.brand, lostluggage.colors, lostluggage.description, "
            + "`date lost`, lostluggage.timeLost, lostluggage.status from lostluggage "
            + "inner join luggageowner"
            + " on lostluggage.ownerid = luggageowner.ownerid "
            + "where labelnr = " + labelnr);

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
        } catch (SQLException ex) {
            System.out.println("Failed to retrieve lost luggage matchinfo ");
            System.err.println(ex.getMessage());
        }

        return LostInfo;
    }

    /**
     *
     * @param lost a instance of the Lostluggage class containing the information
     * of the lostluggage the was selected
     * @param found a instance of the Lostluggage class containing the information
     * of the lostluggage the was selected
     */
    public void createMatch(LostLuggage lost, FoundLuggage found) {

        try {

            Connection matchCheckConnection = db.getConnection();
            Statement statement = matchCheckConnection.createStatement();

            String query = "Update Foundluggage SET labelnr = " + lost.getLabelnr()
                + ", ownerid = " + lost.getOwnerid() + ", flightnr = " + lost.getFlightnr()
                + ", ownername = '" + lost.getFirstName() + "' , insertion = '"
                + lost.getInsertion() + "' , lastname = '" + lost.getLastName()
                + "' , destination = '" + lost.getDestination() + "' , status = 'matched' " + "where foundID = "
                + found.getCaseid();

            String query2 = "update LostLuggage SET status = 'matched' WHERE labelnr = "
                + lost.getLabelnr();

            statement.executeUpdate(query);
            statement.executeUpdate(query2);

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("failed to create a new match");
            alert.showAndWait();
            System.err.println(ex.getMessage());
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("succedfully created a new match");
        ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType ViewMatchBtn = new ButtonType("View match");
        alert.getButtonTypes().setAll(okButton, ViewMatchBtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ViewMatchBtn) {

            matchInformatie matchscherm = new matchInformatie();
            GridPane infoScherm = matchscherm.matchInfo(lost, found);
            basisPane.addnewpane(infoScherm);

        }
    }
}
