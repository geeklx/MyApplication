package com.shining.p010_recycleviewall.utils;

import android.content.Context;

import com.shining.p010_recycleviewall.widget.loading.ShapeLoadingDialog;


/**
 * Toast管理
 */
public class ShowLoadingUtil {
    public static ShapeLoadingDialog shapeLoadingDialog;//loading样式2

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
//            mHandler.postDelayed(dismisRun,10*1000);
            return true;
        } else {
            return false;
        }
    }

    /***
     * @return true取消成功 false 加载框不存在
     * @description: 取消加载框
     */
    public static boolean dismissProgressDialog2() {
        if (shapeLoadingDialog != null && shapeLoadingDialog.getDialog().isShowing()) {
            shapeLoadingDialog.dismiss();
            shapeLoadingDialog = null;
            return true;
        } else {
            return false;
        }
    }

    public static void onDestroy() {
        if (shapeLoadingDialog != null) { dismissProgressDialog2();}
        shapeLoadingDialog = null;
    }
}