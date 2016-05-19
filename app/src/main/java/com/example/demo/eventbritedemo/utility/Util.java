package com.example.demo.eventbritedemo.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Util {
    public static String getFormattedDate() {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }
}
