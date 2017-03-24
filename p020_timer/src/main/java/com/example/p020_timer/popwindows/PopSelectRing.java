package com.example.p020_timer.popwindows;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.p020_timer.R;
import com.example.p020_timer.application.DemoApplication;
import com.example.p020_timer.util.ConstantUtil;
import com.example.p020_timer.util.SpUtils;


public class PopSelectRing extends PopupWindow implements View.OnClickListener {
    private Button mBtSure, mBtCancel;
    private View mMenuView;
    private Activity context;
    private BtnListener popListener;
    private RelativeLayout rl_ring1, rl_ring2, rl_ring3;
    private TextView tv_ring1, tv_ring2, tv_ring3;
    private ImageView iv_ring1, iv_ring2, iv_ring3;
    private int CURITEM = 1, ITEM1 = 1, ITEM2 = 2, ITEM3 = 3;

    public void setPopListener(BtnListener popListener) {
        this.popListener = popListener;
    }

    @SuppressWarnings("deprecation")
    public PopSelectRing(Activity activity) {
        super(activity);
        this.context = activity;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popwindow_select_ring, null);


        mBtSure = (Button) mMenuView.findViewById(R.id.button_ok);
        mBtCancel = (Button) mMenuView.findViewById(R.id.button_cancel);

        rl_ring1 = (RelativeLayout) mMenuView.findViewById(R.id.rl_ring1);
        rl_ring2 = (RelativeLayout) mMenuView.findViewById(R.id.rl_ring2);
        rl_ring3 = (RelativeLayout) mMenuView.findViewById(R.id.rl_ring3);
        tv_ring1 = (TextView) mMenuView.findViewById(R.id.tv_ring1);
        tv_ring2 = (TextView) mMenuView.findViewById(R.id.tv_ring2);
        tv_ring3 = (TextView) mMenuView.findViewById(R.id.tv_ring3);
        iv_ring1 = (ImageView) mMenuView.findViewById(R.id.iv_ring1);
        iv_ring2 = (ImageView) mMenuView.findViewById(R.id.iv_ring2);
        iv_ring3 = (ImageView) mMenuView.findViewById(R.id.iv_ring3);


        mBtSure.setOnClickListener(this);
        mBtCancel.setOnClickListener(this);
        rl_ring1.setOnClickListener(this);
        rl_ring2.setOnClickListener(this);
        rl_ring3.setOnClickListener(this);


        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
//         mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
//                hideInputMethod(context);
                return true;
            }
        });

        checkItemWhich();

    }

    private void checkItemWhich() {
        int spItem = (int) SpUtils.getInstance(DemoApplication.get()).get(ConstantUtil.CURRENTALARMSOUND, 1);
        if (spItem <= 0 || spItem >= 4) {
            spItem = 1;
        }
        CURITEM = spItem;
        if (CURITEM == ITEM1) {
            selectWhich(ITEM1);
        } else if (CURITEM == ITEM2) {
            selectWhich(ITEM2);
        } else if (CURITEM == ITEM3) {
            selectWhich(ITEM3);
        }
    }


    private void selectWhich(int which) {
        rl_ring1.setBackground(null);
        rl_ring2.setBackground(null);
        rl_ring3.setBackground(null);
        iv_ring1.setVisibility(View.GONE);
        iv_ring2.setVisibility(View.GONE);
        iv_ring3.setVisibility(View.GONE);
        tv_ring1.setTypeface(Typeface.DEFAULT);
        tv_ring2.setTypeface(Typeface.DEFAULT);
        tv_ring3.setTypeface(Typeface.DEFAULT);
        if (which == ITEM1) {
            rl_ring1.setBackgroundColor(context.getResources().getColor(R.color.timer_select_bg));
            iv_ring1.setVisibility(View.VISIBLE);
            tv_ring1.setTypeface(Typeface.DEFAULT_BOLD);
            CURITEM = ITEM1;
        } else if (which == ITEM2) {
            rl_ring2.setBackgroundColor(context.getResources().getColor(R.color.timer_select_bg));
            iv_ring2.setVisibility(View.VISIBLE);
            tv_ring2.setTypeface(Typeface.DEFAULT_BOLD);
            CURITEM = ITEM2;
        } else if (which == ITEM3) {
            rl_ring3.setBackgroundColor(context.getResources().getColor(R.color.timer_select_bg));
            iv_ring3.setVisibility(View.VISIBLE);
            tv_ring3.setTypeface(Typeface.DEFAULT_BOLD);
            CURITEM = ITEM3;
        }
    }


    public void showUpdateDialog() {
        // 显示窗口
        if (context == null) {
            return;
        }
        showAtLocation(context.getWindow().getDecorView(),
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                , 0, 0); // 设置layout在PopupWindow中显示的位置
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_ring1:
                selectWhich(ITEM1);
                break;
            case R.id.rl_ring2:
                selectWhich(ITEM2);
                break;
            case R.id.rl_ring3:
                selectWhich(ITEM3);
                break;
            case R.id.button_ok:
                if (popListener != null) {
                    popListener.onOkClick(CURITEM);
                }
                dismiss();
                break;
            case R.id.button_cancel:
                dismiss();
                break;

            default:
                break;
        }
    }


    public interface BtnListener {
        void onOkClick(int ring);

    }

    public void hideInputMethod(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mMenuView, InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(mMenuView.getWindowToken(), 0); //强制隐藏键盘

    }


    public void showInputMethod(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

    }

}
