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
}
