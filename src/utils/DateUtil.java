/**
 * Final Project for ITMD510.
 * Program to create a account book to store and operate data with different users's privilege through database, display with JavaFX.
 * @author: Li Mingyi Student, File Name: DateUtil.java
 * This file represents a Helper functions for handling dates, including transfer and validate;
 */

package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtil {

//    The date pattern that is used for conversion.
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

//    The date formatter.
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     *
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDateTime parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDateTime::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}
