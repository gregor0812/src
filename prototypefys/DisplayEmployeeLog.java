/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 *
 * @author Michael Cheung
 */
public class DisplayEmployeeLog extends HomeScreen{
    private static Rootpane rootpane = new Rootpane();
    private static GridPane emplog = new GridPane();
    private static adminScherm terugscherm = new adminScherm();
    private static StackPane terugadminscherm = terugscherm.maakAdminScherm();
    private ObservableList<ObservableList> data;
    private TableView tableview;
    private TableView<Logs> EmployeeLog;
   private  ObservableList<Logs> logList;
   private Database db = new Database();
    DisplayEmployeeLog(){
        
    }
    public void buildData(){

        logList = FXCollections.observableArrayList();
        try{
                        Connection c = db.getConnection();
            Statement statement = c.createStatement();

            String SQL = "SELECT * from CUSTOMer";
            
            ResultSet rs = c.createStatement().executeQuery(SQL);
            
            logList = FXCollections.observableArrayList();
            while (rs.next()) {
                //Iterate Row
                // the data will be added to the arraylist
                EmployeeLog.setItems(logList);
                

            }
            
            }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");

        }
    }
    
    
    
   
    public GridPane employeelog() {
         Label response = new Label("");
        Label title = new Label("Corendon Employee Log\n");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.CADETBLUE);
        emplog.setHgap(10);
       emplog.setVgap(10);
       
        
        
        emplog.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(emplog, 900, 800);
        Connection c;

        
        {
        
    }
logList = FXCollections.observableArrayList(
                );
Connection connection;

        
        
        EmployeeLog = new TableView<Logs>(logList);
        
        TableColumn<Logs, Integer> EmployeeID = new TableColumn<>("employeenumber");
        EmployeeID.setCellValueFactory(new PropertyValueFactory<>("Employee"));
        EmployeeLog.getColumns().add(EmployeeID);
        
        TableColumn<Logs, String> EmployeeFirstName = new TableColumn<>("firstname");
        EmployeeFirstName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        EmployeeLog.getColumns().add(EmployeeFirstName);
      TableColumn<Logs, String> EmployeeLastName = new TableColumn<>("firstname");
       EmployeeLastName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        EmployeeLog.getColumns().add(EmployeeLastName);
        
        TableColumn<Logs, String> Action = new TableColumn<>("Action");
        Action.setCellValueFactory(new PropertyValueFactory<>("Action"));
        
        
       EmployeeLog.getColumns().add(Action);
        
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
            bt_backButton.setPrefSize(100, 50);

            bt_backButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
             rootpane.addnewpane(terugadminscherm);
            }
            });
        response.setFont(Font.font("Arial", 14));
        emplog.getChildren().addAll(title,EmployeeLog, response);
        emplog.add(bt_backButton, 1, 4);
        return emplog;
    }
    
    
    
    

    /**
     * @param args the command line arguments
     */
    
}
