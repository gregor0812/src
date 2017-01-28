/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import cataloog.BagageCatalogue;
import cataloog.FoundLuggage;
import cataloog.LostLuggage;
import database.Database;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
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
import javafx.scene.text.Text;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author IS-109-2
 */
public class matchInformatie {

    

    public matchInformatie() {

    }

    private Rootpane rootpane = new Rootpane();
    private Database db = new Database();

    public GridPane matchInfo(LostLuggage lostInfo, FoundLuggage foundInfo) {

        Button btnmainmenu;

        Button btnS;

        HBox Menu = new HBox();
        // ------------------------------
        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("return to catalogue");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));

        btnS = new Button(); // button Submit
        btnS.setText("make pdf");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        Label caseid = new Label();
        caseid.setText("The case id is: " + lostInfo.getCaseid());
        caseid.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btnmainmenu, 1, 15);
        grid.add(caseid, 40, 31, 1, 1);
        // GridPane.setConstraints(btn2, 2, 15);
        GridPane.setConstraints(btnS, 40, 33, 2, 2);

        grid.setStyle("-fx-background-color: white");

        btnmainmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                BagageCatalogue scherm2 = new BagageCatalogue();
                GridPane cataloog = scherm2.MaakCatalogue();
                rootpane.addnewpane(cataloog);

            }
        });

        Text Header = new Text("match information");
        Header.setFont(Font.font("Verdana", FontWeight.BOLD, 42));
        grid.add(Header, 20, 3, 5, 10);

        Label Case = new Label("lost information");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        Label label = new Label("Label Information");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(label, 30, 16, 15, 1);

        Label luggage = new Label("Luggage information");
        luggage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(luggage, 30, 26, 15, 1);

        Label date = new Label("Date lost:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField(lostInfo.getDateLost());
        grid.add(dateT, 20, 17);
        dateT.setPromptText("yyyy-mm-dd");
        dateT.setDisable(true);

        Label timeLostL = new Label("time lost:");
        grid.add(timeLostL, 10, 18);
        TextField timeLostT = new TextField(lostInfo.getTimeLost());
        grid.add(timeLostT, 20, 18);
        timeLostT.setDisable(true);

        Label airport = new Label("Airport:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airportT = new TextField(lostInfo.getAirport());
        grid.add(airportT, 20, 19);
        airportT.setDisable(true);

        Label labelNumber = new Label("Label number:");
        grid.add(labelNumber, 30, 17, 10, 1);
        TextField labelNumberT = new TextField(Integer.toString(lostInfo.getLabelnr()));
        grid.add(labelNumberT, 40, 17);
        labelNumberT.setDisable(true);

        Label flight = new Label("Flight number:");
        grid.add(flight, 30, 18, 10, 1);
        TextField flightT = new TextField(Integer.toString(lostInfo.getFlightnr()));
        grid.add(flightT, 40, 18);
        flightT.setDisable(true);

        Label destination = new Label("Destination:");
        grid.add(destination, 30, 19, 10, 1);
        TextField destinationT = new TextField(lostInfo.getDestination());
        grid.add(destinationT, 40, 19);
        destinationT.setDisable(true);

        Label FoundInfo = new Label("found information:");
        FoundInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(FoundInfo, 30, 22, 12, 1);

        Label dateFoundL = new Label("date found:");
        grid.add(dateFoundL, 30, 23);
        TextField dateFoundT = new TextField(foundInfo.getDateFound());
        grid.add(dateFoundT, 40, 23);
        dateFoundT.setDisable(true);

        Label timeFoundL = new Label("time found:");
        grid.add(timeFoundL, 30, 24);
        TextField timeFoundT = new TextField(foundInfo.getTimeFound());
        grid.add(timeFoundT, 40, 24);

        Label airportFoundL = new Label("airport found:");
        grid.add(airportFoundL, 30, 25);
        TextField airportFoundT = new TextField(foundInfo.getAirport());
        grid.add(airportFoundT, 40, 25);
        airportFoundT.setDisable(true);

        Label OwnerInfo = new Label("Owner information: ");
        OwnerInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(OwnerInfo, 10, 21, 12, 1);

        Text OwnerIdText = new Text("the ownerID is: " + lostInfo.getOwnerid());
        OwnerIdText.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(OwnerIdText, 10, 22, 10, 1);

        Label naamReiziger = new Label("first Name:");
        grid.add(naamReiziger, 10, 23, 10, 1);
        TextField naamReizigerT = new TextField(lostInfo.getFirstName());
        grid.add(naamReizigerT, 20, 23);
        naamReizigerT.setDisable(true);

        Label NameInsertion = new Label("insertion:");
        grid.add(NameInsertion, 10, 24, 10, 1);
        TextField NameInsertionT = new TextField(lostInfo.getInsertion());
        grid.add(NameInsertionT, 20, 24);
        NameInsertionT.setDisable(true);

        Label lastName = new Label("last name:");
        grid.add(lastName, 10, 25, 10, 1);
        TextField LastNameT = new TextField(lostInfo.getLastName());
        grid.add(LastNameT, 20, 25);
        LastNameT.setDisable(true);

        Label phone1 = new Label("phone number 1:");
        grid.add(phone1, 10, 26, 10, 1);
        TextField phone1T = new TextField(getOwnerinfo(lostInfo.getOwnerid(), 5));
        grid.add(phone1T, 20, 26);
        phone1T.setDisable(true);

        Label phone2L = new Label("phone number 2:");
        grid.add(phone2L, 10, 27, 10, 1);
        TextField phone2T = new TextField(getOwnerinfo(lostInfo.getOwnerid(), 6));
        grid.add(phone2T, 20, 27);
        phone2T.setDisable(true);

        Label emailL = new Label("email: ");
        grid.add(emailL, 10, 28, 10, 1);
        TextField emailT = new TextField(getOwnerinfo(lostInfo.getOwnerid(), 7));
        grid.add(emailT, 20, 28);
        emailT.setDisable(true);

        Label addOwnerNotes = new Label("Additional notes:");
        grid.add(addOwnerNotes, 10, 29, 10, 1);
        TextField addOwnerNotesT = new TextField(getOwnerinfo(lostInfo.getOwnerid(), 8));
        grid.add(addOwnerNotesT, 20, 29, 1, 1);
        addOwnerNotesT.setDisable(true);

        Label ownerAdd = new Label("Address:");
        grid.add(ownerAdd, 10, 30, 10, 1);
        TextField ownerAddT = new TextField(getAddressInfo(lostInfo.getOwnerid(), 2));
        grid.add(ownerAddT, 20, 30);
        ownerAddT.setDisable(true);

        Label ownerCity = new Label("City:");
        grid.add(ownerCity, 10, 31, 10, 1);
        TextField ownerCityT = new TextField(getAddressInfo(lostInfo.getOwnerid(), 4));
        grid.add(ownerCityT, 20, 31);
        ownerCityT.setDisable(true);

        Label ownerZip = new Label("Zipcode:");
        grid.add(ownerZip, 10, 32, 10, 1);
        TextField ownerZipT = new TextField(getAddressInfo(lostInfo.getOwnerid(), 3));
        grid.add(ownerZipT, 20, 32);
        ownerZipT.setDisable(true);

        Label ownerCountry = new Label("Country:");
        grid.add(ownerCountry, 10, 33, 10, 1);
        TextField ownerCountryT = new TextField(getAddressInfo(lostInfo.getOwnerid(), 5));
        grid.add(ownerCountryT, 20, 33);
        ownerCountryT.setDisable(true);

        Label itemType = new Label("Type:");
        grid.add(itemType, 30, 27, 10, 1);
        TextField itemTypeT = new TextField(lostInfo.getItemname());
        grid.add(itemTypeT, 40, 27);
        itemTypeT.setDisable(true);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 28, 10, 1);
        TextField itemBrandT = new TextField(lostInfo.getBrand());
        grid.add(itemBrandT, 40, 28);
        itemBrandT.setDisable(true);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 29, 10, 1);
        TextField itemColorT = new TextField(lostInfo.getColors());
        grid.add(itemColorT, 40, 29);
        itemColorT.setDisable(true);

        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 30, 10, 1);
        TextField addNotesT = new TextField(lostInfo.getDescription());
        grid.add(addNotesT, 40, 30);
        addNotesT.setDisable(true);

        ImageView Calendar = new ImageView("/resources/Calendar-icon.png");
        Calendar.setFitHeight(30);
        Calendar.setFitWidth(30);

        grid.add(Calendar, 21, 17);

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);

        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  matchInfoPdf makePdf = new matchInfoPdf();
                
                makePdf.maakMatchPdf(lostInfo, foundInfo);
                
                FoundLuggageMail newmail = new FoundLuggageMail();
                GridPane createMailForm = newmail.makeTheMail(lostInfo, foundInfo);

                rootpane.addnewpane(createMailForm);
            }
        });

      

        return grid;

    }

    public String getOwnerinfo(int ownerid, int infoNumber) {

        String info = "";

        try {

            Connection userinfoConnection = db.getConnection();
            Statement statement = userinfoConnection.createStatement();

            ResultSet ownerinfo = statement.executeQuery("select * from luggageowner "
                + "where ownerid = " + ownerid + "");

            while (ownerinfo.next()) {
                info = ownerinfo.getString(infoNumber);
            }

        } catch (Exception ex) {
            System.out.println("failed to get user information");
            System.err.println(ex.getMessage());
        }

        return info;
    }

    public String getAddressInfo(int ownerid, int infoNumber) {

        String info = "";

        try {

            Connection userinfoConnection = db.getConnection();
            Statement statement = userinfoConnection.createStatement();

            ResultSet ownerinfo = statement.executeQuery("select * from address "
                + "where ownerid = " + ownerid + "");

            while (ownerinfo.next()) {
                info = ownerinfo.getString(infoNumber);
            }

        } catch (Exception ex) {
            System.out.println("failed to get user information");
            System.err.println(ex.getMessage());
        }

        return info;
    }

    public String matchInfoString(String string) {

        return string;
    }

    
    
    
    
    
}
