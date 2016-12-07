
package prototypefys;

import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.GridPane;
import nl.hva.hboict.sql.DataTable;
import nl.hva.hboict.sql.SQLDataBase;

/**
 *
 * @author Koen Hengsdijk
 */
public class ReportGeneration {
    
    public final String DB_NAME = "MyAirline";
    public final String DB_SERVER = "localhost:3306";
    public final String DB_ACCOUNT = "beheerder";
    public final String DB_PASSWORD = "nooitgedacht";

    ReportGeneration() {

    }
    
    public GridPane MakeReportScreen(){
        
         // setup a connection to MyAirline database on my local server
        SQLDataBase dataBase
                = new SQLDataBase(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
        
        // get a table of airport information from the database
        DataTable airportData
                = dataBase.executeDataTableQuery("SELECT country FROM bagage");
        
        DataTable spanje 
            = dataBase.executeDataTableQuery("SELECT COUNT(Country) from bagage WHERE Country = 'spain'");
        
        DataTable nederland 
            = dataBase.executeDataTableQuery("SELECT COUNT(Country) from bagage WHERE Country = 'netherlands'");
        DataTable turkije 
            = dataBase.executeDataTableQuery("SELECT COUNT(Country) from bagage WHERE Country = 'turkey'");
        
        int spanjeInt = spanje.getValue();
        
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("spain", spanje.getInt),
                new PieChart.Data("turkey", 25),
                new PieChart.Data("netherlands", 10)
               );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("lost bagage per country");
        
        
        
        
        
        GridPane root = new GridPane();
        
        
        root.add(chart, 1, 1);
        
        return root;
    }
    
        
    // test 5634t
    
    
    
    
//    private static PieChart createJavaFXChartAirport() {
//        
//
//        ObservableList<PieChart.Data> pieChartData =
//                FXCollections.observableArrayList(
//                new PieChart.Data("Grapefruit", 13),
//                new PieChart.Data("Oranges", 25),
//                new PieChart.Data("Plums", 10),
//                new PieChart.Data("Pears", 22),
//                new PieChart.Data("Apples", 30));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("lost bagage per country");
//
//        return chart;
//    }

}

//package prototypefys;
//
//import java.util.Collections;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.chart.PieChart;
//import javafx.scene.layout.GridPane;
//import nl.hva.hboict.sql.DataTable;
//import nl.hva.hboict.sql.SQLDataBase;
//
///**
// *
// * @author Koen Hengsdijk
// */
//public class ReportGeneration {
//    
//    public final String DB_NAME = "MyAirline";
//    public final String DB_SERVER = "localhost:3306";
//    public final String DB_ACCOUNT = "beheerder";
//    public final String DB_PASSWORD = "nooitgedacht";
//
//    ReportGeneration() {
//
//    }
//    
//    public GridPane MakeReportScreen(){
//        
//         // setup a connection to MyAirline database on my local server
//        SQLDataBase dataBase
//                = new SQLDataBase(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
//        
//        // get a table of airport information from the database
//        DataTable airportData
//                = dataBase.executeDataTableQuery("SELECT country FROM bagage");
//        
//        GridPane root = new GridPane();
//        
//        
//        root.add(createJavaFXChartAirport(airportData), 1, 1);
//        
//        return root;
//    }
//    
//    
//    
//    
//    
//    
//    
//    private static PieChart createJavaFXChartAirport(DataTable airportData) {
//
//        // convert the data to the JavaFX Charts format
//        ObservableList<PieChart.Data> airportChartData = FXCollections.observableArrayList();
//        
//        int spain = Collections.frequency(airportChartData, "spain");
//        int netherlands = Collections.frequency(airportChartData, "netherlands");
//        int turkey = Collections.frequency(airportChartData, "turkey");
//        airportChartData.add(
//                new PieChart.Data(("spain"), spain),
//                new PieChart.Data(("netherlands"), netherlands));
//
//        // build the JavaFX chart
//        PieChart chart = new PieChart(airportChartData);
//        chart.setTitle("verloren koffers per land");
//
//        return chart;
//    }
//
//}

