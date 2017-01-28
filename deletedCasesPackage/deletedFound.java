/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletedCasesPackage;

import cataloog.FoundLuggage;

/**
 *
 * @author IS-109-2
 */
public class deletedFound extends FoundLuggage {

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
    public deletedFound(int caseid, int labelnr, Integer ownerid, int flightnr,
        String firstName, String insertion, String lastName, String airport,
        String destination, String itemname, String brand, String colors,
        String description, String dateFound, String timeFound, String status) {
        super(caseid, labelnr, ownerid, flightnr, firstName, insertion,
            lastName, airport, destination, itemname, brand, colors,
            description, dateFound, timeFound, status);
    }

}
