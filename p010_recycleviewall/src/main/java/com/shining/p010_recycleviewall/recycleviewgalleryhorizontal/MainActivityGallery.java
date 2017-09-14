package com.shining.p010_recycleviewall.recycleviewgalleryhorizontal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shining.p010_recycleviewall.R;
import com.shining.p010_recycleviewall.domain.Biaoge_listBean;
import com.shining.p010_recycleviewall.recycleviewgalleryhorizontal.galleryutils.GalleryLayoutManager;
import com.shining.p010_recycleviewall.recycleviewgalleryhorizontal.galleryutils.ScaleTransformer;
import com.shining.p010_recycleviewall.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shining on 2017/9/8.
 */

public class MainActivityGallery extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private RecycleAdapterGalleryHorizontal mAdapter1;
    private List<Biaoge_listBean> mList1;
    private GalleryLayoutManager galleryLayoutManager;
    private TextView tv_left;
    private TextView tv_right;
    private LinearLayoutManager mLinearLayoutManager1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gallery);
        findView();
        addlisteners();
        Data1();
    }

    private void findView() {
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);
        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);

        mAdapter1 = new RecycleAdapterGalleryHorizontal(this);
        galleryLayoutManager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        galleryLayoutManager.attach(recyclerView1, 3);
        galleryLayoutManager.setItemTransformer(new ScaleTransformer());//这个参数控制图片显示大小
        galleryLayoutManager.setOnItemSelectedListener(new GalleryLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(RecyclerView recyclerView, View item, int position) {
                Biaoge_listBean cureentBean = mList1.get(position);
//                refreshUi(cureentBean);
                ToastUtil.showToastShort(cureentBean.getText_content());
            }
        });
        recyclerView1.setAdapter(mAdapter1);

    }

    private void addlisteners() {
        mAdapter1.setOnItemiv1ClickLitener(new RecycleAdapterGalleryHorizontal.OnItemiv1ClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                recyclerView1.smoothScrollToPosition(position);
                galleryLayoutManager.smoothScrollToPosition(recyclerView1, null, position);

            }
        });
        //左
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (galleryLayoutManager.getCurSelectedPosition() == 0) {

                    return;
                }
//                recyclerView1.smoothScrollToPosition(galleryLayoutManager.getCurSelectedPosition() - 1);
                galleryLayoutManager.smoothScrollToPosition(recyclerView1, null, galleryLayoutManager.getCurSelectedPosition() - 1);
            }
        });
        //右
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (galleryLayoutManager.getCurSelectedPosition() == mAdapter1.getItemCount() - 1) {

                    return;
                }
//                recyclerView1.smoothScrollToPosition(galleryLayoutManager.getCurSelectedPosition() + 1);
                galleryLayoutManager.smoothScrollToPosition(recyclerView1, null, galleryLayoutManager.getCurSelectedPosition() + 1);
            }
        });
    }

    private void Data1() {
        mList1 = new ArrayList<Biaoge_listBean>();
        mList1.add(new Biaoge_listBean("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2440056051,1814893398&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=971168677,3745489943&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=482734402,4246398199&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4178157609,1852525503&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3406177486,551831020&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1564225817,2214582224&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=828563509,129320750&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=971168677,3745489943&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=482734402,4246398199&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4178157609,1852525503&fm=27&gp=0.jpg"));
        mList1.add(new Biaoge_listBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3406177486,551831020&fm=27&gp=0.jpg"));

        if (mList1 != null && mList1.size() > 0) {
            mAdapter1.setContacts(mList1);
            mAdapter1.notifyDataSetChanged();
//            recyclerView1.smoothScrollToPosition(galleryLayoutManager.getCurSelectedPosition() + 4);
        }
    }

}
