package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Koen Hengsdijk
 */
public class ReportLost {
    private Database db = new Database();
    
    Rootpane rootpane = new Rootpane();
    
    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();
    
    ReportLost(){
        
    }
    
    public GridPane MakeLostReport(){
        
        Button btn;
        Button btn2;
        Button btnS;
        
         HBox Menu = new HBox();
        // ------------------------------
        btn = new Button(); // button 1
        btn.setText("Main Menu");
        btn.setPrefSize(160, 50);
        btn.setStyle("-fx-base:darkred;-fx-border-color:white");
        btn.setFont(Font.font("Verdana", 12));
        // ------------------------------
        btn2 = new Button(); // button 2
        btn2.setText("Options");
        btn2.setPrefSize(160, 50);
        btn2.setStyle("-fx-base:darkred;-fx-border-color:white");
        btn2.setFont(Font.font("Verdana", 12));
        //--------------------------------
        btnS = new Button(); // button Submit
        btnS.setText("Submit Case");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        GridPane.setConstraints(btn, 1, 15);
        
        GridPane.setConstraints(btn2, 2, 15);
        
        GridPane.setConstraints(btnS, 40, 28);
        Label caseid = new Label();
        caseid.setText("The case id is: " + getCaseId());
        caseid.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        
        GridPane.setConstraints(caseid, 40, 27);
        
        grid.setStyle("-fx-background-color: white");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(homescreen);
            }
            });
        
        Label Case = new Label("lost");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 10, 23, 15, 1);

        Label date = new Label("Date:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField();
        grid.add(dateT, 20, 17);
        dateT.setPromptText("dd/mm/yyyy");

        Label time = new Label("Time:");
        grid.add(time, 10, 18, 10, 1);
        TextField timeT = new TextField();
        grid.add(timeT, 20, 18);

        Label airport = new Label("Airport:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airtportT = new TextField();
        grid.add(airtportT, 20, 19);

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
        
        Label naamReiziger = new Label("Name traveler:");
        grid.add(naamReiziger, 30, 20, 10, 1);
        TextField naamReizigerT = new TextField();
        grid.add(naamReizigerT, 40, 20);

        Label itemType = new Label("Type:");
        grid.add(itemType, 10, 24, 10, 1);
        TextField itemTypeT = new TextField();
        grid.add(itemTypeT, 20, 24);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 10, 25, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 20, 25);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 10, 26, 10, 1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 20, 26);

        Label lostandfound = new Label("Lost-and-found ID");
        grid.add(lostandfound, 10, 20, 10, 1);
        TextField lostandfoundT = new TextField();
        grid.add(lostandfoundT, 20, 20);

        

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 10, 27, 10, 1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 20, 27);
        
        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);
        
        grid.add(Calendar, 21, 17);
        
        
        
        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);
        
        // Toevoegen van buttons
        grid.getChildren().addAll(btn, btn2, btnS, caseid);
        
        return grid;
     
    }
    
    public int getCaseId(){
        
        int newCaseId = 0;
        
        try{
        
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery("select max(caseid) from lostluggage");
            
            while (TableData.next()){
            newCaseId = TableData.getInt(1) + 1;
            
            }
            
        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }
        
        
        return newCaseId;
    }
    
    
    
}
