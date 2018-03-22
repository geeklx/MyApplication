package com.example.shining.p022_hios20.base;

import android.os.Bundle;

import com.example.shining.p022_hios20.R;


public class WebViewPartActivity extends WebViewActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_webview_part_layout);
        url = "http://liangxiao.blog.51cto.com/";

        findview();
        onclickListener();
        setupWebView();
        loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
