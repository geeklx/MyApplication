package com.example.p031_mokuaihua_viewpager_fragment.demo1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseIndexNetFragment;
import com.example.p031_mokuaihua_viewpager_fragment.utils.ToastUtil;

/**
 * Created by shining on 2017/8/14.
 */

public class Demo1Fragment2 extends BaseIndexNetFragment {

    @Override
    public void call(Object value) {
        String ids = (String) value;
        ToastUtil.showToastShort(ids);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo1_fragment2;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);

    }
}
