package com.example.shining.p009_glide411;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.shining.p009_glide411.glide.options.GlideOptions;
import com.example.shining.p009_glide411.glide.options.GlideOptionsFactory;
import com.example.shining.p009_glide411.glide.options.GlideUtil;
import com.example.shining.p009_glide411.glide.transformation.GlideCircleTransform;
import com.example.shining.p009_glide411.glide.transformation.GlideRoundTransform;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_comm1;
    private ImageView iv_comm2;
    private ImageView iv_comm22;
    private ImageView iv_comm3;
    private ImageView iv_comm4;
    private ImageView iv_comm5;

    private String url = "http://short.im.rockhippo.cn/uploads/msg/201703/20170309/1485/1489068660846.jpg";
    private String url1 = "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg";
    private String url2 = "http://img0.bdstatic.com/img/image/touxiang01.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        common_user();

        iv_comm3 = (ImageView) findViewById(R.id.iv_comm3);
        iv_comm4 = (ImageView) findViewById(R.id.iv_comm4);
        iv_comm5 = (ImageView) findViewById(R.id.iv_comm5);
        //3
        GlideUtil.display(MainActivity.this, iv_comm3, url2, GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        //4
        GlideOptions glideOptions = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 1000);
        GlideUtil.display(MainActivity.this, iv_comm4, url2, glideOptions);
//        Glide.with(context).load(ratings.getSku_image()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.iv_imgurl);
        //5
        GlideUtil.display(MainActivity.this, iv_comm5, url2);
    }

    private void common_user() {
        //common
        iv_comm1 = (ImageView) findViewById(R.id.iv_comm1);
        iv_comm2 = (ImageView) findViewById(R.id.iv_comm2);
        iv_comm22 = (ImageView) findViewById(R.id.iv_comm22);

        Glide.with(this).load(url2).into(iv_comm1);
        // Glide.with(this).clear(iv_comm1);
        //只在拖动和静止时加载，自动滑动时不加载。
        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Glide.with(MainActivityGlide.this).resumeRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Glide.with(MainActivityGlide.this).pauseRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Glide.with(MainActivityGlide.this).resumeRequests();
                        break;
                }
            }
        });*/
        //自定义
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform());
        Glide.with(this).load(url2).apply(options).into(iv_comm2);

        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransform());
        Glide.with(this).load(url2).apply(options2).into(iv_comm22);
    }
}
