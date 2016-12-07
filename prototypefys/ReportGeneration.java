package prototypefys;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        
        
        
       
        
        
         int spanjeInt = dataTableToString(spanje);
        int nederInt = dataTableToString(nederland);
        int turkInt = dataTableToString(turkije);
       
       
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("spain", spanjeInt),
                new PieChart.Data("turkey", turkInt),
                new PieChart.Data("netherlands", nederInt)
               );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("lost bagage per country");
        
        
        
        
        
        GridPane root = new GridPane();
        
        
        root.add(chart, 1, 1);
        
        return root;
    }
    
    
    public int dataTableToString(DataTable data){
        // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print some output: goes to your special stream
    System.out.println(data);
    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    
    String Gegevens = baos.toString();
    
    String cijferString = Character.toString(Gegevens.charAt(2));
    
    int Cijfer = Integer.parseInt(cijferString);
    
        return Cijfer;
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

