package com.example.p006_activity_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.p006_activity_fragment.fragment.base.BaseActivity;
import com.example.p006_activity_fragment.fragment.base.BaseFragment;
import com.example.p006_activity_fragment.fragment.base.BaseIndexFragment;
import com.example.p006_activity_fragment.fragment.index.IndexFragmentFactory;
import com.example.p006_activity_fragment.util.FragmentHelper;
import com.example.p006_activity_fragment.util.ViewHelper;
import com.example.p006_activity_fragment.widget.IndexPagerIndicator;
import com.example.p006_activity_fragment.widget.IndexViewPager;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final int PAGE_COUNT = 2;
    private static final String PAGE_ID = "pager_index_";

    @BindView(R.id.weather)
    TextView mWeatherTextView; // 天气
    @BindView(R.id.info)
    TextView mCarInfoTextView; // 出行

    // top right
    @BindView(R.id.voice)
    ImageView mVoiceImageView; // 语音
    @BindView(R.id.person_center)
    ImageView mPersonCenterImageView; // 个人中心
    @BindView(R.id.message)
    View mMessageImageView; // 消息
    @BindView(R.id.settings)
    ImageView mSettingsImageView; // 设置
    @BindView(R.id.sound)
    ImageView mSoundImageView; // 音量
    @BindView(R.id.wifi)
    ImageView mWifiImageView; // wifi

    @BindView(R.id.main_layout)
    LinearLayout main_layout;
    // viewpager
    @BindView(R.id.pager)
    IndexViewPager mViewPager;
    @BindView(R.id.indicator)
    IndexPagerIndicator mIndicator;
    @BindView(R.id.left_view)
    View left_view;
    @BindView(R.id.right_view)
    View right_view;
    private static final int REQUEST_CODE_FACTORYTEST = 768;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        Log.e("aaa", mVoiceImageView + "--" + mPersonCenterImageView + "--" +
                mMessageImageView + "--" + mSettingsImageView + "--" + mSoundImageView + "--" + mWifiImageView);
        ViewHelper.click(this, mVoiceImageView, mPersonCenterImageView,
                mMessageImageView, mSettingsImageView, mSoundImageView, mWifiImageView);

        mViewPager.setAdapter(new IndexPagerAdapter());
        mIndicator.setupWithViewPager(mViewPager);
        mIndicator.setOnItemClickListener(new IndexPagerIndicator.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                mViewPager.setCurrentItem(pos);
            }
        });
        left_view.setOnClickListener(this);
        right_view.setOnClickListener(this);

        setupFragments();

    }


    /**
     * 初始化首页fragments
     */
    private void setupFragments() {
        mViewPager.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                if (((ViewGroup) parent).getChildCount() < PAGE_COUNT) {
                    return;
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SparseArrayCompat<Class<? extends BaseFragment>> array = IndexFragmentFactory.get();
                int size = array.size();
                BaseFragment item;
                for (int i = 0; i < size; i++) {
                    item = FragmentHelper.newFragment(array.valueAt(i), null);
                    ft.replace(array.keyAt(i), item, item.getClass().getName());
                }
                ft.commitAllowingStateLoss();
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }
        });
    }

    private void setWallpaper() {
//        int index = (int) SpUtils.getInstance().get(ConstantUtil.WALLPAPER_ID, -1);
//        int wallpaperRes = index == -1 ? R.drawable.index_background0 : WallpaperSettingFragment.imageIds[index];
        main_layout.setBackgroundResource(R.drawable.index_background);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setWallpaper();
    }

    /**
     * 首页viewpager adapter
     */
    private class IndexPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            MyLogUtil.d(PAGE_ID + position);
            int layoutId = getResources().getIdentifier(PAGE_ID + position, "layout", getPackageName());
            if (layoutId == 0) {
                throw new UnsupportedOperationException("layout not found!");
            }
            View itemLayout = LayoutInflater.from(MainActivity.this).inflate(layoutId, container, false);
            container.addView(itemLayout);
            return itemLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voice: // 语音
//
                break;
            case R.id.person_center: // 个人中心
//                targetPersonalCenter();
                break;
            case R.id.message: // 消息

                break;
            case R.id.settings: // 设置

                break;
            case R.id.sound: // 音量

                break;
            case R.id.wifi: // wifi

                break;
            case R.id.left_view:

                break;
            case R.id.right_view:

                break;
        }
    }


    /**
     * fragment间通讯
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

    /**
     * 跳转个人中心
     */
//    public void targetPersonalCenter() {
//        startActivity(MineActivity.class);
//    }
    public IndexViewPager getViewPager() {
        return mViewPager;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_FACTORYTEST:

                break;
            default:
                break;
        }
    }
}