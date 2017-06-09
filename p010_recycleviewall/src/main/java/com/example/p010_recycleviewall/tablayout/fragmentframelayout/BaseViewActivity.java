package com.example.p010_recycleviewall.tablayout.fragmentframelayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.utils.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseViewActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.home)
    public View mHomeView;
    @BindView(R.id.back)
    public View mBackView;
    @BindView(R.id.tab)
    public TabLayout mCateTabLayout;
    @BindView(R.id.cart)
    public View mCartView;
    @BindView(R.id.fav)
    public View mFavView;
    @BindView(R.id.order)
    public View mOrderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ViewHelper.click(this, mHomeView, mBackView, mCartView, mFavView, mOrderView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home: {
                click_home();
                break;
            }
            case R.id.back: {
                click_back();
                break;
            }
            case R.id.cart: { // 购物车
                click_cart();
                break;
            }
            case R.id.fav: { // 收藏夹
                click_fav();
                break;
            }
            case R.id.order: { // 订单
                click_order();
                break;
            }
            default:
                break;
        }
    }

    protected abstract void click_order();

    protected abstract void click_fav();

    protected abstract void click_cart();

    protected abstract void click_back();

    protected abstract void click_home();

}
