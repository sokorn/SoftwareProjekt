package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * wandelt ein Stringdatum des formats dd-MM-yyyy in ein Date Objekt um
 *
 */
public class DateParser {

    public static Date parseToDate(String stringDate) {
        Date birthdate = null;
        try {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            birthdate = dt.parse(stringDate);
        } catch (ParseException ex) {
        }
        return birthdate;
    }
}
