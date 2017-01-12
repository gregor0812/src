package prototypefys;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Daniel
 */
public class resetPassword {

    public resetPassword() {

    }

    public GridPane maakPasswordReset() {

        GridPane basis = new GridPane();
        basis.setAlignment(Pos.CENTER);
        basis.setHgap(10);
        basis.setVgap(10);
        basis.setPadding(new Insets(10, 10, 10, 10));

        Text username = new Text("Username:");
        basis.add(username, 4, 2);
        username.setFill(Color.BLACK);
        TextField userText = new TextField();
        userText.setPrefColumnCount(1);
        userText.setPrefWidth(100);
        basis.add(userText, 5, 2);
        
        Text explanation = new Text("Your password will be resetted, your new password will be sent to you mailbox");
        basis.add(explanation, 4, 0);
        username.setFill(Color.BLACK);


        Button resetPassword = new Button();
        resetPassword.setText("Reset password");
        resetPassword.setStyle("-fx-background-color: #009b91;-fx-text-fill:#eaaf00");

        basis.add(resetPassword, 4, 3);
        
        
        
        Button back = new Button("Logout ");
            back.setStyle("-fx-background-color: #009b91;-fx-text-fill:#eaaf00");  
            back.setPrefSize(100, 20);
            back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginScherm scherm1 = new LoginScherm();
                GridPane loginScherm = scherm1.MaakHetScherm();
                Rootpane rootpane = new Rootpane();
                rootpane.addnewpane(loginScherm);
            }
            });
            
            basis.add(back, 4, 6);

        return basis;

    }

    //int generatedPassword;
    //generatedPass = (int) (Math.random() * 999999 + 100000);
    //System.out.println(generatedPassword);
}
