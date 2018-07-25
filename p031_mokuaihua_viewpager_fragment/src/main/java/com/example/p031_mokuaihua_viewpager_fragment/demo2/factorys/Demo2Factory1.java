package com.example.p031_mokuaihua_viewpager_fragment.demo2.factorys;

import android.support.v4.util.SparseArrayCompat;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo2.configs.Demo2Config;
import com.example.p031_mokuaihua_viewpager_fragment.demo2.fragments.Demo2Fragment1;
import com.example.p031_mokuaihua_viewpager_fragment.demo2.fragments.Demo2Fragment2;

public class Demo2Factory1 {

    public static void setup() {
//        IndexConfig.PAGE_COUNT = 3;
//        IndexConfig.PAGE_ID = "old_pager_index_";
//        IndexConfig.DEFAULT_PAGE_INDEX = 1;
        registerFragments(Demo2Config.getFragments());
    }

    private static void registerFragments(SparseArrayCompat<Class<? extends BaseFragment>> sIndexFragments) {
        sIndexFragments.put(R.id.demo2_page_0_item_1, Demo2Fragment1.class);//菜谱
        sIndexFragments.put(R.id.demo2_page_0_item_2, Demo2Fragment2.class);//视频

    }
}
