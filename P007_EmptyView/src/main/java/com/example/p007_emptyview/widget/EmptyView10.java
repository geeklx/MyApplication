package com.example.p007_emptyview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.p007_emptyview.R;

public class EmptyView10 extends FrameLayout implements View.OnClickListener {

    private View mLoadingView;
    private View mNodataLayout;
    private View mErrorNetLayout;

    private View mBindView;

    private RetryListener mListener;

    // 数据为空，网络失败， 加载中
    private String[] mNoticeString = new String[] {"暂无数据", "获取数据失败\n请检查网络是否通畅", ""};

    public EmptyView10(Context context) {
        this(context, null, 0);
    }

    public EmptyView10(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView10(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyleAttr, 0);
        int loadingLayoutId = ta.getResourceId(R.styleable.EmptyView_loading_layout, 0);
        int nodataLayoutId = ta.getResourceId(R.styleable.EmptyView_nodata_layout, 0);
        int erroNetLayoutId = ta.getResourceId(R.styleable.EmptyView_errornet_layout, 0);
        ta.recycle();

        checkLayout(loadingLayoutId, nodataLayoutId);
        erroNetLayoutId = erroNetLayoutId == 0 ? nodataLayoutId : erroNetLayoutId;

        LayoutInflater inflater = LayoutInflater.from(context);
        mLoadingView = inflater.inflate(loadingLayoutId, this, false);
        mNodataLayout = inflater.inflate(nodataLayoutId, this, false);
        mErrorNetLayout = inflater.inflate(erroNetLayoutId, this, false);

        addView(mLoadingView);
        addView(mNodataLayout);
        addView(mErrorNetLayout);

        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setText();
        setClick(mErrorNetLayout);
    }

    private void setText() {
        TextView noDataNotice = ViewHelper.f(mNodataLayout, R.id.empty_nodata_notice);
        if (noDataNotice != null) { noDataNotice.setText(mNoticeString[0]);}

        TextView errNotice = ViewHelper.f(mErrorNetLayout, R.id.empty_errnet_notice);
        if (errNotice != null) { errNotice.setText(mNoticeString[1]);}
    }

    private void setClick(View ...contentViews) {
        for (View item : contentViews) {
            if (ViewHelper.f(item, R.id.empty_errnet_btn) != null) {
                ViewHelper.click(this, ViewHelper.f(item, R.id.empty_errnet_btn));
            } else {
                ViewHelper.click(this, item);
            }
        }
    }

    private void checkLayout(int loading, int nodata) {
        if (loading == 0 || nodata == 0) {
            throw new UnsupportedOperationException("you must apply loading_layout and nodata_layout");
        }
    }

    /**
     * 加载中
     */
    public void loading() {
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.VISIBLE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.GONE);
    }

    /**
     * 加载成功
     */
    public void success() {
        bindViewVisible(View.VISIBLE);
        setVisibility(View.GONE);
    }

    /**
     * 无数据,json返回成功， 但是数据集为空
     */
    public void nodata() {
//        if (!MobileUtils.isNetworkConnected(getContext())) {
//            errorNet();
//            return;
//        }
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.VISIBLE);
        mErrorNetLayout.setVisibility(View.GONE);
    }

    /**
     * 网络访问失败，或者json ok为false
     */
    public void errorNet() {
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.VISIBLE);
    }

    private void bindViewVisible(int visibility) {
        if (mBindView != null) mBindView.setVisibility(visibility);
    }

    /**
     * 设置绑定的view
     * @param view
     * @return
     */
    public EmptyView10 bind(View view) {
        mBindView = view;
        return this;
    }

    /**
     * 设置提醒语句
     * @param notices 大小为3， 0：数据为空提醒，默认数据为空;
     *                1: 网络失败：默认获取数据失败请检查网络是否通畅;
     *                2: 加载中，预留字段
     * @return
     */
    public EmptyView10 notices(String ...notices) {
        int length = notices.length > 3 ? 3 : notices.length;
        for (int i = 0; i < length; i++) {
            mNoticeString[i] = notices[i];
        }

        setText();
        return this;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) { mListener.retry();}
    }

    /**
     * 设置重试回调
     * @param li
     */
    public void setRetryListener(RetryListener li) {
        mListener = li;
    }

    public interface RetryListener {
        void retry();
    }
}
