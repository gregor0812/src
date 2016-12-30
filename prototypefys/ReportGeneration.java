package prototypefys;

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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

    ReportGeneration() {

    }

    public BorderPane MakeReportScreen() {

        // this boolean checks if a barchart is used or a piechart
        // this boolean checks whether the lost or found bagage is shown
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white");

        VBox ButtonContainer = new VBox(10);
        ButtonContainer.setPadding(new Insets(25, 25, 25, 25));

        ButtonContainer.setStyle("-fx-base:darkred;-fx-border-color:darkred");
        ButtonContainer.setAlignment(Pos.CENTER);
        ButtonContainer.setPrefSize(280, 250);
        ButtonContainer.setMaxSize(280, 250);

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

        Button ShowFound = new Button(); // button 1
        ShowFound.setText("Show found luggage per airport");
        ShowFound.setPrefSize(210, 50);
        ShowFound.setStyle("-fx-base:darkred;-fx-border-color:white");
        ShowFound.setFont(Font.font("Verdana", 12));
        ShowFound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (BarchartUsed) {
                    
                     String BarChartQuery = "select airport, count(airport) "
                        + "from foundluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "found bagage per airport";

                    BarChart bc = MakeBarchart(BarChartQuery);

                    root.setCenter(bc);
                    
                 String FoundAirportQuery = "select airport, count(airport) "
                        + "from foundluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String FoundLuggageName = "Found luggage per airport";
                    PieChart showFound = createPieChart(FoundAirportQuery, FoundLuggageName);

                    root.setCenter(showFound);
                    
                    
                } else {

                    
                     String BarChartQuery = "select airport, count(airport) "
                        + "from foundluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "found bagage per airport";

                    BarChart bc = MakeBarchart(BarChartQuery);

                    root.setCenter(bc);

                }

            }

        });

        Button ShowLost = new Button(); // button 1
        ShowLost.setText("Show lost luggage per airport");
        ShowLost.setPrefSize(210, 50);
        ShowLost.setStyle("-fx-base:darkred;-fx-border-color:white");
        ShowLost.setFont(Font.font("Verdana", 12));
        ShowLost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (BarchartUsed) {

                    String LostAirportQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "lost bagage per airport";

                    PieChart showLost = createPieChart(LostAirportQuery, LostLuggageName);

                    root.setCenter(showLost);
                } else {

                    String BarChartQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "lost bagage per airport";

                    BarChart bc = MakeBarchart(BarChartQuery);

                    root.setCenter(bc);

                }
            }
        });

        Button ShowBarChart = new Button(); // button 1

        ShowBarChart.setPrefSize(210, 50);
        ShowBarChart.setText("Show barchart");
        ShowBarChart.setStyle("-fx-base:darkred;-fx-border-color:white");
        ShowBarChart.setFont(Font.font("Verdana", 12));
        ShowBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (BarchartUsed) {

                    String BarChartQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "lost bagage per airport";

                    BarChart bc = MakeBarchart(BarChartQuery);

                    root.setCenter(bc);
                    ShowBarChart.setText("Show piechart");
                    BarchartUsed = false;
                } else {
                    String LostAirportQuery = "select airport, count(airport) "
                        + "from lostluggage "
                        + "where airport is not null "
                        + "group by airport";
                    String LostLuggageName = "lost bagage per airport";

                    PieChart showLost = createPieChart(LostAirportQuery, LostLuggageName);

                    root.setCenter(showLost);
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
        String LostLuggageName = "lost bagage per airport";

        PieChart test = createPieChart(LostAirportQuery, LostLuggageName);

        root.setLeft(ButtonContainer);
        root.setCenter(test);
        //root.setAlignment(Pos.CENTER); 
        return root;
    }

    // test 5634t
    private static PieChart createPieChart(String query, String TableName) {

        ObservableList data = FXCollections.observableArrayList();

        try {

            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            while (TableData.next()) {

                data.add(new PieChart.Data(TableData.getString(1), TableData.getInt(2)));

            }

        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        final PieChart chart = new PieChart(data);
        chart.setTitle(TableName);
        chart.setPrefSize(600, 600);
        return chart;
    }

    private static BarChart MakeBarchart(String query) {

        ObservableList data = FXCollections.observableArrayList();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc
            = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("airport");
        yAxis.setLabel("lost luggage");

        XYChart.Series series1 = new XYChart.Series();
        try {
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            series1.setName("kutbarchart");
            while (TableData.next()) {

                series1.getData().add(new XYChart.Data(TableData.getString(1), TableData.getInt(2)));

            }

        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        bc.getData().addAll(series1);

        return bc;
    }

}
