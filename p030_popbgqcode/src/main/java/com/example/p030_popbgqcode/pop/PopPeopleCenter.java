package com.example.p030_popbgqcode.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.p030_popbgqcode.R;
import com.example.p030_popbgqcode.utils.BitmapUtils;
import com.example.p030_popbgqcode.utils.QrCodeUtil;

public class PopPeopleCenter extends PopupWindow {
    private ImageView pc_iv1, pc_iv2, pc_iv3;
    private TextView pc_tv1, pc_tv2, pc_tv3;
    private ImageView pc_iv4;
    private Activity mActivity;
    private View mMenuView;

    public PopPeopleCenter(Activity activity) {

    }


    @SuppressWarnings("deprecation")
    public PopPeopleCenter(Activity activity, String str) {
        super(activity);
        this.mActivity = activity;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.activity_poppeoplecenter, null);

        pc_iv1 = (ImageView) mMenuView.findViewById(R.id.pc_iv1);//

        QrCodeUtil.createQRCode(pc_iv1, str, 300);//生成二维码bufen

        pc_iv1.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        Bitmap shot = BitmapUtils.takeScreenShot(activity.getWindow().getDecorView());
        Bitmap blur = BitmapUtils.blur(activity, shot);
        this.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), blur));
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        mMenuView.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//
////				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
////				int music_seek_bar_cycle = (int) event.getY();
////				if (event.getAction() == MotionEvent.ACTION_UP) {
////					if (music_seek_bar_cycle < height) {
////						dismiss();
////					}
////				}
//                dismiss();
//                return true;
//            }
//        });

    }

    /**
     * 调用popbufen
     *
     * @param activity
     * @param str
     */
    public static void showVerDialog(Activity activity, String str) {
        PopPeopleCenter showVerCode = new PopPeopleCenter(activity, str);
        // 显示窗口
        showVerCode.showAtLocation(activity.getWindow().getDecorView(),
                Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL
                , 0, 0); // 设置layout在PopupWindow中显示的位置
    }
}
