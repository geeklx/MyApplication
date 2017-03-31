package com.example.p021_webviewjs;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


    protected WebView mWebView;

    private String mUrl;

    private Button btn1;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrl = "file:///android_asset/demo/web.html";
        setupWebView();
        loadUrl(mUrl);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        /**
         *  调用js.
         *  WebView.loadUrl("javascript:js中定义的方法")
         */
        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // 无参数调用  android端调用js 方法
                mWebView.loadUrl("javascript:actionFromNative()");
            }
        });
        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // 传递参数调用
                mWebView.loadUrl("javascript:actionFromNativeWithParam(" + "'come from Native'" + ")");
            }
        });
    }

    protected void loadUrl(String url) {
        mWebView.loadUrl(url);
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
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.addJavascriptInterface(this, "fridge");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 如果接受ssl错误， 接受证书， 继续执行
                Log.d("geek", error.toString());
                handler.proceed();
            }
        });
    }

    /**
     * js调用此方法
     */
    @android.webkit.JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "我可以跳转了~", Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * js调用此方法. 并且将参数传递过来
     *
     * @param str js  传递过来的参数
     */
    @android.webkit.JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "我可以拿到你给我的方法跳转了~" + str, Toast.LENGTH_LONG).show();

            }
        });

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
    protected void onDestroy() {
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

}
