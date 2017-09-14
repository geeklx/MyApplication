package com.example.shining.p039_edittextpwd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;

public class MainActivity extends AppCompatActivity {

    private EditTextPassword inputETP1;
    private EditTextPassword inputETP2;
    private boolean mIsShow1 = false;
    private boolean mIsShow2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputETP1 = (EditTextPassword) findViewById(R.id.etp_input1);
        inputETP2 = (EditTextPassword) findViewById(R.id.etp_input2);
        //设置输入为密码模式
        inputETP1.setInputType(InputType.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
        inputETP1.setDrawableRightListener(new EditTextPassword.DrawableRightListener() {
            @Override
            public void onDrawableRightClick() {
                //同样地可以在这可以实现其他的效果，比如一键清空
                if (mIsShow1) {
                    inputETP1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_grey, 0);
                    inputETP1.setInputType(InputType.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
                } else {
                    inputETP1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_orange, 0);
//                    inputETP.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    inputETP1.setInputType(InputType.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_NORMAL);
                }
                mIsShow1 = !mIsShow1;
            }
        });

        inputETP2.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        inputETP2.setDrawableRightListener(new EditTextPassword.DrawableRightListener() {
            @Override
            public void onDrawableRightClick() {
                //同样地可以在这可以实现其他的效果，比如一键清空
                if (mIsShow2) {
                    inputETP2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_grey, 0);
                    inputETP2.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    inputETP2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_orange, 0);
                    inputETP2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                mIsShow2 = !mIsShow2;
            }
        });
    }


}
