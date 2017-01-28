/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * Encrypt text
 *
 * @author IS-109-2
 */
public class Encription {

    /**
     * Encrypt a string of text and return the encrypted value
     * 
     * @param clearText Clear text that must be encrytped
     * @return Encrypted text
     */
    public static String encrypt(String clearText) {
        
        // Create an empty String to store encrypted data
        String data="";
        
        try {
            // Define wich encryption needs to be used
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // Encrytp the clear text and save it in a new string
            messageDigest.update(clearText.getBytes());
            String encrypted = new String(messageDigest.digest());
            
            data = encrypted;
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
        
        // Return the encrypted data
        return data;
        
    }
}
