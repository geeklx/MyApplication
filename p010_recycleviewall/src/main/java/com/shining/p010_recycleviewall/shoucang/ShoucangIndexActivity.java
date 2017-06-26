package com.shining.p010_recycleviewall.shoucang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.shining.p010_recycleviewall.shoucang.basefragment.BaseActivity;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseFragment;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseIndexFragment;
import com.shining.p010_recycleviewall.shoucang.fragment.ScFragmentHelper;
import com.shining.p010_recycleviewall.shoucang.fragment.ScIndexFragmentFactory;

import butterknife.BindView;

/**
 * Created by shining on 2017/2/27 0027.
 */

public class ShoucangIndexActivity extends BaseActivity implements View.OnClickListener {

    @BindView(com.shining.p010_recycleviewall.R.id.home)
    LinearLayout home;
    @BindView(com.shining.p010_recycleviewall.R.id.back)
    LinearLayout back;

    @Override
    protected int getLayoutId() {
        return com.shining.p010_recycleviewall.R.layout.activity_shoucang_index;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        onclickListener();
        setupFragments();
    }

    private void onclickListener() {
        home.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    /**
     * 初始化首页fragments
     */
    private void setupFragments() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SparseArrayCompat<Class<? extends BaseFragment>> array = ScIndexFragmentFactory.get();
        int size = array.size();
        BaseFragment item;
        for (int i = 0; i < size; i++) {
            item = ScFragmentHelper.newFragment(array.valueAt(i), null);
            ft.replace(array.keyAt(i), item, item.getClass().getName());
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.shining.p010_recycleviewall.R.id.home:
                onBackPressed();
                break;
            case com.shining.p010_recycleviewall.R.id.back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    /**
     * fragment间通讯bufen
     *
     * @param value 要传递的值
     * @param tag   要通知的fragment的tag
     */
    public void callFragment(Object value, String... tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;
        for (String item : tag) {
            if (TextUtils.isEmpty(item)) {
                continue;
            }

            fragment = fm.findFragmentByTag(item);
            if (fragment != null && fragment instanceof BaseIndexFragment) {
                ((BaseIndexFragment) fragment).call(value);
            }
        }
    }
}
