package com.haiersmart.voice.utils;

/**
 * Created with Android Studio.
 *
 * @author : Hsueh
 * @email : i@hsueh.top
 * @date : 2017-03-01 14:18
 */
public class LocationData {

    private static double latitude;
    private static double longitude;

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        LocationData.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        LocationData.longitude = longitude;
    }
}
