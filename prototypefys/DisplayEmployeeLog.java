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
    private ObservableList<EmployeeTracking> data;
    private TableView tableview;
    private TableView EmployeeLog;
   private  ObservableList logList;
   private Database db = new Database();
    DisplayEmployeeLog(){
         
    }
    public void buildData(){
 
        
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
 
         
        {
         
    }
logList = FXCollections.observableArrayList(
                );
 
 
         logList = FXCollections.observableArrayList();
        try{
                        Connection c = db.getConnection();
            Statement statement = c.createStatement();
 
            String SQL = "SELECT * from simplelogs";
             
            ResultSet rs = c.createStatement().executeQuery(SQL);
             
 
 
         
        EmployeeLog = new TableView<String>(logList);
         
        TableColumn<EmployeeTracking, Integer> EmployeeID = new TableColumn<>("EmployeeID");
        EmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        EmployeeLog.getColumns().add(EmployeeID);
         
        TableColumn<EmployeeTracking, String> EmployeeFirstName = new TableColumn<>("EmployeeFirstName");
        EmployeeFirstName.setCellValueFactory(new PropertyValueFactory<>("EmployeeFirstName"));
        EmployeeLog.getColumns().add(EmployeeFirstName);
      TableColumn<EmployeeTracking, String> EmployeeLastName = new TableColumn<>("EmployeeLastName");
       EmployeeLastName.setCellValueFactory(new PropertyValueFactory<>("EmployeeLastName"));
        EmployeeLog.getColumns().add(EmployeeLastName);
         
        TableColumn<EmployeeTracking,String> Action = new TableColumn<>("Action");
        Action.setCellValueFactory(new PropertyValueFactory<>("Action"));
         
         
       EmployeeLog.getColumns().add(Action);
         
        EmployeeLog.setPrefWidth(800);
        EmployeeLog.setPrefHeight(600);
         
        TableView.TableViewSelectionModel tvSelLogging = 
                EmployeeLog.getSelectionModel();
         
        tvSelLogging.selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal) {
                int index = (int)newVal;
 
            }
        });
                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
 
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                    System.out.println(row);
                }
 
                logList.add(new EmployeeTracking(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)
                ));
 
                //Iterate Row
                // the data will be added to the arraylist
                EmployeeLog.setItems(logList);
                 
//ik probeer wel effe te restarten
            }
             
             
            }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
 
        }
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