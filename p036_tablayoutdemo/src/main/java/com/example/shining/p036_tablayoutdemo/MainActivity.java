package com.example.shining.p036_tablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.shining.p036_tablayoutdemo.tablayoutcommon.TabSelectAdapter;
import com.example.shining.p036_tablayoutdemo.tablayoutcommon.TabUtils;
import com.example.shining.p036_tablayoutdemo.tablayoutcommon.UnAnimTabLayout;

public class MainActivity extends AppCompatActivity {

    private UnAnimTabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter mAdapter;
    private String[] titles = {"热点", "推荐", "标题党", "视频", "本地农业", "科技", "政治", "农业", "其他"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (UnAnimTabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        //初始化bufen
//        tab_add.removeAllTabs();
//        for (FmNewFoodFenleiBean item : list_tab_add) {
//            tab_add.addTab(tab_add.newTab()
//                    .setTag(item.getFood_category_id()).setText(item.getFood_category_name()));
//        }

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabSelectAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabUtils.tabSelect(tabLayout, tab);
//                TabUtils.tabBoldCurrent(tabLayout, tab);
//                int tag = (int) tab.getTag();
//                LvToastUtil.showToast(TabActivity1.this, tag + "");

            }
        });

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout, 10, 10);
            }
        });

    }

    class TabAdapter extends FragmentStatePagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int pos) {
            return TabFragment.getInstance(titles[pos]);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}

