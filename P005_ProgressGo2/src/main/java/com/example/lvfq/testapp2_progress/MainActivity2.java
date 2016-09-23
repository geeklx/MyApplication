package com.example.lvfq.testapp2_progress;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends Activity {

    private CustomDialog customDialog;
    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        customDialog = new CustomDialog(this);

        url = "http://www.baidu.com/";
        webView = (WebView) findViewById(R.id.webview);
//        LayoutInflater inflater = LayoutInflater.from(mContext);

//        final View textEntryView = inflater.inflate(
//                R.layout.activity_dialog_progress_tz, null);

//        lDialog = new Dialog(RegActivity1.this,
//                android.R.style.Theme_Translucent_NoTitleBar);
//        lDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        lDialog.setCanceledOnTouchOutside(true);
//        lDialog.setContentView(textEntryView);
//        lDialog.show();
//        showProgressDialog2(getResources().getString(R.string.loading));
        customDialog.show();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

            }
        });
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);// WebSettings.LOAD_CACHE_ELSE_NETWORK

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // newProgress 1-100之间的整数
                // Log.i("---------------RegActivity-------------",
                // "newProgress："+newProgress);
                // mHandler.sendEmptyMessage(newProgress);
                // if (newProgress == 100) {
                // // 网页加载完毕，关闭ProgressDialog
                // closeDialog();
                // } /*else {
                // // 网页正在加载,打开ProgressDialog
                // // openDialog(newProgress);
                // if (dialog == null) {
                // mHandler.sendEmptyMessage(START_PROGRESS);
                // }else{
                //
                // }
                //
                // }*/
                // mdialog.setProgress(newProgress);

                //第一种
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        count += 10;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (customDialog != null && customDialog.isShowing()) {
                                    customDialog.setProgress(count);
                                }
                            }
                        });
                        if (count >= 100) {
                            timer.cancel();
                            closeDialog();
                        }
                    }
                }, 0,2000);
                customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (timer != null) timer.cancel();
                        count = 0;
                    }
                });

                //第二种
//                if (newProgress == 100) {
//                    // dialog.dismiss();
//                    closeDialog();
//                } else {
//                    openDialog(newProgress);
//                }

                super.onProgressChanged(view, newProgress);
            }

            private void closeDialog() {
                if (customDialog != null && customDialog.isShowing()) {
                    customDialog.dismiss();
                }
            }

            private void openDialog(final int newProgress) {
                if (customDialog != null && customDialog.isShowing()) {
                    customDialog.setProgress(newProgress);
                }
            }
        });
    }
    private int count = 0;
}
