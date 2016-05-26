package com.example.demo.eventbritedemo.utility;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.demo.eventbritedemo.ApplicationClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utility {
    public static String getFormattedDate() {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                (Constants.DateFormats.YYYY_MM_DD_T_HH_MM_SS_Z, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getFormattedDate(final Calendar calendar) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                (Constants.DateFormats.YYYY_MM_DD_T_HH_MM_SS_Z, Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getStringDate(final String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                    (Constants.DateFormats.YYYY_MM_DD_T_HH_MM_SS_Z, Locale.ENGLISH);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));

            simpleDateFormat = new SimpleDateFormat(Constants.DateFormats
                    .DD_MMM_YYYY_AT_HH_MM_SS, Locale.ENGLISH);
            return simpleDateFormat.format(calendar.getTime());

//            return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) +
//                    " " + calendar.get(Calendar.DAY_OF_MONTH) +
//                    ", " + calendar.get(Calendar.YEAR) + " at " + calendar.get(Calendar.HOUR) +
//                    ":" + calendar.get(Calendar.MINUTE) + " " +
//                    calendar.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        Toast.makeText(ApplicationClass.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        Toast.makeText(ApplicationClass.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
