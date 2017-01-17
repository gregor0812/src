/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletedCasesPackage;

import cataloog.FoundLuggage;

/**
 *
 * @author Koen Hengsdijk
 */
public class deletedFound extends FoundLuggage {

    public deletedFound(int caseid, int labelnr, Integer ownerid, int flightnr,
        String firstName, String insertion, String lastName, String airport,
        String destination, String itemname, String brand, String colors,
        String description, String dateFound, String timeFound, String status) {
        super(caseid, labelnr, ownerid, flightnr, firstName, insertion,
            lastName, airport, destination, itemname, brand, colors,
            description, dateFound, timeFound, status);
    }

}
