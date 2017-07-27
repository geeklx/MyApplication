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

import com.shining.p010_recycleviewall.application.ConstantNetUtil;
import com.shining.p010_recycleviewall.application.NetConfig;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseActivity;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseFragment;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseIndexFragment;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig1;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig2;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig3;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig4;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig5;
import com.shining.p010_recycleviewall.shoucang.configs.ShoucangConfig6;
import com.shining.p010_recycleviewall.shoucang.fragment.ScFragmentHelper;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO 多版本切换 写此方法bufen
        which_version();
//        ShoucangConfig0.config();//manifestPlaceholders的妙用
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        onclickListener();
        setupFragments();
    }

    private void which_version() {
        if (ConstantNetUtil.VERSION_APK == NetConfig.version_name0) {
            ShoucangConfig.config();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name1) {
            ShoucangConfig1.config();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name2) {
            ShoucangConfig2.config();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name3) {
            ShoucangConfig3.config();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name4) {
            ShoucangConfig4.config();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name5) {
            ShoucangConfig5.config();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name6) {
            ShoucangConfig6.config();
        }
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
//        SparseArrayCompat<Class<? extends BaseFragment>> array = ScIndexFragmentFactory.get();//一个版本模式bufen
        //TODO 多版本模式bufen
        SparseArrayCompat<Class<? extends BaseFragment>> array = which_version_fragment_config();//demo
//        SparseArrayCompat<Class<? extends BaseFragment>> array = ShoucangConfig0.getFragments();//manifestPlaceholders的妙用
        int size = array.size();
        BaseFragment item;
        for (int i = 0; i < size; i++) {
            item = ScFragmentHelper.newFragment(array.valueAt(i), null);
            ft.replace(array.keyAt(i), item, item.getClass().getName());
        }
        ft.commitAllowingStateLoss();
    }

    private SparseArrayCompat<Class<? extends BaseFragment>> which_version_fragment_config() {
        if (ConstantNetUtil.VERSION_APK == NetConfig.version_name0) {
            return ShoucangConfig.getFragments();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name1) {
            return ShoucangConfig1.getFragments();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name2) {
            return ShoucangConfig2.getFragments();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name3) {
            return ShoucangConfig3.getFragments();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name4) {
            return ShoucangConfig4.getFragments();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name5) {
            return ShoucangConfig5.getFragments();
        } else if (ConstantNetUtil.VERSION_APK == NetConfig.version_name6) {
            return ShoucangConfig6.getFragments();
        }
        return ShoucangConfig.getFragments();
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
