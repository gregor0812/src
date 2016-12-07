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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Daniel
 */
public class adminScherm {
    
    Rootpane rootpane = new Rootpane();
    
    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();    
    private static DisplayEmployeeLog employeelog = new DisplayEmployeeLog();
    private static FlowPane employeeScherm = employeelog.employeelog();
    
    
    
    
    adminScherm(){
        
    }   
    
    public HBox maakAdminScherm() {
    
    

    
        HBox hbox = new HBox(200);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox1 = new VBox(20);
        vbox1.setAlignment(Pos.CENTER);
        
                    
            Button bt_MA = new Button("Manage Accounts ");
            bt_MA.setPrefSize(150, 50);
            bt_MA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("X");
            }
            });
            
            Button bt_VL = new Button("View Logs ");
            bt_VL.setPrefSize(150, 50);
            bt_VL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(employeeScherm);
            }
            });
            
            Button bt_VDC = new Button("View Deleted Cases ");
            bt_VDC.setPrefSize(150, 50);
            bt_VDC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("X");
            }
            });
            
            Button bt_backButton = new Button("Back ");
            bt_backButton.setPrefSize(150, 50);
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
    
            
        hbox.getChildren().addAll(vbox1);
    
        return hbox;
       
            
    }
 
     

}