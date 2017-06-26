package com.shining.p010_recycleviewall.shoucang.basefragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shining.p010_recycleviewall.net.Net;
import com.shining.p010_recycleviewall.utils.ViewHelper;
import com.shining.p010_recycleviewall.widget.IMEUtil;

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
        if (autoHideIME()) { rootView = IMEUtil.wrap(rootView);}
        ButterKnife.bind(this, rootView);
        setup(rootView, savedInstanceState);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        HookUtil.hookClick(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {

    }

    protected <T extends View> T f(View rootView, @IdRes int resId) {
        return ViewHelper.f(rootView, resId);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(getActivity(), activity));
    }

    public void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        startActivityForResult(new Intent(getActivity(), activity), requestCode);
    }



    protected abstract int getLayoutId();

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Net.getInstance().get().cancel(getClass().getName());
//        ShowLoadingUtil.onDestory();
        super.onDestroy();
    }

    protected boolean autoHideIME() {
        return false;
    }
}
