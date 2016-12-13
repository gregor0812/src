/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Delug
 */
public class BaggageInventoryInsert {
    
    public static ArrayList<BaggageInventory> invlist = new ArrayList<>();
    public static void main(String[] args)
    {
        Connection connection = null;
        
        try {
             // call the JDBC driver at runtime
            Class.forName("org.sqlite.JDBC");

            // create a conection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:Inventory.db");

            System.out.println("Database connection opened successfully");
        
      
        
        Statement statement = connection.createStatement();
        
        BaggageInventory newItem = new BaggageInventory("EXAMPLEITEMHERE","A spooky item","09-07-2015");
        
        ResultSet rs = statement.executeQuery("SELECT * FROM Inventory ORDER BY item;");
        while (rs.next())
        {
            invlist.add(new BaggageInventory(rs));
        }
        
        
        }
           catch ( ClassNotFoundException | SQLException e ){
               System.err.println( e.getClass().getName()+":"+e.getMessage());
               System.exit(0);
           }
        for (BaggageInventory each : invlist)
        {
            System.out.println(each.toString());
        }
        
    }
            
}
