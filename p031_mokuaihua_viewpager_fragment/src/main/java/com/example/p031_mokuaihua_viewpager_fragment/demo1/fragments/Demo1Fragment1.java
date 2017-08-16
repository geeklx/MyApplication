package com.example.p031_mokuaihua_viewpager_fragment.demo1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseIndexNetFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo1.Demo1Activity;

/**
 * Created by shining on 2017/8/14.
 */

public class Demo1Fragment1 extends BaseIndexNetFragment {


    @Override
    public void call(Object value) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo1_fragment1;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        rootView.findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToFragment("demo1的fragment1页面");
            }
        });
    }

    /**
     * 页面传值操作部分
     *
     * @param id1
     */
    private void SendToFragment(String id1) {
        //举例
//        IndexFoodFragmentUpdateIds iff = new IndexFoodFragmentUpdateIds();
//        iff.setFood_definition_id(id1);
//        iff.setFood_name(id2);
        if (getActivity() != null && getActivity() instanceof Demo1Activity) {
            ((Demo1Activity) getActivity()).callFragment(id1, Demo1Fragment2.class.getName());
        }
    }
}
