 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import prototypefys.manageEmployees.employeetable;

/**
 *
 * @author Daniel
 */
public class adminScherm {
    
    Rootpane rootpane = new Rootpane();
    
    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();    
    private static DisplayEmployeeLog employeelog = new DisplayEmployeeLog();
    private static GridPane employeeScherm = employeelog.employeelog();
    
    private static final Rootpane basisPane = new Rootpane();

     
    
    public adminScherm(){
        
    }   
    
    public StackPane maakAdminScherm() {
    
        // make the main area
        StackPane basis = new StackPane();

    
        HBox adminstart = new HBox(200);
        adminstart.setAlignment(Pos.CENTER);

        VBox vbox1 = new VBox(20);
        vbox1.setAlignment(Pos.CENTER);
        
                adminstart.setStyle("-fx-background-color: #16302e");
        
        // make a empoyee view screen
        GridPane EmployeeData = new GridPane();
        
                    
            Button bt_MA = new Button("Manage Accounts ");
            bt_MA.setStyle("-fx-base:darkred;-fx-border-color:black");   
            bt_MA.setPrefSize(180, 20);
            bt_MA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
               employeetable makeTable = new employeetable();
                GridPane  table = makeTable.MaakEmployeeTable();
                rootpane.addnewpane(table);
            }
            });
            
            Button bt_VL = new Button("View Logs ");
            bt_VL.setPrefSize(180, 20);
            bt_VL.setStyle("-fx-base:darkred;-fx-border-color:black");
            bt_VL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(employeeScherm);
            }
            });
            
            Button bt_VDC = new Button("View Deleted Cases ");
            bt_VDC.setPrefSize(180, 20);
            bt_VDC.setStyle("-fx-base:darkred;-fx-border-color:black");
            bt_VDC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("X");
            }
            });
            
            Button bt_backButton = new Button("Back ");
            bt_backButton.setPrefSize(180, 20);
            bt_backButton.setStyle("-fx-base:darkred;-fx-border-color:black");
            bt_backButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
              rootpane.addnewpane(homescreen);
            }
            });
        
            vbox1.getChildren().add(bt_MA);
            vbox1.getChildren().add(bt_VL);
            vbox1.getChildren().add(bt_VDC);
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
    
    
    
    
    
    
    
     

}