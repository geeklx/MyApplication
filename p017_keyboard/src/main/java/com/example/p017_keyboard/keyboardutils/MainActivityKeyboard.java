package com.example.p017_keyboard.keyboardutils;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.p017_keyboard.R;

public class MainActivityKeyboard extends AppCompatActivity {

    PasswordInputView edtPwd;
    KeyboardView keyboardView;
    ImageView iv_delete;
    Button btn_popwin_ok;

    private NumKeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_keyboard);
        edtPwd = (PasswordInputView) findViewById(R.id.trader_pwd_set_pwd_edittext);
        keyboardView = (KeyboardView) findViewById(R.id.keyboard_view_my);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        btn_popwin_ok = (Button) findViewById(R.id.btn_popwin_ok);
        addListener();

    }
    private void addListener() {
        edtPwd.setInputType(InputType.TYPE_NULL); // 屏蔽系统软键盘
        keyboardUtil = new NumKeyboardUtil(MainActivityKeyboard.this, edtPwd, keyboardView);
        edtPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 关闭软键盘
//                hideSoftKeyboard();
                keyboardUtil.showKeyboard();
                return false;
            }
        });
        edtPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideSystemKeyBoard();
                    keyboardUtil.showKeyboard();
                } else {
                    keyboardUtil.hideKeyboard();
                }
            }
        });

        // 确定按钮
        btn_popwin_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 销毁弹出框
                if (TextUtils.isEmpty(edtPwd.getText().toString())) {
                    ToastUtil toastUtil = new ToastUtil(MainActivityKeyboard.this);
                    toastUtil.showToastCenter("请输入有效密码！");
                    return;
                }
                if (!(edtPwd.getText().toString().length() == edtPwd.getPasswordLength())) {
                    ToastUtil toastUtil = new ToastUtil(MainActivityKeyboard.this);
                    ToastUtil.showToastCenter("请输入有效密码！");
                    return;
                }
                Intent data = new Intent();
                data.putExtra("edtPwd", edtPwd.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
        //missbufen
        iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 销毁弹出框
                finish();
            }
        });
    }

    public void hideSystemKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtPwd.getWindowToken(), 0);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                keyboardUtil.hideKeyboard();
            }
        }
        return super.onTouchEvent(event);
    }
}
