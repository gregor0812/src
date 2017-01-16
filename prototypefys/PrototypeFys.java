package prototypefys;

import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Node;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * jasdhakjsdjkas
 * @author Koen Hengsdijk
 */
public class PrototypeFys extends Application {
    
    private static Stage pStage;
    
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
   
    
    public static void maakpdf(Node node, Stage prStage){
        PrinterJob job = PrinterJob.createPrinterJob();
                if (job != null) {
                    job.showPrintDialog(prStage); // Window must be your main Stage
                    job.printPage(node);
                    job.endJob();
                }
    }
    
     public static Stage getPrimaryStage() {
        return pStage;
    }
}
