package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

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
