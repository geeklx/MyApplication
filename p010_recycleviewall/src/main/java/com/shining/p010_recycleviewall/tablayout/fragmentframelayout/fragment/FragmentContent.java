package com.shining.p010_recycleviewall.tablayout.fragmentframelayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shining.p010_recycleviewall.domain.LabFour;
import com.shining.p010_recycleviewall.domain.TabLayoutcontentBean;
import com.shining.p010_recycleviewall.domain.TabLayouttitleBean;
import com.shining.p010_recycleviewall.tablayout.fragmentframelayout.adapter.TabSelectAdapter;
import com.shining.p010_recycleviewall.tablayout.fragmentviewpager.RecycleViewAdapter1;
import com.shining.p010_recycleviewall.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created shining 2016年11月24日23:07:28
 */
public class FragmentContent extends Fragment {

    private TabLayout mTabLayout;
    private String mCurrentCateId;//头部item
    private String categoryId;//底部item
    private boolean isTou_onclick = false;
    private Context mContext;
    private View vRoot;
    private RecyclerView recyclerView;
    private RecycleViewAdapter1 GAdaptor;
    private static final int Len = 12;
    private boolean allLoad = false;
    private int which_page;
    private int mLastItemVisible;
    private List<TabLayoutcontentBean> contentlist;
    private List<TabLayouttitleBean> titlelist;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            categoryId = getArguments().getString("cate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (vRoot == null) {
            vRoot = LayoutInflater.from(mContext).inflate(com.shining.p010_recycleviewall.R.layout.fragment_content, container, false);
        }
        findview();
        onclicklistener();
        initData();
        return vRoot;
    }

    /**
     * 第一次进来加载bufen
     */
    private void initData() {
        doNetWork_toubu();
        which_page = 1;
        //categoryId
        doNetWorkContent2(which_page);
    }

    /**
     * 加载头部bufen
     */
    private void doNetWork_toubu() {
        //假数据bufen
        titlelist = new ArrayList<TabLayouttitleBean>();
        LabFour lf = new LabFour();
        titlelist = lf.getImpressions();
        //加载数据bufen
        ToubuShuaxin(titlelist);
    }

    private void ToubuShuaxin(List<TabLayouttitleBean> titlelist) {
        mTabLayout.removeAllTabs();
        for (TabLayouttitleBean item : titlelist) {
            mTabLayout.addTab(mTabLayout.newTab()
                    .setTag(item.getCategoryId())
                    .setText(item.getName()));
        }
    }

    private void onclicklistener() {
        //头部tablayout
        mTabLayout.addOnTabSelectedListener(new TabSelectAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tag = (String) tab.getTag();
                if (TextUtils.isEmpty(tag)) {
                    return;
                }
                //TODO geek
                if (!(tag.equals(mCurrentCateId))) {
                    mCurrentCateId = tag;
                    GAdaptor = new RecycleViewAdapter1(mContext);
                    recyclerView.setAdapter(GAdaptor);
                    which_page = 1;
                    doNetWorkContent1(which_page);
                    isTou_onclick = true;
                }
            }
        });
        //中间bufen
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                super.onScrollStateChanged(view, scrollState);
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastItemVisible + 1 == GAdaptor.getItemCount()) {
                    if (!allLoad) {
                        //setListFooterLoading();
                        which_page++;
                        if (isTou_onclick) {
                            doNetWorkContent1(which_page);
                        } else {
                            doNetWorkContent2(which_page);
                        }
                    } else {
                        // ToastUtil.showToastShort(getResources().getString(R.string.last_item_show));
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView view, int dx, int dy) {
                super.onScrolled(view, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                mLastItemVisible = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
        });
    }

    private void findview() {
        mTabLayout = (TabLayout) vRoot.findViewById(com.shining.p010_recycleviewall.R.id.tl_shop_top);
        recyclerView = (RecyclerView) vRoot.findViewById(com.shining.p010_recycleviewall.R.id.rv_album);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4, LinearLayoutManager.HORIZONTAL, false));
        GAdaptor = new RecycleViewAdapter1(mContext);
        recyclerView.setAdapter(GAdaptor);
    }

    /**
     * 底部点击bufen
     *
     * @param cateId
     */
    public void getCate(String cateId) {
        doNetWork_toubu();
        GAdaptor = new RecycleViewAdapter1(mContext);
        recyclerView.setAdapter(GAdaptor);
        which_page = 1;
        doNetWorkContent2(which_page);
        isTou_onclick = false;
    }

    private Handler mHandler = new Handler();

    /**
     * 底部 获取中间bufen
     *
     * @param which_pages
     */
    private void doNetWorkContent2(final int which_pages) {
//        ToastUtil.showToastCenter(categoryId);
        if (which_pages == 1) {
            //第一页
            contentlist = new ArrayList<TabLayoutcontentBean>();
            LabFour lf = new LabFour();
            contentlist = lf.getmParent_model();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    GAdaptor.setContacts(contentlist);
                    GAdaptor.notifyDataSetChanged();
                }
            });
            if (contentlist.size() < Len) {
                allLoad = true;
            } else {
                allLoad = false;
            }
        } else {
            //第二页
            List<TabLayoutcontentBean> contentlists = new ArrayList<TabLayoutcontentBean>();
            LabFour lf = new LabFour();
            contentlists = lf.getmParent_model();
            GAdaptor.addConstacts(contentlists);
            GAdaptor.notifyDataSetChanged();
            if (contentlists.size() < Len) {
                allLoad = true;
            } else {
                allLoad = false;
            }
        }
    }

    /**
     * 头部 获取中间bufen
     *
     * @param which_pages
     */
    private void doNetWorkContent1(final int which_pages) {
        ToastUtil.showToastCenter(mCurrentCateId);
        if (which_pages == 1) {
            //第一页
            contentlist = new ArrayList<TabLayoutcontentBean>();
            LabFour lf = new LabFour();
            contentlist = lf.getmParent_model();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    GAdaptor.setContacts(contentlist);
                    GAdaptor.notifyDataSetChanged();
                }
            });
            if (contentlist.size() < Len) {
                allLoad = true;
            } else {
                allLoad = false;
            }
        } else {
            //第二页
            List<TabLayoutcontentBean> contentlists = new ArrayList<TabLayoutcontentBean>();
            LabFour lf = new LabFour();
            contentlists = lf.getmParent_model();
            GAdaptor.addConstacts(contentlists);
            GAdaptor.notifyDataSetChanged();
            if (contentlists.size() < Len) {
                allLoad = true;
            } else {
                allLoad = false;
            }
        }
    }


    @Override
    public void onDestroy() {
//        mPresenter.onDestory();
        super.onDestroy();
    }
}
