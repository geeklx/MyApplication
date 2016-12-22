package com.example.p018_activity_fragmenta_b.mywalletchangemima;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by shining on 2016/12/21 0021.
 */

public class MyFragmentHelper {
    /**
     * 新建fragment实例
     * @param fragmentKlass
     * @param bundle
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T newFragment(Class<T> fragmentKlass, Bundle bundle) {
        T res = null;
        try {
            res = fragmentKlass.newInstance();
            if (bundle != null) {
                res.setArguments(bundle);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据fragment的完整包名+名称实例化fragment
     * @param className
     * @param bundle
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T newFragment(String className, Bundle bundle) {
        T res = null;
        try {
            res = (T) Class.forName(className).newInstance();
            if (bundle != null) {
                res.setArguments(bundle);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
