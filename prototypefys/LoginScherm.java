package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Koen Hengsdijk
 */
public class LoginScherm {
    
    private Button login = new Button();
    private Button resetPassword = new Button();
    LoginScherm() {

    }
    
   
    
    
    
    private Database db = new Database();
    
    private HomeScreen nieuwscherm = new HomeScreen();
    private HBox homescreen = nieuwscherm.maakhomescreen();
    
    private homeScreenEmployee employeeScherm = new homeScreenEmployee();
    private HBox HomeEmployee= employeeScherm.maakhomescreen();
    
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
        
        Label usernameL = new Label("Username:");
        root.add(usernameL, 0, 1);
       
        TextField userText = new TextField();
        userText.setPrefColumnCount(1);
        userText.setPrefWidth(100);
        root.add(userText, 1, 1);

        Label passwordL = new Label("Password:");
        root.add(passwordL, 0, 2);
        
        PasswordField passwordText = new PasswordField();
        passwordText.setPrefColumnCount(1);
        passwordText.setPrefWidth(10);
        root.add(passwordText, 1, 2);

        
        
        Button login = new Button();
        login.setText("Login");
        login.setStyle("-fx-background-color: #009b91;-fx-text-fill:#eaaf00");
        
        Button resetPassword = new Button();
        resetPassword.setText("Reset password");
        resetPassword.setStyle("-fx-background-color: #ffffff;-fx-text-fill:BLACK");

        root.setStyle("-fx-background-color: #ffffff");
               
        

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                
                String username = userText.getText();
                String password = passwordText.getText();
                
                
                LoginCheck(username, password);
               // rootpane.addnewpane(homescreen);
            }
        });
        
        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("X");
                
               resetPassword scherm = new resetPassword();
               //StackPane resetPassword = scherm.maakPasswordReset();
               //rootpane.addnewpane(resetPassword);
               
            }
        });
        
        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                  String username = userText.getText();
                String password = passwordText.getText();
                
                
                LoginCheck(username, password);
                        
                    
                    break;
            }
        });
            
        root.add(login, 0 , 3);
        root.add(resetPassword, 0 , 4);
        
        
        return root;
    }
    
    
   public void LoginCheck(String username, String password){
       
       try {
        
        Connection firstConnection = db.getConnection();
        
       
        Statement statement = firstConnection.createStatement();
        Statement statement2 = firstConnection.createStatement();
        Statement statement3 = firstConnection.createStatement();
        System.out.println("database connected");
        
        ResultSet knownUsers
                = statement.executeQuery("SELECT username, password FROM employee;");
        
        System.out.println(knownUsers + "teststring");
        
        ResultSet NumberUsers = statement2.executeQuery("select count(*) as total from employee;");
       
        
        if (NumberUsers.next()){
        
        }
        
        String[] ListOfKnownUsers;
           ListOfKnownUsers = new String[1];
        
           
        for (int i = 0; knownUsers.next(); i++){
            ListOfKnownUsers[i] = 
                (knownUsers.getString(1) + " " + knownUsers.getString(2) );
                System.out.println(ListOfKnownUsers[i]);
        }
        
       String EnteredUser = username + " " + password;
      
       boolean GoodPassword = false;
       
       // checks the role
       String role= "";
       
       ResultSet roleSet = statement3.executeQuery("select role from employee " +
        "where password = '" +  password + "'");
       while(roleSet.next()){
        role = roleSet.getString(1);
       }
       
       
       for (int i = 0; i < ListOfKnownUsers.length; i++){
           if(EnteredUser.equals(ListOfKnownUsers[i])){
               
                if("admin".equals(role)){          
               
                rootpane.addnewpane(homescreen);
                GoodPassword = true;
                break;
                }
                else{
                    
                rootpane.addnewpane(HomeEmployee);
                GoodPassword = true;
                break;
                    
                }
                
                
           }
           
           
               
       }
       
       if (GoodPassword){
           firstConnection.close();
       }
       else{
       Alert alert = new Alert(Alert.AlertType.WARNING);
       alert.setTitle("Wrong password");
       alert.setHeaderText("Wrong password");
       alert.setContentText("Wrong password/username");
       alert.showAndWait();
       
       
       }   
        
           
        } catch (Exception ex){
            System.out.println("exception 2 ");
            System.out.println(ex);
        }
                    
       
              
           
   }
    
   
}
