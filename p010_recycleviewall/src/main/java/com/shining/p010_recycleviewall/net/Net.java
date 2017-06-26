package com.shining.p010_recycleviewall.net;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.shining.p010_recycleviewall.net.parser.FastJsonParserFactory;
import com.shining.p010_recycleviewall.utils.ParamsUtils;

import org.loader.glin.Callback;
import org.loader.glin.Glin;
import org.loader.glin.call.Call;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by qibin on 2016/7/19. <br />
 * 网络请求
 */

public class Net {

    private static Net sInstance;
    private Glin mGlin;

    private Net() {
        mGlin = new Glin.Builder()
                .client(new OkClient())
                .baseUrl("https://www.onehaier.com/")
                .timeout(5 * 1000)
                .debug(true)
                .parserFactory(new FastJsonParserFactory())
                .resultInterceptor(new ResultInterceptor())
                .build();
    }

    public static Net getInstance() {
        if (sInstance == null) {
            sInstance = new Net();
        }
        return sInstance;
    }

    /**
     * 获取网络框架
     *
     * @return
     */
    public Glin get() {
        return mGlin;
    }

    /**
     * 创建一个业务请求
     *
     * @param convertClass 业务请求接口的class
     * @param tag          本次网络请求的tag
     * @return
     */
    public <T> T create(Class<T> convertClass, Object tag) {
        return get().create(convertClass, tag);
    }

    /**
     * 创建一个业务请求 <br />
     * {@link Net#create}方法的静态封装
     *
     * @param convertClass 业务请求接口的class
     * @param tag          本次网络请求的tag
     * @return
     */
    public static <T> T build(Class<T> convertClass, Object tag) {
        return getInstance().create(convertClass, tag);
    }

    /**
     * @param context    tag
     * @param api        api接口
     * @param methodName 要使用的方法名
     * @param args       提交参数
     * @param callback
     * @param <T>
     * @return
     * @deprecated 直接访问网络并back, 慎用！
     */
    @SuppressWarnings("unchecked")
    public static <T, V> void invoke(Context context, Class<T> api, String methodName, Object args, Callback<V> callback) {
        T res = build(api, context.getClass().getName());
        try {
            Method m = res.getClass().getDeclaredMethod(methodName, String.class);
            m.setAccessible(true);
            Call<V> call = (Call<V>) m.invoke(res, ParamsUtils.just(args));
            call.enqueue(callback);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context    tag
     * @param api        api接口
     * @param methodName 要使用的方法名
     * @param args       提交参数(JSONObject类型)
     * @param callback
     * @param <T>
     * @return
     * @deprecated 直接访问网络并back，慎用！
     */
    @SuppressWarnings("unchecked")
    public static <T, V> void invoke(Context context, Class<T> api, String methodName, JSONObject args, Callback<V> callback) {
        T res = build(api, context.getClass().getName());
        try {
            Method m = res.getClass().getDeclaredMethod(methodName, String.class);
            m.setAccessible(true);
            Call<V> call = (Call<V>) m.invoke(res, ParamsUtils.just(args));
            call.enqueue(callback);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
