package com.mingle.widget;

import android.content.Context;
import android.os.Handler;


/**
 * Toast管理
 */
public class ShowLoadingUtil {
    public static ShapeLoadingDialog shapeLoadingDialog;//loading样式2
//    public static SimpleLoadingDialog simpleLoadingDialog;//loading样式2
    private static final long AUTO_DISMISS_TIME = 5000;
    private static Handler disHandler = new Handler();
    private static Thread disThread = new Thread(new Runnable() {
        @Override
        public void run() {
            dismissProgressDialog2();
        }
    });

    /**
     * @description: 显示ProgressDialog
     */
    public static boolean showProgressDialog2(Context context, String progressDesc) {
        if (progressDesc != null && !"".equals(progressDesc)) {
            if (shapeLoadingDialog == null) {
                shapeLoadingDialog = new ShapeLoadingDialog(context);
                //TODO 控制loading下面的字
                shapeLoadingDialog.setLoadingText(progressDesc);
                shapeLoadingDialog.setCanceledOnTouchOutside(false);
                shapeLoadingDialog.show();
            } else {
                shapeLoadingDialog.show();
            }
            /*if (simpleLoadingDialog == null) {
                simpleLoadingDialog = new SimpleLoadingDialog(context);
                //TODO 控制loading下面的字
                simpleLoadingDialog.setLoadingText(progressDesc);
                simpleLoadingDialog.setCanceledOnTouchOutside(false);
                simpleLoadingDialog.show();
            } else {
                simpleLoadingDialog.show();
            }*/
            disHandler.postDelayed(disThread,AUTO_DISMISS_TIME);
//            mHandler.postDelayed(dismisRun,10*1000);
            return true;
        } else {
            disHandler.postDelayed(disThread,AUTO_DISMISS_TIME);
            return false;
        }
//        return true;
    }

    /***
     * @return true取消成功 false 加载框不存在
     * @description: 取消加载框
     */
    public static boolean dismissProgressDialog2() {
        if (shapeLoadingDialog != null && shapeLoadingDialog.getDialog().isShowing()) {
            shapeLoadingDialog.dismiss();
            shapeLoadingDialog = null;
            disHandler.removeCallbacks(disThread);
            return true;
        } else {
            disHandler.removeCallbacks(disThread);
            return false;
        }
        /*if (simpleLoadingDialog != null && simpleLoadingDialog.getDialog().isShowing()) {
            simpleLoadingDialog.dismiss();
            simpleLoadingDialog = null;
            disHandler.removeCallbacks(disThread);
            return true;
        } else {
            disHandler.removeCallbacks(disThread);
            return false;
        }*/
//        return true;
    }

}