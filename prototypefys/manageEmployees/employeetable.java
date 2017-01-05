/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys.manageEmployees;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prototypefys.Rootpane;
import prototypefys.adminScherm;

/**
 *
 * @author Koen Hengsdijk
 */
public class employeetable {

    Rootpane rootpane = new Rootpane();
    private ObservableList<Employee> data;
    private TableView<Employee> employeeView = new TableView();
    private Database db = new Database();

    public employeetable() {
    }

    public GridPane MaakEmployeeTable() {
        
        employeeView.getItems().clear();
            employeeView.getColumns().clear();
        employeeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        GridPane root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(8);
        root.setHgap(10);
        
                
        Button backbuton = new Button("back to adminscreen");
        backbuton.setPrefSize(180, 20);
        backbuton.setStyle("-fx-base:darkred;-fx-border-color:black");
        backbuton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                adminScherm scherm6 = new adminScherm();
                StackPane adminScherm = scherm6.maakAdminScherm();
                rootpane.addnewpane(adminScherm);
            }
        });

        TableColumn<Employee, String> usernameColumn = new TableColumn<>("username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Employee, String> passwordColumn = new TableColumn<>("password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Employee, String> firstnameColumn = new TableColumn<>("first name");
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        TableColumn<Employee, String> insertionColumn = new TableColumn<>("insertion");
        insertionColumn.setCellValueFactory(new PropertyValueFactory<>("insertion"));

        TableColumn<Employee, String> lastnameColumn = new TableColumn<>("lastname");
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        TableColumn<Employee, String> roleColumn = new TableColumn<>("role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        try {

            // a connection is made
            String query = "select * from employee";

            Connection catalogueConnect = db.getConnection();
            Statement statement = catalogueConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            // this while loop gets data in the ovservable list
            data = FXCollections.observableArrayList();
            while (TableData.next()) {
                //Iterate Row

                data.add(new Employee(TableData.getString(1), TableData.getString(2),
                    TableData.getString(3), TableData.getString(4), TableData.getString(5), TableData.getString(6)));

            }

            employeeView.setItems(data);
            employeeView.getColumns().addAll(usernameColumn, passwordColumn, firstnameColumn,
                insertionColumn, lastnameColumn, roleColumn);

        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        Button editTable = new Button("edit employee");
        editTable.setPrefSize(180, 20);
        editTable.setStyle("-fx-base:darkred;-fx-border-color:black");
        editTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Employee employee = employeeView.getSelectionModel().getSelectedItem();
               rootpane.addnewpane(editUser(employee));            
            }
        });
        
        Button addEmployee = new Button("add employee");
        addEmployee.setPrefSize(180, 20);
        addEmployee.setStyle("-fx-base:darkred;-fx-border-color:black");
        addEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
               rootpane.addnewpane(addUser());            
            }
        });
        
        
        root.add(employeeView, 0, 0);

        VBox buttonContainer = new VBox(10);

        buttonContainer.getChildren().addAll(backbuton, editTable, addEmployee);

        root.add(buttonContainer, 1, 0);

        return root;
    }
        
    public GridPane editUser(Employee employee) {

        Button btnmainmenu;
        
        Button btnS;

        
        // ------------------------------
        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("return to employee view");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));
        // ------------------------------

        //--------------------------------
        btnS = new Button(); // button Submit
        btnS.setText("Submit changes");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btnmainmenu, 1, 15);

        // GridPane.setConstraints(btn2, 2, 15);
        GridPane.setConstraints(btnS, 20, 25);

        grid.setStyle("-fx-background-color: white");

        btnmainmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                

                rootpane.addnewpane(MaakEmployeeTable());
            }
        });

        Label Case = new Label("employeedata");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        

        Label date = new Label("Username:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField(employee.getUsername());
        grid.add(dateT, 20, 17);
        

        Label time = new Label("Password:");
        grid.add(time, 10, 18, 10, 1);
        TextField timeT = new TextField(employee.getPassword());
        grid.add(timeT, 20, 18);

        Label airport = new Label("Firstname:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airtportT = new TextField(employee.getFirstname());
        grid.add(airtportT, 20, 19);

        Label labelNumber = new Label("Insertion:");
        grid.add(labelNumber, 10, 20, 10, 1);
        TextField labelNumberT = new TextField(employee.getInsertion());
        grid.add(labelNumberT, 20, 20);
        labelNumberT.setEditable(false);

        Label flight = new Label("Lastname:");
        grid.add(flight, 10, 21, 10, 1);
        TextField flightT = new TextField(employee.getLastname());
        grid.add(flightT, 20, 21);

        Label destination = new Label("Role:");
        grid.add(destination, 10, 22, 10, 1);
        TextField destinationT = new TextField(employee.getRole());
        grid.add(destinationT, 20, 22);

        
        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);

        return grid;

    }
    public GridPane addUser() {

        Button btnmainmenu;
        
        Button btnS;

        
        // ------------------------------
        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("return to employee view");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));
        // ------------------------------

        //--------------------------------
        btnS = new Button(); // button Submit
        btnS.setText("create new employee");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btnmainmenu, 1, 15);

        // GridPane.setConstraints(btn2, 2, 15);
        GridPane.setConstraints(btnS, 20, 25);

        grid.setStyle("-fx-background-color: white");

        btnmainmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                

                rootpane.addnewpane(MaakEmployeeTable());
            }
        });

        Label Case = new Label("employee data");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 16, 15, 1);

        

        Label date = new Label("Username:");
        grid.add(date, 10, 17, 10, 1);
        TextField dateT = new TextField();
        grid.add(dateT, 20, 17);
        

        Label time = new Label("Password:");
        grid.add(time, 10, 18, 10, 1);
        TextField timeT = new TextField();
        grid.add(timeT, 20, 18);

        Label airport = new Label("Firstname:");
        grid.add(airport, 10, 19, 10, 1);
        TextField airtportT = new TextField();
        grid.add(airtportT, 20, 19);

        Label labelNumber = new Label("Insertion:");
        grid.add(labelNumber, 10, 20, 10, 1);
        TextField labelNumberT = new TextField();
        grid.add(labelNumberT, 20, 20);
        labelNumberT.setEditable(false);

        Label flight = new Label("Lastname:");
        grid.add(flight, 10, 21, 10, 1);
        TextField flightT = new TextField();
        grid.add(flightT, 20, 21);

        Label destination = new Label("Role:");
        grid.add(destination, 10, 22, 10, 1);
        TextField destinationT = new TextField();
        grid.add(destinationT, 20, 22);

        

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);

        return grid;

    }
}
