package prototypefys;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * jasdhakjsdjkas
 * @author Koen Hengsdijk
 */
public class PrototypeFys extends Application {
    
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
        
    
        primaryStage.setTitle("loginscherm test");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        primaryStage.setMaximized(true);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
   
    
    
}
