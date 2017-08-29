package com.shining.p010_recycleviewall.recycleviewlistviewaddheadandfootervertical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivityVertical extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_content2;
    private RecyclerView recyclerView2;
    private RecycleAdapterVertical mAdapter2;
    private List<Scroll_listBean> mList2;
    private LinearLayout ll_header2;
    private LinearLayout ll_footer2;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper2;

    private boolean move = false;
    private int mIndex = 0;
    private int firstvisiableposition;
    private int lastvisiableposition;
    private int currentProduct = 1;
    private String mDefinitionId;
    private String mName;
    public static final String addId = "-1";//是否显示加号bufen


    private void addAdapter() {
        mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
        mHeaderAndFooterWrapper2.addHeaderView(ll_header2);
        mHeaderAndFooterWrapper2.addFootView(ll_footer2);
        recyclerView2.setAdapter(mHeaderAndFooterWrapper2);
    }

    private void createAdapter() {
        ll_header2 = (LinearLayout) View.inflate(this, R.layout.activity_addheader_vertical, null);
        ll_footer2 = (LinearLayout) View.inflate(this, R.layout.activity_addfooter_vertical, null);
        mAdapter2 = new RecycleAdapterVertical(this);
        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
        mLinearLayoutManager1.setOrientation(OrientationHelper.VERTICAL);
        recyclerView2.setLayoutManager(mLinearLayoutManager1);
//        recyclerView.setAdapter(mAdapter);
        addAdapter();

        LinearSnapHelper snapHelperCenter = new LinearSnapHelper();
//        GravitySnapHelper snapHelperCenter = new GravitySnapHelper(Gravity.TOP, false, this);
        snapHelperCenter.attachToRecyclerView(recyclerView2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclelistview_vertical);
        findView();
        addlisteners();
        DataFirst();
    }

    private void DataFirst() {
        Data2();
        mAdapter2.setContacts(mList2);
        mAdapter2.notifyDataSetChanged();
        recyclerView2.smoothScrollToPosition(3);

        //传值fragmentbufen
        int currentPos = currentProduct > mList2.size() - 1 ? 0 : currentProduct;
        //TODO new videoid
        //判断是否为添加更多 设置为前一个图片 保持数据不刷新bufen
        if (mList2.get(currentPos).getId() == addId) {
//            SendToIndexCookBookFragment2(mList2.get(currentPos - 1).getId(), mList2.get(currentPos - 1).getText_content());
        } else {
//            SendToIndexCookBookFragment2(mList2.get(currentPos).getId(), mList2.get(currentPos).getText_content());
        }
    }


    private void findView() {
        tv_content2 = (TextView) findViewById(R.id.tv_content2);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
        createAdapter();

    }

    private void addlisteners() {
        //小图点击bufen
        mAdapter2.setOnItemiv1ClickLitener(new RecycleAdapterVertical.OnItemiv1ClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Scroll_listBean listItem = (Scroll_listBean) mAdapter2.getItem(position);
                //请求服务器部分
                if (listItem != null) {
                    //滚动到中间位置bufen
                    smoothMoveToPosition(position, recyclerView2);
                }
            }
        });
        //大图点击bufen
        mAdapter2.setOnItemiv2ClickLitener(new RecycleAdapterVertical.OnItemiv2ClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Scroll_listBean listItem = (Scroll_listBean) mAdapter2.getItem(position);
                //请求服务器部分

            }
        });

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                super.onScrollStateChanged(recyclerView, scrollState);
                if (move && scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    move = false;
                    int n = mIndex - ((LinearLayoutManager) recyclerView2.getLayoutManager()).findFirstVisibleItemPosition();
                    if (0 <= n && n < recyclerView2.getChildCount()) {
                        int top = recyclerView2.getChildAt(n).getTop();
                        recyclerView2.smoothScrollBy(0, top);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstvisiableposition = ((LinearLayoutManager) recyclerView2.getLayoutManager()).findFirstVisibleItemPosition();
                lastvisiableposition = ((LinearLayoutManager) recyclerView2.getLayoutManager()).findLastVisibleItemPosition();
                //由于返回的是一个view，需要调用findViewById方法找到子类的view
                MyLogUtil.e("&&&&&", dx + " " + dy);
                if (mList2 == null || mList2.size() <= 0) {
                    return;
                }
                View v = recyclerView.findChildViewUnder(100, 550);//0-500
                if (v == null) {
                    return;
                }
                TextView tv_position = (TextView) v.findViewById(R.id.tv_position);
                if (tv_position == null) {
                    return;
                }
                int pos = Integer.valueOf(tv_position.getText().toString());
                String tempId = ((Scroll_listBean) mAdapter2.getItem(pos)).getId();
                //防止滚动时改变数据源崩溃bufen
                int currentCount = resetDispatchScrollCounter(0, recyclerView2);// 返回的是old  状态修改为0
                currentProduct = pos;
                mAdapter2.setSelectIndex(pos);
                mAdapter2.notifyItemChanged(pos);
                mHeaderAndFooterWrapper2.notifyDataSetChanged();
                resetDispatchScrollCounter(currentCount, recyclerView2);// 返回的是0 状态修改为old
                //防止滚动时改变数据源崩溃bufen
                if (tempId.equals(addId)) {
                    return;
                }
                if (!(tempId.equals(mDefinitionId))) {
                    mDefinitionId = tempId;
                    mName = ((Scroll_listBean)
                            mAdapter2.getItem(pos)).getText_content();
                    tv_content2.setText(mName);
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
        if (n < 0 || n >= mAdapter2.getItemCount()) {
            return;
        }
        mIndex = n;
        int firstItem = ((LinearLayoutManager) recycler_view.getLayoutManager()).findFirstVisibleItemPosition();
        int lastItem = ((LinearLayoutManager) recycler_view.getLayoutManager()).findLastVisibleItemPosition();
        if (n <= firstItem) {
            recycler_view.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            int top = recycler_view.getChildAt(n - firstItem).getTop();
            recycler_view.smoothScrollBy(0, top);
        } else {
            recycler_view.smoothScrollToPosition(n);
            move = true;
        }
    }

    private void Data2() {
        mList2 = new ArrayList<Scroll_listBean>();
        mList2.add(new Scroll_listBean("1", "小姐姐1", "http://imgsrc.baidu.com/forum/pic/item/23b6bdd3fd1f4134fa2794492f1f95cad1c85e19.jpg"));
        mList2.add(new Scroll_listBean("2", "小姐姐2", "http://imgsrc.baidu.com/forum/pic/item/78b75434970a304e4a3231cddbc8a786c9175c19.jpg"));
        mList2.add(new Scroll_listBean("3", "小姐姐3", "http://imgsrc.baidu.com/forum/pic/item/b5e6301f95cad1c8d5ab6d10753e6709c93d5119.jpg"));
        mList2.add(new Scroll_listBean("4", "小姐姐4", "http://imgsrc.baidu.com/forum/pic/item/9e7db86eddc451da0e619fdcbcfd5266d116329e.jpg"));
        mList2.add(new Scroll_listBean("5", "小姐姐5", "http://imgsrc.baidu.com/forum/pic/item/28e3c41373f08202fc35020841fbfbedaa641b8e.jpg"));
        mList2.add(new Scroll_listBean("6", "小姐姐6", "http://imgsrc.baidu.com/forum/pic/item/f7a619f3d7ca7bcbf3dc5418b4096b63f724a88f.jpg"));
        mList2.add(new Scroll_listBean("7", "小姐姐7", "http://imgsrc.baidu.com/forum/pic/item/33554766d01609243f0f302cde0735fae6cd3403.jpg"));
        mList2.add(new Scroll_listBean("8", "小姐姐8", "http://imgsrc.baidu.com/forum/pic/item/8b5c0ed5ad6eddc4dcdc26cc33dbb6fd52663372.jpg"));
        mList2.add(new Scroll_listBean("9", "小姐姐9", "http://imgsrc.baidu.com/forum/pic/item/895bc2ca7bcb0a460b19190e6163f6246b60af73.jpg"));
    }

    //页面点击bufen
    @Override
    public void onClick(View v) {

    }

}
