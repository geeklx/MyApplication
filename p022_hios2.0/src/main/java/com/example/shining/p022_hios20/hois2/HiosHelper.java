package com.example.shining.p022_hios20.hois2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiosHelper {

    private static final String FLAG_ACTION = "{act}";
    private static String adWebAction;
    private static String webAction;

    /**
     * 配置跳转的广告webActivity的action
     * @param adAct
     * @param webAct
     */
    public static void config(String adAct, String webAct) {
        adWebAction = adAct;
        webAction = webAct;
    }

//    /**
//     * hios事件
//     * @param activity
//     * @param item
//     * @deprecated Use {@link #click(Activity, String, String)} instead.
//     */
//    @Deprecated
//    public static void click(final Activity activity, final AdListItem item) {
//        click(activity, item.getAid(), item.getUrl());
//    }
//
//    /**
//     * hios事件
//     * @param activity
//     * @param receiver
//     * @param item
//     * @deprecated Use {@link #click(Activity, Object, String, String)} instead.
//     */
//    @Deprecated
//    public static void click(final Activity activity, final Object receiver, final AdListItem item) {
//        click(activity, receiver, item.getAid(), item.getUrl());
//    }

    /**
     * hios事件， 可能跳转网页，可能跳转activity，可能执行activity上的一个方法， 但这个方法的参数必须是一个Map
     * @param activity
     * @param aid
     * @param url
     */
    public static void click(final Activity activity, final String aid, final String url) {
        click(activity, activity, aid, url);
    }

    /**
     * hios事件， 可能跳转网页，可能跳转activity，可能执行receiver上的一个方法， 但这个方法的参数必须是一个Map
     * @param activity
     * @param receiver
     * @param aid
     * @param url
     */
    public static void click(final Activity activity, final Object receiver,
                             final String aid, final String url) {
        if (testingShowWebView(activity, aid, url)) { return;}
        shouldOverrideUrl(activity, receiver, url);
    }

    /**
     * 适用广告系统2.0版本的广告点击处理
     * @param act 目标activity
     * @param receiver 如果是方法执行，receiver为接收者
     * @param url 广告地址，区分http(s)和hios
     */
    public static void resolveAd(Activity act, Object receiver, String url) {
        if (!HiosHelper.shouldOverrideUrl(act, receiver, url)) {
            Intent it = new Intent(webAction);
            it.putExtra("url", url);
            try {
                act.startActivity(it);
            } catch (ActivityNotFoundException e) {
                Log.e("Activity", "No Activity found to handle intent " + it);
            }
        }
    }

    /**
     * 是否拦截网页，如果拦截了， 这里会默认处理，调用者无需处理
     * @param activity
     * @param url
     * @return true 拦截， false 不拦截
     */
    public static boolean shouldOverrideUrl(final Activity activity, final String url) {
        return shouldOverrideUrl(activity, activity, url);
    }

    /**
     * 是否拦截网页，如果拦截了， 这里会默认处理，调用者无需处理
     * @param activity
     * @param url
     * @return true 拦截， false 不拦截
     */
    public static boolean shouldOverrideUrl(final Activity activity,
                                            final Object receiver, final String url) {
        Uri uri = Uri.parse(url);
        if (!checkUriHost(uri)) { return false;}

        String host = uri.getHost();
        if (TextUtils.isEmpty(host)) { return false;}

        if (!host.contains(".")) { // invoke method
            invokeMethod(activity, receiver, uri, host);
        } else { // target activity
            Pair<String, String> pair = HiosAlias.getByName(host);

            // when host is alias
            if (pair != null) {
                activity(activity, uri, pair);
                return true;
            }

            // when the host is end with {act},
            // use the host as Activity action
            if (host.endsWith(FLAG_ACTION)) {
                host = host.replace(FLAG_ACTION, "");
                activity(activity, uri, new Intent(host));
                return true;
            }

            // when host is class name
            if (host.startsWith(".")) {
                host = App.get().getPackageName() + host;
            }

            activity(activity, uri, host);
        }

        return true;
    }

    /**
     * 测试数据是否可以跳转webview， 如果可以直接跳转webview
     * @return
     */
    public static boolean testingShowWebView(final Activity activity,
                                             final String aid, final String url) {
        if (TextUtils.isEmpty(url)) {
            Intent intent = new Intent(adWebAction);
            intent.putExtra("aid", aid);
            try {
                activity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.e("Activity", "No Activity found to handle intent " + intent);
            }
            return true;
        }

        return false;
    }

    public static boolean checkUriHost(Uri uri) {
        return UriHelper.HIOS_SCHEME.equalsIgnoreCase(uri.getScheme());
    }

    /**
     * 调用receiver上的方法
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

        if (!conditions.isEmpty()) {
            if (conditions.contains(UriHelper.CONDITION_LOGIN)) {
//                UserUtils.get().loginToDo(activity, new Runnable() {
//                    @Override
//                    public void run() { methodInvoker(receiver, host, map);}
//                });

                return;
            }

            if (conditions.contains(UriHelper.CONDITION_OR_LOGIN)) {
//                if (!UserUtils.get().isUserLogin()) {
//                    UserUtils.get().login(activity);
//                    return;
//                }
            }
        }

        methodInvoker(receiver, host, map);
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
        if (!AppUtils.isIntentAvailable(activity, intent)) { return;}

        List<String> conditions = UriHelper.queryStringFillIntent(intent, uri);
        if (!conditions.isEmpty()) {
            if (conditions.contains(UriHelper.CONDITION_LOGIN)) {
//                UserUtils.get().loginToDo(activity, new Runnable() {
//                    @Override
//                    public void run() { activity.startActivity(intent);}
//                });

                return;
            }

            if (conditions.contains(UriHelper.CONDITION_OR_LOGIN)) {
//                if (!UserUtils.get().isUserLogin()) {
//                    UserUtils.get().login(activity);
//                    return;
//                }
            }
        }

        activity.startActivity(intent);
    }
}
