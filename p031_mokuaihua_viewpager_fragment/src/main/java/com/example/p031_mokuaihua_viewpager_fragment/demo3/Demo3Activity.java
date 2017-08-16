package com.example.p031_mokuaihua_viewpager_fragment.demo3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.base.BaseFragment;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.configs.Demo3Config;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.fragments.Demo3Fragment10;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.fragments.Demo3Fragment20;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.utils.IndexPagerIndicator;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.utils.ViewPagerChangeAdapter;
import com.example.p031_mokuaihua_viewpager_fragment.utils.ComFragmentHelper;
import com.example.p031_mokuaihua_viewpager_fragment.utils.MyLogUtil;

import java.util.List;

import static com.example.p031_mokuaihua_viewpager_fragment.demo3.configs.Demo3Config.PAGE_COUNT;

public class Demo3Activity extends FragmentActivity implements OnClickListener {

    private ViewPager mViewPager;
    private IndexPagerIndicator mIndicator;

    //当前选中的项
    private int currenttab = -1;
//    private static final int PAGE_COUNT = 2;
//    private static final String PAGE_LAYOUT_ID = "demo3_layout_pager_item_";


    private Demo3Fragment10 oneFragment;
    private Demo3Fragment20 twoFragment;

    /**
     * 页面集合
     */
    List<Fragment> fragmentList;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Demo3Config.config();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        mContext = Demo3Activity.this;
        findview();
        addListener();
        doNetWork();
    }

    private void doNetWork() {
        setupViewPager();
        setupFragments();
//        fragmentList = new ArrayList<Fragment>();
//        oneFragment = new ShezhimimaOneFragment();
//        twoFragment = new ShezhimimaTwoFragment();
//
//        fragmentList.add(oneFragment);
//        fragmentList.add(twoFragment);

//        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
    }

    private void setupViewPager() {
        mViewPager.setOffscreenPageLimit(Demo3Config.PAGE_COUNT);
        mViewPager.setAdapter(new IndexPagerAdapter());

        mIndicator.setupWithViewPager(mViewPager);
        mIndicator.setOnItemClickListener(new IndexPagerIndicator.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                mViewPager.setCurrentItem(pos);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPagerChangeAdapter() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
//                    MobEventHelper.onEvent(Demo3Activity.this, "UI2_index_personal_center");//统计
                }
            }
        });

        mViewPager.setCurrentItem(Demo3Config.DEFAULT_PAGE_INDEX);//设置当前显示标签页为第一页
    }

    /**
     * 初始化首页fragments
     */
    private void setupFragments() {
        // 使用HierarchyChangeListener的目的是防止在viewpager的itemview还没有准备好就去inflateFragment
        // 带来的问题
        mViewPager.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                if (((ViewGroup) parent).getChildCount() < PAGE_COUNT) {
                    return;
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SparseArrayCompat<Class<? extends BaseFragment>> array = Demo3Config.getFragments();
                int size = array.size();
                BaseFragment item;
                for (int i = 0; i < size; i++) {
                    item = ComFragmentHelper.newFragment(array.valueAt(i), null);
                    ft.replace(array.keyAt(i), item, item.getClass().getName());
                }
                ft.commitAllowingStateLoss();
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }
        });


    }


    private void addListener() {

    }

    private void findview() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_my);
        mIndicator = (IndexPagerIndicator) findViewById(R.id.indicator);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 首页viewpager adapter
     */
    public class IndexPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            MyLogUtil.d(Demo3Config.PAGE_LAYOUT_ID + position);
            int layoutId = getResources().getIdentifier(Demo3Config.PAGE_LAYOUT_ID + position, "layout", getPackageName());
            if (layoutId == 0) {
                throw new UnsupportedOperationException("layout not found!");
            }
            View itemLayout = LayoutInflater.from(Demo3Activity.this).inflate(layoutId, container, false);
            container.addView(itemLayout);
            return itemLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 定义自己的ViewPager适配器。
     * 也可以使用FragmentPagerAdapter。关于这两者之间的区别，可以自己去搜一下。
     */
    public class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == currenttab) {
                return;
            }
            currenttab = mViewPager.getCurrentItem();
        }
    }

    //手动设置ViewPager要显示的视图
    public void changeView(int desTab) {
        mViewPager.setCurrentItem(desTab, true);
    }

//    public IndexViewPager getViewPager() {
//        return mViewPager;
//    }

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
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).call(value);
            }
        }
    }
}
