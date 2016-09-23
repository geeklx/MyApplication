package com.example.p006_activity_fragment.fragment.index;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.p006_activity_fragment.R;
import com.example.p006_activity_fragment.fragment.base.BaseFragment;

import butterknife.BindView;

/**
 * 易果专区<br />
 * Created by geek on 2016/8/1.
 */

public class IndexShopFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv.setText("易果专区");
    }
}
