package com.example.shining.p022_hios20.hois2;

import android.content.Intent;
import android.net.Uri;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriHelper {
    public static final String REGEXP = "^\\{([byoilfds]{1})\\}(.+)$";

    public static final String HIOS_SCHEME = "hios";
    public static final String CONDITION = "condition";

    public static final String CONDITION_LOGIN = "login";
    public static final String CONDITION_OR_LOGIN = "or_login";

    public static final String BOOLEAN = "b";
    public static final String BYTE = "y";
    public static final String SHORT = "o";
    public static final String INT = "i";
    public static final String LONG = "l";
    public static final String FLOAT = "f";
    public static final String DOUBLE = "d";
    public static final String STRING = "s";

    public static List<String> queryStringFillMap(Map<String, Object> map, Uri uri) {

        List<String> conditions = uri.getQueryParameters(CONDITION);

        Pattern pattern = Pattern.compile(REGEXP);
        Matcher matcher;

        Set<String> paramsNames = uri.getQueryParameterNames();
        String queryValue, tag, value;

        for (String name : paramsNames) {
            if (CONDITION.equals(name)) { continue;}

            queryValue = uri.getQueryParameter(name);
            matcher = pattern.matcher(queryValue);
            // groupCount() == allCount - 1
            if (matcher.matches() && matcher.groupCount() == 2) {
                tag = matcher.group(1);
                value = matcher.group(2);

                if (BOOLEAN.equals(tag)) {
                    MapFiller.fillBoolean(map, name, value);
                } else if (BYTE.equals(tag)) {
                    MapFiller.fillByte(map, name, value);
                } else if (SHORT.equals(tag)) {
                    MapFiller.fillShort(map, name, value);
                } else if (INT.equals(tag)) {
                    MapFiller.fillInt(map, name, value);
                } else if (LONG.equals(tag)) {
                    MapFiller.fillLong(map, name, value);
                } else if (FLOAT.equals(tag)) {
                    MapFiller.fillFloat(map, name, value);
                } else if (DOUBLE.equals(tag)) {
                    MapFiller.fillDouble(map, name, value);
                } else {
                    MapFiller.fillString(map, name, value);
                }
            }else {
                MapFiller.fillString(map, name, queryValue);
            }
        }

        return conditions;
    }

    /**
     * 解析uri中的querystring到intent中
     * @param intent intent
     * @param uri uri
     * @return 如果querystring有CONDITION的key， 则返回，并且不加入到intent里
     * @see #CONDITION
     * @see #CONDITION_LOGIN
     *  @see #CONDITION_OR_LOGIN
     */
    public static List<String> queryStringFillIntent(Intent intent, Uri uri) {

        List<String> conditions = uri.getQueryParameters(CONDITION);

        Pattern pattern = Pattern.compile(REGEXP);
        Matcher matcher;

        Set<String> paramsNames = uri.getQueryParameterNames();
        String queryValue, tag, value;

        for (String name : paramsNames) {
            if (CONDITION.equals(name)) { continue;}

            queryValue = uri.getQueryParameter(name);
            matcher = pattern.matcher(queryValue);
            // groupCount() == allCount - 1
            if (matcher.matches() && matcher.groupCount() == 2) {
                tag = matcher.group(1);
                value = matcher.group(2);

                if (BOOLEAN.equals(tag)) {
                    IntentFiller.fillBoolean(intent, name, value);
                } else if (BYTE.equals(tag)) {
                    IntentFiller.fillByte(intent, name, value);
                } else if (SHORT.equals(tag)) {
                    IntentFiller.fillShort(intent, name, value);
                } else if (INT.equals(tag)) {
                    IntentFiller.fillInt(intent, name, value);
                } else if (LONG.equals(tag)) {
                    IntentFiller.fillLong(intent, name, value);
                } else if (FLOAT.equals(tag)) {
                    IntentFiller.fillFloat(intent, name, value);
                } else if (DOUBLE.equals(tag)) {
                    IntentFiller.fillDouble(intent, name, value);
                } else {
                    IntentFiller.fillString(intent, name, value);
                }
            }else {
                IntentFiller.fillString(intent, name, queryValue);
            }
        }

        return conditions;
    }
}
