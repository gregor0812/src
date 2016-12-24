
package prototypefys;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Koen Hengsdijk
 */
public class Rootpane {
    
    private static BorderPane EerstePane = new BorderPane();
    
    public Rootpane(){      
    }
    
    public Pane getRootPane(){
       
        return EerstePane;
    } 
    
    public void addnewpane(Pane NieuwPane){
        EerstePane.getChildren().clear();
        EerstePane.setCenter(NieuwPane);
        
    }

    void add(ImageView Corendon, int i, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setStyle(String fxbackgroundcolor_white) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
