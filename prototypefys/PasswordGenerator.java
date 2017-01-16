/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import java.util.Random;

/**
 * Generate a strong, multicharacter password
 *
 * @author Erik Wolters <erik.wolters@hva.nl>
 */
public class PasswordGenerator {

    // Array with all uppercase characters
    private static final char[] ALPHA_UPPER_CHARACTERS = {'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    // Array with all lowercase characters
    private static final char[] ALPHA_LOWER_CHARACTERS = {'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    // Array with all numeric characters
    private static final char[] NUMERIC_CHARACTERS = {'0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9'};

    // Array with all special characters
    private static final char[] SPECIAL_CHARACTERS = {'~', '`', '!', '@', '#',
        '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{',
        ']', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/',
        '?'};

    // Create random 
    private static Random r = new Random();

    /**
     * Generate a random password
     * 
     * @param length The amount of characters the password must have
     * @return A string with random characters
     */
    public static String generate(int length) {

        String password = "";

        // Limit the random number to the amount of arrays
        int characterTypelow = 1;
        int characterTypeHigh = 5;

        // Create an two demensional array to make it easier to access
        char[][] characters = new char[][]{ALPHA_UPPER_CHARACTERS,
            ALPHA_LOWER_CHARACTERS, NUMERIC_CHARACTERS, SPECIAL_CHARACTERS};

        // Generate as much random characters as requested
        for (int i = 0; i < length; i++) {
            
            // Choose a random character type
            int characterType = r.nextInt(characterTypeHigh - characterTypelow)
                    + characterTypelow;

            // Limit the random to the amount of items in the array
            int low = 0;
            int high;

            // Check wich array needs to be used
            switch (characterType) {

                case 1:
                    high = ALPHA_UPPER_CHARACTERS.length;
                    break;
                case 2:
                    high = ALPHA_LOWER_CHARACTERS.length;
                    break;
                case 3:
                    high = NUMERIC_CHARACTERS.length;
                    break;
                case 4:
                    high = SPECIAL_CHARACTERS.length;
                    break;
                default:
                    high = 1;
                    break;
            }

            // Generate a number to identify a character in the array
            int characterIdentifier = r.nextInt(high - low) + low;

            // Add the caracter to the password String
            password += characters[characterType - 1][characterIdentifier];
        }

        // Return the generated password
        return password;

    }
}
