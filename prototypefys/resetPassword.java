package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.mail.*;
import java.util.*;
import javafx.scene.control.Alert;
import javax.mail.internet.*;

/**
 *
 * @author IS-109-2
 */
public class resetPassword {

    //Dit is om in te loggen op de mail
    private static final String EMAIL_USER_NAME = "corendonis109";
    private static final String EMAIL_PASSWORD = "corendon109";
    private Database db = new Database();

    public resetPassword() {

    }

    public GridPane maakPasswordReset() {

        //Creates the gridpane and sets it's structure.
        GridPane basis = new GridPane();
        basis.setAlignment(Pos.CENTER);
        basis.setHgap(10);
        basis.setVgap(10);
        basis.setPadding(new Insets(10, 10, 10, 10));

        basis.setStyle("-fx-background-color: white");

        //Creates textarea for the username
        Text username = new Text("Username:");
        basis.add(username, 4, 2);
        username.setFill(Color.BLACK);
        TextField userText = new TextField();
        userText.setPrefColumnCount(1);
        userText.setPrefWidth(100);
        basis.add(userText, 5, 2, 1, 1);

        //Short explanation for the user.
        Text explanation = new Text("Your password will be resetted, your new password will be sent to you mailbox.");
        basis.add(explanation, 4, 0, 10, 1);
        username.setFill(Color.BLACK);

        //Creates the button that will activate the "password reset" procedure
        Button resetPassword = new Button();
        resetPassword.setText("Confirm");
        resetPassword.setPrefSize(100, 20);
        resetPassword.setStyle("-fx-background-color: darkred;-fx-text-fill:white");
        resetPassword.setOnAction((ActionEvent event) -> {
            try {

                Connection emailConnection = db.getConnection();
                Statement statement = emailConnection.createStatement();

                String email = getUserEmail(userText.getText());

                //generates new password
                String generatedPassword = PasswordGenerator.generate(10);
                String encryptedPassword = Encription.encrypt(generatedPassword);
                System.out.println(generatedPassword + ", ");

                String sql = "UPDATE employee "
                        + "SET password = ? "
                        + "WHERE email = ?";
                
                PreparedStatement ps = emailConnection.prepareStatement(sql);
                ps.setString(1, encryptedPassword);
                ps.setString(2, email);
                ps.executeUpdate();

                String RECIPIENT = email;
                String from = EMAIL_USER_NAME;
                String pass = EMAIL_PASSWORD;
                String[] to = {RECIPIENT};
                String subject = "Corendon password reset.";
                String body = "You have succesfully resetted your password, your "
                        + "new password is: \n\n" + generatedPassword
                        + "\n\nPlease store this password in a safe location.";
                //send mail
                sendFromGMail(from, pass, to, subject, body);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password reset ");
                alert.setContentText("Password reset has been sent");
                alert.showAndWait();

                userText.setText("");

            } catch (SQLException ex) {
                System.out.println("failed to reset password");
                System.out.println(ex);
            }
        });

        basis.add(resetPassword, 4, 3);

        //creates button that sends you back to the loginscreen
        Button back = new Button("Cancel");
        back.setStyle("-fx-base:darkred;-fx-border-color:white");
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

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            System.out.println(me);
        }
    }

    public String getUserEmail(String username) {

        String email = "";

        try {

            Connection emailConnection = db.getConnection();
            Statement statement = emailConnection.createStatement();

            ResultSet userEmail
                    = statement.executeQuery("select email from employee "
                            + "where username = '" + username + "'");

            while (userEmail.next()) {
                email = userEmail.getString(1);
            }

            System.out.println(email);

        } catch (Exception ex) {
            System.out.println("failed to retrieve user email ");
            System.out.println(ex);
        }

        return email;
    }
}
