/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 * Create a connection to the database.
 *
 * @author Erik Wolters <erik.wolters@hva.nl>
 */
public class Database {

    private static final String DRIVER = "com.mysql.jdbc.Driver"; // DB Driver
    private static final String URL_PREFIX = "jdbc:mysql://"; // DB URL Prefix

    private String username = null; // The username for the connection
    private String password = null; // The password for the connection
    private String hostname = null; // The hostname of the server
    private String database = null; // The database that conatains the tables

    private Connection conn = null; // Database connection

    /**
     * Construcot to create a database with the default values
     */
    public Database() {
        username = "fys";
        password = "ESCXZoaIlK07pwUS";
        hostname = "it95.nl:3306";
        database = "corendon";

        createConnection();
    }

    /**
     * Open a connection to the database
     */
    private void createConnection() {


        
        // Load the database driver.

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // Connect to the database
        try {
            this.conn = DriverManager.getConnection(URL_PREFIX + hostname
                    + "/" + database, username, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    /**
     * Return the connection
     * 
     * @return The database connection
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Close the connection to the database
     */
    public void close() {
        conn = null;
    }

    /**
     * Return all attributes of the database
     *
     * @return
     */
    @Override
    public String toString() {
        return "Database{" + "username=" + username + ", password="
                + password + ", hostname=" + hostname + ", database="
                + database + '}';
    }

}
