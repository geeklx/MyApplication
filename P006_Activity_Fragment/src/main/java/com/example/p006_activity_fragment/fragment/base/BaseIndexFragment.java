package com.example.p006_activity_fragment.fragment.base;

import android.view.View;

import com.example.p006_activity_fragment.MainActivity;
import com.example.p006_activity_fragment.widget.IndexViewPager;


/**
 * Created by qibin on 2016/8/12.
 */

public abstract class BaseIndexFragment extends BaseFragment {

    /**
     * 获取MainActivity的ViewPager
     * @return
     */
    public IndexViewPager findIndexViewPager() {
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) { return null;}
        return ((MainActivity) getActivity()).getViewPager();
    }

    /**
     * 给Viewpager设置事件分发对象
     * @param view
     */
    public void setPagerTouchBindView(View view) {
        IndexViewPager pager = findIndexViewPager();
        if (pager != null) { pager.bind(view);}
    }

    public void removePagerTouchBindView(View view) {
        IndexViewPager pager = findIndexViewPager();
        if (pager != null) { pager.remove(view);}
    }

    public void call(Object value) {

    }
}
