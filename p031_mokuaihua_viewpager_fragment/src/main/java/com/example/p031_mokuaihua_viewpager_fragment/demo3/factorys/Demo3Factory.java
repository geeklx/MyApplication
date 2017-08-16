package com.example.p031_mokuaihua_viewpager_fragment.demo3.factorys;

import android.support.v4.util.SparseArrayCompat;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.configs.Demo3Config;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.fragments.Demo3Fragment10;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.fragments.Demo3Fragment11;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.fragments.Demo3Fragment20;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.fragments.Demo3Fragment21;


/**
 * 首页模块fragment的工厂, 首页模块有需要更换的，可以在此修改，格式为id->Fragment.class<br />
 * Created by shining on 2016/8/1.
 */

public class Demo3Factory {

    public static void setup() {
        Demo3Config.PAGE_COUNT = 2;
        Demo3Config.PAGE_LAYOUT_ID = "activity_demo3_layout_pager_item_";
        Demo3Config.DEFAULT_PAGE_INDEX = 0;
        registerFragments(Demo3Config.getFragments());
    }

    private static void registerFragments(SparseArrayCompat<Class<? extends BaseFragment>> sIndexFragments) {

        sIndexFragments.put(R.id.fragment_demo3_pager_index_0_0, Demo3Fragment10.class);//第一屏 layout1
        sIndexFragments.put(R.id.fragment_demo3_pager_index_0_1, Demo3Fragment11.class);//第一屏 layout2

        sIndexFragments.put(R.id.fragment_demo3_pager_index_1_0, Demo3Fragment20.class);//第二屏 layout1
        sIndexFragments.put(R.id.fragment_demo3_pager_index_1_1, Demo3Fragment21.class);//第二屏 layout2

    }
}
