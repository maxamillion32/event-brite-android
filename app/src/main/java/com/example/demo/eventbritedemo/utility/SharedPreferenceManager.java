package com.example.demo.eventbritedemo.utility;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.demo.eventbritedemo.ApplicationClass;
import com.google.gson.Gson;

public final class SharedPreferenceManager {

    private static SharedPreferences mSharedPreference;

    static {
        mSharedPreference = PreferenceManager
                .getDefaultSharedPreferences(ApplicationClass.getInstance());
    }

    private SharedPreferenceManager() {
    }

    /**
     * This method is used to set String values in Preference file.
     *
     * @param key
     * @param value - String value to be set.
     */
    public static void setStringValue(String key, String value) {
        mSharedPreference.edit().putString(key, value).apply();
    }

    /**
     * This method is used to get the String value from Preference file.
     *
     * @param key
     * @return value
     * @throws NullPointerException
     */
    public static String getStringValue(String key) throws NullPointerException {
        return mSharedPreference.getString(key, null);
    }

    /**
     * This method is used to set Int values in Preference file.
     *
     * @param key
     * @param value - Int value to be set.
     */
    public static void setIntValue(String key, int value) {
        mSharedPreference.edit().putInt(key, value).apply();
    }

    /**
     * This method is used to get the Int value from Preference file. If key is not found, -1
     * will be returned.
     *
     * @param key
     * @return value
     */
    public static int getIntValue(String key) {
        return mSharedPreference.getInt(key, 0);
    }

    /**
     * This method is used to set Boolean values in Preference file.
     *
     * @param key
     * @param value - Boolean value to be set.
     */
    public static void setBooleanValue(String key, boolean value) {
        mSharedPreference.edit().putBoolean(key, value).apply();
    }

    /**
     * This method is used to get the Boolean value from Preference file. If key is not found,
     * false will be returned.
     *
     * @param key
     * @return value
     */
    public static boolean getBooleanValue(String key) {
        return mSharedPreference.getBoolean(key, false);
    }

    /**
     * This method is used to clear all the data from shared preference.
     * This method should be called when user logs out of the application
     */
    public static void clearSharedPreference() {
        mSharedPreference.edit().clear().apply();
    }

    /**
     * Function to save object in shared preferences
     *
     * @param key
     * @param object
     */
    public static void setObject(String key, Object object) {
        setStringValue(key, new Gson().toJson(object));
    }

    /**
     * function to get object from shared preferences
     *
     * @param key
     * @param a
     * @param <T>
     * @return
     */
    public static <T> T getObject(String key, Class<T> a) {

        String gson = getStringValue(key);
        try {
            return new Gson().fromJson(gson, a);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to set long values in Preference file.
     *
     * @param key
     * @param value - Int value to be set.
     */
    public static void setLongValue(String key, long value) {
        mSharedPreference.edit().putLong(key, value).apply();
    }

    /**
     * This method is used to get the long value from Preference file. If key is not found, 0
     * will be returned.
     *
     * @param key
     * @return value
     */
    public static long getLongValue(String key) {
        return mSharedPreference.getLong(key, 0);
    }

    public static void removeValue(String key) {
        mSharedPreference.edit().remove(key).apply();
    }

    public static void setAccessToken(String value) {
        setStringValue("access_token", value);
    }

    public static String getAccessToken() {
        return getStringValue("access_token");
    }

    public static String getUserId() {
        return getStringValue(Constants.SharedPreferencesKeys.USER_ID);
    }
}
