/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletedCasesPackage;

import cataloog.LostLuggage;

/**
 *
 * @author IS-109-2
 */
public class deletedLost extends LostLuggage {

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
    public deletedLost(int caseid, int ownerid, String firstName, String insertion,
        String lastName, int labelnr, int flightnr, String destination,
        String airport, String itemname, String brand, String colors,
        String description, String dateLost, String timeLost, String status) {
        super(caseid, ownerid, firstName, insertion, lastName, labelnr, flightnr,
            destination, airport, itemname, brand, colors, description, dateLost, timeLost, status);
    }

}
