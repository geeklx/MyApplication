package com.example.p012_welcomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 广告页
 */
public class AdvertisementActivity extends Activity implements View.OnClickListener {

    private String TAG = AdvertisementActivity.class.getSimpleName();
    private TextView tv_vertifyView;
    private Button buttonjump;
    private RelativeLayout rel_web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        findview();
        addlistener();
        initdata();
    }

    private void findview() {
        tv_vertifyView = (TextView) findViewById(R.id.tv_vertifyView);
        buttonjump = (Button) findViewById(R.id.buttonjump);
        rel_web = (RelativeLayout) findViewById(R.id.rel_web);
    }

    private void addlistener() {
        tv_vertifyView.setOnClickListener(this);
        rel_web.setOnClickListener(this);
    }

    private void initdata() {
        //倒计时
        rel_web.setOnClickListener(this);
        buttonjump.setOnClickListener(this);
        timer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_vertifyView:
                timer.cancel();
                Intent intent = new Intent(AdvertisementActivity.this, MainActivity.class);
                intent.putExtra("web", "tv_vertifyView");
                startActivity(intent);
                finish();
                break;
            case R.id.rel_web:
                timer.cancel();
                Intent intentweb = new Intent(AdvertisementActivity.this, MainActivity.class);
                intentweb.putExtra("web", "rel_web");
                startActivity(intentweb);
                finish();
                break;
        }
    }

    private CountDownTimer timer = new CountDownTimer(4000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tv_vertifyView.setText("(" + (millisUntilFinished / 1000) + ")" + "跳过");
        }

        @Override
        public void onFinish() {
            Log.d(TAG, "CountDownTimer onFinish():跳转主页");
            tv_vertifyView.setEnabled(true);
            tv_vertifyView.setText("(0)跳过");
            Intent intent = new Intent(AdvertisementActivity.this, MainActivity.class);
            intent.putExtra("web", "1");
            startActivity(intent);
            finish();

        }
    };

    @Override
    public void finish() {
        super.finish();
        timer.cancel();
    }

    //    @Override
//    protected void networkCallBack(NetMessage message) {
//        switch (message.getRequestCode()) {
//            case REQUEST_CODE_NET_1: {
//                if (message.getOk()) {
//                    Log.i("guangdiantong-----", message.getResult().toString() + "");
//                    // zhuanjiaTitle = new ArrayList<ZhuanjiaTitle>();
//                    // zhuanjiaTitle.addAll(JsonUtils.getBeanList(message.result,
//                    // "Items", ZhuanjiaTitle.class));
//                    // // if (zhuanjiaTitle.size()>0) {
//                    // // adaptor.setContacts(zhuanjiaTitle);
//                    // // adaptor.notifyDataSetChanged();
//                    // // }
//                    // handler.post(new Runnable() {
//                    // @Override
//                    // public void run() {
//                    // adaptor.setContacts(zhuanjiaTitle);
//                    // adaptor.notifyDataSetChanged();
//                    // }
//                    // });
//                }
//            }
//            break;
//            default:
//                break;
//        }
//    }

}
