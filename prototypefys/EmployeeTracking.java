/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author IS-109-2
 */
public class EmployeeTracking {
    private IntegerProperty EmployeeID;
    private StringProperty EmployeeFirstName;
    private StringProperty EmployeeLastName;
    private StringProperty Action;
    
    public EmployeeTracking(int EmployeeID, String EmployeeFirstName, String EmployeeLastName, String Action){
        this.EmployeeID = new SimpleIntegerProperty(EmployeeID);
        this.EmployeeFirstName = new SimpleStringProperty(EmployeeFirstName);
        this.EmployeeLastName = new SimpleStringProperty(EmployeeLastName);
        this.Action = new SimpleStringProperty (Action);
        
        
        
        
       
                
    }
    public int getEmployeeID() {
        return EmployeeID.get();
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID.set(EmployeeID);
    }
   public String getEmployeeFirstName(){
        return EmployeeFirstName.get();
    }
    
    public void setEmployeeFirstName(String EmployeefirstName) {
        this.EmployeeFirstName.set(EmployeefirstName);
    }
    
    public String getEmployeeLastName(){
        return EmployeeLastName.get();
    }
    
    public void setEmployeeLastName(String EmployeeLastName) {
        this.EmployeeLastName.set(EmployeeLastName);
    }
   
    public String getAction(){
        return Action.get();
    }
    
    public void setAction(String Action) {
        this.Action.set(Action);
    }
    
}



