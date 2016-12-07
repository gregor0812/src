package prototypefys;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

/**
 *
 * @author Koen Hengsdijk
 */
public class LoginScherm {
    
    private Button login = new Button();

    LoginScherm() {

    }
    
    public final String DB_NAME = "fys";
    public final String DB_SERVER = "it95.nl:3306";
    public final String DB_ACCOUNT = "fys";
    public final String DB_PASSWORD = "ESCXZoaIlK07pwUS";
    
    HomeScreen nieuwscherm = new HomeScreen();
    HBox homescreen = nieuwscherm.maakhomescreen();
    
    Rootpane rootpane = new Rootpane();

    public GridPane MaakHetScherm() {
        
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        

        ImageView Corendon = new ImageView("/resources/CorendonAirlines.png");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        
        root.add(Corendon, 0, 0, 2, 1);
        
        Text username = new Text("Username:");
        root.add(username, 0, 1);

        TextField text = new TextField();
        text.setPrefColumnCount(1);
        text.setPrefWidth(100);
        root.add(text, 1, 1);

        Text password = new Text("Password:");
        root.add(password, 0, 2);

        TextField text2 = new TextField();
        text2.setPrefColumnCount(1);
        text2.setPrefWidth(10);
        root.add(text2, 1, 2);

        root.setStyle("-fx-background-color: #eaaf00;");
        
        Button login = new Button();
        login.setText("Login");
        login.setStyle("-fx-background-color: #009b91;-fx-text-fill:#eaaf00");

        root.setStyle("-fx-background-color: #16302e");
        
        AudioClip plonkSound = new AudioClip
    ("https://ia800501.us.archive.org/33/items/nyannyannyan/NyanCatoriginal.mp3");
        
        



        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                plonkSound.play();
                
                rootpane.addnewpane(homescreen);
            }
        });
        
        root.add(login, 0 , 3);
        
        
        
        return root;
    }
    
    
   
    
    public void nieuwscherm(){
       
   }
}
