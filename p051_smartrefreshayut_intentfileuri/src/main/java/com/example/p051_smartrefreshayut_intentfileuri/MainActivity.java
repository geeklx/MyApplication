package com.example.p051_smartrefreshayut_intentfileuri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static boolean isFirstEnter = true;
    private List<Collection<Model>> mlist = new ArrayList<>();


    @Override
    protected void onPause() {
        super.onPause();
//        startActivity(new Intent("com.demo2"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setlayout1();
//        setlayout2();
        findViewById(R.id.tv_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.demo3"));
            }
        });
    }

    private void setlayout1() {
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        final ClassicsFooter classicsFooter = (ClassicsFooter) findViewById(R.id.classfooter);
        //第一次进入演示刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            refreshLayout.autoRefresh();
        }
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAdapter.loadmore(loadModels());
                        mlist.add(loadModels());
                        if (mlist.size() > 3) {
                            Toast.makeText(getBaseContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.finishLoadmoreWithNoMoreData();//设置之后，将不会再触发加载事件
                        } else {
                            refreshlayout.finishLoadmore();
                        }
                    }
                }, 1000);
            }
        });
    }

    private void setlayout2() {
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableAutoLoadmore(false);//开启自动加载功能（非必须）
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAdapter.refresh(initData());
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAdapter.loadmore(initData());
                        mlist.add(loadModels());
                        if (mlist.size() > 4) {
                            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.finishLoadmoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
                            refreshlayout.finishLoadmore();
                        }
                    }
                }, 2000);
            }
        });

        //触发自动刷新
        refreshLayout.autoRefresh();
    }


    private class Model {
        int imageId;
        int avatarId;
        String name;
        String nickname;
    }


    /**
     * 模拟数据
     */
    private Collection<Model> loadModels() {
        return Arrays.asList(
                new Model() {{
                    this.name = "但家香酥鸭";
                    this.nickname = "爱过那张脸";
//                    this.imageId = R.mipmap.image_practice_repast_1;
//                    this.avatarId = R.mipmap.image_avatar_1;
                }}, new Model() {{
                    this.name = "香菇蒸鸟蛋";
                    this.nickname = "淑女算个鸟";
//                    this.imageId = R.mipmap.image_practice_repast_2;
//                    this.avatarId = R.mipmap.image_avatar_2;
                }}, new Model() {{
                    this.name = "花溪牛肉粉";
                    this.nickname = "性感妩媚";
//                    this.imageId = R.mipmap.image_practice_repast_3;
//                    this.avatarId = R.mipmap.image_avatar_3;
                }}, new Model() {{
                    this.name = "破酥包";
                    this.nickname = "一丝丝纯真";
//                    this.imageId = R.mipmap.image_practice_repast_4;
//                    this.avatarId = R.mipmap.image_avatar_4;
                }}, new Model() {{
                    this.name = "盐菜饭";
                    this.nickname = "等着你回来";
//                    this.imageId = R.mipmap.image_practice_repast_5;
//                    this.avatarId = R.mipmap.image_avatar_5;
                }}, new Model() {{
                    this.name = "米豆腐";
                    this.nickname = "宝宝树人";
//                    this.imageId = R.mipmap.image_practice_repast_6;
//                    this.avatarId = R.mipmap.image_avatar_6;
                }});
    }
}
