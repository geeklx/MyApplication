package com.example.p006_activity_fragment.fragment.index;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p006_activity_fragment.R;
import com.example.p006_activity_fragment.fragment.base.BaseIndexFragment;

import butterknife.BindView;

/**
 * 健康食谱<br />
 * Created by geek on 2016/8/1.
 */

public class IndexCookBookFragment extends BaseIndexFragment {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_main;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv.setText("健康食谱");
    }
    @Override
    public void call(Object value) {
//        IndexFoodFragmentUpdateIds ids = (IndexFoodFragmentUpdateIds) value;
        if (value.toString() == null) {
            return;
        }
        Toast.makeText(getActivity(), value.toString(), Toast.LENGTH_LONG).show();
    }
}
