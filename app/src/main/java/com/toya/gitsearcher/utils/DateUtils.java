package com.toya.gitsearcher.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class DateUtils {

    public static String convertDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date d = sdf.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy");
            return sdf2.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}
