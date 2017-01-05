/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys.manageEmployees;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Koen Hengsdijk
 */
public class Employee {
    private StringProperty username;
    private StringProperty password;
    private StringProperty firstname;
    private StringProperty insertion;
    private StringProperty lastname;
    private StringProperty role;

    public Employee(String username, String password, String firstname, 
        String insertion, String lastname, String role) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.firstname = new SimpleStringProperty(firstname);
        this.insertion = new SimpleStringProperty(insertion);
        this.lastname = new SimpleStringProperty(lastname);
        this.role = new SimpleStringProperty(role);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getInsertion() {
        return insertion.get();
    }

    public void setInsertion(String insertion) {
        this.insertion.set(insertion);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }
    
    
    
    
    
}
