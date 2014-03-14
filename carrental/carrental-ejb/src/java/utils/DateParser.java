/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class DateParser{
    
    public static Date parseToDate(String date) {
        Date birthdate = null;
        try {
            SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
            birthdate = dt.parse(date);
        } catch (ParseException ex) {
        }
        return birthdate;
    }
}
