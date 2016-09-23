package com.example.p006_activity_fragment.fragment.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.p006_activity_fragment.util.ViewHelper;

import butterknife.ButterKnife;

/**
 * Created by qibin on 2016/7/21.
 */

public abstract class BaseFragment extends Fragment {
    public final String TAG = getClass().getSimpleName().toString();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        setup(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(TAG);
    }

    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {

    }

    protected <T extends View> T f(View rootView, @IdRes int resId) {
        return ViewHelper.f(rootView, resId);
    }

    protected abstract int getLayoutId();

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(TAG);
    }
}
