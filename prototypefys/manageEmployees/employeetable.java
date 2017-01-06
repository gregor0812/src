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
import javafx.scene.control.Alert;
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

        TableColumn<Employee, String> employeenumberColumn = new TableColumn<>("employee number");
        employeenumberColumn.setCellValueFactory(new PropertyValueFactory<>("employeenumber"));

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
                data.add(new Employee(TableData.getInt(1), TableData.getString(2), TableData.getString(3), TableData.getString(4),
                    TableData.getString(5), TableData.getString(6), TableData.getString(7)));
            }
            employeeView.setItems(data);
            employeeView.getColumns().addAll(employeenumberColumn, usernameColumn,
                passwordColumn, firstnameColumn,
                insertionColumn, lastnameColumn, roleColumn);

        } catch (Exception ex) {
            System.out.println("failed to load employee table ");
            System.out.println(ex);
        }

        Button editTable = new Button("edit employee");
        editTable.setPrefSize(180, 20);
        editTable.setStyle("-fx-base:darkred;-fx-border-color:black");
        editTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Employee employee = employeeView.getSelectionModel().getSelectedItem();

                if (employee != null) {

                    rootpane.addnewpane(editUser(employee));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("error");
                    alert.setHeaderText("edit employee");
                    alert.setContentText("Select a employee to edit");

                    alert.showAndWait();
                }

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

        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("return to employee view");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));

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
        GridPane.setConstraints(btnS, 20, 25);
        grid.setStyle("-fx-background-color: white");

        btnmainmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rootpane.addnewpane(MaakEmployeeTable());
            }
        });

        Label employeeLabel = new Label("employeedata");
        employeeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(employeeLabel, 10, 15, 15, 1);

        Label employeenumberLabel = new Label("Employee number:");
        grid.add(employeenumberLabel, 10, 16, 10, 1);
        TextField employeenumberText = new TextField(Integer.toString(employee.getEmployeenumber()));
        employeenumberText.setDisable(true);
        grid.add(employeenumberText, 20, 16);

        Label userNameLabel = new Label("Username:");
        grid.add(userNameLabel, 10, 17, 10, 1);
        TextField userNameText = new TextField(employee.getUsername());
        grid.add(userNameText, 20, 17);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 10, 18, 10, 1);
        TextField passwordText = new TextField(employee.getPassword());
        grid.add(passwordText, 20, 18);

        Label firstNameLabel = new Label("Firstname:");
        grid.add(firstNameLabel, 10, 19, 10, 1);
        TextField firstNameText = new TextField(employee.getFirstname());
        grid.add(firstNameText, 20, 19);

        Label InsertionLabel = new Label("Insertion:");
        grid.add(InsertionLabel, 10, 20, 10, 1);
        TextField InsertionText = new TextField(employee.getInsertion());
        grid.add(InsertionText, 20, 20);
       

        Label lastnameLabel = new Label("Lastname:");
        grid.add(lastnameLabel, 10, 21, 10, 1);
        TextField lastnameText = new TextField(employee.getLastname());
        grid.add(lastnameText, 20, 21);

        Label roleLabel = new Label("Role:");
        grid.add(roleLabel, 10, 22, 10, 1);
        TextField roleText = new TextField(employee.getRole());
        grid.add(roleText, 20, 22);

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);

        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int employeenumber = Integer.parseInt(employeenumberText.getText());
                String username = userNameText.getText();
                String password = passwordText.getText();
                String firstname = firstNameText.getText();    
                String insertion = InsertionText.getText();
                String lastname = lastnameText.getText();
                String role = roleText.getText();
                
                
                
                updateUser(employeenumber, username, password, firstname,
                    insertion, lastname, role);

            }
        });

        return grid;

    }

    public void updateUser(int employeenumber, String username, String password, String firstname,
        String insertion, String lastname, String role) {

        try {

            // a connection is made
            Connection employeetableConnect = db.getConnection();
            Statement statement = employeetableConnect.createStatement();

            String databaseQuery = ("UPDATE `corendon`.`employee` SET username ="
                + "'" + username + "', " + "`password`='" + password + "', firstname = "
                + "'" + firstname + "', insertion = '" + insertion + "', "
                + "lastname = '" + lastname + "', role = '" + role + "' "
                + "WHERE `employeenumber`= " + employeenumber + "; ");

            System.out.println(databaseQuery);

            statement.executeUpdate(databaseQuery);
            

        } catch (Exception ex) {
            System.out.println("failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

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
        grid.add(Case, 10, 15, 15, 1);
        
        Label employeenumberlabel = new Label("employee number:");
        grid.add(employeenumberlabel, 10, 16, 10, 1);
        TextField employeenumberText = new TextField(Integer.toString(getEmployeenumber()));
        employeenumberText.setDisable(true);
        grid.add(employeenumberText, 20, 16);
        
        
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 10, 17, 10, 1);
        TextField userNameText = new TextField();
        grid.add(userNameText, 20, 17);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 10, 18, 10, 1);
        TextField passwordText = new TextField();
        grid.add(passwordText, 20, 18);

        Label firstnameLabel = new Label("Firstname:");
        grid.add(firstnameLabel, 10, 19, 10, 1);
        TextField firstNameText = new TextField();
        grid.add(firstNameText, 20, 19);

        Label insetionLabel = new Label("Insertion:");
        grid.add(insetionLabel, 10, 20, 10, 1);
        TextField InsertionText = new TextField();
        grid.add(InsertionText, 20, 20);
        InsertionText.setEditable(false);

        Label lastnameLabel = new Label("Lastname:");
        grid.add(lastnameLabel, 10, 21, 10, 1);
        TextField lastnameText = new TextField();
        grid.add(lastnameText, 20, 21);

        Label roleLabel = new Label("Role:");
        grid.add(roleLabel, 10, 22, 10, 1);
        TextField roleText = new TextField();
        grid.add(roleText, 20, 22);

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);
        
        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int employeenumber = Integer.parseInt(employeenumberText.getText());
                String username = userNameText.getText();
                String password = passwordText.getText();
                String firstname = firstNameText.getText();    
                String insertion = InsertionText.getText();
                String lastname = lastnameText.getText();
                String role = roleText.getText();
                
                
                
                addUser(employeenumber, username, password, firstname,
                    insertion, lastname, role);

            }
        });

        return grid;

    }
    
    public int getEmployeenumber(){
        
        int newEmployeenumber = 0;

        try {

            Connection ReportGenerationConnect = db.getConnection();
            Statement statement = ReportGenerationConnect.createStatement();
            ResultSet TableData = statement.executeQuery("select max(employeenumber)"
                + " from employee");

            while (TableData.next()) {
                newEmployeenumber = TableData.getInt(1) + 1;

            }

        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }

        return newEmployeenumber;
        
    }
    
    
    public void addUser(int employeenumber, String username, String password, String firstname,
        String insertion, String lastname, String role) {

        try {

            // a connection is made
            Connection employeetableConnect = db.getConnection();
            Statement statement = employeetableConnect.createStatement();

            String databaseQuery = ("insert into employee" +
            " values (" + employeenumber + ", ' " + username + "', ' " + password 
                + "', ' " + firstname + "', '" + insertion + "', ' " + lastname 
                + "', '" + role + "')");

            System.out.println(databaseQuery);

            statement.executeUpdate(databaseQuery);
            

        } catch (Exception ex) {
            System.out.println("failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }
    
}
