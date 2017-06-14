/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.p022_hois.hoisjump;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.example.p022_hois.application.DemoApplication;
import com.example.p022_hois.hioscommon.AdListItem;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiosHelper {

    private static Class<? extends Activity> webViewActivity;

    public static void configWebActivity(Class<? extends Activity> activity) {
        webViewActivity = activity;
    }

    /**
     * banner点击， 可能跳转网页，可能跳转activity，可能执行activity上的一个方法， 但这个方法的参数必须是一个Map
     *
     * @param activity
     * @param item
     */
    public static void click(final Activity activity, final AdListItem item) {
        click(activity, activity, item);
    }

    /**
     * banner点击， 可能跳转网页，可能跳转activity，可能执行receiver上的一个方法， 但这个方法的参数必须是一个Map
     *
     * @param activity
     * @param receiver
     * @param item
     */
    public static void click(final Activity activity, final Object receiver, final AdListItem item) {
        if (testingShowWebView(activity, item)) {
            return;
        }

        String itemUri = item.getUrl();
        shouldOverrideUrl(activity, receiver, itemUri);
    }

    /**
     * 是否拦截网页，如果拦截了， 这里会默认处理，调用者无需处理
     *
     * @param activity
     * @param url
     * @return true 拦截， false 不拦截
     */
    public static boolean shouldOverrideUrl(final Activity activity, final String url) {
        boolean is_stop = shouldOverrideUrl(activity, activity, url);
        return is_stop;
    }

    /**
     * 是否拦截网页，如果拦截了， 这里会默认处理，调用者无需处理
     *
     * @param activity
     * @param url
     * @return true 拦截， false 不拦截
     */
    public static boolean shouldOverrideUrl(final Activity activity, final Object receiver, final String url) {
        Uri uri = Uri.parse(url);
        if (!checkUriHost(uri)) {
            Log.d("geek", "geek");
            return false;
        }

        String host = uri.getHost();
        if (TextUtils.isEmpty(host)) {
            return false;
        }

        if (!host.contains(".")) { // invoke method
            invokeMethod(activity, receiver, uri, host);
        } else { // target activity
            Pair<String, String> pair = HiosAlias.getByName(host);
            // when host is alias
            if (pair != null) {
                activity(activity, uri, pair);
                return true;
            }

            // when host is class name
            if (host.startsWith(".")) {
                host = DemoApplication.get().getPackageName() + host;
            }

            activity(activity, uri, host);
        }

        return true;
    }

    /**
     * 测试数据是否可以跳转webview， 如果可以直接跳转webview
     *
     * @param activity
     * @param item
     * @return
     */
    public static boolean testingShowWebView(final Activity activity, final AdListItem item) {
        String itemUri = item.getUrl();
        if (TextUtils.isEmpty(itemUri)) {
            Intent intent = new Intent(activity, webViewActivity);
            intent.putExtra("aid", item.getAid());
            activity.startActivity(intent);
            return true;
        }

        return false;
    }

    public static boolean checkUriHost(Uri uri) {
        return UriHelper.HIOS_SCHEME.equalsIgnoreCase(uri.getScheme());
    }

    /**
     * 调用receiver上的方法
     *
     * @param receiver
     * @param methodName
     * @param map
     */
    private static void methodInvoker(Object receiver, String methodName,
                                      HashMap<String, Object> map) {
        for (Class<?> klass = receiver.getClass();
             !(Object.class.equals(klass));
             klass = klass.getSuperclass()) {
            try {
                Method method = klass.getDeclaredMethod(methodName, Map.class);
                method.setAccessible(true);
                method.invoke(receiver, map);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void invokeMethod(Activity activity, final Object receiver, Uri uri,
                                     final String host) {
        final HashMap<String, Object> map = new HashMap<>();
        List<String> conditions = UriHelper.queryStringFillMap(map, uri);
        if (conditions.contains(UriHelper.CONDITION_LOGIN)) {
//            UserUtils.loginToDo(activity, new Runnable() {
//                @Override
//                public void run() {
//                    methodInvoker(receiver, host, map);
//                }
//            });
            methodInvoker(receiver, host, map);
        } else {
            methodInvoker(receiver, host, map);
        }
    }

    private static void activity(final Activity activity, Uri uri, Pair<String, String> pair) {
        String packageName = pair.first;
        String className = pair.second;

        if (className.startsWith(".")) {
            className = packageName + className;
        }

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, className));
        activity(activity, uri, intent);
    }

    /**
     * 跳转activity
     *
     * @param activity
     * @param uri
     * @param host
     */
    private static void activity(final Activity activity, Uri uri, String host) {
        try {
            Class<?> klass = Class.forName(host);
            final Intent intent = new Intent(activity, klass);
            activity(activity, uri, intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void activity(final Activity activity, Uri uri, final Intent intent) {
        List<String> conditions = UriHelper.queryStringFillIntent(intent, uri);
        if (conditions.contains(UriHelper.CONDITION_LOGIN)) {
//            UserUtils.loginToDo(activity, new Runnable() {
//                @Override
//                public void run() {
//                    activity.startActivity(intent);
//                }
//            });
            activity.startActivity(intent);
        } else {
            activity.startActivity(intent);
        }
    }
}
