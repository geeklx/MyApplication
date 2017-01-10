package com.example.p010_recycleviewall.recycleviewbiaoge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.domain.Biaoge_listBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shining on 2017/1/10 0010.
 */

public class MainActivityBiaoge extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView1;
    private RecycleAdapterbiaoge mAdapter1;
    private List<Biaoge_listBean> mList1;

    private RecyclerView recyclerView2;
    private RecycleAdapterbiaoge mAdapter2;
    private List<Biaoge_listBean> mList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclelistview_biaoge);
        findView();
        addlisteners();
        Data1();
        Data2();
    }

    private void findView() {
        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view11);

        mAdapter1 = new RecycleAdapterbiaoge(this);
        recyclerView1.setLayoutManager(new FullyGridLayoutManager(this, 3));
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setAdapter(mAdapter1);

        mAdapter2 = new RecycleAdapterbiaoge(this);
        recyclerView2.setLayoutManager(new FullyGridLayoutManager(this, 3));
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setAdapter(mAdapter2);
    }

    private void addlisteners() {

    }

    private void Data1() {
        mList1 = new ArrayList<Biaoge_listBean>();
        mList1.add(new Biaoge_listBean("食材1"));
        mList1.add(new Biaoge_listBean("食材2"));
        mList1.add(new Biaoge_listBean("食材3"));
        mList1.add(new Biaoge_listBean("食材4食材4食材4食材4食材4"));

        //判断条件如果
        if ((mList1.size()-1)%3==0){
            //1 4 7 add 2
            mList1.add(new Biaoge_listBean(""));
            mList1.add(new Biaoge_listBean(""));
        }
        if ((mList1.size()-1)%3==1){
            //2 5 8 add 1
            mList1.add(new Biaoge_listBean(""));
        }

        if (mList1 != null && mList1.size() > 0) {
            mAdapter1.setContacts(mList1);
            mAdapter1.notifyDataSetChanged();
        }
    }

    private void Data2() {
        mList2 = new ArrayList<Biaoge_listBean>();
        mList2.add(new Biaoge_listBean("食材11"));
        mList2.add(new Biaoge_listBean("食材12"));
        mList2.add(new Biaoge_listBean("食材13"));
        mList2.add(new Biaoge_listBean("食材14食材14食材14食材14食材14食材14"));
        mList2.add(new Biaoge_listBean("食材14食材14食材14食材14"));

        //判断条件如果
        if ((mList2.size()-1)%3==0){
            //1 4 7 add 2
            mList2.add(new Biaoge_listBean(""));
            mList2.add(new Biaoge_listBean(""));
        }
        if ((mList2.size()-1)%3==1){
            //2 5 8 add 1
            mList2.add(new Biaoge_listBean(""));
        }

        if (mList2 != null && mList1.size() > 0) {
            mAdapter2.setContacts(mList2);
            mAdapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {


    }
}
