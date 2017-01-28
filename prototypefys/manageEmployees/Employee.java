package prototypefys.manageEmployees;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author IS-109-2
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

    /**
     *
     * @param employeenumber the number of the employee
     * @param username the username of the employee
     * @param password the password of the employee
     * @param firstname the first name of the employee
     * @param insertion the insertion of the employee
     * @param lastname the lastname of the employee
     * @param role the role of the employee
     * @param email the email of the employee
     */
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
    
    /**
     *
     * @return this returns the employee number
     */
    public int getEmployeenumber() {
        return employeenumber.get();
    }

    /**
     *
     * @param employeenumber the number of the employee
     */
    public void setEmployeenumber(int employeenumber) {
        this.employeenumber.set(employeenumber);
    }
    
    /**
     *
     * @return this returns the username of the employee
     */
    public String getUsername() {
        return username.get();
    }

    /**
     *
     * @param username the username of the employee
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     *
     * @return the password of the employee
     */
    public String getPassword() {
        return password.get();
    }

    /**
     *
     * @param password the password of the employee
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     *
     * @return the first name of the employee
     */
    public String getFirstname() {
        return firstname.get();
    }

    /**
     *
     * @param firstname the first name of the employee
     */
    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    /**
     *
     * @return the insertion of the employee
     */
    public String getInsertion() {
        return insertion.get();
    }

    /**
     *
     * @param insertion the insertion of the employee
     */
    public void setInsertion(String insertion) {
        this.insertion.set(insertion);
    }

    /**
     *
     * @return the last name of the employee
     */
    public String getLastname() {
        return lastname.get();
    }

    /**
     *
     * @param lastname the lastname of the employee
     */
    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    /**
     *
     * @return the role of the employee
     */
    public String getRole() {
        return role.get();
    }

    /**
     *
     * @param role the role of the employee
     */
    public void setRole(String role) {
        this.role.set(role);
    }
    
    /**
     *
     * @return the email of the employee
     */
    public String getEmail(){
        return email.get();
    }
    
    /**
     *
     * @param email the email of the employee
     */
    public void setEmail(String email){
        this.email.set(email);
    }
    
    
    
    
    
}
