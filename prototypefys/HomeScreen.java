/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

/**
 *
 * @author Koen Hengsdijk
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeScreen {
    
    Rootpane rootpane = new Rootpane();
    
    private static LoginScherm scherm1 = new LoginScherm();
    private static GridPane loginScherm = scherm1.MaakHetScherm();
    
    BagageCatalogue scherm2 = new BagageCatalogue();
    GridPane cataloog = scherm2.MaakCatalogue();

    viewExistingCase scherm3 = new viewExistingCase();
    GridPane viewcase = scherm3.MaakExistingCase();
    
    submitCase scherm4 = new submitCase();
    GridPane submitCase = scherm4.MakeSubmitScreen();
    
    ReportLost scherm5 = new ReportLost();
    GridPane ReportLost = scherm5.MakeLostReport();

    
    
//    ReportGeneration scherm4 = new ReportGeneration();
//    GridPane rapport = scherm4.MakeReportScreen();

    
    HomeScreen(){
        
    }
    
    public HBox maakhomescreen(){
        
        HBox hbox = new HBox(200);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox1 = new VBox(20);
        vbox1.setAlignment(Pos.CENTER);

        
            Button bt_VEC = new Button("View Existing Case");
            bt_VEC.setStyle("-fx-base:darkred;-fx-border-color:white");
            bt_VEC.setPrefSize(160, 50);
            bt_VEC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(viewcase);
            }
            });

            Button bt_VBC = new Button("View Baggage Catalogue ");
            bt_VBC.setStyle("-fx-base:darkred;-fx-border-color:white");
            bt_VBC.setPrefSize(160, 50);
            bt_VBC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(cataloog);
            }
            });
    
            Button bt_RFB = new Button("Report Found Baggage ");
            bt_RFB.setStyle("-fx-base:darkred;-fx-border-color:white");  
            bt_RFB.setPrefSize(160, 50);
            bt_RFB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(submitCase);
            }
            });
            
            Button bt_RLB = new Button("Report Lost Baggage ");
            bt_RLB.setStyle("-fx-base:darkred;-fx-border-color:white"); 
            bt_RLB.setPrefSize(160, 50);
            bt_RLB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(ReportLost);
            }
            });
          
            Button bt_LOG = new Button("Logout ");
            bt_LOG.setStyle("-fx-base:darkred;-fx-border-color:white");  
            bt_LOG.setPrefSize(120, 50);
            bt_LOG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(loginScherm);
            }
            });
                
                
            Button bt_OPT = new Button("Options ");
            bt_OPT.setStyle("-fx-base:darkred;-fx-border-color:white");   
            bt_OPT.setPrefSize(120, 50);
            
            Button bt_STA = new Button("Statistics");
            bt_STA.setStyle("-fx-base:darkred;-fx-border-color:white");   
            bt_STA.setPrefSize(120, 50);
            
            hbox.setStyle("-fx-background-color: white");
            
            vbox1.getChildren().add(bt_VEC);
            vbox1.getChildren().add(bt_VBC);
            vbox1.getChildren().add(bt_RFB);
            vbox1.getChildren().add(bt_RLB);
            vbox1.getChildren().add(bt_OPT);
            vbox1.getChildren().add(bt_LOG);
            vbox1.getChildren().add(bt_STA);
            bt_STA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
//                rootpane.addnewpane(rapport);
            }
            });
        

        hbox.getChildren().addAll(vbox1);
    
        return hbox;
        }
}   