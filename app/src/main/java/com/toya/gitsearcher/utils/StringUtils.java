package com.toya.gitsearcher.utils;

/**
 * Created by AXIOO on 10/31/2017.
 */

public class StringUtils {

    public static String kConverter(int k){
        if (k > 1000){
            return String.valueOf(k/1000) + "k";
        }
        return String.valueOf(k);
    }

}
