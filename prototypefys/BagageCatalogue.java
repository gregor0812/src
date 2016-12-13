package prototypefys;

import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import nl.hva.hboict.sql.DataRow;
import nl.hva.hboict.sql.DataTable;
import nl.hva.hboict.sql.SQLDataBase;

/**
 *
 * @author Koen Hengsdijk
 */
public class BagageCatalogue {

    
    /**
     * Onzin comment
     * 
     */
    private static final Rootpane basisPane = new Rootpane();
    
    private static final HomeScreen thuisScherm = new HomeScreen();
    private static final HBox mainmenu = thuisScherm.maakhomescreen();
    
    BagageCatalogue() {
    }
    
    public final String DB_NAME = "corendon";
    public final String DB_SERVER = "it95.nl:3306";
    public final String DB_ACCOUNT = "fys";
    public final String DB_PASSWORD = "ESCXZoaIlK07pwUS";

    
      // setup a connection to MyAirline database on my local server
        SQLDataBase dataBase
                = new SQLDataBase(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
        
        // get a table of airport information from the database
        DataTable airportData
                = dataBase.executeDataTableQuery("SELECT * FROM bagage");
    
    public GridPane MaakCatalogue() {
        
       
        
        GridPane root = new GridPane();
        
        root.getColumnConstraints().add(new ColumnConstraints(200));
        root.setPadding(new Insets(30,30,30,30));
        
        
        GridPane Zoekscherm = new GridPane();
        Zoekscherm.setPadding(new Insets(25, 25, 25, 25));
        Zoekscherm.setHgap(10);
        Zoekscherm.setVgap(10);
        Zoekscherm.setPrefSize(150, 100);
        Zoekscherm.setMaxSize(150, 100);
        Zoekscherm.setStyle("-fx-base:darkred;-fx-border-color:darkred");
        Zoekscherm.setAlignment(Pos.CENTER);
        Zoekscherm.setPrefSize(250, 250);
        Zoekscherm.setMaxSize(250, 250);
        
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
        
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
        "CaseID",
        "Country",
        "Flight Code"
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
                
                airportData = dataBase.executeDataTableQuery("SELECT * FROM bagage "
                    + "WHERE " + output + " = " + "'" + zoekConditie + "'");
                
                root.add(createJavaFXReadOnlyDataTableView(airportData), 2 , 3 , 2, 3);
                
            }
        });
        
        Button reset = new Button("Show all");
        reset.setMinSize(70, 20);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
               airportData = dataBase.executeDataTableQuery("Select * FROM bagage");
               root.add(createJavaFXReadOnlyDataTableView(airportData), 2 , 3 , 2, 3);
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

        Button buttonViewCase = new Button("View Case");
        buttonViewCase.setMinSize(150, 20);
        buttonViewCase.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonViewCase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("X");
                //basisPane.addnewpane(mainmenu);
            }
            });
        
        Button buttonProjected = new Button("Options");
        buttonProjected.setStyle("-fx-base:darkred;-fx-border-color:white");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
        
        HBox tabelKnoppen = new HBox();
        tabelKnoppen.getChildren().addAll(zoekTabel, reset);
        tabelKnoppen.setSpacing(10);
        Zoekscherm.add(tekst, 1, 1);
        Zoekscherm.add(comboBox, 1, 0);
        Zoekscherm.add(buttonViewCase, 1, 3);
//        Zoekscherm.add(ZoekTabel, 1, 2);
//        Zoekscherm.add(reset, 2, 2);
        Zoekscherm.add(tabelKnoppen, 1, 2);
        root.add(EmptyPane, 0, 1);
        root.add(Zoekscherm, 0 , 3);
        root.add(hbox, 0, 2);
        root.add(EmptyPane2, 1, 1);
        
        
        root.add(createJavaFXReadOnlyDataTableView(airportData), 2 , 3 , 2, 3);
        

        return root;
        
        
    }
    private static TableView<DataRow> createJavaFXReadOnlyDataTableView(DataTable dt) {
        TableView<DataRow> tv = new TableView<>();
        tv.setPrefWidth(5000);

        // define a JavaFX TableColumn for every column in the DataTable
        for (int c = 0; c < dt.getNColumns(); c++) {
            TableColumn<DataRow, String> tc = new TableColumn(dt.getColumnName(c));
            tc.setStyle("-fx-alignment: CENTER;");
            tc.setResizable(true);
            final int colIndex = c;

            // configure the Cell Value generator
            tc.setCellValueFactory(data -> {
                DataRow dr = data.getValue();
                String cellValue;
                cellValue = dr.getString(colIndex);
                return new ReadOnlyStringWrapper(cellValue);
            });
            tv.getColumns().add(tc);
        }

        // Add all Data Table data to the TableView
        for (int i = 0; i < dt.size(); i++) {
            tv.getItems().add(dt.get(i));
        }

        // align the view with the boundaries of the container
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //tv.setColumnResizePolicy((param) -> true);

        return tv;
    }

    
}
