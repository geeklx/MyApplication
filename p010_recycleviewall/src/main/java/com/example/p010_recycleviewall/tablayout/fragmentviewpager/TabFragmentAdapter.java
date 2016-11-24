/**
 * Copyright  2015,  Smart  Haier
 * All  rights  reserved.
 * Description:
 * Author:  geyanyan
 * Date:  2016/11/18
 * FileName:  TabFragmentAdapter.java
 * History:
 * 1.  Date:2016/11/18 17:59
 * Author:geyanyan
 * Modification:
 */
package com.example.p010_recycleviewall.tablayout.fragmentviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.p010_recycleviewall.domain.TabLayouttitleBean;

import java.util.List;

public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    private final List<TabLayouttitleBean> titles;
    private Context context;
    private List<Fragment> fragments;

    public TabFragmentAdapter(List<Fragment> fragments, List<TabLayouttitleBean> titles, FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }
}
