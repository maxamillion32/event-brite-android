package com.example.demo.eventbritedemo.utility;

public final class Constants {

    public static final class SharedPreferencesKeys {
        public static final String USER_ID = "user_id";
        public static final String ACCESS_TOKEN = "access_token";
    }

    public static final class IntentKeys {
        public static final String EVENT = "event";
        public static final String EVENT_ID = "event_id";
        public static final String VENUE_ID = "venue_id";
    }

    public interface ViewFlipperConstants {
        int LOADING = 0;
        int ERROR = 1;
        int SUCCESS = 2;
    }

    public static final class DateFromats {
        public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        public static final String DD_MMM_YYYY_AT_HH_MM_SS = "dd MMM, yyyy at HH:mm:ss";
    }
}
