package com.shining.p010_recycleviewall.shoucang.lookall;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shining.p010_recycleviewall.shoucang.adapter.ShoucangLookAllAdapter;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseActivity;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodDeletefoodModel;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodItemBean;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodlistModel;
import com.shining.p010_recycleviewall.shoucang.iview.ILookAllDeleteView;
import com.shining.p010_recycleviewall.shoucang.iview.ILookAllView;
import com.shining.p010_recycleviewall.shoucang.presenter.LookAllDeletePresenter;
import com.shining.p010_recycleviewall.shoucang.presenter.LookAllPresenter;
import com.shining.p010_recycleviewall.utils.ToastUtil;
import com.shining.p010_recycleviewall.widget.emptyview.EmptyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shining on 2017/3/1 0001.
 */

public class LookAllActivity extends BaseActivity implements ILookAllView, ILookAllDeleteView, EmptyView.RetryListener, View.OnClickListener {

    @BindView(com.shining.p010_recycleviewall.R.id.home)
    LinearLayout home;
    @BindView(com.shining.p010_recycleviewall.R.id.back)
    LinearLayout back;
    @BindView(com.shining.p010_recycleviewall.R.id.rl_ceng1)
    RelativeLayout rl_ceng1;
    @BindView(com.shining.p010_recycleviewall.R.id.ll_ceng2)
    LinearLayout ll_ceng2;
    @BindView(com.shining.p010_recycleviewall.R.id.tv_bianji)
    TextView tv_edit;
    @BindView(com.shining.p010_recycleviewall.R.id.tv_quanxuan)
    TextView tv_quanxuan;
    @BindView(com.shining.p010_recycleviewall.R.id.tv_shanchu)
    TextView tv_delete;
    @BindView(com.shining.p010_recycleviewall.R.id.tv_quxiao)
    TextView tv_quxiao;
    @BindView(com.shining.p010_recycleviewall.R.id.empty_view)
    EmptyView mEmptyView;
    @BindView(com.shining.p010_recycleviewall.R.id.recycler_view1)
    RecyclerView recycler_view1;

    private LookAllPresenter mLookAllPresenter = new LookAllPresenter();
    private LookAllDeletePresenter mLookAllDeletePresenter = new LookAllDeletePresenter();

    private List<FmNewFoodItemBean> list_content;
    private ShoucangLookAllAdapter mAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLookAllPresenter.onDestory();
        mLookAllDeletePresenter.onDestory();
    }

    @Override
    protected int getLayoutId() {
        return com.shining.p010_recycleviewall.R.layout.activity_shoucang_lookalldemo;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findView();
        onclickListener();
        mLookAllPresenter.onCreate(this);
        mLookAllDeletePresenter.onCreate(this);
        shezhi_ceng(0);
        doNetWork();
    }

    private void doNetWork() {
        mEmptyView.loading();
        getAdapterclear();
        mLookAllPresenter.getLookAllPresenter();
    }

    private void findView() {
        //数据列表bufen
        mAdapter = new ShoucangLookAllAdapter(this);
        recycler_view1.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false));
        recycler_view1.setAdapter(mAdapter);
    }

    private void onclickListener() {
        mEmptyView.bind(recycler_view1).setRetryListener(this);
        if (mEmptyView.getmNodataLayout() != null) {
            TextView tv_notice_caipu = (TextView) mEmptyView.getmNodataLayout().findViewById(com.shining.p010_recycleviewall.R.id.tv_notice_caipu);
            tv_notice_caipu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToastCenter("没有数据的点击事件部分");
                }
            });
        }
        home.setOnClickListener(this);
        back.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
        tv_quanxuan.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_quxiao.setOnClickListener(this);
    }

    @Override
    public void retry() {
        shezhi_ceng(0);
        doNetWork();
    }

    /**
     * 清除content适配器的数据bufen
     */
    private void getAdapterclear() {
        if (list_content == null) {
            list_content = new ArrayList<>();
        }
        int start = 0;
        int size = list_content.size();
        if (size == 0) return;
        list_content.clear();
        mAdapter.notifyItemRangeRemoved(start, size);
    }

    /**
     * 0 全隐藏 1 只显示编辑 2 只显示全选取消删除
     *
     * @param is_vis
     */
    public void shezhi_ceng(int is_vis) {
        if (is_vis == 0) {
            rl_ceng1.setVisibility(View.GONE);
            ll_ceng2.setVisibility(View.GONE);
        } else if (is_vis == 1) {
            rl_ceng1.setVisibility(View.VISIBLE);
            ll_ceng2.setVisibility(View.GONE);
        } else if (is_vis == 2) {
            rl_ceng1.setVisibility(View.GONE);
            ll_ceng2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 适配器设置全选布局bufen 0不勾 1 勾勾
     *
     * @param is_gou
     */
    public void shezhi_quanxuan_gougou(int is_gou) {
        if (is_gou == 0) {
            Drawable drawable = getResources().getDrawable(com.shining.p010_recycleviewall.R.drawable.foodmanager_choose1);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_quanxuan.setCompoundDrawables(drawable, null, null, null);
        } else if (is_gou == 1) {
            Drawable drawable = getResources().getDrawable(com.shining.p010_recycleviewall.R.drawable.foodmanager_choose2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_quanxuan.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置全选的状态tv_drawable_leftbufen
     *
     * @param drawable_r
     */
    private void shezhi_tv_drawable(int drawable_r, boolean is_cho) {
        Drawable drawable = getResources().getDrawable(drawable_r);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_quanxuan.setCompoundDrawables(drawable, null, null, null);
        //收集ID的选择状态设置bufen
        for (int i = 0; i < mAdapter.getMratings().size(); i++) {
            FmNewFoodItemBean ratings = mAdapter.getMratings().get(i);
            if (is_cho) {
                if (!ratings.ischoose()) {
                    ratings.setIschoose(is_cho);
                    mAdapter.getAllListids().add(ratings.getFridge_food_id());
                }
            } else {
                ratings.setIschoose(is_cho);
            }
        }
    }

    /**
     * 设置全选的勾勾bufen
     */
    public void shezhi_quanxuan() {
        if (mAdapter.getAllListids() == null || mAdapter.getAllListids().size() == 0) {
            //设置全选 收集ID的选择状态设置 true bufen
            shezhi_tv_drawable(com.shining.p010_recycleviewall.R.drawable.foodmanager_choose2, true);
        } else {
            //设置非全选 非全选两种情况 1.全选 2.部分全选
            if (mAdapter.getAllListids().size() == mAdapter.getMratings().size()) {
                //删除IDbufen
                mAdapter.setAll_ids();
                //1 设置为非全选 清除收集ID的选择状态 false bufen
                shezhi_tv_drawable(com.shining.p010_recycleviewall.R.drawable.foodmanager_choose1, false);
            } else {
                //2 设置为全选 收集ID的选择状态设置 true bufen
                shezhi_tv_drawable(com.shining.p010_recycleviewall.R.drawable.foodmanager_choose2, true);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置编辑功能
     */
    public void shezhi_bianji() {
        //清除收集的ids
        mAdapter.setAll_ids();
        //显示全选状态的层bufen
        mAdapter.setIs_vis_rl_unuser(true);
        //取消字段选中状态的item的值bufen
        for (FmNewFoodItemBean ratings : mAdapter.getMratings()) {
            ratings.setIschoose(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置取消功能
     */
    public void shezhi_quxiao() {
        //清除收集的ids
        mAdapter.setAll_ids();
        //隐藏全选状态的层bufen
        if (mAdapter.is_vis_rl_unuser()) {
            mAdapter.setIs_vis_rl_unuser(false);
        }
        //取消字段选中状态的item的值bufen
        for (FmNewFoodItemBean ratings : mAdapter.getMratings()) {
            ratings.setIschoose(false);
        }
    }

    /**
     * 设置删除功能
     */
    public void shezhi_shanchu() {
        if (mAdapter.getAllListids() == null || mAdapter.getAllListids().size() == 0) {
            ToastUtil.showToastCenter("请至少选择一种食材");
            return;
        }
        mLookAllDeletePresenter.getLookAllDeletePresenter(mAdapter.getAllString_ids());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.shining.p010_recycleviewall.R.id.home:
                onBackPressed();
                break;
            case com.shining.p010_recycleviewall.R.id.back:
                onBackPressed();
                break;
            case com.shining.p010_recycleviewall.R.id.tv_bianji:
                //编辑
                shezhi_bianji();
                shezhi_ceng(2);
                break;
            case com.shining.p010_recycleviewall.R.id.tv_quanxuan:
                //全选
                shezhi_quanxuan();
                break;
            case com.shining.p010_recycleviewall.R.id.tv_shanchu:
                //删除
                shezhi_shanchu();
                break;
            case com.shining.p010_recycleviewall.R.id.tv_quxiao:
                //取消
                shezhi_quxiao();
                mAdapter.notifyDataSetChanged();
                shezhi_quanxuan_gougou(0);
                shezhi_ceng(1);
                break;
            default:
                break;
        }
    }

    //列表
    @Override
    public void onGetLookAllSuccess(FmNewFoodlistModel data) {
        mEmptyView.success();
        shezhi_ceng(1);
        list_content = new ArrayList<>();
        list_content = data.getFood_list();
        mAdapter.setContacts(list_content);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetLookAllNotok(boolean isok) {
        mEmptyView.errorNet();
        shezhi_ceng(0);
    }

    @Override
    public void onGetLookAllEmpty(String msg) {
        mEmptyView.nodata();
        shezhi_ceng(0);
    }

    @Override
    public void onGetLookAllFailed(String msg) {
        mEmptyView.nodata();
        shezhi_ceng(0);
    }

    //删除
    private String fridge_have_food;

    @Override
    public void onGetLookAllDeleteSuccess(FmNewFoodDeletefoodModel data) {
        fridge_have_food = data.getFridge_have_food();
        if (fridge_have_food.equals("false")) {
            shezhi_ceng(0);
        }
        ToastUtil.showToastCenter("已删除");
        shezhi_quxiao();
        doNetWork();
    }

    @Override
    public void onGetLookAllDeleteNotok(boolean isok) {
        ToastUtil.showToastCenter("删除失败");
    }

    @Override
    public void onGetLookAllDeleteEmpty(String msg) {
        ToastUtil.showToastCenter("删除失败");
    }

    @Override
    public void onGetLookAllDeleteFailed(String msg) {
        ToastUtil.showToastCenter("删除失败");
    }

}
