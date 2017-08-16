package com.example.p031_mokuaihua_viewpager_fragment.base;

import android.view.View;

import com.example.p031_mokuaihua_viewpager_fragment.demo3.Demo3Activity;
import com.example.p031_mokuaihua_viewpager_fragment.utils.AppManager;
import com.example.p031_mokuaihua_viewpager_fragment.utils.IndexViewPager;

/**
 * Created by geek on 2016/8/12.
 */

public abstract class BaseIndexFragment extends BaseFragment {

    /**
     * 获取MainActivity的ViewPager
     * @return
     */
    public IndexViewPager findIndexViewPager() {
        if (AppManager.getInstance().top() instanceof Demo3Activity){

        }
        if (getActivity() == null || !(getActivity() instanceof Demo3Activity)) { return null;}
        return null;
//        return ((Demo3Activity) getActivity()).getViewPager();
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
