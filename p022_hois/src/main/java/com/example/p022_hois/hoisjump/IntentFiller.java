package com.example.p022_hois.hoisjump;

import android.content.Intent;
import android.util.Log;

public class IntentFiller {

    public static void fillBoolean(Intent intent, String name, String value) {
        Log.d("uri", name + " = (boolean)" + value);
        try {
            intent.putExtra(name, Boolean.parseBoolean(value));
        } catch (Exception e) {
            intent.putExtra(name, false);
        }
    }

    public static void fillByte(Intent intent, String name, String value) {
        Log.d("uri", name + " = (byte)" + value);
        try {
            intent.putExtra(name, Byte.parseByte(value));
        } catch (Exception e) {
            intent.putExtra(name, (byte) 0);
        }
    }

    public static void fillShort(Intent intent, String name, String value) {
        Log.d("uri", name + " = (short)" + value);
        try {
            intent.putExtra(name, Short.parseShort(value));
        } catch (Exception e) {
            intent.putExtra(name, (short) 0);
        }
    }

    public static void fillInt(Intent intent, String name, String value) {
        Log.d("uri", name + " = (int)" + value);
        try {
            intent.putExtra(name, Integer.parseInt(value));
        } catch (Exception e) {
            intent.putExtra(name, 0);
        }
    }

    public static void fillLong(Intent intent, String name, String value) {
        Log.d("uri", name + " = (long)" + value);
        try {
            intent.putExtra(name, Long.parseLong(value));
        } catch (Exception e) {
            intent.putExtra(name, 0L);
        }
    }

    public static void fillFloat(Intent intent, String name, String value) {
        Log.d("uri", name + " = (float)" + value);
        try {
            intent.putExtra(name, Float.parseFloat(value));
        } catch (Exception e) {
            intent.putExtra(name, 0.f);
        }
    }

    public static void fillDouble(Intent intent, String name, String value) {
        Log.d("uri", name + " = (double)" + value);
        try {
            intent.putExtra(name, Double.parseDouble(value));
        } catch (Exception e) {
            intent.putExtra(name, 0.0);
        }
    }

    public static void fillString(Intent intent, String name, String value) {
        Log.d("uri", name + " = (String)" + value);
        try {
            intent.putExtra(name, value);
        } catch (Exception e) {
            intent.putExtra(name, "");
        }
    }
}