/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Daniel
 */
public class viewExistingCase {
    
    Rootpane rootpane = new Rootpane();
    
    private static HomeScreen nieuwscherm = new HomeScreen();
    private static HBox homescreen = nieuwscherm.maakhomescreen();
    
    viewExistingCase(){
        
    }
    
    public GridPane MaakExistingCase(){
 

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #16302e");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Enter Case ID#");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.WHITE);
        grid.add(scenetitle, 0, 0, 2, 1);
        
        
        Text userName = new Text("Case ID#:");
        userName.setFill(Color.WHITE);
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Scene scene = new Scene(grid, 300, 275);
        
        

        Button btn1 = new Button("Search case ID#");
        grid.add(btn1, 2, 1);
        btn1.setPrefSize(100, 20);
        btn1.setText("Search");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("X");
            }
        });
        
        Button btn2 = new Button("View baggage catalogue");
        grid.add(btn2, 1, 2);
        btn2.setPrefSize(100, 20);
        btn2.setText("Back");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
              rootpane.addnewpane(homescreen);
            }
        });
        
        Button btn3 = new Button("View options");
        grid.add(btn3, 2, 2);
        btn3.setPrefSize(100, 20);
        btn3.setText("Options");
        btn3.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
              System.out.println("X");
            }
        });
        
//GridPane.setStyle("-fx-background-color: #eaaf00;");
  return grid;       
}
    
}
