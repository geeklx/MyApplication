package com.example.p009_glide;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewHelper;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;


public class MainActivityDemo1 extends AppCompatActivity implements View.OnClickListener {

    private Button aaaBtn;
    private Button bbbBtn;
    private ImageView bbbImg;
    private Button cccBtn;
    private TextView aaaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);
        initView();
    }

    private void initView() {

        aaaBtn = (Button) findViewById(R.id.aaa_btn);
        aaaBtn.setOnClickListener(this);
        aaaText = (TextView) findViewById(R.id.aaa_text);
        aaaText.setOnClickListener(this);
        bbbBtn = (Button) findViewById(R.id.bbb_btn);
        bbbBtn.setOnClickListener(this);
        bbbImg = (ImageView) findViewById(R.id.bbb_img);
        bbbImg.setOnClickListener(this);
    }


    private void animateClickView(final View v, final ClickAnimation callback) {
        float factor = 2;
        animate(v).scaleX(factor).scaleY(factor).alpha(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ViewHelper.setScaleX(v, 1);
                ViewHelper.setScaleY(v, 1);
                ViewHelper.setAlpha(v, 1);
                if (callback != null) {
                    callback.onClick(v);
                }
                super.onAnimationEnd(animation);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        animateClickView(v, new ClickAnimation() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (v == aaaBtn) {
                    Toast.makeText(getApplicationContext(), "aaaBtn has been clicked", Toast.LENGTH_SHORT)
                            .show();
                } else if (v == aaaText) {
                    Toast.makeText(getApplicationContext(), "aaaText has been clicked", Toast.LENGTH_SHORT)
                            .show();
                } else if (v == bbbBtn) {
                    Toast.makeText(getApplicationContext(), "bbbBtn has been clicked", Toast.LENGTH_SHORT)
                            .show();
                } else if (v == bbbImg) {
                    Toast.makeText(getApplicationContext(), "bbbImg has been clicked", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    public interface ClickAnimation {
        public void onClick(View v);
    }
}
