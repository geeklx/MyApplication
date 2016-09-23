package com.example.p006_activity_fragment.fragment.index;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.p006_activity_fragment.MainActivity;
import com.example.p006_activity_fragment.R;
import com.example.p006_activity_fragment.fragment.base.BaseIndexFragment;

import butterknife.BindView;

/**
 * 食材管理<br />
 * Created by geek on 2016/8/1.
 */

public class IndexFoodFragment extends BaseIndexFragment {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_main;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);

//        getView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SendToIndexCookBookFragment("1", ",2");
//
//            }
//        });

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToIndexCookBookFragment("1", ",2");
            }
        });
        tv.setText("食材管理");
    }

    /**
     * 刷新页面操作部分
     *
     * @param id1
     * @param id2
     */
    private void SendToIndexCookBookFragment(String id1, String id2) {
//        IndexFoodFragmentUpdateIds iff = new IndexFoodFragmentUpdateIds();
//        iff.setFood_definition_id(id1);
//        iff.setFridge_food_ids(id2);

        if (getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).callFragment(id1 + id2, IndexCookBookFragment.class.getName());
        }
    }
}
