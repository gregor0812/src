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
        root.setAlignment(Pos.TOP_CENTER);
        root.getColumnConstraints().add(new ColumnConstraints(200));
        root.setPadding(new Insets(30, 30, 30, 30));

        GridPane Zoekscherm = new GridPane();
        Zoekscherm.setPadding(new Insets(25, 25, 25, 25));
        Zoekscherm.setHgap(10);
        Zoekscherm.setVgap(10);
        Zoekscherm.setPrefSize(150, 100);
        Zoekscherm.setMaxSize(150, 100);
        Zoekscherm.setStyle("-fx-base:darkred;-fx-border-color:darkred");
        Zoekscherm.setAlignment(Pos.CENTER);
        Zoekscherm.setPrefSize(250, 280);
        Zoekscherm.setMaxSize(250, 280);

        root.setStyle("-fx-background-color: white");

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

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        root.add(Corendon, 0, 1, 10, 1);

        // the standard tableview is added here     
        LostLuggageTable("Select * FROM lostluggage");
        //root.add(catalogue, 2, 3, 2, 3);

        catalogue.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        FoundLuggageTable("Select * FROM foundluggage");
        catalogueFound.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TablePane.getChildren().addAll(catalogue, catalogueFound);
        catalogueFound.setVisible(false);

        root.add(TablePane, 2, 3, 2, 3);

        ObservableList<String> options
            = FXCollections.observableArrayList(
                "caseid",
                "airport",
                "ownerid",
                "labelnr",
                "flightnumber",
                "itemname",
                "color"
            );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setMinSize(150, 20);
        Button zoekTabel = new Button("Search");
        zoekTabel.setMinSize(70, 20);
        zoekTabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String output = (String) comboBox.getValue();
                String zoekConditie = (String) tekst.getText();
                if ("color".equals(output)) {
                    output = "colors";
                }

                if (lostOrFound) {
                    catalogue.getItems().clear();
                    catalogue.getColumns().clear();

                    LostLuggageTable("SELECT * FROM lostluggage "
                        + "WHERE `" + output + "` LIKE " + "'%" + zoekConditie + "%'");
                } else {
                    catalogue.getItems().clear();
                    catalogue.getColumns().clear();
                    FoundLuggageTable("SELECT * FROM foundluggage "
                        + "WHERE " + output + " LIKE " + "'%" + zoekConditie + "%'");
                }

            }
        });

        Button showFound = new Button("show found luggage");
        showFound.setMinSize(150, 20);
        showFound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                catalogueFound.setVisible(true);
                catalogue.setVisible(false);
                // LostLuggageTable("Select * FROM foundluggage");
                lostOrFound = false;
                FoundLuggageTable("Select * FROM foundluggage");
            }
        });

        Button showLost = new Button("Show lost luggage");
        showLost.setMinSize(150, 20);
        showLost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                catalogue.setVisible(true);
                catalogueFound.setVisible(false);
                LostLuggageTable("Select * FROM lostluggage");
                // LostLuggageTable("Select * FROM lostluggage");
                lostOrFound = true;
            }
        });

        Button deleteCase = new Button("Delete case");
        deleteCase.setMinSize(150, 20);
        deleteCase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (lostOrFound) {

                    try {

                        Integer person = catalogue.getSelectionModel().getSelectedItem().getCaseid();

                        DeleteCase(person);
                        LostLuggageTable("Select * FROM foundluggage");

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

                        DeleteCase(person);
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

        Button buttonCurrent = new Button("Main Menu");
        //buttonCurrent.setPrefSize(90, 50);
        buttonCurrent.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonCurrent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                basisPane.addnewpane(mainmenu);
            }
        });
        buttonCurrent.setPrefSize(100, 20);

        Button buttonViewCase = new Button("Edit Case");
        buttonViewCase.setMinSize(150, 20);
        buttonViewCase.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonViewCase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                int selectedIndex
//                        = catalogue.getSelectionModel().getSelectedIndex();
                if (lostOrFound) {

                    LostLuggage person = catalogue.getSelectionModel().getSelectedItem();

                    if (person != null) {

                        GridPane Editform = LostLuggageEdit.MakeLostReport(person);

                        basisPane.addnewpane(Editform);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("error");
                        alert.setHeaderText("edit case");
                        alert.setContentText("Select a case to edit");

                        alert.showAndWait();
                    }

                } else {

                    FoundLuggage EditFound = catalogueFound.getSelectionModel().getSelectedItem();

                    if (EditFound != null) {

                        GridPane Editform = LostLuggageEdit.MakeLostReport(EditFound);

                        basisPane.addnewpane(Editform);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("foutmelding");
                        alert.setHeaderText("Wijzig luchthaven");
                        alert.setContentText("Select a case to edit");

                        alert.showAndWait();
                    }

                }

            }
        });

        Button buttonProjected = new Button("Options");
        buttonProjected.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        HBox tabelKnoppen = new HBox();
        tabelKnoppen.getChildren().addAll(zoekTabel, showFound, showLost, deleteCase);
        tabelKnoppen.setSpacing(10);
        Zoekscherm.add(tekst, 1, 1);
        Zoekscherm.add(comboBox, 1, 0);
        Zoekscherm.add(buttonViewCase, 1, 3);
        Zoekscherm.add(showFound, 1, 4);
        Zoekscherm.add(showLost, 1, 5);
        Zoekscherm.add(deleteCase, 1, 6);
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

        TableColumn<LostLuggage, Integer> owneridColumn = new TableColumn<>("ownerid");
        owneridColumn.setCellValueFactory(new PropertyValueFactory<>("ownerid"));

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

                data.add(new LostLuggage(TableData.getInt(1), TableData.getInt(2), TableData.getInt(3),
                    TableData.getInt(4), TableData.getString(5), TableData.getString(6),
                    TableData.getString(7), TableData.getString(8),
                    TableData.getString(9), TableData.getString(10), TableData.getString(11),
                    TableData.getString(12)));

            }

            catalogue.getItems().clear();
            catalogue.getColumns().clear();

            //System.out.println(data);
            catalogue.setItems(data);
            catalogue.getColumns().addAll(caseidColumn, owneridColumn, labelnrColumn,
                destinationColumn, flightnrColumn, airportColumn, itemnameColumn, brandColumn,
                colorsColumn, descriptionColumn, dateLostColumn, statusColumn);
        } catch (Exception ex) {
            System.out.println("exception 2 ");
            System.out.println(ex);
        }

    }

    public void FoundLuggageTable(String query) {
        catalogueFound.setEditable(true);

        // de table colums are made here
        TableColumn<FoundLuggage, Integer> caseidColumn = new TableColumn<>("foundID");
        caseidColumn.setCellValueFactory(new PropertyValueFactory<>("caseid"));

        TableColumn<FoundLuggage, Integer> labelnrColumn = new TableColumn<>("labelnr");
        labelnrColumn.setCellValueFactory(new PropertyValueFactory<>("labelnr"));

        TableColumn<FoundLuggage, Integer> flightnrColumn = new TableColumn<>("flightnumber");
        flightnrColumn.setCellValueFactory(new PropertyValueFactory<>("flightnr"));

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

                dataFound.add(new FoundLuggage(TableData.getInt(1), TableData.getInt(2), TableData.getInt(3),
                    TableData.getString(5), TableData.getString(4), TableData.getString(6),
                    TableData.getString(7), TableData.getString(8),
                    TableData.getString(8), TableData.getString(10), TableData.getString(11)));

            }

            //System.out.println(data);
            catalogueFound.getItems().clear();
            catalogueFound.getColumns().clear();

            catalogueFound.setItems(dataFound);
            catalogueFound.getColumns().addAll(caseidColumn, labelnrColumn,
                flightnrColumn, airportColumn, destinationColumn, itemnameColumn,
                brandColumn, colorsColumn, descriptionColumn, dateFoundColumn, statusColumn);
        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

    }
    
    public void DeleteCase(int id) {

        

        try {

            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
           statement.executeUpdate("DELETE FROM `corendon`.`lostluggage` WHERE `lostID`='" + id +"';");

            

        } catch (Exception ex) {
            System.out.println("failed to delete case ");
        }

       
    }
    

}
