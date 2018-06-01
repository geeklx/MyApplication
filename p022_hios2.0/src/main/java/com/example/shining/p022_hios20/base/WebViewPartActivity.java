package com.example.shining.p022_hios20.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.shining.p022_hios20.R;


public class WebViewPartActivity extends WebViewActivity {

    private String url;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_webview_part_layout);
        url = "http://liangxiao.blog.51cto.com/";

        findview();
        onclickListener();
        setupWebView();
        loadUrl(url);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),getResources().getColor(android.R.color.holo_green_light),getResources().getColor(android.R.color.holo_orange_light),getResources().getColor(android.R.color.holo_red_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        set_clear_history();
                        setupWebView();
                        loadUrl(url);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
