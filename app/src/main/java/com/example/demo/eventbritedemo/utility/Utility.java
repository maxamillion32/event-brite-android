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
                ("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getFormattedDate(final Calendar calendar) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getStringDate(final String date) {
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat
                    ("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            return calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR);
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
}
