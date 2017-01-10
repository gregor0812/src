/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys.manageEmployees;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Koen Hengsdijk
 */
public class Employee {
    
    private IntegerProperty employeenumber;
    private StringProperty username;
    private StringProperty password;
    private StringProperty firstname;
    private StringProperty insertion;
    private StringProperty lastname;
    private StringProperty role;
    private StringProperty email;

    public Employee(int employeenumber, String username, String password, String firstname, 
        String insertion, String lastname, String role, String email) {
        this.employeenumber = new SimpleIntegerProperty(employeenumber);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.firstname = new SimpleStringProperty(firstname);
        this.insertion = new SimpleStringProperty(insertion);
        this.lastname = new SimpleStringProperty(lastname);
        this.role = new SimpleStringProperty(role);
        this.email = new SimpleStringProperty(email);
    }
    
    public int getEmployeenumber() {
        return employeenumber.get();
    }

    public void setEmployeenumber(int employeenumber) {
        this.employeenumber.set(employeenumber);
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
    
    public String getEmail(){
        return email.get();
    }
    
    public void setEmail(String email){
        this.email.set(email);
    }
    
    
    
    
    
}
