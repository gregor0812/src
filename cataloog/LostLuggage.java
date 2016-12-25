/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cataloog;

/**
 *
 * @author Koen Hengsdijk
 */
public class LostLuggage {

    private int caseid;
    private int ownerid;
    private int labelnr;
    private int flightnr;
    private String airport;
    private String itemname;
    private String colors;
    private String description;

    public LostLuggage(int caseid, int ownerid, int labelnr, int flightnr,
        String airport, String itemname, String colors, String description) {
        this.caseid = caseid;
        this.ownerid = ownerid;
        this.labelnr = labelnr;
        this.flightnr = flightnr;
        this.airport = airport;
        this.itemname = itemname;
        this.colors = colors;
        this.description = description;
    }

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public int getLabelnr() {
        return labelnr;
    }

    public void setLabelnr(int labelnr) {
        this.labelnr = labelnr;
    }

    public int getFlightnr() {
        return flightnr;
    }

    public void setFlightnr(int flightnr) {
        this.flightnr = flightnr;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
