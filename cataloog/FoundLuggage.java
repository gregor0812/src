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
 * @author IS-109-2
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
    
    /**
     *
     * @param caseid the case id of the found luggage
     * @param labelnr the labelnr of the foundluggage
     * @param ownerid the owner id of the found luggage
     * @param flightnr the flightnr of the found luggage
     * @param firstName the first name of the found luggage owner
     * @param insertion the insertion of the found luggage owner
     * @param lastName the last name of the found luggage owner
     * @param airport the airport where the luggage is found
     * @param destination the destination of the luggage
     * @param itemname the item type of the found luggage
     * @param brand the brand of the found luggage
     * @param colors the color of the found luggage
     * @param description the description of the found luggage
     * @param dateFound the date the luggage was found
     * @param timeFound the time the luggage was found
     * @param status the status of the luggage
     */
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

    /**
     *
     * @return the case id of the foundluggage
     */
    public int getCaseid() {
        return caseid.get();
    }

    /**
     *
     * @param caseid the case id of the foundluggage
     */
    public void setCaseid(int caseid) {
        this.caseid.set(caseid);
    }

    /**
     *
     * @return the labelnr of the found luggage
     */
    public int getLabelnr() {
        return labelnr.get();
    }

    /**
     *
     * @param labelnr the labelnr of the found luggage
     */
    public void setLabelnr(int labelnr) {
        this.labelnr.set(labelnr);
    }

    /**
     *
     * @return the owner id of the found luggage owner
     */
    public Integer getOwnerid(){
        return ownerid.get();
    }
    
    /**
     *
     * @param ownerid the owner id of the found luggage owner
     */
    public void setOwnerid(Integer ownerid){
        this.ownerid.set(ownerid);
    }
    
    /**
     *
     * @return the flightnr of the foundluggage
     */
    public int getFlightnr() {
        return flightnr.get();
    }

    /**
     *
     * @param flightnr the flightnr of the foundluggage
     */
    public void setFlightnr(int flightnr) {
        this.flightnr.set(flightnr);
    }

    /**
     *
     * @return the first name of the found luggage owner
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     *
     * @param firstName the first name of the found luggage owner
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    /**
     *
     * @return the insertion of the found luggage owner
     */
    public String getInsertion() {
        return insertion.get();
    }

    /**
     *
     * @param insertion the insertion of the foundluggage owner
     */
    public void setInsertion(String insertion) {
        this.insertion.set(insertion);
    }
    
    /**
     *
     * @return the lastname of the found luggage owner
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     *
     * @param lastName the lastname of the found luggage owner
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    /**
     *
     * @return the airport where the luggage was found
     */
    public String getAirport() {
        return airport.get();
    }

    /**
     *
     * @param airport the airport where the luggage was found
     */
    public void setAirport(String airport) {
        this.airport.set(airport);
    }
    
    /**
     *
     * @return the destination of the found luggage
     */
    public String getDestination() {
        return destination.get();   
    }
    
    /**
     *
     * @param destination the destination of the found luggage
     */
    public void setDestination(String destination){
        this.destination.set(destination);
    }
    
    /**
     *
     * @return the item name of the foundluggage
     */
    public String getItemname() {
        return itemname.get();
    }

    /**
     *
     * @param itemname the item name of the foundluggage
     */
    public void setItemname(String itemname) {
        this.itemname.set(itemname);
    }
    
    /**
     *
     * @return the brand of the found luggage
     */
    public String getBrand() {
        return brand.get();
    }

    /**
     *
     * @param brand the brand of the found luggage
     */
    public void setBrand(String brand) {
        this.brand.set(brand);
    }
    
    /**
     *
     * @return the color of the found luggage
     */
    public String getColors() {
        return colors.get();
    }

    /**
     *
     * @param colors the color of the found luggage
     */
    public void setColors(String colors) {
        this.colors.set(colors);
    }

    /**
     *
     * @return the description of the found luggage
     */
    public String getDescription() {
        return description.get();
    }

    /**
     *
     * @param description the description of the found luggage
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    /**
     *
     * @return the date the luggage was found
     */
    public String getDateFound() {
        return dateFound.get();
    }

    /**
     *
     * @param dateFound the date the luggage was found
     */
    public void setDateFound(String dateFound) {
        this.dateFound.set(dateFound);
    }
    
    /**
     * 
     * @return the time the luggage was found
     */
    public String getTimeFound() {
        return timeFound.get();
    }

    /**
     *
     * @param timeFound the time the luggage was found
     */
    public void setTimeFound(String timeFound) {
     this.timeFound.set(timeFound);
    }

    /**
     *
     * @return the status of the found luggage
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param status the status of the found luggage
     */
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    
}
