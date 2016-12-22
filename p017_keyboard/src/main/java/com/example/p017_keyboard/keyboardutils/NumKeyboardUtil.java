package com.example.p017_keyboard.keyboardutils;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;

import com.example.p017_keyboard.R;


/**
 * Created by shining on 2016/12/20 0020.
 */

public class NumKeyboardUtil {
    private KeyboardView keyboardView;
    private Keyboard k;// 数字键盘
    private PasswordInputView ed;

    public NumKeyboardUtil(Activity act, PasswordInputView edit, KeyboardView keyboardViews) {
        this.ed = edit;
        this.keyboardView = keyboardViews;
        k = new Keyboard(act.getApplication(), R.xml.number);
        keyboardView.setKeyboard(k);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        //一些特殊操作按键的codes是固定的比如完成、回退等
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
                hideKeyboard();
            } else { //将要输入的数字现在编辑框中
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };

    public void showKeyboard() {
        if (getKeyboardVisible() == View.GONE || getKeyboardVisible() == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        if (getKeyboardVisible() == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    public int getKeyboardVisible() {
        return keyboardView.getVisibility();
    }
}
