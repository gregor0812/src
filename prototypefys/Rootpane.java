
package prototypefys;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author IS-109-2
 */
public class Rootpane {
    
    private static BorderPane EerstePane = new BorderPane();
    
    /**
     *
     */
    public Rootpane(){      
    }
    
    /**
     *
     * @return this will return the orignal borderpane
     */
    public Pane getRootPane(){
       
        return EerstePane;
    } 
    
    /**
     *
     * @param NieuwPane this will clear the original pane and a new pane
     */
    public void addnewpane(Pane NieuwPane){
        EerstePane.getChildren().clear();
        EerstePane.setCenter(NieuwPane);
        
    }

    
    
}
