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
public class LostLuggage {

    private IntegerProperty caseid;
    private IntegerProperty ownerid;
    private StringProperty firstName;
    private StringProperty insertion;
    private StringProperty lastName;
    private IntegerProperty labelnr;
    private IntegerProperty flightnr;
    private StringProperty destination;
    private StringProperty airport;
    private StringProperty itemname;
    private StringProperty brand;
    private StringProperty colors;
    private StringProperty description;
    private StringProperty dateLost;
    private StringProperty timeLost;
    private StringProperty status;
    
    /**
     *
     * @param caseid the case id of the lost luggage
     * @param ownerid the owner id of the lost luggage
     * @param firstName the first name of the owner 
     * @param insertion the instertion of the owner
     * @param lastName the last name of the owner
     * @param labelnr the labelnr of the lostluggage
     * @param flightnr the flightnr of the lost luggage 
     * @param destination the destination of the lost luggage
     * @param airport the airport where the luggage was reported as lost
     * @param itemname the item name of the lost luggage
     * @param brand the brand of the lost luggage
     * @param colors the color of the lost luggage
     * @param description the description of the lost luggage
     * @param dateLost the date the luggage was reported lost
     * @param timeLost the time the lost luggage was reported lost
     * @param status the status of the lost luggage
     */
    public LostLuggage(int caseid, int ownerid, String firstName, 
        String insertion, String lastName, int labelnr, int flightnr, String destination,
        String airport, String itemname, String brand, String colors, 
        String description, String dateLost, String timeLost, String status) {
        this.caseid =  new SimpleIntegerProperty(caseid);
        this.ownerid = new SimpleIntegerProperty(ownerid);
        this.firstName = new SimpleStringProperty(firstName);
        this.insertion = new SimpleStringProperty(insertion);
        this.lastName = new SimpleStringProperty(lastName);
        this.labelnr = new SimpleIntegerProperty(labelnr);
        this.flightnr = new SimpleIntegerProperty(flightnr);
        this.destination = new SimpleStringProperty(destination);
        this.airport = new SimpleStringProperty(airport);
        this.itemname = new SimpleStringProperty(itemname);
        this.brand = new SimpleStringProperty(brand);
        this.colors = new SimpleStringProperty(colors);
        this.description = new SimpleStringProperty(description);
        this.dateLost = new SimpleStringProperty(dateLost);
        this.timeLost = new SimpleStringProperty(timeLost);
        this.status = new SimpleStringProperty(status);
    }

    /**
     *
     * @return this will return the case id of the lost luggage
     */
    public int getCaseid() {
        return caseid.get();
    }

    /**
     *
     * @param caseid this is the case id of the lost luggage
     */
    public void setCaseid(int caseid) {
        this.caseid.set(caseid);
    }

    /**
     *
     * @return this will return the ownerid of the lost luggage
     */
    public int getOwnerid() {
        return ownerid.get();
    }

    /**
     *
     * @param ownerid this is the ownerid of the lost luggage
     */
    public void setOwnerid(int ownerid) {
        this.ownerid.set(ownerid);
    }
    
    /**
     *
     * @return this will return the first name of the owner of the lost luggage
     */
    public String getFirstName(){
        return firstName.get();
    }
    
    /**
     *
     * @param firstName this is the first name of the owner of the lost luggage
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    /**
     *
     * @return this will return the instertion of the lost luggage owner
     */
    public String getInsertion() {
        return insertion.get();
    }

    /**
     *
     * @param insertion this is the instertion of the owner of the lost luggage
     */
    public void setInsertion(String insertion) {
        this.insertion.set(insertion);
    }
    
    /**
     *
     * @return this will return the last name of the luggage owner
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     *
     * @param lastName this is the last name of the luggage owner
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    /**
     *
     * @return this will return the labelnr of the lostluggage
     */
    public int getLabelnr() {
        return labelnr.get();
    }

    /**
     *
     * @param labelnr this is the labelnr of the lost luggage
     */
    public void setLabelnr(int labelnr) {
        this.labelnr.set(labelnr);
    }

    /**
     *
     * @return this will return the flight nr of the lostluggage
     */
    public int getFlightnr() {
        return flightnr.get();
    }

    /**
     *
     * @param flightnr this is the flightnr of the lost luggage
     */
    public void setFlightnr(int flightnr) {
        this.flightnr.set(flightnr);
    }
    
    /**
     *
     * @return this will return the destination of the lost luggage
     */
    public String getDestination() {
        return destination.get();   
    }
    
    /**
     *
     * @param destination this is the destination of the lost luggage
     */
    public void setDestination(String destination){
        this.destination.set(destination);
    }

    /**
     *
     * @return this will return the airport were the luggage was reported as lost
     */
    public String getAirport() {
        return airport.get();
    }

    /**
     *
     * @param airport the airport were the luggage was reported as lost
     */
    public void setAirport(String airport) {
        this.airport.set(airport);
    }

    /**
     *
     * @return the item name of the lost luggage
     */
    public String getItemname() {
        return itemname.get();
    }

    /**
     *
     * @param itemname the itemname of the lost luggage
     */
    public void setItemname(String itemname) {
        this.itemname.set(itemname);
    }
    
    /**
     *
     * @return the brand of the lost luggage
     */
    public String getBrand() {
        return brand.get();
    }

    /**
     *
     * @param brand the brand of the lost luggage
     */
    public void setBrand(String brand) {
        this.brand.set(brand);
    }
    
    /**
     *
     * @return the color of the lost lugggage
     */
    public String getColors() {
        return colors.get();
    }

    /**
     *
     * @param colors the color of the lost luggage
     */
    public void setColors(String colors) {
        this.colors.set(colors);
    }

    /**
     *
     * @return the description of the lost luggage
     */
    public String getDescription() {
        return description.get();
    }

    /**
     *
     * @param description the description of the lost lugggage
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    /**
     *
     * @return the date the luggage was reported lost
     */
    public String getDateLost() {
        return dateLost.get();
    }

    /**
     *
     * @param dateLost the date the luggage was reported lost
     */
    public void setDateLost(String dateLost) {
        this.dateLost.set(dateLost);
    }
    
    /**
     *
     * @return the time the luggage was reported lost
     */
    public String getTimeLost() {
        return timeLost.get();
    }

    /**
     *
     * @param timeLost the time the luggage was reported lost
     */
    public void setTimeLost(String timeLost) {
     this.timeLost.set(timeLost);
    }

    /**
     *
     * @return the status of the luggage
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param status the status of the luggage
     */
    public void setStatus(String status) {
        this.status.set(status);
    }
    
}
