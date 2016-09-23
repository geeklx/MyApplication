package com.example.p007_emptyview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.p007_emptyview.widget.EmptyView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EmptyView.RetryListener {

    private EmptyView mEmptyView;
    private LinearLayout containlayout;
    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        mEmptyView = (EmptyView) findViewById(R.id.empty_view);
        containlayout = (LinearLayout) findViewById(R.id.containlayout);

        mEmptyView.bind(containlayout).setRetryListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);

    }

    @Override
    public void retry() {
        //重试
        mEmptyView.loading();
        //加载数据
//        mEmptyView.success();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                mEmptyView.loading();
                break;
            case R.id.tv2:
                mEmptyView.errorNet();
                break;
            case R.id.tv3:
                mEmptyView.nodata();
                break;
            case R.id.tv4:
                mEmptyView.success();
                break;
            default:

                break;
        }
    }
}
