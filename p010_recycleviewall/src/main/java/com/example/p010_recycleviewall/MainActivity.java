package com.example.p010_recycleviewall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.p010_recycleviewall.application.ConstantNetUtil;
import com.example.p010_recycleviewall.recycleviewbiaoge.MainActivityBiaoge;
import com.example.p010_recycleviewall.recycleviewdifferentitem.MainActivityDff;
import com.example.p010_recycleviewall.recycleviewgridview.MainActivity2;
import com.example.p010_recycleviewall.recycleviewgridviewaddheadandfooter.MainActivity4;
import com.example.p010_recycleviewall.recycleviewlistview.MainActivity1;
import com.example.p010_recycleviewall.recycleviewlistviewaddheadandfooter.MainActivity3;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.ShopIndexActivity;
import com.example.p010_recycleviewall.tablayout.fragmentviewpager.MainActivityTabLayout;

public class MainActivity extends AppCompatActivity {


    private static final String APP_ID = "App_id";
    private AlipayClient alipayClient;
    private static final String APP_PRIVATE_KEY = "密钥";
    private static final String APP_PUBLIC_KEY = "支付宝公钥";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_vis(ConstantNetUtil.SERVER_CHANGE_B, ConstantNetUtil.SERVER_CHANGE_B1, ConstantNetUtil.SERVER_CHANGE_B2,
                ConstantNetUtil.SERVER_CHANGE_B3, ConstantNetUtil.SERVER_CHANGE_B4, ConstantNetUtil.SERVER_CHANGE_B5,
                ConstantNetUtil.SERVER_CHANGE_B6);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityTabLayout.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopIndexActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityBiaoge.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityDff.class);
                startActivity(intent);
            }
        });
        //支付宝扫码支付bufen
        alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "GBK", APP_PUBLIC_KEY);
        findViewById(R.id.tv7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
                request.setBizContent("{" +
                        "    \"out_trade_no\":\"20150320010101002\"," +
                        "    \"total_amount\":88.88," +
                        "    \"subject\":\"Iphone6 16G\"," +
                        "    \"store_id\":\"NJ_001\"," +
                        "    \"timeout_express\":\"90m\"," +
                        "  }");//设置业务参数
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            AlipayTradePrecreateResponse response = alipayClient.execute(request);
                            System.out.println("结果=" + response.getQrCode());

                        } catch (AlipayApiException e) {
                            System.out.println("进入异常");
                            Log.e("1", "Exception: " + Log.getStackTraceString(e));
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


    /**
     * @param b
     * @param b1
     * @param b2
     * @param b3
     * @param b4
     * @param b5
     * @param b6
     */
    private void set_vis(boolean b, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6) {
        if (b) {
            findViewById(R.id.tv1).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv1).setVisibility(View.GONE);
        }
        if (b1) {
            findViewById(R.id.tv2).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv2).setVisibility(View.GONE);
        }
        if (b2) {
            findViewById(R.id.tv3).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv3).setVisibility(View.GONE);
        }
        if (b3) {
            findViewById(R.id.tv4).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv4).setVisibility(View.GONE);
        }
        if (b4) {
            findViewById(R.id.tv5).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv5).setVisibility(View.GONE);
        }
        if (b5) {
            findViewById(R.id.tv6).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv6).setVisibility(View.GONE);
        }
        if (b6) {
            findViewById(R.id.tv7).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tv7).setVisibility(View.GONE);
        }
    }

}
