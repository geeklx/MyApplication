package com.example.p031_mokuaihua_viewpager_fragment.demo5;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.demo5.domain.ShopCategoryItem;
import com.example.p031_mokuaihua_viewpager_fragment.demo5.fragment.FragmentContent;
import com.example.p031_mokuaihua_viewpager_fragment.demo5.fragment.FragmentIndex;
import com.example.p031_mokuaihua_viewpager_fragment.demo5.tablayoutcommon.TabSelectAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Demo5Activity extends AppCompatActivity {

    private static final String INDEX_TAG = "index";
    private static final String LIST_TAG = "list";
    public TabLayout mCateTabLayout;
    private List<ShopCategoryItem> mDataTablayout;

    private FragmentIndex mShopIndexFragment; // 商城首页
    private FragmentContent mShopGoodsListFragment; // 商城商品列表

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5);
        findview();
        onclick();

        mFragmentManager = getSupportFragmentManager();
        donetwork();
    }

    private void donetwork() {
        data_tablayout();

//        showFragment(ShopCategoryItem.DEF_TAG_ID);
    }

    private void onclick() {
        mCateTabLayout.addOnTabSelectedListener(new TabSelectAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tag = (String) tab.getTag();
                if (TextUtils.isEmpty(tag)) {
                    return;
                }
                showFragment(tag);
//                hookForNoAnimator();
            }
        });

    }

    private void findview() {
        mCateTabLayout = (TabLayout) findViewById(R.id.tab);

    }

    private void showFragment(String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);

        if (tag.equalsIgnoreCase(ShopCategoryItem.DEF_TAG_ID)) { // 推荐fragment
            if (mShopIndexFragment == null) {
                mShopIndexFragment = new FragmentIndex();
                transaction.add(R.id.container, mShopIndexFragment, INDEX_TAG);
            } else {
                transaction.show(mShopIndexFragment);
                mShopIndexFragment.initData();
            }
        } else {
            if (mShopGoodsListFragment == null) {
                mShopGoodsListFragment = new FragmentContent();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mShopGoodsListFragment.setArguments(args);
                transaction.add(R.id.container, mShopGoodsListFragment, LIST_TAG);
            } else {
                transaction.show(mShopGoodsListFragment);
                mShopGoodsListFragment.getCate(tag);
            }
        }

        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mShopIndexFragment != null) {
            transaction.hide(mShopIndexFragment);
        }
        if (mShopGoodsListFragment != null) {
            transaction.hide(mShopGoodsListFragment);
        }
    }

    /**
     * hook掉当前选中tab， 从而达到禁用滑动动画效果， 解决动画卡断问题
     */
    private void hookForNoAnimator() {
        try {
            Field f = mCateTabLayout.getClass().getDeclaredField("mScrollAnimator");
            f.setAccessible(true);
            f.set(mCateTabLayout, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void data_tablayout() {
        mDataTablayout = new ArrayList<>();
        mDataTablayout.add(new ShopCategoryItem("1", "shining01"));
        mDataTablayout.add(new ShopCategoryItem("2", "shining02"));
        mDataTablayout.add(new ShopCategoryItem("3", "shining03"));
        mDataTablayout.add(new ShopCategoryItem("4", "shining04"));
        mDataTablayout.add(new ShopCategoryItem("5", "shining05"));
        mDataTablayout.add(new ShopCategoryItem("6", "shining06"));

        mDataTablayout.add(0, buildFixedCate());

        for (ShopCategoryItem item : mDataTablayout) {
            mCateTabLayout.addTab(mCateTabLayout.newTab()
                    .setTag(item.getCategory2_id()).setText(item.getCategory2_name()));
        }
    }

    /**
     * 创建固定的类目（本地创建“推荐”）
     *
     * @return
     */
    private ShopCategoryItem buildFixedCate() {
        ShopCategoryItem item = new ShopCategoryItem();
        item.setCategory2_name(ShopCategoryItem.DEF_TAG_NAME);
        item.setCategory2_id(ShopCategoryItem.DEF_TAG_ID);
        return item;
    }

    @Override
    protected void onDestroy() {
//        mPresenter.onDestory();
        super.onDestroy();
    }
}