package com.example.p010_recycleviewall.tablayout.fragmentframelayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.utils.ToastUtil;
import com.example.p010_recycleviewall.utils.ViewHelper;

import butterknife.BindView;

public abstract class BaseShopRecyclerActivity<T> extends AppCompatActivity
        implements View.OnClickListener {

    @BindView(R.id.home)
    View mHomeView;

    @BindView(R.id.back)
    View mBackView;

    @BindView(R.id.tab)
    TabLayout mCateTabLayout;

    @BindView(R.id.cart)
    View mCartView;

    @BindView(R.id.fav)
    View mFavView;

    @BindView(R.id.order)
    View mOrderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewHelper.click(this, mHomeView, mBackView, mCartView, mFavView, mOrderView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home: {
                ToastUtil.showToastCenter("home");
                break;
            }
            case R.id.back: {
                onBackPressed();
                break;
            }
            case R.id.cart: { // 购物车
                ToastUtil.showToastCenter("购物车");
                break;
            }
            case R.id.fav: { // 收藏夹
                ToastUtil.showToastCenter("收藏夹");
                break;
            }
            case R.id.order: { // 订单
                ToastUtil.showToastCenter("订单");
                break;
            }
            default:
                break;
        }
    }
}
