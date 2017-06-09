package com.example.p010_recycleviewall.tablayout.fragmentframelayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.domain.ShopCategoryItem;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.adapter.TabSelectAdapter;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.fragment.FragmentContent;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.fragment.FragmentIndex;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.presenter.ShopCategoryPresenter;
import com.example.p010_recycleviewall.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.List;

public class ShopIndexActivity extends BaseViewActivity implements IShopCategoryView {

    private static final String INDEX_TAG = "index";
    private static final String LIST_TAG = "list";

    private ShopCategoryPresenter mPresenter;

    private FragmentIndex mShopIndexFragment; // 商城首页
    private FragmentContent mShopGoodsListFragment; // 商城商品列表

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tablayout_fragmentframelayout);
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

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

        //底部分类bufen
        mPresenter = new ShopCategoryPresenter();
        mPresenter.onCreate(this);
        mPresenter.getCategories();
        //fragment切换bufen
        showFragment(ShopCategoryItem.DEF_TAG_ID);
    }

    @Override
    protected void click_order() {
        ToastUtil.showToastCenter("订单");
    }

    @Override
    protected void click_fav() {
        ToastUtil.showToastCenter("收藏夹");
    }

    @Override
    protected void click_cart() {
        ToastUtil.showToastCenter("购物车");
    }

    @Override
    protected void click_back() {
        onBackPressed();
    }

    @Override
    protected void click_home() {
        ToastUtil.showToastCenter("主页");
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
                args.putString("cate", tag);
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

    @Override
    public void onGetShopCategory(List<ShopCategoryItem> categories) {
        for (ShopCategoryItem item : categories) {
            mCateTabLayout.addTab(mCateTabLayout.newTab()
                    .setTag(item.getCategory2_id()).setText(item.getCategory2_name()));
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }
}
