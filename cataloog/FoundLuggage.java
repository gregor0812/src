/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cataloog;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Koen Hengsdijk
 */
public class FoundLuggage {
    
    // the propertys are all colums of the tableview
   private IntegerProperty caseid;
    private IntegerProperty labelnr;
    private IntegerProperty ownerid;
    private IntegerProperty flightnr;
    private StringProperty firstName;
    private StringProperty insertion;
    private StringProperty lastName;
    private StringProperty airport;
    private StringProperty destination;
    private StringProperty itemname;
    private StringProperty brand;
    private StringProperty colors;
    private StringProperty description;
    private StringProperty dateFound;
    private StringProperty timeFound;
    private StringProperty status;
    
    
    public FoundLuggage(int caseid, int labelnr, Integer ownerid, int flightnr, 
        String firstName, String insertion, String lastName,
        String airport,String destination, String itemname, String brand, String colors,
        String description, String dateFound, String timeFound, String status) {
        
        this.caseid =  new SimpleIntegerProperty(caseid);
        this.labelnr = new SimpleIntegerProperty(labelnr);
        this.ownerid = new SimpleIntegerProperty(ownerid);
        this.flightnr = new SimpleIntegerProperty(flightnr);
        this.firstName = new SimpleStringProperty(firstName);
        this.insertion = new SimpleStringProperty(insertion);
        this.lastName = new SimpleStringProperty(lastName);
        this.airport = new SimpleStringProperty(airport);
        this.destination = new SimpleStringProperty(destination);
        this.itemname = new SimpleStringProperty(itemname);
        this.brand = new SimpleStringProperty(brand);
        this.colors = new SimpleStringProperty(colors);
        this.description = new SimpleStringProperty(description);
        this.dateFound = new SimpleStringProperty(dateFound);
        this.timeFound = new SimpleStringProperty(timeFound);
        this.status = new SimpleStringProperty(status);
    }

    public int getCaseid() {
        return caseid.get();
    }

    public void setCaseid(int caseid) {
        this.caseid.set(caseid);
    }

    

    public int getLabelnr() {
        return labelnr.get();
    }

    public void setLabelnr(int labelnr) {
        this.labelnr.set(labelnr);
    }

    public Integer getOwnerid(){
        return ownerid.get();
    }
    
    public void setOwnerid(Integer ownerid){
        this.ownerid.set(ownerid);
    }
    
    public int getFlightnr() {
        return flightnr.get();
    }

    public void setFlightnr(int flightnr) {
        this.flightnr.set(flightnr);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public String getInsertion() {
        return insertion.get();
    }

    public void setInsertion(String insertion) {
        this.insertion.set(insertion);
    }
    
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    
    public String getAirport() {
        return airport.get();
    }

    public void setAirport(String airport) {
        this.airport.set(airport);
    }
    
    public String getDestination() {
        return destination.get();   
    }
    
    public void setDestination(String destination){
        this.destination.set(destination);
    }
    
    public String getItemname() {
        return itemname.get();
    }

    public void setItemname(String itemname) {
        this.itemname.set(itemname);
    }
    
    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }
    

    public String getColors() {
        return colors.get();
    }

    public void setColors(String colors) {
        this.colors.set(colors);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public String getDateFound() {
        return dateFound.get();
    }

    public void setDateFound(String dateFound) {
        this.dateFound.set(dateFound);
    }
    
    public String getTimeFound() {
        return timeFound.get();
    }

    public void setTimeFound(String timeFound) {
     this.timeFound.set(timeFound);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
    
    
}
