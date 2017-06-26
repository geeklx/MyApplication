package com.shining.p010_recycleviewall.shoucang.fragment;

import android.support.v4.util.SparseArrayCompat;

import com.shining.p010_recycleviewall.shoucang.basefragment.BaseFragment;


/**
 * Created by shining on 2017/2/27 0027.
 */

public class ScIndexFragmentFactory {
    private static SparseArrayCompat<Class<? extends BaseFragment>> sIndexFragments = new SparseArrayCompat<>();

    static {

        sIndexFragments.put(com.shining.p010_recycleviewall.R.id.sc_page_0_item_0, ShangpinFragment.class);//商品
        sIndexFragments.put(com.shining.p010_recycleviewall.R.id.sc_page_0_item_1, CaipuFragment.class);//菜谱
        sIndexFragments.put(com.shining.p010_recycleviewall.R.id.sc_page_0_item_2, ShipinFragment.class);//视频
        sIndexFragments.put(com.shining.p010_recycleviewall.R.id.sc_page_0_item_3, DiantaiFragment.class);//电台
        sIndexFragments.put(com.shining.p010_recycleviewall.R.id.sc_page_0_item_4, YinyueFragment.class);//音乐

    }

    public static Class<? extends BaseFragment> get(int id) {
        if (sIndexFragments.indexOfKey(id) < 0) {
            throw new UnsupportedOperationException("cannot find fragment by " + id);
        }
        return sIndexFragments.get(id);
    }

    public static SparseArrayCompat<Class<? extends BaseFragment>> get() {
        return sIndexFragments;
    }
}
