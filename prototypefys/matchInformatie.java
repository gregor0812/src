/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import cataloog.BagageCatalogue;
import database.Database;
import java.io.File;
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
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Koen Hengsdijk
 */
public class matchInformatie {

    matchInformatie() {
    
        }
    
    private Rootpane rootpane = new Rootpane();
    private Database db = new Database();
    
    public GridPane matchInfo(){
        
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
        btnS.setText("Submit Case");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        Label caseid = new Label();
        caseid.setText("The case id is: " );
        caseid.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btnmainmenu, 1, 15);
        grid.add(caseid, 40, 30, 1, 1);
        // GridPane.setConstraints(btn2, 2, 15);
        GridPane.setConstraints(btnS, 40, 32, 2, 2);

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
        TextField dateT = new TextField();
        grid.add(dateT, 20, 17);
        dateT.setPromptText("yyyy-mm-dd");
        
        Label timeLostT = new Label("time lost:");
        grid.add(timeLostT, 10, 18);
        TextField timeLostL = new TextField();
        grid.add(timeLostL, 20, 18);
        
        Label airport = new Label("Airport:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airportT = new TextField();
        grid.add(airportT, 20, 19);

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
        
        Label FoundInfo = new Label("found information:");
        FoundInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(FoundInfo, 30, 22, 12, 1);
        
        Label dateFoundL = new Label("date found:");
        grid.add(dateFoundL, 30, 23);
        TextField dateFoundT = new TextField();
        grid.add(dateFoundT, 40, 23 );
        
         Label timeFoundL = new Label("time found:");
        grid.add(timeFoundL, 30, 24);
        TextField timeFoundT = new TextField();
        grid.add(timeFoundT, 40, 24 );
        
        Label airportFoundL = new Label("airpot found:");
        grid.add(airportFoundL, 30, 25);
        TextField airportFoundT = new TextField();
        grid.add(airportFoundT, 40, 25);
            
        
        
        Label OwnerInfo = new Label("Owner information: ");
        OwnerInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(OwnerInfo, 10, 21, 12, 1);
        
        Text OwnerIdText = new Text("the ownerID is:");
        OwnerIdText.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(OwnerIdText, 10, 22, 10 , 1);
        Label naamReiziger = new Label("first Name:");
        grid.add(naamReiziger, 10, 23, 10, 1);
        TextField naamReizigerT = new TextField();
        grid.add(naamReizigerT, 20, 23);

        Label NameInsertion = new Label("insertion:");
        grid.add(NameInsertion, 10, 24, 10, 1);
        TextField NameInsertionT = new TextField();
        grid.add(NameInsertionT, 20, 24);

        Label lastName = new Label("last name:");
        grid.add(lastName, 10, 25, 10, 1);
        TextField LastNameT = new TextField();
        grid.add(LastNameT, 20, 25);

        Label phone1 = new Label("phone number 1:");
        grid.add(phone1, 10, 26, 10, 1);
        TextField phone1T = new TextField();
        grid.add(phone1T, 20, 26);

        Label phone2L = new Label("phone number 2:");
        grid.add(phone2L, 10, 27, 10, 1);
        TextField phone2T = new TextField();
        grid.add(phone2T, 20, 27);

        Label emailL = new Label("email: ");
        grid.add(emailL, 10, 28, 10, 1);
        TextField emailT = new TextField();
        grid.add(emailT, 20, 28);

        Label addOwnerNotes = new Label("Additional notes:");
        grid.add(addOwnerNotes, 10, 29, 10, 1);
        TextField addOwnerNotesT = new TextField();
        grid.add(addOwnerNotesT, 20, 29, 1, 1);

        Label ownerAdd = new Label("Address:");
        grid.add(ownerAdd, 10, 30, 10, 1);
        TextField ownerAddT = new TextField();
        grid.add(ownerAddT, 20, 30);

        Label ownerCity = new Label("City:");
        grid.add(ownerCity, 10, 31, 10, 1);
        TextField ownerCityT = new TextField();
        grid.add(ownerCityT, 20, 31);

        Label ownerZip = new Label("Zipcode:");
        grid.add(ownerZip, 10, 32, 10, 1);
        TextField ownerZipT = new TextField();
        grid.add(ownerZipT, 20, 32);

        Label ownerCountry = new Label("Country:");
        grid.add(ownerCountry, 10, 33, 10, 1);
        TextField ownerCountryT = new TextField();
        grid.add(ownerCountryT, 20, 33);

        
        Label itemType = new Label("Type:");
        grid.add(itemType, 30, 27, 10, 1);
        TextField itemTypeT = new TextField();
        grid.add(itemTypeT, 40, 27);

        Label itemBrand = new Label("Brand:");
        grid.add(itemBrand, 30, 28, 10, 1);
        TextField itemBrandT = new TextField();
        grid.add(itemBrandT, 40, 28);

        Label itemColor = new Label("Color:");
        grid.add(itemColor, 30, 29, 10, 1);
        TextField itemColorT = new TextField();
        grid.add(itemColorT, 40, 29);


        Label addNotes = new Label("Additional notes:");
        grid.add(addNotes, 30, 30, 10, 1);
        TextField addNotesT = new TextField();
        grid.add(addNotesT, 40, 30);

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
                
                File file = fileChooser();
                
            
                
        //String fileRaw = file.getAbsolutePath();
       
        //filePath = fileRaw.replace("\\","\\\\");
        
                
                
                GenerateSignedPdf pdfmaker = new GenerateSignedPdf();
                pdfmaker.MakePdf();

            }
        });

        return grid;
        
        
        
        
    }
    
    public File fileChooser() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Error bij het initialeren van de look en feel van de filechooser:\n" + ex);
        }
        JPanel frame = new JPanel();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG & GIF Images", "jpg", "png", "gif");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
            //picture_button.setText(selectedFile.getName());
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            return null;
        }
    }
    
}
