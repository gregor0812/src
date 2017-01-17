package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

/**
 *
 * @author Koen Hengsdijk
 */
public class ReportGeneration {

    private static Database db = new Database();

    private static Rootpane rootpane = new Rootpane();

    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();

    // this boolean checks if a barchart is used or a piechart
    private boolean BarchartUsed = true;
    // this boolean check whether the lost luggage is shown or the found
    private boolean lostOrFound = true;
    
    private PieChart chart = new PieChart();
    
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    
    private BarChart<String, Number> bc;
    
    private ObservableList dataPie = FXCollections.observableArrayList();
    
    private XYChart.Series series1 = new XYChart.Series();
    
  

    ReportGeneration() {

    }

    public BorderPane MakeReportScreen() {

        // this boolean checks if a barchart is used or a piechart
        // this boolean checks whether the lost or found bagage is shown
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white");

        // this vbox contains the buttons
        VBox ButtonContainer = new VBox(10);
        ButtonContainer.setPadding(new Insets(25, 25, 25, 25));

        ButtonContainer.setStyle("-fx-base:darkred;-fx-border-color:white");
        ButtonContainer.setAlignment(Pos.CENTER);
        ButtonContainer.setPrefSize(280, 250);
        ButtonContainer.setMaxSize(280, 250);

        // this button will return to the main menu
        Button mainMenu = new Button(); // button 1
        mainMenu.setText("Main Menu");
        mainMenu.setPrefSize(205, 50);
        mainMenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        mainMenu.setFont(Font.font("Verdana", 12));
        mainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                rootpane.addnewpane(homescreen);
            }
        });

        // this button will show the statistics of the found luggage
        Button ShowFound = new Button(); // button 1
        ShowFound.setText("Show found luggage per airport");
        ShowFound.setPrefSize(210, 50);
        ShowFound.setStyle("-fx-base: #BC3434;-fx-border-color:white");
        ShowFound.setFont(Font.font("Verdana", 12));
        ShowFound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // this boolean checks if the barchart is used or not
                if (BarchartUsed) {

                    // this query selects the data from the database
                    String FoundAirportQuery = "select airport, count(airport) "
                        + "from foundluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String FoundLuggageName = "Found luggage per airport";
                    setPieChartData(FoundAirportQuery, FoundLuggageName);
                    root.setCenter(null);
                    root.setCenter(chart);
                    
                    lostOrFound = false;
                    
                } else {

                    String BarChartQuery = "select airport, count(airport) "
                        + "from foundluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "Found luggage per airport";

                     String xAxisName = "Airport";
                    String yAxisName = "Lost luggage";
                    
                    setBarchartData(BarChartQuery, LostLuggageName, xAxisName, yAxisName);
                    
                    root.setCenter(null);
                    root.setCenter(bc);
                    lostOrFound = false;

                }

            }

        });

        Button ShowLost = new Button(); // button 1
        ShowLost.setText("Show lost luggage per airport");
        ShowLost.setPrefSize(210, 50);
        ShowLost.setStyle("-fx-base: #BC3434;-fx-border-color:white");
        ShowLost.setFont(Font.font("Verdana", 12));
        ShowLost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (BarchartUsed) {
                    
                    // this query selects the data from the database
                    String LostAirportQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    // this is the title of the piechart
                    String LostLuggageName = "Lost luggage per airport";

                    setPieChartData(LostAirportQuery, LostLuggageName);
                    root.setCenter(null);
                    root.setCenter(chart);
                    
                    lostOrFound = true;
                } else {
                    
                    // this query selects the data from the database
                    String BarChartQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "Lost luggage per airport";
                     String xAxisName = "Airport";
                    String yAxisName = "Lost luggage";
                    // a barchart is made using the query
                    setBarchartData(BarChartQuery, LostLuggageName, xAxisName, yAxisName);
                    root.setCenter(null);
                    root.setCenter(bc);
                    lostOrFound = true;
                }
            }
        }); 
        
        // this button wil show the barchart or the piechart depending on which
        // is shown at the moment
        Button ShowBarChart = new Button(); // button 1

        ShowBarChart.setPrefSize(210, 50);
        ShowBarChart.setText("Show barchart");
        ShowBarChart.setStyle("-fx-base: #BC3434;-fx-border-color:white");
        ShowBarChart.setFont(Font.font("Verdana", 12));
        ShowBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lostOrFound = true;
                if (BarchartUsed) {

                    String BarChartQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "Lost luggage per airport";
                    String xAxisName = "Airport";
                    String yAxisName = "Lost luggage";
                    
                    
                    setBarchartData(BarChartQuery, LostLuggageName, xAxisName, yAxisName);

                    root.setCenter(bc);
                    
                    // the text of the 
                    ShowBarChart.setText("Show piechart");
                    
                    // by setting this boolean to false the rest of the program
                    // will know that a barchart is shown
                    BarchartUsed = false;
                } else {
                    String LostAirportQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "Lost luggage per airport";

                    setPieChartData(LostAirportQuery, LostLuggageName);

                    root.setCenter(chart);
                    ShowBarChart.setText("Show barchart");
                    BarchartUsed = true;
                }
            }
        });

        ButtonContainer.getChildren().addAll(mainMenu, ShowFound, ShowLost, ShowBarChart);

        String LostAirportQuery = "select airport, count(airport) "
            + "from lostluggage "
            + "where airport is not null "
            + "group by airport";
        String LostLuggageName = "Lost luggage per airport";

        setPieChartData(LostAirportQuery, LostLuggageName);

        HBox dateControls = new HBox();
        
        Label startDateLabel = new Label("Select a starting date: ");
        
        
        // this datepicker selects the start date to get data out of a certain period
        DatePicker startDate = new DatePicker();
        startDate.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate date = startDate.getValue();
                System.err.println("Selected date: " + date);
            }
        });
        
        String pattern = "yyyy-MM-dd";
        startDate.setPromptText(pattern.toLowerCase());
        startDate.setConverter(new StringConverter<LocalDate>() {
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
        
        Label endDateLabel = new Label("Select a ending date: ");
        
        DatePicker endDate = new DatePicker();
        endDate.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate date = endDate.getValue();
                System.err.println("Selected date: " + date);
            }
        });
        
        endDate.setPromptText(pattern.toLowerCase());
        endDate.setConverter(new StringConverter<LocalDate>() {
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
        
        Button DateSelection = new Button("Show selection");
        DateSelection.setPrefSize(180, 20);
        DateSelection.setStyle("-fx-base:darkred;-fx-border-color:white");
        DateSelection.setFont(Font.font("Verdana", 12));
        DateSelection.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
            
            if(BarchartUsed)
            {    
                
                if(lostOrFound)
                {
                String beginDate = startDate.getValue().toString();   
                String endingDate = endDate.getValue().toString();  
                
                String LostAirportQuery = "select airport, count(airport) "
                + "from lostluggage "
                + "where airport is not null and `date lost` between '" 
                + beginDate + "' and '" + endingDate + "' "
                + "group by airport";
                String LostLuggageName = "Lost luggage per airport between " 
                + beginDate + " and " + endingDate ;
                 setPieChartData(LostAirportQuery, LostLuggageName);
            
                }
                else
                {
                    String beginDate = startDate.getValue().toString();   
                String endingDate = endDate.getValue().toString();  
                
                String LostAirportQuery = "select airport, count(airport) "
                + "from foundluggage "
                + "where airport is not null and `dateFound` between '" 
                + beginDate + "' and '" + endingDate + "' "
                + "group by airport";
                String LostLuggageName = "Found luggage per airport between " 
                + beginDate + " and " + endingDate ;
                 setPieChartData(LostAirportQuery, LostLuggageName);
                    
                }
            }
            }
        });
        
            
        dateControls.setSpacing(10);
        dateControls.getChildren().addAll(startDateLabel, startDate, endDateLabel,
            endDate, DateSelection);
        
        dateControls.setAlignment(Pos.TOP_CENTER);
        
        root.setLeft(ButtonContainer);
        root.setCenter(chart);
        root.setBottom(dateControls);
        //root.setAlignment(Pos.CENTER); 
        
            

              
        
        return root;
    }

    // test 5634t
    private void setPieChartData(String query, String TableName) {
        
        dataPie.clear();
        

        try {

            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            while (TableData.next()) {

                dataPie.add(new PieChart.Data(TableData.getString(1), TableData.getInt(2)));
                
            }

        } catch (Exception ex) {
            System.out.println("Exception 2 ");
        }

        
        chart.setTitle(TableName);
        chart.setPrefSize(600, 600);
        chart.setData(dataPie);
        
    }

    private void setBarchartData(String query, String title, String xAxisLabel, String yAxisLabel) {
        
        bc = null;
        
        bc = new BarChart<>(xAxis, yAxis);
        
        System.out.println(query);
        
        series1.getData().clear();
        bc.getData().clear();
        
        bc.setTitle(title);
        
        xAxis.setLabel(null);
        yAxis.setLabel(null);
        
        xAxis.setLabel(xAxisLabel);
        yAxis.setLabel(yAxisLabel);

        
        try {
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            series1.setName("Airportname");
            while (TableData.next()) {

                series1.getData().add(new XYChart.Data(TableData.getString(1), TableData.getInt(2)));

            }

        } catch (Exception ex) {
            System.out.println("Exception 2 ");
        }

        bc.getData().addAll(series1);

        
    }

}
