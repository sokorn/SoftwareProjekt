package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * wandelt ein Stringdatum des formats dd-MM-yyyy in ein Date Objekt um
 *
 */
public class DateParser {

    public static Date parseToDate(String date) {
        Date birthdate = null;
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            birthdate = dt.parse(date);
        } catch (ParseException ex) {
        }
        return birthdate;
    }
}
