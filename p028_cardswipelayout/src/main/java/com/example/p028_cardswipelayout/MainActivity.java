package com.example.p028_cardswipelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p028_cardswipelayout.adapters.OnItemClickListener;
import com.example.p028_cardswipelayout.adapters.RecycleAdapter2;
import com.example.p028_cardswipelayout.domain.Biaoge_listBean;
import com.example.p028_cardswipelayout.jcenterutils.CardConfig;
import com.example.p028_cardswipelayout.jcenterutils.CardItemTouchHelperCallback;
import com.example.p028_cardswipelayout.jcenterutils.CardLayoutManager;
import com.example.p028_cardswipelayout.jcenterutils.OnSwipeListener;
import com.example.p028_cardswipelayout.jcenterutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.p028_cardswipelayout.R.id.recyclerView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private RecyclerView recyclerView1;
    private RecycleAdapter2 recycleAdapter2;

    private List<Integer> list = new ArrayList<>();

    private Toast toast;


    private List<Biaoge_listBean> mList1 = new ArrayList<>();
    private CardItemTouchHelperCallback cardItemTouchHelperCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        onclickListener();
        doNetWork();

    }

    private void doNetWork() {
        Data1();
        recycleAdapter2.setContacts(mList1);
        cardItemTouchHelperCallback.setContacts(mList1);
        recycleAdapter2.notifyDataSetChanged();
    }

    int i = 0;

    private void findview() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(this);

        recyclerView1 = (RecyclerView) findViewById(recyclerView);

        recycleAdapter2 = new RecycleAdapter2(this);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(recycleAdapter2);
        //这个方法目前只能支持nexus手机 还在想怎么办
        recyclerView1.addOnItemTouchListener(new OnItemClickListener(recyclerView1) {
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder, int position) {
//                Biaoge_listBean ratings = mList1.get(position);
//                Toast.makeText(MainActivity.this, "第" + ratings.getText_content() + "详情Long", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Biaoge_listBean ratings = mList1.get(position);
                ToastUtil.showToastShort(MainActivity.this, "第" + ratings.getText_content() + "详情");
            }
        });

        //cardswipelayout
        cardItemTouchHelperCallback = new CardItemTouchHelperCallback(recycleAdapter2, mList1);
        //设置ItemTouchHelper
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardItemTouchHelperCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView1, touchHelper);
        recyclerView1.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView1);
    }

    private void onclickListener() {
        cardItemTouchHelperCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                RecycleAdapter2.ViewHolder cardholder = (RecycleAdapter2.ViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    cardholder.iv_dislike.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    cardholder.iv_like.setAlpha(Math.abs(ratio));
                } else {
                    cardholder.iv_dislike.setAlpha(0f);
                    cardholder.iv_like.setAlpha(0f);
                }

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
//                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                RecycleAdapter2.ViewHolder cardholder = (RecycleAdapter2.ViewHolder) viewHolder;
                cardholder.itemView.setAlpha(1f);
                cardholder.iv_dislike.setAlpha(0f);
                cardholder.iv_like.setAlpha(0f);
                ToastUtil.showToastShort(MainActivity.this, direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right");
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(MainActivity.this, "data clear", Toast.LENGTH_SHORT).show();
                recyclerView1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doNetWork();
                    }
                }, 3000L);
            }
        });
        //不支持这种方法
//        recycleAdapter2.setOnItemClickLitener(new RecycleAdapter2.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //img click detail bufen
//                Biaoge_listBean ratings = mList1.get(position);
//                ToastUtil.showToastShort(MainActivity.this, "第" + ratings.getText_content() + "详情");
////                if (toast == null) {
////                    toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
////                }
////                toast.setText("第" + ratings.getText_content() + "详情");
////                toast.show();
////                Toast.makeText(MainActivity.this, "第" + ratings.getText_content() + "详情", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void Data1() {
        mList1 = new ArrayList<Biaoge_listBean>();
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_01, "小姐姐1"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_02, "小姐姐2"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_03, "小姐姐3"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_04, "小姐姐4"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_05, "小姐姐5"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_06, "小姐姐6"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_07, "小姐姐7"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
//                tv1.setOnClickListener(null);
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
//                i++;
//                ToastUtil.showToastCenter(MainActivity.this, i + "");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv1.setOnClickListener(MainActivity.this);
//                        ToastUtil.showToastCenter(MainActivity.this,"可以用了~");
//                    }
//                }, 3000);
                break;
            default:
                break;
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            String a = "";
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}
