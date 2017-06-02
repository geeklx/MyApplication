package com.example.p022_hois.hoisjump;

import android.util.Log;

import java.util.Map;

public class MapFiller {

    public static void fillBoolean(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (boolean)" + value);
        try {
            map.put(name, Boolean.parseBoolean(value));
        } catch (Exception e) {
            map.put(name, false);
        }
    }

    public static void fillByte(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (byte)" + value);
        try {
            map.put(name, Byte.parseByte(value));
        } catch (Exception e) {
            map.put(name, (byte) 0);
        }
    }

    public static void fillShort(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (short)" + value);
        try {
            map.put(name, Short.parseShort(value));
        } catch (Exception e) {
            map.put(name, (short) 0);
        }
    }

    public static void fillInt(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (int)" + value);
        try {
            map.put(name, Integer.parseInt(value));
        } catch (Exception e) {
            map.put(name, 0);
        }
    }

    public static void fillLong(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (long)" + value);
        try {
            map.put(name, Long.parseLong(value));
        } catch (Exception e) {
            map.put(name, 0L);
        }
    }

    public static void fillFloat(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (float)" + value);
        try {
            map.put(name, Float.parseFloat(value));
        } catch (Exception e) {
            map.put(name, 0.f);
        }
    }

    public static void fillDouble(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (double)" + value);
        try {
            map.put(name, Double.parseDouble(value));
        } catch (Exception e) {
            map.put(name, 0.0);
        }
    }

    public static void fillString(Map<String, Object> map, String name, String value) {
        Log.d("uri", name + " = (String)" + value);
        try {
            map.put(name, value);
        } catch (Exception e) {
            map.put(name, "");
        }
    }
}