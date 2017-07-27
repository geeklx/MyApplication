package com.shining.p010_recycleviewall.shoucang.factorys;

import android.support.v4.util.SparseArrayCompat;

import com.shining.p010_recycleviewall.R;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseFragment;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig1;
import com.shining.p010_recycleviewall.shoucang.fragment.CaipuFragment;
import com.shining.p010_recycleviewall.shoucang.fragment.DiantaiFragment;
import com.shining.p010_recycleviewall.shoucang.fragment.ShangpinFragment;
import com.shining.p010_recycleviewall.shoucang.fragment.ShipinFragment;
import com.shining.p010_recycleviewall.shoucang.fragment.YinyueFragment;

public class ShoucangFactory1 {

    public static void setup() {
//        IndexConfig.PAGE_COUNT = 3;
//        IndexConfig.PAGE_ID = "old_pager_index_";
//        IndexConfig.DEFAULT_PAGE_INDEX = 1;
        registerFragments(ShoucangConfig1.getFragments());
    }

    private static void registerFragments(SparseArrayCompat<Class<? extends BaseFragment>> sIndexFragments) {
        sIndexFragments.put(R.id.sc_page_0_item_0, ShangpinFragment.class);//商品
        sIndexFragments.put(R.id.sc_page_0_item_1, CaipuFragment.class);//菜谱
        sIndexFragments.put(R.id.sc_page_0_item_2, ShipinFragment.class);//视频
        sIndexFragments.put(R.id.sc_page_0_item_3, DiantaiFragment.class);//电台
        sIndexFragments.put(R.id.sc_page_0_item_4, YinyueFragment.class);//音乐
    }
}
