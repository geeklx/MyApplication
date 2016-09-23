package com.example.p006_activity_fragment.fragment.index;

import android.support.v4.util.SparseArrayCompat;

import com.example.p006_activity_fragment.R;
import com.example.p006_activity_fragment.fragment.base.BaseFragment;


/**
 * 首页模块fragment的工厂, 首页模块有需要更换的，可以在此修改，格式为id->Fragment.class<br />
 * Created by geek on 2016/8/1.
 */

public class IndexFragmentFactory {
    private static SparseArrayCompat<Class<? extends BaseFragment>> mIndexFragments = new SparseArrayCompat<>();
    static {
        mIndexFragments.put(R.id.page_0_item_1, IndexShopFragment.class);
        mIndexFragments.put(R.id.page_0_item_2, IndexBannerFragment.class);
        mIndexFragments.put(R.id.page_0_item_3, IndexMusicFragment.class);
        mIndexFragments.put(R.id.page_0_item_4, IndexFoodFragment.class);
        mIndexFragments.put(R.id.page_0_item_5, IndexCookBookFragment.class);
        mIndexFragments.put(R.id.page_1_item_1, IndexDeliveryFragment.class);
        mIndexFragments.put(R.id.page_1_item_2, IndexControllerFragment.class);
        mIndexFragments.put(R.id.page_1_item_3, IndexLeaveMessageFragment.class);
        mIndexFragments.put(R.id.page_1_item_4, IndexPersonalCenterFragment.class);
        mIndexFragments.put(R.id.page_1_item_5, IndexLeisureFragment.class);
    }

    public static Class<? extends BaseFragment> get(int id) {
        if (mIndexFragments.indexOfKey(id) < 0) { throw new UnsupportedOperationException("cannot find fragment by " + id);}
        return mIndexFragments.get(id);
    }

    public static SparseArrayCompat<Class<? extends BaseFragment>> get() {
        return mIndexFragments;
    }
}
