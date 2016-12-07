/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 *
 * @author Michael Cheung
 */
public class DisplayEmployeeLog {
    Rootpane rootpane = new Rootpane();
    FlowPane emplog = new FlowPane();
    private static adminScherm terugscherm = new adminScherm();
    private static rootpane terugadminscherm = terugscherm.maakAdminScherm();
    
    DisplayEmployeeLog(){
        
    }
    
    
    
   
    public FlowPane employeelog() {
         Label response = new Label("");
        Label title = new Label("Corendon Employee Log\n");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.CADETBLUE);
       
        
        
        emplog.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(emplog, 900, 800);
        
        ObservableList<Logs> logList = FXCollections.observableArrayList(
                new Logs("001", "Fisher", "Deleted record"), 
                new Logs("002", "Freed", "Created new case"), 
                new Logs("003", "Keegan", "Edited case"), 
                new Logs("004", "Slattery", "Created new case"), 
                new Logs("005", "Young", "Deleted case"), 
                new Logs("006", "Jones", "Created new account"), 
                new Logs("007", "King", "Deleted account"), 
                new Logs("008", "Kauffman", "Changed password"), 
                new Logs("009", "Shilling", "Deleted record"), 
                new Logs("010", "Sigler", "Edited Case")
                );
    
        TableView<Logs> EmployeeLog;
        
        EmployeeLog = new TableView<Logs>(logList);
        
        TableColumn<Logs, String> fName = new TableColumn<>("Employee ID");
        fName.setCellValueFactory(new PropertyValueFactory<>("Employee"));
        EmployeeLog.getColumns().add(fName);
        
        TableColumn<Logs, String> lName = new TableColumn<>("Employee Name");
        lName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        EmployeeLog.getColumns().add(lName);
        
        TableColumn<Logs, String> cell = new TableColumn<>("Action");
        cell.setCellValueFactory(new PropertyValueFactory<>("Action"));
       EmployeeLog.getColumns().add(cell);
        
        EmployeeLog.setPrefWidth(800);
        EmployeeLog.setPrefHeight(600);
        
        TableView.TableViewSelectionModel<Logs> tvSelLogging = 
                EmployeeLog.getSelectionModel();
        
        tvSelLogging.selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal) {
                int index = (int)newVal;
                response.setText("The most recent action of this employee is "
                        +logList.get(index).getAction());
            }
        });
        
                    Button bt_backButton = new Button("Back ");
            bt_backButton.setPrefSize(150, 50);
            bt_backButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
             rootpane.addnewpane(terugscherm);
            }
            });
        response.setFont(Font.font("Arial", 14));
        emplog.getChildren().addAll(title,EmployeeLog, response);
        return emplog;
    }
    
    
    
    

    /**
     * @param args the command line arguments
     */
    
}
