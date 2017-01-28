/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import database.Database;
import deletedCasesPackage.deletedCasesView;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import prototypefys.manageEmployees.employeetable;

/**
 *
 * @author IS-109-2
 */
public class adminScherm {

    private Database db = new Database();

    Rootpane rootpane = new Rootpane();

    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();
    private static DisplayEmployeeLog employeelog = new DisplayEmployeeLog();
    private static GridPane employeeScherm = employeelog.employeelog();

    private static final Rootpane basisPane = new Rootpane();

    public adminScherm() {

    }

    public StackPane maakAdminScherm() {

        // make the main area
        StackPane basis = new StackPane();

        HBox adminstart = new HBox(200);
        adminstart.setAlignment(Pos.CENTER);

        VBox vbox1 = new VBox(20);
        vbox1.setAlignment(Pos.CENTER);

        adminstart.setStyle("-fx-background-color: white");

        // make a empoyee view screen
        GridPane EmployeeData = new GridPane();

        Button bt_MA = new Button("Manage Accounts ");
        bt_MA.setStyle("-fx-base:darkred;-fx-border-color:white");
        bt_MA.setPrefSize(180, 50);
        bt_MA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                employeetable makeTable = new employeetable();
                GridPane table = makeTable.MaakEmployeeTable();
                rootpane.addnewpane(table);
            }
        });

        Button bt_VL = new Button("View Logs ");
        bt_VL.setPrefSize(180, 50);
        bt_VL.setStyle("-fx-base:darkred;-fx-border-color:white");
        bt_VL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                rootpane.addnewpane(employeeScherm);
            }
        });

        Button bt_VDC = new Button("View Deleted Cases ");
        bt_VDC.setPrefSize(180, 50);
        bt_VDC.setStyle("-fx-base:darkred;-fx-border-color:white");
        bt_VDC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                deletedCasesView instantie = new deletedCasesView();

                rootpane.addnewpane(instantie.makeTableView());

            }
        });

        Button bt_backButton = new Button("Back ");
        bt_backButton.setPrefSize(180, 50);
        bt_backButton.setStyle("-fx-base:darkred;-fx-border-color:white");
        bt_backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rootpane.addnewpane(homescreen);
            }
        });

        Button deleteCases = new Button("delete old cases");
        deleteCases.setPrefSize(180, 50);
        deleteCases.setStyle("-fx-base:darkred;-fx-border-color:white");
        deleteCases.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Create a dialog to delete cases
                Dialog dialog = new Dialog<>();
                dialog.setTitle("delete old cases");
                dialog.setHeaderText("select a date to delete cases \n "
                    + "older then that date");

                DatePicker datePicker = new DatePicker();
                datePicker.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        LocalDate date = datePicker.getValue();
                        System.err.println("Selected date: " + date);
                    }
                });

                // this pattern is the data format used
                String pattern = "yyyy-MM-dd";

                datePicker.setPromptText(pattern.toLowerCase());

                datePicker.setConverter(new StringConverter<LocalDate>() {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                    @Override
                    public String toString(LocalDate date) {
                        if (date != null) {
                            return dateFormatter.format(date);
                        } else {
                            return "";
                        }
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        if (string != null && !string.isEmpty()) {
                            return LocalDate.parse(string, dateFormatter);
                        } else {
                            return null;
                        }
                    }
                });

                Button deleteSelection = new Button("delete cases");
                deleteSelection.setStyle("-fx-base:darkred;-fx-border-color:white");
                deleteSelection.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                            DeleteCase(datePicker.getValue().toString());
                           Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("succes");
                        alert.setContentText("the cases have been deleted");

                        alert.showAndWait();
                            
                            
                            dialog.close();
                    }
                });

                // gridpane to add the datepicker
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));
                grid.add(datePicker, 1, 0);
                grid.add(deleteSelection, 1, 2);
                
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(false);
                
                
                dialog.getDialogPane().setContent(grid);

                dialog.showAndWait();

            }
        });

        vbox1.getChildren().add(bt_MA);
        vbox1.getChildren().add(bt_VL);
        vbox1.getChildren().add(bt_VDC);
        vbox1.getChildren().add(deleteCases);
        vbox1.getChildren().add(bt_backButton);

        adminstart.getChildren().addAll(vbox1);

        basis.getChildren().add(adminstart);

        Button bt_goback = new Button("Back");
        EmployeeData.add(bt_goback, 1, 1);

        bt_goback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                basis.getChildren().clear();
                basis.getChildren().add(adminstart);
            }
        });

        return basis;

    }

    public void DeleteCase(String datechosen) {

        try {
            // a connection is made
            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            // this query will delete the selected row from the database
            statement.executeUpdate(" update lostluggage set destroyed = 1  "
                + "where `date lost` < '" + datechosen + "'; ");
            
            statement.executeUpdate("update foundluggage set destroyed = 1  "
                + "where dateFound < '" + datechosen + "';");

        } catch (Exception ex) {
            System.out.println("Failed to delete case ");
            System.out.println(ex);
        }

    }

}
