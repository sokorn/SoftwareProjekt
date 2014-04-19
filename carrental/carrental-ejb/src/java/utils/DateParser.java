package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasse mit einer statischen Methode, um Daten von String in Dateobjekte um
 * zuwandeln.
 *
 */
public class DateParser
{

    /**
     * Wandelt ein Datum in Stringformat in ein Dateobjekt um. Das
     * Ausgangsformat ist "yyyy-MM-dd"
     *
     * @param stringDate Datum in Stringformat
     * @return das Datum als Dateobjekt
     */
    public static Date parseToDate(String stringDate)
    {
        Date dateObject = null;
        try
        {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            dateObject = dt.parse(stringDate);
        } catch (ParseException ex)
        {
        }
        return dateObject;
    }
}
