/*
 * To change DataCache license header, choose License Headers in Project Properties.
 * To change DataCache template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

/**
 *
 * @author IS-109-2
 */
public class DataCache {

    private static int employeenumber; // The unique identifier of the user
    private static String username; // Username to login with
    private static String password; // Password to login with
    private static String role; // Permission role of the user
    private static String firstname; // First name of the user
    private static String insertion; // Insertion of the user
    private static String lastname; // The lastname of the user
    private static String email; // The email address of the user
    
    public static int getEmployeenumber() {
        return DataCache.employeenumber;
    }

    public static void setEmployeenumber(int employeenumber) {
        DataCache.employeenumber = employeenumber;
    }

    /**
     * Get the firstname
     *
     * @return A string with the firstname
     */
    public static String getFirstname() {
        return firstname;
    }

    /**
     * Set the firstname
     *
     * @param firstname the prefered firstname
     */
    public static void setFirstname(String firstname) {
        DataCache.firstname = firstname;
    }

    public static String getInsertion() {
        return insertion;
    }

    public static void setInsertion(String insertion) {
        DataCache.insertion = insertion;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        DataCache.lastname = lastname;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DataCache.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataCache.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        DataCache.role = role;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        DataCache.email = email;
    }

    public static String getNaam() {
        return firstname + " " + insertion + " " + lastname;
    }

}
