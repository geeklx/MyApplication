package com.example.p006_activity_fragment.fragment.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p006_activity_fragment.R;
import com.example.p006_activity_fragment.fragment.base.BaseIndexFragment;

import butterknife.BindView;

/**
 * 广告banner <br />
 * Created by geek on 2016/8/1.
 */

public class IndexBannerFragment extends BaseIndexFragment {

    //    @BindView(R.id.pager)
//    ViewPager mPager;
    @BindView(R.id.tv)
    TextView tv;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv.setText("广告banner");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    getView().getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
//        mPager.setAdapter(new BannerAdapter());
    }

    class BannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(container.getContext());
            tv.setText("PAGE" + position);
            tv.setBackgroundColor(Color.YELLOW);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(20.f);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
