package com.example.p010_recycleviewall.tablayout.fragmentviewpager;

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

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.domain.LabFour;
import com.example.p010_recycleviewall.domain.TabLayoutcontentBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created shining 2016年11月24日23:07:28
 */
public class FragmentA extends Fragment {

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
            vRoot = LayoutInflater.from(mContext).inflate(R.layout.fragment_a, container, false);
        }
        recyclerView = (RecyclerView) vRoot.findViewById(R.id.rv_album);
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
        which_page = 1;
        doNetWorkContent(which_page);
        return vRoot;
    }

    private Handler mHandler = new Handler();

    private void doNetWorkContent(final int which_pages) {
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
