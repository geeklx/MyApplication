package com.example.p031_mokuaihua_viewpager_fragment.demo4.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseIndexNetFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo5.Demo5Activity;
import com.example.p031_mokuaihua_viewpager_fragment.utils.ToastUtil;

/**
 * Created by shining on 2017/8/14.
 */

public class Demo4Fragment21 extends BaseIndexNetFragment {

    private TextView tv1;

    @Override
    public void call(Object value) {
        String ids = (String) value;
        tv1.setText(ids);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo4_fragment21;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tv1= (TextView) rootView.findViewById(R.id.tv21);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Demo5Activity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        ToastUtil.showToastCenter("刷新操作~");
    }
}
