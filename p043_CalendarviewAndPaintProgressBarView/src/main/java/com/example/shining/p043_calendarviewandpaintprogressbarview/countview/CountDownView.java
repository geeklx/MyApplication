package com.example.shining.p043_calendarviewandpaintprogressbarview.countview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.example.shining.p043_calendarviewandpaintprogressbarview.R;

public class CountDownView extends View {
    //圆轮颜色
    public int[] mRingColorList = new int[]{
            Color.parseColor("#1A04BD95"),
            Color.parseColor("#3304BD95"),
            Color.parseColor("#4D04BD95"),
            Color.parseColor("#6604BD95"),
            Color.parseColor("#8004BD95"),
            Color.parseColor("#9904BD95"),
            Color.parseColor("#B204BD95"),
            Color.parseColor("#CC04BD95"),
            Color.parseColor("#E604BD95"),
    };

    private float startAngle_scroll = -4;//起始滚动点bufen2
    private final int DEGREE_PROGRESS_DISTANCE = 1;
    //刻度线的颜色bufen
    private String bgArcColor = "#1A000000";
    //圆心
    private float centerX;
    private float centerY;
    private float longdegree = 1;
    //直径
    private int diameter = 1;
    private int diameter_r = 6;//设置圆形大小bufen

    //圆轮宽度
    private float mRingWidth;
    //圆轮进度值文本大小
    private int mRingProgessTextSize;
    //宽度
    private int mWidth;
    //高度
    private int mHeight;
    private String text;
    private Paint progressPaint;
    //圆环的矩形区域
    private RectF mRectF;
    //
    private int mProgessTextColor;
    private int mCountdownTime;
    private float mCurrentProgress;
    private OnCountDownFinishListener mListener;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCofig(context, attrs);
        initView();

        this.setWillNotDraw(false);
    }

    /**
     * 初始化布局配置
     *
     * @param context
     * @param attrs
     */
    private void initCofig(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        //old
//        mRingColor = a.getColor(R.styleable.CountDownView_ringColor, context.getResources().getColor(R.color.colorAccent));
        //new
//        int color1 = a.getColor(R.styleable.CountDownView_front_color1, ContextCompat.getColor(context, R.color.colorAccent));
//        int color2 = a.getColor(R.styleable.CountDownView_front_color2, ContextCompat.getColor(context, R.color.colorPrimary));
//        int color3 = a.getColor(R.styleable.CountDownView_front_color3, ContextCompat.getColor(context, R.color.green2));
//        mRingColorList = new int[]{color1, color2, color3};

        mRingWidth = a.getFloat(R.styleable.CountDownView_ringWidth, 40);
        mRingProgessTextSize = a.getDimensionPixelSize(R.styleable.CountDownView_progressTextSize, DisplayUtils.sp2px(context, 20));
        mProgessTextColor = a.getColor(R.styleable.CountDownView_progressTextColor, context.getResources().getColor(R.color.colorAccent));
        mCountdownTime = a.getInteger(R.styleable.CountDownView_countdownTime, 60);
        a.recycle();
    }

    private void initView() {
        //圆心
        diameter = 3 * getScreenWidth() / diameter_r;
        centerX = (2 * longdegree + mRingWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2;
        centerY = (2 * longdegree + mRingWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2;
    }

    public void setCountdownTime(int mCountdownTime) {
        this.mCountdownTime = mCountdownTime;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRectF = new RectF(0 + mRingWidth / 2, 0 + mRingWidth / 2,
                mWidth - mRingWidth / 2, mHeight - mRingWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //整个弧
        Paint allArcPaint = new Paint();
        allArcPaint.setAntiAlias(true);
        allArcPaint.setStyle(Paint.Style.STROKE);
        allArcPaint.setStrokeWidth(mRingWidth);
        allArcPaint.setColor(Color.parseColor(bgArcColor));
//        allArcPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(mRectF, startAngle_scroll, 360, false, allArcPaint);

        //颜色
        //当前进度的弧形
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
//        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(mRingWidth);
//        progressPaint.setColor(Color.WHITE);
        //设置渐变色
        //半径 = 宽/2-圆环的宽度
        SweepGradient sweepGradient = new SweepGradient(centerX, centerY, mRingColorList, null);
//        Matrix matrix = new Matrix();
//        matrix.setRotate(startAngle_dian, centerX, centerY);//这个方法是以哪个点为中心进行旋转多少度
//        sweepGradient.setLocalMatrix(matrix);
        progressPaint.setShader(sweepGradient);
        canvas.drawArc(mRectF, startAngle_scroll, mCurrentProgress - 360, false, progressPaint);

        //文字居中显示
        //绘制文本
        Paint vTextPaint = new Paint();
        vTextPaint.setAntiAlias(true);
        vTextPaint.setTextAlign(Paint.Align.CENTER);
        text = mCountdownTime - (int) (mCurrentProgress / 360f * mCountdownTime) + "";
        vTextPaint.setTextSize(mRingProgessTextSize);
        vTextPaint.setColor(mProgessTextColor);
        Paint.FontMetricsInt fontMetrics = vTextPaint.getFontMetricsInt();
        int baseline = (int) ((mRectF.bottom + mRectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
        canvas.drawText(text, mRectF.centerX(), baseline, vTextPaint);
    }

    private ValueAnimator getValA(long countdownTime) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(countdownTime);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0);
        return valueAnimator;
    }

    public ValueAnimator mValueAnimator;

    public ValueAnimator getmValueAnimator() {
        return mValueAnimator;
    }

    public void setmValueAnimator(ValueAnimator mValueAnimator) {
        this.mValueAnimator = mValueAnimator;
    }

    /**
     * 开始倒计时
     */
    public void startCountDown() {
        setClickable(false);
        mValueAnimator = getValA(mCountdownTime * 1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float i = Float.valueOf(String.valueOf(animation.getAnimatedValue()));
                mCurrentProgress = (int) (360 * (i / 100f));
                invalidate();
            }
        });
        mValueAnimator.start();
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //倒计时结束回调
                if (mListener != null) {
                    mListener.countDownFinished();
                }
                jieshu_set();
            }

        });
    }

    public void jieshu_set() {
        setClickable(true);
        mCountdownTime = 0;
        mValueAnimator.cancel();
    }

    public void setAddCountDownListener(OnCountDownFinishListener mListener) {
        this.mListener = mListener;
    }

    public interface OnCountDownFinishListener {
        void countDownFinished();
    }

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}