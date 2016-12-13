/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Delug
 */
public class BaggageInventory implements Comparable<BaggageInventory> {
    
    private String item;
    private String description;
    private String dateOfArrival;
    
    public BaggageInventory(ResultSet rs) throws SQLException {
        loadFromSQL(rs);
    }
    
    public BaggageInventory(String item, String description, String DateOfArrival){
       
    
    this.item = item;
    this.description = description;
    this.dateOfArrival = dateOfArrival;
    }
    
    public String getItem()
    {
        return item;
    }
   
    public String toString()
    {
        return "\tItem:"+item+"\n"+description+" "+dateOfArrival;
    }
    
    public String  toDBString()
    {
        return item+","+description+","+dateOfArrival;
    }
    
    public String getSQLInserrt()
    {
        return "INSERT INTO BaggageInventory (item, description, dateOfArrival"
                + "VALUES (item+\",\"+description+\",\"+dateOfArrival);";
    }
        
        public void loadFromSQL(ResultSet rs) throws SQLException
        {
                item = rs.getString("item");
                description = rs.getString("description");
                dateOfArrival = rs.getString("dateOfArrival");
    }
        
        @Override
        public int compareTo (BaggageInventory obj)
        {
            return this.item.compareTo(obj.getItem());
        }
           
    
}
