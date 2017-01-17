package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    

    private homeScreenEmployee employeeScherm = new homeScreenEmployee();
    private HBox HomeEmployee = employeeScherm.maakhomescreen();

    Rootpane rootpane = new Rootpane();

    public GridPane MaakHetScherm() {

         StatisticsPDF test = new StatisticsPDF();
         test.makeStatistics();
        
        
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

        login.setStyle("-fx-background-color: #ffffff;-fx-text-fill:BLACK");

        login.setStyle("-fx-background-color: darkred;-fx-text-fill:white");

        Button resetPassword = new Button();
        resetPassword.setText("Reset password");
        resetPassword.setStyle("-fx-background-color: darkred;-fx-text-fill:white");

        root.setStyle("-fx-background-color: #baf9ff");
        root.setStyle("-fx-background-color: white");

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String username = userText.getText();
                String password = passwordText.getText();

                if (loginCheck(username, password)) {
                    HBox homescreen = nieuwscherm.maakhomescreen();
                    rootpane.addnewpane(homescreen);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Verification error");
                    alert.setHeaderText("Wrong password/username");
                    alert.setContentText("Wrong password/username");
                    alert.showAndWait();
                }

                // rootpane.addnewpane(homescreen);
            }
        });

        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                resetPassword scherm = new resetPassword();

                GridPane resetPassword = scherm.maakPasswordReset();
                rootpane.addnewpane(resetPassword);

            }
        });

        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    login.fire();
                    break;
            }
        });

        root.add(login, 0, 3);
        root.add(resetPassword, 0, 4);

        return root;
    }

    /**
     * Check the entered credentials and save the account info
     *
     * @param username The username used to login
     * @param password The password used to login
     * @return True if the verification is successfull
     */
    public boolean loginCheck(String username, String password) {
        System.out.println(password);

        // Encrypt password
        password = Encription.encrypt(password);
        System.out.println(password);

        // SQL query to 
        String sql = "SELECT employeenumber, username, password, firstname, "
                + "insertion, lastname, role, email "
                + "FROM employee "
                + "WHERE username = '" + username + "' "
                + "AND password = '" + password + "' "
                + "LIMIT 1";
        System.out.println(sql);

        try {
            // Load the databse connection
            Connection conn = db.getConnection();
            // Create a statement
            Statement stmt = conn.createStatement();
            // Create a resultset to store all 
            ResultSet rs = stmt.executeQuery(sql);
            // Loop through all results
            while (rs.next()) {
                // Save user data in
                DataCache.setEmployeenumber(rs.getInt("employeenumber"));
                DataCache.setUsername(rs.getString("username"));
                DataCache.setPassword(rs.getString("password"));
                DataCache.setFirstname(rs.getString("firstname"));
                DataCache.setInsertion(rs.getString("insertion"));
                DataCache.setLastname(rs.getString("lastname"));
                DataCache.setRole(rs.getString("role"));
                DataCache.setEmail(rs.getString("email"));

                // Return true
                return true;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.out.println(e);
        }

        return false;

    }

}
