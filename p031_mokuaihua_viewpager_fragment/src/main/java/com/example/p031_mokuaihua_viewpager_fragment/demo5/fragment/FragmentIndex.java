package com.example.p031_mokuaihua_viewpager_fragment.demo5.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p031_mokuaihua_viewpager_fragment.R;

public class FragmentIndex extends Fragment {

    private String tablayoutId;
    private Context mContext;
    private View vRoot;
    private TextView tv1;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (vRoot == null) {
            vRoot = LayoutInflater.from(mContext).inflate(R.layout.activity_demo5_fragment_index, container, false);
        }
        tv1 = (TextView) vRoot.findViewById(R.id.tv1);

//        initData();
        return vRoot;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
//        which_page = 1;
        //categoryId
//        doNetWorkContent(which_page);
    }

}
