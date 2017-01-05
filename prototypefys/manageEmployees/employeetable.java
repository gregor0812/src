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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
    
    public employeetable(){
    }
    
    public  GridPane MaakEmployeeTable() {
        GridPane root = new GridPane();
        
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
        
        root.add(backbuton, 1, 1);
            
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
                TableData.getString(3), TableData.getString(4), TableData.getString(5)
                ,TableData.getString(6)));

            }

            
            employeeView.setItems(data);
            employeeView.getColumns().addAll(usernameColumn, passwordColumn, firstnameColumn, 
                insertionColumn, lastnameColumn, roleColumn );
            
        } catch (Exception ex) {
            System.out.println("exception 2 ");
        }
        
        root.add(employeeView, 0, 0);
        
        
        return root;
    }
    
}
