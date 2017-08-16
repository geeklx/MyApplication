package com.example.p031_mokuaihua_viewpager_fragment.base;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.p031_mokuaihua_viewpager_fragment.MainActivity;
import com.example.p031_mokuaihua_viewpager_fragment.R;
import com.example.p031_mokuaihua_viewpager_fragment.utils.AppManager;
import com.example.p031_mokuaihua_viewpager_fragment.utils.MyLogUtil;
import com.example.p031_mokuaihua_viewpager_fragment.utils.ViewHelper;

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

//        HookUtil.hookClick(this);
    }

    private void interceptCreateView() {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                if (view != null && view instanceof EditText) {
                    MyLogUtil.d("***", "IME_FLAG_NO_EXTRACT_UI");
                    EditText et = (EditText) view;
                    et.setImeOptions(et.getImeOptions() | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    return et;
                }
                return view;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) { HookUtil.hookClick(this);}
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            MyLogUtil.d("qibin", "mobClickEvent");
//            MobEventHelper.onEvent(this, "effective_click");
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgentFixed.onResume(this);
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
//        MobclickAgentFixed.onPause(this);
    }

    @Override
    public void finish() {
        hideSoftKeyboard();
//        ShowLoadingUtil.onDestory();
        super.finish();
        AppManager.getInstance().remove(this);
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }

    @Override
    protected void onDestroy() {
//        Net.getInstance().get().cancel(getClass().getName());
        AppManager.getInstance().remove(this);
//        ShowLoadingUtil.onDestory();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onHomePressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void noAnimFinish(){
        hideSoftKeyboard();
//        ShowLoadingUtil.onDestory();
        super.finish();
        AppManager.getInstance().remove(this);
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
