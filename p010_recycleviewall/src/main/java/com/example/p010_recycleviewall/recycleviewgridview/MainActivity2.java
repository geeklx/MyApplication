package com.example.p010_recycleviewall.recycleviewgridview;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.api.FoodManagerApi;
import com.example.p010_recycleviewall.domain.FoodmanagerrightgetParams;
import com.example.p010_recycleviewall.domain.LabOne;
import com.example.p010_recycleviewall.domain.LabThree;
import com.example.p010_recycleviewall.domain.LabTwo;
import com.example.p010_recycleviewall.domain.PackageOneKeyBuyBeanNew;
import com.example.p010_recycleviewall.domain.PackageOneKeyBuyBeanNewList;
import com.example.p010_recycleviewall.net.Net;
import com.example.p010_recycleviewall.utils.ParamsUtils;
import com.example.p010_recycleviewall.utils.ShowLoadingUtil;

import org.loader.glin.Callback;
import org.loader.glin.Result;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements
        View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    //刷新bufen
    private SwipeRefreshLayout mSwipeLayout;
    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter2 mAdapter;
    private List<PackageOneKeyBuyBeanNew> mratings;
    //分页bufen
    private static final int Len = 5;
    private boolean allLoad = false;
    private int which_page = 1;
    private int mLastItemVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclegridview);
        findView();
        addlisteners();
        DataFirst();
    }

    private void DataFirst() {
        ShowLoadingUtil.showProgressDialog2(MainActivity2.this, "正在加载...");
        doNetWork(which_page);
    }

    /**
     * 请求数据
     * 这里是用新的数据源填充，真正使用的时候就不用写这步了~
     */
    private void doNetWorkRefresh(final int which_pages) {
        FoodmanagerrightgetParams p = new FoodmanagerrightgetParams(
                "", "", which_pages, Len, "");
        Net.build(FoodManagerApi.class, getClass().getName()).food_category_query(ParamsUtils.just(p)).enqueue(new Callback<PackageOneKeyBuyBeanNewList>() {
            @Override
            public void onResponse(Result<PackageOneKeyBuyBeanNewList> result) {

                if (which_pages == 1) {
                    //第一页 由于这里请求瞎写的，不会返回成功，我们反向判断就行了 !result.isOK()
                    if (!result.isOK()) {
                        mratings = new ArrayList<PackageOneKeyBuyBeanNew>();
                        //假数据bufen
                        LabThree lt = new LabThree();
                        mratings =lt.getmParent_model1();
                        Toast.makeText(MainActivity2.this, lt.getmParent_model1().size()+"",Toast.LENGTH_LONG).show();
                        if (mratings != null && mratings.size() > 0) {
                            //布局bufen
//                            right1();
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.setContacts(mratings);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                            if (mratings.size() < Len) {
                                allLoad = true;
//                            hideListFooter();
                            } else {
                                allLoad = false;
                            }
                        } else {
                            //布局bufen
//                            right2();
                        }

                    } else {
//                    ToastUtil.showToastLong(result.getMessage());
                        //布局bufen
//                        right3();
                    }
                    mSwipeLayout.setRefreshing(false);
                    //这里给大家封装了一个loading的自定义控件 直接独立出来就能用 ，注意导包和style，xml就行了~
                    ShowLoadingUtil.dismissProgressDialog2();
                }
            }
        });
    }

    /**
     * 请求数据
     * 这里大家使用自己的请求数据方式来判断第一页和第二页就行了，我这里找的是loader的请求方式，形式仅供参考~
     */
    private void doNetWork(final int which_pages) {
        FoodmanagerrightgetParams p = new FoodmanagerrightgetParams(
                "", "", which_pages, Len, "");
        Net.build(FoodManagerApi.class, getClass().getName()).food_category_query(ParamsUtils.just(p)).enqueue(new Callback<PackageOneKeyBuyBeanNewList>() {
            @Override
            public void onResponse(Result<PackageOneKeyBuyBeanNewList> result) {

                if (which_pages == 1) {
                    //第一页 由于这里请求瞎写的，不会返回成功，我们反向判断就行了 !result.isOK()
                    if (!result.isOK()) {
                        mratings = new ArrayList<PackageOneKeyBuyBeanNew>();
                        //假数据bufen
//                        mratings = LabOne.getmInstance().getmParent_model1();
                        LabOne lo = new LabOne();
                        mratings = lo.getmParent_model1();
//                        Toast.makeText(MainActivity2.this, LabOne.getmInstance().
//                                getmParent_model1().size()+"",Toast.LENGTH_LONG).show();
                        if (mratings != null && mratings.size() > 0) {
                            //布局bufen
//                            right1();
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    mAdapter.setContacts(mratings);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                            if (mratings.size() < Len) {
                                allLoad = true;
//                            hideListFooter();
                            } else {
                                allLoad = false;
                            }
                        } else {
                            //布局bufen
//                            right2();
                        }

                    } else {
//                    ToastUtil.showToastLong(result.getMessage());
                        //布局bufen
//                        right3();
                    }
                    mSwipeLayout.setRefreshing(false);
                    //这里给大家封装了一个loading的自定义控件 直接独立出来就能用 ，注意导包和style，xml就行了~
                    ShowLoadingUtil.dismissProgressDialog2();
                } else {
                    //第二页
                    if (!result.isOK()) {
                        Toast.makeText(MainActivity2.this,"进来了",Toast.LENGTH_LONG).show();
                        List<PackageOneKeyBuyBeanNew> list = new ArrayList<PackageOneKeyBuyBeanNew>();
                        //假数据bufen
//                        list = LabTwo.getmInstance().getmParent_model2();
                        LabTwo lt = new LabTwo();
                        list = lt.getmParent_model2();
                        if (list != null && list.size() > 0) {
                            mAdapter.addConstacts(list);
                            mAdapter.notifyDataSetChanged();
                            if (list.size() < Len) {
                                allLoad = true;
//                            hideListFooter();
                            } else {
                                allLoad = false;
                            }
                        } else {
//                            resetListFooter();
                        }
                    } else {
//                    ToastUtil.showToastLong(result.getMessage());
                    }
                    ShowLoadingUtil.dismissProgressDialog2();
                }
            }
        });
    }

    private void createAdapter(){
        mAdapter = new RecycleAdapter2(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mAdapter);
    }

    private void findView() {
        refresh();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        createAdapter();
        which_page = 1;

    }


    private void addlisteners() {
        //按分类
        mAdapter.setOnItemClickLitener(new RecycleAdapter2.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PackageOneKeyBuyBeanNew listItem = (PackageOneKeyBuyBeanNew)
                        mAdapter.getItem(position);
                //请求服务器部分
                Toast.makeText(MainActivity2.this, listItem.getGoods_id(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                super.onScrollStateChanged(view, scrollState);
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastItemVisible + 1 == mAdapter.getItemCount()) {
                    if (!allLoad) {
//                        setListFooterLoading();
                        which_page++;
                        //请求数据
                        doNetWork(which_page);
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
        //listview+gridview写法
//        gridViewRight.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
//                        && mLastItemVisible) {
//                    if (!allLoad) {
////                        setListFooterLoading();
//                        which_page++;
//                        doNetWorkRight(which_page);
//                    } else {
//                        // ToastUtil.showToastShort(getResources().getString(R.string.last_item_show));
//                    }
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//                mLastItemVisible = (totalItemCount > 0)
//                        && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
//            }
//        });
    }

    //刷新bufen
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(MainActivity2.this, "刷新了请求数据", Toast.LENGTH_LONG).show();
                allLoad = false;
                which_page = 1;
                doNetWorkRefresh(which_page);
                // mSwipeLayout.setRefreshing(false);
            }
        }, 3000);
    }

    //页面点击bufen
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recycler_view1:
                //点击bufen

                break;
            default:
                break;
        }
    }

    private void refresh() {
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
//        mSwipeLayout.setColorScheme(getResources().getColor(android.R.color.holo_blue_bright),
//                getResources().getColor(android.R.color.holo_green_light),
//                getResources().getColor(android.R.color.holo_orange_light),
//                getResources().getColor(android.R.color.holo_red_light));
        ViewTreeObserver vto = mSwipeLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onGlobalLayout() {
                final DisplayMetrics metrics = getResources()
                        .getDisplayMetrics();
                Float mDistanceToTriggerSync = Math.min(
                        ((View) mSwipeLayout.getParent()).getHeight() * 0.6f,
                        150 * metrics.density);
                try {
                    Field field = SwipeRefreshLayout.class
                            .getDeclaredField("mDistanceToTriggerSync");
                    field.setAccessible(true);
                    field.setFloat(mSwipeLayout, mDistanceToTriggerSync);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ViewTreeObserver obs = mSwipeLayout.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
    }
}
