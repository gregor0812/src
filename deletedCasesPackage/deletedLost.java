/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletedCasesPackage;

import cataloog.LostLuggage;

/**
 *
 * @author Koen Hengsdijk
 */
public class deletedLost extends LostLuggage {

    public deletedLost(int caseid, int ownerid, String firstName, String insertion,
        String lastName, int labelnr, int flightnr, String destination,
        String airport, String itemname, String brand, String colors,
        String description, String dateLost, String timeLost, String status) {
        super(caseid, ownerid, firstName, insertion, lastName, labelnr, flightnr,
            destination, airport, itemname, brand, colors, description, dateLost, timeLost, status);
    }

}
