package com.example.p022_hois.base;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.p022_hois.R;
import com.example.p022_hois.hois1.HiosHelper;


/**
 * Created by qibin on 2016/8/13.
 */

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private View mBackImageView;
    private View mBackView;
    private View mCloseView;
    private TextView mTitleTextView;
    private ProgressBar mProgressBar;

    protected WebView mWebView;

    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_webview_layout);
        findview();
        onclickListener();
        setupWebView();
        mUrl = getIntent().getStringExtra("url");
        loadUrl(mUrl);
    }

    private void findview() {
        mBackImageView = findViewById(R.id.ic_back);
        mBackView = findViewById(R.id.back);
        mCloseView = findViewById(R.id.close);
        mTitleTextView = (TextView) findViewById(R.id.title);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
    }

    private void onclickListener(){
        mBackImageView.setOnClickListener(this);
        mBackView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);
    }

    protected void setupWebView() {
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ViewGroup parent = (ViewGroup) findViewById(R.id.container);
        parent.addView(mWebView);

        WebSettings settings = mWebView.getSettings();//拿到webbiew的settings
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
        settings.setUseWideViewPort(true);//让图片更适合窗口
        settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //使WebView支持js
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);

        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.addJavascriptInterface(this, "fridge");//js对应tag

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return HiosHelper.shouldOverrideUrl(WebViewActivity.this, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 如果接受到ssl错误， 接受证书， 继续执行
                Log.d("geek", error.toString());
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = view.getTitle();
                if (title != null) {
                    mTitleTextView.setText(title);
                }
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitleTextView.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }

    protected void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_back || id == R.id.back) {
            onBackPressed();
            return;
        }

        if (id == R.id.close) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
//        FixInputMethodBug.fixFocusedViewLeak(getApplication());

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        super.onDestroy();
    }

    // *****js调用获取信息****

    private String user_id = "";

    /**
     * 通知获取userId,通过调用js函数onGetUserId来回调user_id
     */
    @JavascriptInterface
    public void userId() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
//                loginToDo(new Runnable() {
//                    @Override
//                    public void run() {
//                        mWebView.loadUrl("javascript:onGetUserId(\"" + user_id + "\")");
//                    }
//                });
                mWebView.loadUrl("javascript:onGetUserId(\"" + user_id + "\")");
            }
        });
    }

    private String mode = "";

    /**
     * 获取fridgeModel
     *
     * @return
     */
    @JavascriptInterface
    public String fridgeModel() {
        return mode;
    }


    @JavascriptInterface
    public void finishPage() {
        finish();
    }

//    private void loginToDo(Runnable r) {
//        UserUtils.loginToDo(this, r);
//    }

    // *****end****
}
