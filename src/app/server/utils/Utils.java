package app.server.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Utils {

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public static boolean hasAdultAge(Date subscriberAge) {
        return Period.between(convertToLocalDate(subscriberAge), convertToLocalDate(new Date())).getYears() >= 16;
    }

}
