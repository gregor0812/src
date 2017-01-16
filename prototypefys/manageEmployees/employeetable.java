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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import prototypefys.Encription;
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

        root.setStyle("-fx-background-color: white");
        
        Button backbuton = new Button("Back to administrator screen");
        backbuton.setPrefSize(180, 50);
        backbuton.setStyle("-fx-base:darkred;-fx-border-color:white");
        backbuton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                adminScherm scherm6 = new adminScherm();
                StackPane adminScherm = scherm6.maakAdminScherm();
                rootpane.addnewpane(adminScherm);
            }
        });
        
        // the table columns are made here
        TableColumn<Employee, String> employeenumberColumn = new TableColumn<>("employee number");
        employeenumberColumn.setCellValueFactory(new PropertyValueFactory<>("employeenumber"));
        employeenumberColumn.setMinWidth(100);
        
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
        
        TableColumn<Employee, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(160);

        try {
            // a query to select all the info from the database
            String query = "select * from employee";
            
            // a connection is mad
            Connection catalogueConnect = db.getConnection();
            Statement statement = catalogueConnect.createStatement();
            ResultSet TableData = statement.executeQuery(query);

            // this while loop gets data in the ovservable list
            data = FXCollections.observableArrayList();
            while (TableData.next()) {
                //while there is info in the resultset it will be added to the observable list
                data.add(new Employee(TableData.getInt(1), TableData.getString(2), TableData.getString(3), TableData.getString(4),
                    TableData.getString(5), TableData.getString(6), TableData.getString(7), TableData.getString(8)));
            }
            
            // the info is added to the tableview
            employeeView.setItems(data);
            // the columns are added to the tableview
            employeeView.getColumns().addAll(employeenumberColumn, usernameColumn,
                passwordColumn, firstnameColumn,
                insertionColumn, lastnameColumn, roleColumn, emailColumn);
            
            // the exception will catch and display an error if something goes wrong
        } catch (Exception ex) {
            System.out.println("Failed to load employee table ");
            System.out.println(ex);
        }
        
        // a button to edit the employee info
        Button editTable = new Button("Edit employee");
        editTable.setPrefSize(180, 50);
        editTable.setStyle("-fx-base:#BC3434;-fx-border-color:white");
        editTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // the selected info will be used to make an employee object
                Employee employee = employeeView.getSelectionModel().getSelectedItem();
                
                // if a table row is selected a editform will be made
                if (employee != null) {
                     // the form is added to the screen
                    rootpane.addnewpane(editUser(employee));
                } else {
                    // an alert is shown if there is no row selected
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Edit employee");
                    alert.setContentText("Select a employee to edit");

                    alert.showAndWait();
                }
            }
        });
        // this is a button to add a new employee
        Button addEmployee = new Button("Add employee");
        addEmployee.setPrefSize(180, 50);
        addEmployee.setStyle("-fx-base:#BC3434;-fx-border-color:white");
        addEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                // a form will be added to the screen to make the new employee
                rootpane.addnewpane(addUser());                                
            }
        });
        
        // this is a button to remove an employee
        Button removeEmployee = new Button("Remove employee");
        removeEmployee.setPrefSize(180, 50);
        removeEmployee.setStyle("-fx-base:#BC3434;-fx-border-color:white");
        removeEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                // a employee object will be made using the selected row
                Employee employee = employeeView.getSelectionModel().getSelectedItem();
                if (employee != null) {
                    // a confirmation popup will be shown to confirm if the user
                    // really wants to delete the employee
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Deleting employee");
                    alert.setContentText("Are you sure that you want "
                        + "to delete this employee?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                    removeUser(employee.getEmployeenumber());
                    
                    Alert confirmed = new Alert(AlertType.INFORMATION);
                    confirmed.setTitle("Confirmation Dialog");
                    confirmed.setHeaderText("Succes");
                    confirmed.setContentText("The employee was deleted ");
                    confirmed.showAndWait();
                    
                    rootpane.addnewpane(MaakEmployeeTable());
                    } else {
                    
                    }
                    
                    // a alert to show the user that they have not selected anything
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
                    alert.setHeaderText("Remove employee");
                    alert.setContentText("Select a employee to remove");

                    alert.showAndWait();
                }                                        
            }
        });
        
        // the tableview is added
        root.add(employeeView, 1, 0);
        // this vbox will contain the control buttons
        VBox buttonContainer = new VBox(10);
        // the buttons are added
        buttonContainer.getChildren().addAll(backbuton, editTable, addEmployee, 
            removeEmployee);
        // the vbox is added to the screen
        root.add(buttonContainer, 0, 0);
        root.setAlignment(Pos.CENTER);
        return root;
    }
    
    // this method will generate an edit form using a employee object
    public GridPane editUser(Employee employee) {
        
        // a button to return to the employeetable
        Button btnmainmenu;
        // a button to sumbit the changes
        Button btnS;

        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("Return to employee view");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));

        btnS = new Button(); // button Submit
        btnS.setText("Submit changes");
        btnS.setPrefSize(160, 50);
        btnS.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnS.setFont(Font.font("Verdana", 12));

        // a gridpane to contain all the buttons and fields
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

        Label employeeLabel = new Label("Employee data");
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
        
        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 10, 23, 10, 1);
        TextField emailText = new TextField(employee.getEmail());
        grid.add(emailText, 20, 23);        

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);

        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                // the employee data is received from the textfields
                int employeenumber = Integer.parseInt(employeenumberText.getText());
                String username = userNameText.getText();
                String password = passwordText.getText();
                String firstname = firstNameText.getText();    
                String insertion = InsertionText.getText();
                String lastname = lastnameText.getText();
                String role = roleText.getText();
                String email = emailText.getText();
                
                // this method will update the employee info
                updateUser(employeenumber, username, password, firstname,
                    insertion, lastname, role, email);

            }
        });

        return grid;

    }

    public void updateUser(int employeenumber, String username, String password, String firstname,
        String insertion, String lastname, String role, String email) {

        try {

            // a connection is made
            Connection employeetableConnect = db.getConnection();
            Statement statement = employeetableConnect.createStatement();
            
            // Encrupt the password
            password = Encription.encrypt(password);
            
            // this query will update the user info using the data entered into the method
            String databaseQuery = ("UPDATE `corendon`.`employee` SET username ="
                + "'" + username + "', " + "`password`='" + password + "', firstname = "
                + "'" + firstname + "', insertion = '" + insertion + "', "
                + "lastname = '" + lastname + "', role = '" + role + "', email = '" + email + "' "
                + "WHERE `employeenumber`= " + employeenumber + "; ");
            
           // String databaseQuery = ("UPDATE `corendon`.`employee` SET `password`" + generatedPassword + " WHERE `employeenumber`= " + employeenumber + "; ");

            System.out.println(databaseQuery);
            
            // the query is executed here
            statement.executeUpdate(databaseQuery);
            

        } catch (Exception ex) {
            System.out.println("Failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }
    // this method returns a gridpane with a form to add users
    public GridPane addUser() {

        Button btnmainmenu;

        Button btnS;

        // ------------------------------
        btnmainmenu = new Button(); // button 1
        btnmainmenu.setText("Return to employee view");
        btnmainmenu.setPrefSize(250, 50);
        btnmainmenu.setStyle("-fx-base:darkred;-fx-border-color:white");
        btnmainmenu.setFont(Font.font("Verdana", 12));
        // ------------------------------

        //--------------------------------
        btnS = new Button(); // button Submit
        btnS.setText("Create new employee");
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
                
                // this will add the employeeview table to the screen
                rootpane.addnewpane(MaakEmployeeTable());
            }
        });

        Label Case = new Label("Employee data");
        Case.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(Case, 10, 15, 15, 1);
        
        Label employeenumberlabel = new Label("Employee number:");
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
        

        Label lastnameLabel = new Label("Lastname:");
        grid.add(lastnameLabel, 10, 21, 10, 1);
        TextField lastnameText = new TextField();
        grid.add(lastnameText, 20, 21);

        Label roleLabel = new Label("Role:");
        grid.add(roleLabel, 10, 22, 10, 1);
        TextField roleText = new TextField();
        grid.add(roleText, 20, 22);
        
        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 10, 23, 10, 1);
        TextField emailText = new TextField();
        grid.add(emailText, 20, 23);

        ImageView Corendon = new ImageView("/resources/corendon.jpg");
        Corendon.setFitHeight(100);
        Corendon.setFitWidth(300);

        grid.add(Corendon, 1, 1, 10, 10);

        // Toevoegen van buttons
        grid.getChildren().addAll(btnmainmenu, btnS);
        
        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                // the employee information will be gotten from the textfields
                int employeenumber = Integer.parseInt(employeenumberText.getText());
                String username = userNameText.getText();
                String password = passwordText.getText();
                String firstname = firstNameText.getText();    
                String insertion = InsertionText.getText();
                String lastname = lastnameText.getText();
                String role = roleText.getText();
                String email = emailText.getText();
                
               
                
                // using the adduser method the employee info is added to the database
                addUser(employeenumber, username, password, firstname,
                    insertion, lastname, role, email);

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
            System.out.println("Exception 2 ");
        }

        return newEmployeenumber;
        
    }
    
    
    public void addUser(int employeenumber, String username, String password, String firstname,
        String insertion, String lastname, String role, String email) {

        try {

            // a connection is made
            Connection employeetableConnect = db.getConnection();
            Statement statement = employeetableConnect.createStatement();

            // Encrypt the password
            password = Encription.encrypt(password);
            
            String databaseQuery = ("insert into employee" +
            " values (" + employeenumber + ", '" + username + "', '" + password 
                + "', '" + firstname + "', '" + insertion + "', '" + lastname 
                + "', '" + role + "', '" + email + "')");

            System.out.println(databaseQuery);

            statement.executeUpdate(databaseQuery);
            
             // this alert will confirm that the user is added to the database
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add user");
                alert.setHeaderText("User added");
                alert.setContentText("User is added");
                alert.showAndWait();
            

        } catch (Exception ex) {
            System.out.println("Failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }
    
    public void removeUser(int employeenumber) {

        try {

            // a connection is made
            Connection employeetableConnect = db.getConnection();
            Statement statement = employeetableConnect.createStatement();

            String databaseQuery = ("DELETE FROM `corendon`.`employee` WHERE"
                + " `employeenumber`=" + employeenumber + ";");

            System.out.println(databaseQuery);

            statement.executeUpdate(databaseQuery);
            

        } catch (Exception ex) {
            System.out.println("Failed to insert data in to the database ");
            System.err.println(ex.getMessage());
        }

    }
    
    
}
