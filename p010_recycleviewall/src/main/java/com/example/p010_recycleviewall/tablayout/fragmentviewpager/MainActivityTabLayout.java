package com.example.p010_recycleviewall.tablayout.fragmentviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.domain.LabFour;
import com.example.p010_recycleviewall.domain.TabLayoutcontentBean;
import com.example.p010_recycleviewall.domain.TabLayouttitleBean;
import com.example.p010_recycleviewall.widget.NoScrollView.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shining on 2016/11/24 0024.
 */

public class MainActivityTabLayout extends FragmentActivity implements View.OnClickListener {

    private NoScrollViewPager viewPager;
    private TabLayout tablayout;
    private List<TabLayoutcontentBean> contentlist;
    private List<TabLayouttitleBean> titlelist;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_fragmentviewpager);
        findview();
        onclicklistener();
        doNetWork();
    }

    public FragmentA newInstance(String category_id) {
        FragmentA fragmenta = new FragmentA();
        Bundle bundle = new Bundle();
        bundle.putString("category", category_id);
        fragmenta.setArguments(bundle);
        return fragmenta;
    }

    private void doNetWork() {
        titlelist = new ArrayList<TabLayouttitleBean>();
        contentlist = new ArrayList<TabLayoutcontentBean>();
        LabFour lf = new LabFour();
        titlelist = lf.getImpressions();
        contentlist = lf.getmParent_model();
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < titlelist.size(); i++) {
            Fragment fragment = newInstance(titlelist.get(i).getCategoryId());
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TabFragmentAdapter(fragments, titlelist, getSupportFragmentManager(), this));
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabTextColors(getResources().getColor(R.color.black_title), Color.WHITE);
        tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_bg));
    }

    private void onclicklistener() {

    }

    private void findview() {
        viewPager = (NoScrollViewPager) findViewById(R.id.vp_fm);
        tablayout = (TabLayout) findViewById(R.id.tl_fm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tl_fm:

                break;
            default:
                break;

        }
    }
}
