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

import prototypefys.BagageCatalogue;
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

    
    adminScherm scherm6 = new adminScherm();
    HBox adminScherm = scherm6.maakAdminScherm();


    // micheal is hitleer

    
    
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
            bt_VEC.setStyle("-fx-base:darkred;-fx-border-color:black");
            bt_VEC.setPrefSize(160, 50);
            bt_VEC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(viewcase);
            }
            });

            Button bt_VBC = new Button("View Baggage Catalogue ");
            bt_VBC.setStyle("-fx-base:darkred;-fx-border-color:black");
            bt_VBC.setPrefSize(160, 50);
            bt_VBC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(cataloog);
            }
            });
    
            Button bt_RFB = new Button("Report Found Baggage ");
            bt_RFB.setStyle("-fx-base:darkred;-fx-border-color:black");  
            bt_RFB.setPrefSize(160, 50);
            bt_RFB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(submitCase);
            }
            });
            
            Button bt_RLB = new Button("Report Lost Baggage ");
            bt_RLB.setStyle("-fx-base:darkred;-fx-border-color:black"); 
            bt_RLB.setPrefSize(160, 50);
            bt_RLB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(ReportLost);
            }
            });
          

            
            Button bt_ADM = new Button("Admin");
            bt_ADM.setStyle("-fx-base:darkred;-fx-border-color:black"); 
            bt_ADM.setPrefSize(120, 50);
            bt_ADM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(adminScherm);
            }
            });
                        
            Button bt_LOG = new Button("Logout ");
            bt_LOG.setStyle("-fx-base:darkred;-fx-border-color:black");  
            bt_LOG.setPrefSize(120, 50);
            bt_LOG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                rootpane.addnewpane(loginScherm);
            }
            });    
                
            Button bt_OPT = new Button("Options ");
            bt_OPT.setStyle("-fx-base:darkred;-fx-border-color:black");   
            bt_OPT.setPrefSize(120, 50);            
            Button bt_STA = new Button("Statistics");
            bt_STA.setStyle("-fx-base:darkred;-fx-border-color:black");   
            bt_STA.setPrefSize(120, 50);
            
            hbox.setStyle("-fx-background-color: #16302e");
            
            bt_VEC.setPrefSize(180, 20);
            bt_VBC.setPrefSize(180, 20);
            bt_RFB.setPrefSize(180, 20);
            bt_RLB.setPrefSize(180, 20);
            bt_OPT.setPrefSize(180, 20);
            bt_STA.setPrefSize(180, 20);
            bt_ADM.setPrefSize(180, 20);
            bt_LOG.setPrefSize(180, 20);

            
            vbox1.getChildren().add(bt_VEC);
            vbox1.getChildren().add(bt_VBC);
            vbox1.getChildren().add(bt_RFB);
            vbox1.getChildren().add(bt_RLB);
            vbox1.getChildren().add(bt_OPT);
            vbox1.getChildren().add(bt_STA);
            vbox1.getChildren().add(bt_ADM);
            vbox1.getChildren().add(bt_LOG);

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