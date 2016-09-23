package com.example.p006_activity_fragment.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.p006_activity_fragment.R;
import com.example.p006_activity_fragment.MainActivity;
import com.example.p006_activity_fragment.util.AppManager;
import com.example.p006_activity_fragment.util.ViewHelper;

import butterknife.ButterKnife;

/**
 * Created by geek on 2016/7/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName().toString();
    public static final String REQUEST_CODE = "request_code";

    protected Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        interceptCreateView();
        super.onCreate(savedInstanceState);
        AppManager.getInstance().add(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mHandler = new Handler();
        setup(savedInstanceState);
    }

    private void interceptCreateView() {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                if (view instanceof EditText) {
//                    MyLogUtil.d("***", "IME_FLAG_NO_EXTRACT_UI");
                    EditText et = (EditText) view;
                    et.setImeOptions(et.getImeOptions() | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    return et;
                }
                return view;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    protected abstract int getLayoutId();

    protected void setup(@Nullable Bundle savedInstanceState) {

    }

    protected final <T extends View> T f(@IdRes int resId) {
        return ViewHelper.f(this, resId);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void startActivityForResult(Class<? extends Activity> activity, int requestCode) {
        startActivityForResult(new Intent(this, activity), requestCode);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (requestCode != -1 && intent.getIntExtra(REQUEST_CODE, -1) == -1) {
            intent.putExtra(REQUEST_CODE, requestCode);
        }

        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    public void finish() {
        super.finish();
        AppManager.getInstance().remove(this);
    }

    @Override
    protected void onDestroy() {
//        ShowLoadingUtil.onDestroy();
//        Net.getInstance().get().cancel(getClass().getName());
        AppManager.getInstance().remove(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideSoftKeyboard();
        finish();
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }

    public void onHomePressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
