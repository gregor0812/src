package prototypefys;

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
public class submitCase {
    Rootpane rootpane = new Rootpane();
    
    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();
    
    submitCase(){
        
    }
    
    public GridPane MakeSubmitScreen(){
        
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
        
        GridPane.setConstraints(btnS, 40, 30);

        
        grid.setStyle("-fx-background-color: white");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(homescreen);
            }
            });
        
        Label Case = new Label("Case Data");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid.add(Case, 10, 15, 10 , 1);
        
        Label personal = new Label("Personal Data");
        personal.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid.add(personal, 30, 15, 10 ,1);
        
        
        Label caseID = new Label("Case ID#");
        grid.add(caseID, 10, 17, 10 ,1);
        TextField caseIDT = new TextField();
        grid.add(caseIDT, 20, 17);
        
        Label caseName = new Label("Case Name");
        grid.add(caseName, 10, 18, 10 ,1);
        TextField caseNameT = new TextField();
        grid.add(caseNameT, 20, 18);
        
        Label flightNumber = new Label("Flight Number");
        grid.add(flightNumber, 10, 19, 10 ,1);
        TextField flightNumberT = new TextField();
        grid.add(flightNumberT, 20, 19);
        
        Label dateReport = new Label("Date Reported");
        grid.add(dateReport, 10, 20, 10 ,1);
        TextField dateReportT = new TextField();
        grid.add(dateReportT, 20, 20);
        dateReportT.setPromptText("dd/mm/yyyy");
        
        Label status = new Label("Status:");
        grid.add(status, 10, 21, 10 ,1);
        TextField statusT = new TextField();
        grid.add(statusT, 20, 21);
        
        Label airport = new Label("Airport:");
        grid.add(airport, 10, 22, 10 ,1);
        TextField airportT = new TextField();
        grid.add(airportT, 20, 22);
        
        Label itemName = new Label("Item Name");
        grid.add(itemName, 10, 26, 10 ,1);
        TextField itemNameT = new TextField();
        grid.add(itemNameT, 20, 26);
        
        Label itemColor = new Label("Item color");
        grid.add(itemColor, 10, 27, 10 ,1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 20, 27);
        
        Label itemInfo = new Label("Item misc. info");
        grid.add(itemInfo, 10, 28, 10 ,1);
        TextField itemInfoT = new TextField();
        grid.add(itemInfoT, 20, 28);
        
        Label itemLocation = new Label("Item current location");
        grid.add(itemLocation, 10, 29, 10 ,1);
        TextField itemLocationT = new TextField();
        grid.add(itemLocationT, 20, 29);
        
        Label  ownerName = new Label("Owner Name");
        grid.add(ownerName, 30, 17, 10 ,1);
        TextField ownerNameT = new TextField();
        grid.add(ownerNameT, 40, 17);
        
        Label ownerNr1 = new Label("Owner telephone number 1");
        grid.add(ownerNr1, 30, 18, 10 ,1);
        TextField ownerNr1T = new TextField();
        grid.add(ownerNr1T, 40, 18);
        
        Label ownerNr2 = new Label("Owner telephone number 2");
        grid.add(ownerNr2, 30, 19, 10 ,1);
        TextField ownerNr2T = new TextField();
        grid.add(ownerNr2T, 40, 19);
        
        Label ownerMail = new Label("Owner email");
        grid.add(ownerMail, 30, 20, 10 ,1);
        TextField ownerMailT = new TextField();
        grid.add(ownerMailT, 40, 20);
        
        Label ownerAddress = new Label("Owner Address");
        grid.add(ownerAddress, 30, 21, 10 ,1);
        TextField ownerAddressT = new TextField();
        grid.add(ownerAddressT, 40, 21);
        
        Label zipCode = new Label("Zip Code");
        grid.add(zipCode, 30, 26, 10 ,1);
        TextField zipCodeT = new TextField();
        grid.add(zipCodeT, 40, 26);
        
        Label employeeName = new Label("Employee Name");
        grid.add(employeeName, 30, 27, 10 ,1);
        TextField employeeNameT = new TextField();
        grid.add(employeeNameT, 40, 27);
        
        Label lastUpdate = new Label("Last Updated");
        grid.add(lastUpdate, 30, 28, 10 ,1);
        TextField lastUpdateT = new TextField();
        grid.add(lastUpdateT, 40, 28);
        lastUpdateT.setPromptText("dd/mm/yyyy");
        
        Label addNotes = new Label("Additional Notes");
        grid.add(addNotes, 10, 30, 10 ,1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 20, 30);
        addNotesT.setPrefSize(80, 80);
        
        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);
        
        grid.add(Calendar, 21, 20);
        
        ImageView Calendar2 = new ImageView("/resources/Calendar-icon.png");
        Calendar2.setFitHeight(30);
        Calendar2.setFitWidth(30);
        
        grid.add(Calendar2, 41, 28);
        
        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);
        
        
        // Toevoegen van buttons
        grid.getChildren().addAll(btn, btn2, btnS);
        
        return grid;
        
    }
    
}
