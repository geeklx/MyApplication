package com.shining.p010_recycleviewall.tablayout.fragmentframelayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shining.p010_recycleviewall.domain.LabFour;
import com.shining.p010_recycleviewall.domain.TabLayoutcontentBean;
import com.shining.p010_recycleviewall.tablayout.fragmentviewpager.RecycleViewAdapter1;
import com.shining.p010_recycleviewall.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created shining 2016年11月24日23:07:28
 */
public class FragmentIndex extends Fragment {

    private String categoryId;
    private Context mContext;
    private View vRoot;
    private RecyclerView recyclerView;
    private RecycleViewAdapter1 GAdaptor;
    private static final int Len = 12;
    private boolean allLoad = false;
    private int which_page;
    private int mLastItemVisible;
    private List<TabLayoutcontentBean> contentlist;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            categoryId = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (vRoot == null) {
            vRoot = LayoutInflater.from(mContext).inflate(com.shining.p010_recycleviewall.R.layout.fragment_index, container, false);
        }
        recyclerView = (RecyclerView) vRoot.findViewById(com.shining.p010_recycleviewall.R.id.rv_album);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4, LinearLayoutManager.HORIZONTAL, false));
        GAdaptor = new RecycleViewAdapter1(mContext);
        recyclerView.setAdapter(GAdaptor);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                super.onScrollStateChanged(view, scrollState);
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastItemVisible + 1 == GAdaptor.getItemCount()) {
                    if (!allLoad) {
                        //setListFooterLoading();
                        which_page++;
                        doNetWorkContent(which_page);
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
//        initData();
        return vRoot;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
        which_page = 1;
        //categoryId
        doNetWorkContent(which_page);
    }

    private Handler mHandler = new Handler();

    private void doNetWorkContent(final int which_pages) {
        ToastUtil.showToastCenter("首页没有传ID");
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

}
