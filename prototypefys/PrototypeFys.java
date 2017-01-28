package prototypefys;

import cataloog.FoundLuggage;
import cataloog.LostLuggage;
import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Node;


import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 
 * @author IS-109-2
 */
public class PrototypeFys extends Application {
     
    
    private Database db = new Database();
    
    
 private static final Logger logger = LogManager.getLogger();   
    @Override
    public void start(Stage primaryStage) {
      
        LoginScherm loginscherm = new LoginScherm();
        GridPane startscherm = loginscherm.MaakHetScherm();
        
        Rootpane rootpane = new Rootpane();
        rootpane.addnewpane(startscherm);
        Pane scherm = rootpane.getRootPane();
        logger.fatal("Test error please ignore");
        
        
      
        

    
        Scene scene = new Scene(scherm, 1400, 800);
        
    
        primaryStage.setTitle("Corendon luggage system");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("/resources/CDF.png"));
        
        
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
   
     
     
}
