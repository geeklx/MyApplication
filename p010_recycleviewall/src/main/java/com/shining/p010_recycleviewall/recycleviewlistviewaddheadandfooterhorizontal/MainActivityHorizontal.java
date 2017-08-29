package com.shining.p010_recycleviewall.recycleviewlistviewaddheadandfooterhorizontal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shining.p010_recycleviewall.R;
import com.shining.p010_recycleviewall.domain.Scroll_listBean;
import com.shining.p010_recycleviewall.recycleviewcommon.HeaderAndFooterWrapper;
import com.shining.p010_recycleviewall.utils.MyLogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.shining.p010_recycleviewall.R.id.recycler_view1;

public class MainActivityHorizontal extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_left;
    private TextView tv_right;
    private TextView tv_content1;
    private RecyclerView recyclerView1;
    private RecycleAdapterHorizontal mAdapter1;
    private List<Scroll_listBean> mList1;
    private LinearLayout ll_header1;
    private LinearLayout ll_footer1;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper1;

    private boolean move = false;
    private int mIndex = 0;
    private int firstvisiableposition;
    private int lastvisiableposition;
    private int currentProduct = 1;
    private String mDefinitionId;
    private String mName;
    public static final String addId = "-1";//是否显示加号bufen


    private void addAdapter() {
        mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
        mHeaderAndFooterWrapper1.addHeaderView(ll_header1);
        mHeaderAndFooterWrapper1.addFootView(ll_footer1);
        recyclerView1.setAdapter(mHeaderAndFooterWrapper1);
    }

    private void createAdapter() {
        ll_header1 = (LinearLayout) View.inflate(this, R.layout.activity_addheader_horizontal, null);
        ll_footer1 = (LinearLayout) View.inflate(this, R.layout.activity_addfooter_horizontal, null);
        mAdapter1 = new RecycleAdapterHorizontal(this);
        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
        mLinearLayoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setLayoutManager(mLinearLayoutManager1);
//        recyclerView.setAdapter(mAdapter);
        addAdapter();

        LinearSnapHelper snapHelperCenter = new LinearSnapHelper();
//        GravitySnapHelper snapHelperCenter = new GravitySnapHelper(Gravity.TOP, false, this);
        snapHelperCenter.attachToRecyclerView(recyclerView1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclelistview_horizontal);
        findView();
        addlisteners();
        DataFirst();
    }

    private void DataFirst() {
        Data2();
        mAdapter1.setContacts(mList1);
        mAdapter1.notifyDataSetChanged();
        recyclerView1.smoothScrollToPosition(3);

        //传值fragmentbufen
        int currentPos = currentProduct > mList1.size() - 1 ? 0 : currentProduct;
        //TODO new videoid
        //判断是否为添加更多 设置为前一个图片 保持数据不刷新bufen
        if (mList1.get(currentPos).getId() == addId) {
//            SendToIndexCookBookFragment2(mList2.get(currentPos - 1).getId(), mList2.get(currentPos - 1).getText_content());
        } else {
//            SendToIndexCookBookFragment2(mList2.get(currentPos).getId(), mList2.get(currentPos).getText_content());
        }
    }


    private void findView() {
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_content1 = (TextView) findViewById(R.id.tv_content1);
        recyclerView1 = (RecyclerView) findViewById(recycler_view1);
        createAdapter();

    }

    private void addlisteners() {
        //左
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentProduct == 0) {

                    return;
                }
                smoothMoveToPosition(currentProduct - 1, recyclerView1);
//                recyclerView1.smoothScrollToPosition(currentProduct - 1);
            }
        });
        //右
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentProduct == mAdapter1.getItemCount() - 1) {

                    return;
                }
                smoothMoveToPosition(currentProduct + 1, recyclerView1);
//                recyclerView1.smoothScrollToPosition(currentProduct + 1);
            }
        });
        //小图点击bufen
        mAdapter1.setOnItemiv1ClickLitener(new RecycleAdapterHorizontal.OnItemiv1ClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Scroll_listBean listItem = (Scroll_listBean) mAdapter1.getItem(position);
                //请求服务器部分
                if (listItem != null) {
                    //滚动到中间位置bufen
                    smoothMoveToPosition(position, recyclerView1);
                }
            }
        });
        //大图点击bufen
        mAdapter1.setOnItemiv2ClickLitener(new RecycleAdapterHorizontal.OnItemiv2ClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Scroll_listBean listItem = (Scroll_listBean) mAdapter1.getItem(position);
                //请求服务器部分

            }
        });

        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                super.onScrollStateChanged(recyclerView, scrollState);
                if (move && scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    move = false;
                    int n = mIndex - ((LinearLayoutManager) recyclerView1.getLayoutManager()).findFirstVisibleItemPosition();
                    if (0 <= n && n < recyclerView1.getChildCount()) {
                        int top = recyclerView1.getChildAt(n).getTop();
                        recyclerView1.smoothScrollBy(0, top);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstvisiableposition = ((LinearLayoutManager) recyclerView1.getLayoutManager()).findFirstVisibleItemPosition();
                lastvisiableposition = ((LinearLayoutManager) recyclerView1.getLayoutManager()).findLastVisibleItemPosition();
                //由于返回的是一个view，需要调用findViewById方法找到子类的view
                MyLogUtil.e("&&&&&", dx + " " + dy);
                if (mList1 == null || mList1.size() <= 0) {
                    return;
                }
                View v = recyclerView.findChildViewUnder(550, 100);//0-500
                if (v == null) {
                    return;
                }
                TextView tv_position = (TextView) v.findViewById(R.id.tv_position);
                if (tv_position == null) {
                    return;
                }
                int pos = Integer.valueOf(tv_position.getText().toString());
                String tempId = ((Scroll_listBean) mAdapter1.getItem(pos)).getId();
                //防止滚动时改变数据源崩溃bufen
                int currentCount = resetDispatchScrollCounter(0, recyclerView1);// 返回的是old  状态修改为0
                currentProduct = pos;
                mAdapter1.setSelectIndex(pos);
                mAdapter1.notifyItemChanged(pos);
                mHeaderAndFooterWrapper1.notifyDataSetChanged();
                resetDispatchScrollCounter(currentCount, recyclerView1);// 返回的是0 状态修改为old
                //防止滚动时改变数据源崩溃bufen
                if (tempId.equals(addId)) {
                    return;
                }
                if (!(tempId.equals(mDefinitionId))) {
                    mDefinitionId = tempId;
                    mName = ((Scroll_listBean)
                            mAdapter1.getItem(pos)).getText_content();
                    tv_content1.setText(mName);
//                    requestnew();
                }
            }
        });


    }

    /**
     * 防止滚动过程改变数据源反射改变DispatchScrollCounter 0 到 >0
     *
     * @param counter
     * @return
     */
    private int resetDispatchScrollCounter(int counter, RecyclerView recycler_view) {
        int currentCounter = 0;
        Class<?> kclass = recycler_view.getClass();
        try {
            Field mDispatchScrollCounter = kclass.getDeclaredField("DispatchScrollCounter");
            mDispatchScrollCounter.setAccessible(true);
            currentCounter = mDispatchScrollCounter.getInt(recycler_view);// ？
            mDispatchScrollCounter.set(recycler_view, counter);// 0
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return currentCounter;// ?
    }

    private void smoothMoveToPosition(int n, RecyclerView recycler_view) {
        if (n < 0 || n >= mAdapter1.getItemCount()) {
            return;
        }
        mIndex = n;
        int firstItem = ((LinearLayoutManager) recycler_view.getLayoutManager()).findFirstVisibleItemPosition();
        int lastItem = ((LinearLayoutManager) recycler_view.getLayoutManager()).findLastVisibleItemPosition();
        if (n <= firstItem) {
            recycler_view.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            int right = recycler_view.getChildAt(n - firstItem).getRight();
            recycler_view.smoothScrollBy(right / 2, 0);
        } else {
            recycler_view.smoothScrollToPosition(n);
            move = true;
        }
    }

    private void Data2() {
        mList1 = new ArrayList<Scroll_listBean>();
        mList1.add(new Scroll_listBean("1", "小姐姐1", "http://imgsrc.baidu.com/forum/pic/item/23b6bdd3fd1f4134fa2794492f1f95cad1c85e19.jpg"));
        mList1.add(new Scroll_listBean("2", "小姐姐2", "http://imgsrc.baidu.com/forum/pic/item/78b75434970a304e4a3231cddbc8a786c9175c19.jpg"));
        mList1.add(new Scroll_listBean("3", "小姐姐3", "http://imgsrc.baidu.com/forum/pic/item/b5e6301f95cad1c8d5ab6d10753e6709c93d5119.jpg"));
        mList1.add(new Scroll_listBean("4", "小姐姐4", "http://imgsrc.baidu.com/forum/pic/item/9e7db86eddc451da0e619fdcbcfd5266d116329e.jpg"));
        mList1.add(new Scroll_listBean("5", "小姐姐5", "http://imgsrc.baidu.com/forum/pic/item/28e3c41373f08202fc35020841fbfbedaa641b8e.jpg"));
        mList1.add(new Scroll_listBean("6", "小姐姐6", "http://imgsrc.baidu.com/forum/pic/item/f7a619f3d7ca7bcbf3dc5418b4096b63f724a88f.jpg"));
        mList1.add(new Scroll_listBean("7", "小姐姐7", "http://imgsrc.baidu.com/forum/pic/item/33554766d01609243f0f302cde0735fae6cd3403.jpg"));
        mList1.add(new Scroll_listBean("8", "小姐姐8", "http://imgsrc.baidu.com/forum/pic/item/8b5c0ed5ad6eddc4dcdc26cc33dbb6fd52663372.jpg"));
        mList1.add(new Scroll_listBean("9", "小姐姐9", "http://imgsrc.baidu.com/forum/pic/item/895bc2ca7bcb0a460b19190e6163f6246b60af73.jpg"));
    }

    //页面点击bufen
    @Override
    public void onClick(View v) {

    }

}
