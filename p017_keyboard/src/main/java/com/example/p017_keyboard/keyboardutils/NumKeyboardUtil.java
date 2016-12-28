package com.example.p017_keyboard.keyboardutils;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;

import com.example.p017_keyboard.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.inputmethodservice.Keyboard.KEYCODE_CANCEL;


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
        keyboardView.setPreviewEnabled(false);//是否显示隐藏点击pop布局bufen
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

    // 0-9 的数字
    private final List<Character> keyCodes = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    /**
     * 随机打乱数字键盘上显示的数字顺序。
     */
    public void shuffleKeyboard() {
        Keyboard keyboard = keyboardView.getKeyboard();
        if (keyboard != null && keyboard.getKeys() != null
                && keyboard.getKeys().size() > 0) {
            // 随机排序数字
            Collections.shuffle(keyCodes);

            // 遍历所有的按键
            List<Keyboard.Key> keys = keyboardView.getKeyboard().getKeys();
            int index = 0;
            for (Keyboard.Key key : keys) {
                // 如果按键是数字
                if (key.codes[0] != KEYCODE_CANCEL
                        && key.codes[0] != Keyboard.KEYCODE_DELETE) {
                    char code = keyCodes.get(index++);
                    key.codes[0] = code;
                    key.label = Character.toString(code);
                }
            }
            // 更新键盘
            keyboardView.setKeyboard(keyboard);
        }
    }
}
