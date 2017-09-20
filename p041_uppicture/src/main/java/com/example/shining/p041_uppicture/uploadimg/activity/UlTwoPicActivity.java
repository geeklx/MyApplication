package com.example.shining.p041_uppicture.uploadimg.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.shining.p041_uppicture.R;
import com.example.shining.p041_uppicture.uploadimg.adapter.UlAlbumHelper;
import com.example.shining.p041_uppicture.uploadimg.adapter.UlImageGridAdapter;
import com.example.shining.p041_uppicture.uploadimg.bitmap.UlBimp;
import com.example.shining.p041_uppicture.uploadimg.domain.UlImageItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UlTwoPicActivity extends Activity {
    public static final String EXTRA_IMAGE_LIST = "imagelist";

    // ArrayList<Entity> dataList;//用来装载数据源的列表
    List<UlImageItem> dataList;
    GridView gridView;
    UlImageGridAdapter adapter;// 自定义的适配器
    UlAlbumHelper helper;
    Button bt;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(UlTwoPicActivity.this, "最多选择9张图片", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_uploading_image_grid);

        helper = UlAlbumHelper.getHelper();
        helper.init(getApplicationContext());

        dataList = (List<UlImageItem>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);

        initView();
        // initTitleBarWithStringBtn(getString(R.string.album), null);

		/*
         * View view = findViewById(R.id.image_back); if (view != null) {
		 * view.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { onBackPressed(); } }); }
		 */

        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                Collection<String> c = adapter.map.values();
                Iterator<String> it = c.iterator();
                for (; it.hasNext(); ) {
                    list.add(it.next());
                }

                if (UlBimp.act_bool) {
                    // Intent intent = new Intent(UlTwoPicActivity.this,
                    // MainActivity.class);
                    // startActivity(intent);
                    Intent data = new Intent();
                    setResult(RESULT_OK, data);

                    UlBimp.act_bool = false;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (UlBimp.drr.size() < 9) {
                        UlBimp.drr.add(list.get(i));
                        String path = UlBimp.drr.get(UlBimp.drr.size() - 1);
                        System.out.println(path);
                        try {
                            Bitmap bm = UlBimp.revitionImageSize(path);
                            UlBimp.bmp.add(bm);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }
                finish();
            }
        });
    }

    /**
     * 初始化view视图
     */
    private void initView() {
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new UlImageGridAdapter(UlTwoPicActivity.this, dataList, mHandler);
        gridView.setAdapter(adapter);
        adapter.setTextCallback(new UlImageGridAdapter.TextCallback() {
            public void onListen(int count) {
                bt.setText("完成" + "(" + count + ")");
            }
        });

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // if(dataList.get(position).isSelected()){
                // dataList.get(position).setSelected(false);
                // }else{
                // dataList.get(position).setSelected(true);
                // }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
