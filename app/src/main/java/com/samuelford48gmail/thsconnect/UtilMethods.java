package com.samuelford48gmail.thsconnect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class UtilMethods {
    private static final String DATE_FORMAT = "MM-dd";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
