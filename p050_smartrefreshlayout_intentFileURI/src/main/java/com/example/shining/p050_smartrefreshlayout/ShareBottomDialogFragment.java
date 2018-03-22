package com.example.shining.p050_smartrefreshlayout;

import android.view.View;

import com.example.shining.p050_smartrefreshlayout.utils.ToastUtil;

import me.shaohui.bottomdialog.BaseBottomDialog;

public class ShareBottomDialogFragment extends BaseBottomDialog {

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_layout;
    }

    @Override
    public void bindView(View v) {
        switch (v.getId()) {
            case R.id.mRlWechat:
                ToastUtil.showToastCenter("微信");
                break;
            case R.id.mRlWeixinCircle:
                ToastUtil.showToastCenter("朋友圈");
                break;
            case R.id.mRlWeibo:
                ToastUtil.showToastCenter("微博");
                break;
            case R.id.mRlQQ:
                ToastUtil.showToastCenter("QQ");
                break;
            case R.id.mRlQzone:
                ToastUtil.showToastCenter("QQ空间");
                break;
            default:
                break;
        }
    }
}