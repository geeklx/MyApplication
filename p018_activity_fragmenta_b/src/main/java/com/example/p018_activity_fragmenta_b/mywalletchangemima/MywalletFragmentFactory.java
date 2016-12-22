package com.example.p018_activity_fragmenta_b.mywalletchangemima;

import android.support.v4.util.SparseArrayCompat;

import com.example.p018_activity_fragmenta_b.R;


/**
 * 首页模块fragment的工厂, 首页模块有需要更换的，可以在此修改，格式为id->Fragment.class<br />
 * Created by shining on 2016/8/1.
 */

public class MywalletFragmentFactory {
    private static SparseArrayCompat<Class<? extends MywalletBaseFragment>> sIndexFragments = new SparseArrayCompat<>();

    static {
        sIndexFragments.put(R.id.pager_index_0_0, ShezhimimaOneFragment.class);
        sIndexFragments.put(R.id.pager_index_1_0, ShezhimimaTwoFragment.class);
    }

    public static Class<? extends MywalletBaseFragment> get(int id) {
        if (sIndexFragments.indexOfKey(id) < 0) {
            throw new UnsupportedOperationException("cannot find fragment by " + id);
        }
        return sIndexFragments.get(id);
    }

    public static SparseArrayCompat<Class<? extends MywalletBaseFragment>> get() {
        return sIndexFragments;
    }
}
