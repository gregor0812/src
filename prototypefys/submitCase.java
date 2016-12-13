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

    submitCase() {

    }

    public GridPane MakeSubmitScreen() {

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
        grid.setPadding(new Insets(10, 10, 10, 10));
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

        Label Case = new Label("Found");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        Label personal = new Label("Personal Data");
        personal.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(personal, 10, 22, 15, 1);

        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 30, 22, 15, 1);

        Label date = new Label("Date:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField();
        grid.add(dateT, 20, 17);
        dateT.setPromptText("dd/mm/yyyy");

        Label time = new Label("Time:");
        grid.add(time, 10, 18, 10, 1);
        TextField timeT = new TextField();
        grid.add(timeT, 20, 18);

        Label airport= new Label("Airport:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airportT= new TextField();
        grid.add(airportT, 20, 19);

        Label labelN= new Label("Label number:");
        grid.add(labelN, 30, 17, 10, 1);
        TextField labelNT = new TextField();
        grid.add(labelNT, 40, 17);

        Label flightN = new Label("Flight number:");
        grid.add(flightN, 30, 18, 10, 1);
        TextField flightT = new TextField();
        grid.add(flightT, 40, 18);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField();
        grid.add(destinationT, 40, 19);

        Label type = new Label("Type:");
        grid.add(type, 30, 23, 10, 1);
        TextField typeT = new TextField();
        grid.add(typeT, 40, 23);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 24, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 40, 24);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 25, 10, 1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 40, 25);

        Label ownerName = new Label("Name:");
        grid.add(ownerName, 10, 23, 10, 1);
        TextField ownerNameT = new TextField();
        grid.add(ownerNameT, 20, 23);

        Label ownerAdd = new Label("Address:");
        grid.add(ownerAdd, 10, 24, 10, 1);
        TextField ownerAddT = new TextField();
        grid.add(ownerAddT, 20, 24);

        Label ownerCity = new Label("City:");
        grid.add(ownerCity, 10, 25, 10, 1);
        TextField ownerCityT = new TextField();
        grid.add(ownerCityT, 20, 25);

        Label ownerZip = new Label("Zipcode:");
        grid.add(ownerZip, 10, 26, 10, 1);
        TextField ownerZipT = new TextField();
        grid.add(ownerZipT, 20, 26);

        Label ownerCountry = new Label("Country:");
        grid.add(ownerCountry, 10, 27, 10, 1);
        TextField ownerCountryT = new TextField();
        grid.add(ownerCountryT, 20, 27);

        Label telNumber = new Label("Telephone number:");
        grid.add(telNumber, 10, 28, 10, 1);
        TextField telNumberT = new TextField();
        grid.add(telNumberT, 20, 28);

        Label eMail = new Label("E-mail:");
        grid.add(eMail, 10, 29, 10, 1);
        TextField eMailT = new TextField();
        grid.add(eMailT, 20, 29);

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 26, 10, 1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 40, 26);
        

        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);

        grid.add(Calendar, 21, 17);

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btn, btn2, btnS);

        return grid;

    }

}
