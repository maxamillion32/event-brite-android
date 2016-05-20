package com.example.demo.eventbritedemo.utility;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.demo.eventbritedemo.ApplicationClass;

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

    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        Toast.makeText(ApplicationClass.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}
