/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author IS-109-2
 */
public class Logs {
    
 private final SimpleStringProperty Employee = new SimpleStringProperty("");
 private final SimpleStringProperty Name = new SimpleStringProperty("");
 private final SimpleStringProperty Action= new SimpleStringProperty("");

public Logs() {
        this("", "", "");
    }
 
    public Logs(String Employee, String Name, String Action) {
        setEmployee(Employee);
        setName(Name);
        setAction(Action);
    }

    public String getEmployee() {
        return Employee.get();
    }
 
    public void setEmployee(String fName) {
        Employee.set(fName);
    }
        
    public String getName() {
        return Name.get();
    }
    
    public void setName(String fName) {
        Name.set(fName);
    }
    
    public String getAction() {
        return Action.get();
    }
    
    public void setAction(String cell) {
       Action.set(cell);
    }
  
}