package com.example.p031_mokuaihua_viewpager_fragment.demo1.factorys;

import android.support.v4.util.SparseArrayCompat;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo1.fragments.Demo1Fragment1;
import com.example.p031_mokuaihua_viewpager_fragment.demo1.fragments.Demo1Fragment2;


/**
 * Created by shining on 2017/2/27 0027.
 */

public class Demo1FragmentFactory {
    private static SparseArrayCompat<Class<? extends BaseFragment>> sIndexFragments = new SparseArrayCompat<>();

    static {

        sIndexFragments.put(R.id.demo1_page_0_item_0, Demo1Fragment1.class);//模块1
        sIndexFragments.put(R.id.demo1_page_0_item_1, Demo1Fragment2.class);//模块2

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
