/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import cataloog.FoundLuggage;
import cataloog.LostLuggage;
import java.util.Optional;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
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
public class FoundLuggageMail {

    FoundLuggageMail() {
    }

    private Rootpane rootpane = new Rootpane();
    private static final String EMAIL_USER_NAME = "corendonis109";
    private static final String EMAIL_PASSWORD = "corendon109";

    private static boolean sendOrNot = true;

    /**
     *
     * @param lostInfo a instance of the lostluggage class containing the 
     * information of the lost luggage
     * @param foundInfo a instancce of the foundluggage class containing the 
     * information of the foundluggage
     * @return this return a gridpane containing the email maker
     */
    public GridPane makeTheMail(LostLuggage lostInfo, FoundLuggage foundInfo) {
        
        // the gridpane that serves as a basis
        GridPane root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(8);
        root.setHgap(10);
        root.setAlignment(Pos.CENTER);
        
        // the corendon logo
        ImageView Corendon = new ImageView("/resources/CorendonAirlines.png");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        root.add(Corendon, 0, 0, 3, 1);
        root.setStyle("-fx-background-color: white");
        
        // the label and textfield of the person that receives the email
        Label emailL = new Label("to: ");
        root.add(emailL, 1, 1);
        TextField emailT = new TextField();
        root.add(emailT, 2, 1);
        
        // the label and textfield of the subject of the email
        Label subjectL = new Label("subject: ");
        root.add(subjectL, 1, 2);
        TextField subjectT = new TextField();
        root.add(subjectT, 2, 2);
        
        // the label and textarea that will contain the text of the email
        Label emailLabel = new Label("email text:");
        root.add(emailLabel, 1, 3);
        TextArea emailBody = new TextArea();
        root.add(emailBody, 1, 4, 4, 1);
        
        // a button that will return to the matchview
        Button btnBack = new Button(); // button Submit
        btnBack.setText("back to matchview");
        btnBack.setPrefSize(160, 50);
        btnBack.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnBack.setFont(Font.font("Verdana", 12));
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                matchInformatie backtoInfo = new matchInformatie();
                // the rootpane will add the matchview pane
                rootpane.addnewpane(backtoInfo.matchInfo(lostInfo, foundInfo));
            }
        });
        GridPane.setConstraints(btnBack, 1, 8, 2, 2);
        
        // the button that sends the email
        Button btnS = new Button(); // button Submit
        btnS.setText("sent email");
        btnS.setPrefSize(100, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));
        btnS.setOnAction((ActionEvent event) -> {

            

            //generates new password
            int generatedPassword;
            generatedPassword = (int) (Math.random() * 999999 + 99999);
            System.out.println(generatedPassword);

            //String encryptedPassword = Encription.encrypt(generatedPassword);
            String from = EMAIL_USER_NAME;
            String pass = EMAIL_PASSWORD;
            String to = emailT.getText();
            String subject = subjectT.getText();
            String body = emailBody.getText();
            //send mail
            matchEmail(from, pass, to, subject, body);

            if (sendOrNot) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Email sent ");
                alert.setContentText("the email has been sent");

                ButtonType okButton = new ButtonType("write a new email", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType ViewMatchBtn = new ButtonType("go back to matchview");
                alert.getButtonTypes().setAll(okButton, ViewMatchBtn);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ViewMatchBtn) {
                    matchInformatie backtoInfo = new matchInformatie();
                    rootpane.addnewpane(backtoInfo.matchInfo(lostInfo, foundInfo));
                } else if (result.get() == okButton) {
                    FoundLuggageMail newmail = new FoundLuggageMail();
                    GridPane createMailForm = newmail.makeTheMail(lostInfo, foundInfo);

                    rootpane.addnewpane(createMailForm);

                }
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Email ");
                alert.setContentText("failed to send e-mail");
                alert.showAndWait();
            }

        });

        GridPane.setConstraints(btnS, 3, 8, 2, 2);

        root.getChildren().addAll(btnS, btnBack);

        return root;
    }
    
     /**
     *
     * @param from the sender of the emmail
     * @param pass the password of the send of the email
     * @param to the receiver of the email
     * @param subject the subject of the email
     * @param body  the text of the email
     */
    
    private static void matchEmail(String from, String pass, String to,
        String subject, String body) {
        
        // the properties of te email
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
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            //message.setText(body);

            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);
            // the match confirmation form
            String filename = "src\\resources\\pdf\\filledinConfirmation\\FilledMatchInformation.pdf";
            DataSource source = new FileDataSource(filename);
            BodyPart attachtmentBodyPart = new MimeBodyPart();
            attachtmentBodyPart.setDataHandler(new DataHandler(source));
            attachtmentBodyPart.setFileName(filename);
            // the dhl shipment form
            String dhlFile = "src\\resources\\pdf\\DHLShipmentForm.pdf";
            DataSource source2 = new FileDataSource(dhlFile);
            BodyPart dhlBodyPart = new MimeBodyPart();
            dhlBodyPart.setDataHandler(new DataHandler(source2));
            dhlBodyPart.setFileName(filename);
            multipart.addBodyPart(dhlBodyPart);

            multipart.addBodyPart(attachtmentBodyPart);

            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException me) {
            sendOrNot = false;
            System.out.println(me);
        }
    }
}
