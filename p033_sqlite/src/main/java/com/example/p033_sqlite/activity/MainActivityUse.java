package com.example.p033_sqlite.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.opendroidlibrary.db.OpenDroid;
import com.example.p033_sqlite.R;
import com.example.p033_sqlite.domain.DemoUseBean;

import java.util.List;

public class MainActivityUse extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv00;
    private TextView tv0;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainuser);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv00 = (TextView) findViewById(R.id.tv00);
        tv0 = (TextView) findViewById(R.id.tv0);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv00.setOnClickListener(this);
        tv0.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);

    }

    private void get_data() {
        String a = "";
        List<DemoUseBean> result1 = OpenDroid.query.find(DemoUseBean.class);
        for (DemoUseBean res : result1) {
            System.out.println(res.getText_content());
            a = a + res.getText_content() + "\n\n";
        }
        tv1.setText(a);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv00:
                delete_all_data();
                for (int i = 0; i < 10; i++) {
                    DemoUseBean stu = new DemoUseBean();
                    stu.setText_id(i);
                    stu.setText_content("opendroid" + i);
                    stu.save();
                }
                get_data();
                break;
            case R.id.tv0:
                select_data1();
                get_data();
                break;
            case R.id.tv2:
                insert_data();
                get_data();
                break;
            case R.id.tv3:
                update_data();
//                update_data_contentvalue();
                get_data();
                break;
            case R.id.tv4:
//                delete_data();
                delete_which_data();
                get_data();
                break;
            default:
                break;
        }
    }

    private void update_data_contentvalue() {
        /**
         * step 6 : 使用ContentValues更新
         */
        ContentValues cv = new ContentValues();
        cv.put("9", "geekcontentvalue");
        OpenDroid.update(DemoUseBean.class, cv, "_id=?", "9");

        DemoUseBean result = OpenDroid.query.find(DemoUseBean.class, 9);
//        System.out.println(result.getText_content());
    }

    private void insert_data() {
        /**
         * step 1 : 插入一条数据， 查询一条数据
         */
        DemoUseBean stu = new DemoUseBean();
        stu.setText_content("geek100");
        stu.setText_id(100);
        stu.save();

        List<DemoUseBean> result = OpenDroid.query.where("text_id=?", "100").limit(1).find(DemoUseBean.class);
        System.out.println();//result.getText_content()
    }

    private void update_data() {
        /**
         * step 5 : 更新数据
         */
        DemoUseBean stu = new DemoUseBean();
        stu.setText_content("geek9");
        stu.setText_id(9);
        stu.update("text_id=?", "9");

        List<DemoUseBean> result = OpenDroid.query.find(DemoUseBean.class);
        for (DemoUseBean res : result) {
            System.out.println(res.getText_content());
        }
    }

    private void select_data1() {
        /**
         * step 2 : 查询所有记录
         */

        List<DemoUseBean> result = OpenDroid.query.find(DemoUseBean.class);
        for (DemoUseBean res : result) {
            System.out.println(res.getText_content());

        }
    }

    private void select_datas() {
        /**
         * step 3 : 查询多条记录
         */
        List<DemoUseBean> result = OpenDroid.query.find(DemoUseBean.class, 1, 5, 10);
        for (DemoUseBean res : result) {
            System.out.println(res.getId() + " : " + res.getText_content());
        }
    }

    private void select_which_data() {
        /**
         * step 4 : 条件查询
         */
        List<DemoUseBean> result = OpenDroid.query.columns("stuName", "stuAge")
                .where("_id>?", "5").order("_id DESC").limit(3)
                .find(DemoUseBean.class);
        for (DemoUseBean res : result) {
            System.out.println(res.getText_content() + " : " + res.getText_id());
        }
    }

    private void delete_data() {
        /**
         * step 7 : 特定删除
         */
        int length = OpenDroid.delete(DemoUseBean.class, 9);
//        int length = OpenDroid.delete(DemoUseBean.class, 1, 2, 3);
        System.out.println(length);
    }


    private void delete_which_data() {

        /**
         * step 8 : 使用条件删除
         */
        int length = OpenDroid.delete(DemoUseBean.class, "text_id=?", "9");
//        int length = OpenDroid.delete(DemoUseBean.class, "_id>?", "2");
        System.out.println(length);
    }

    private void delete_all_data() {
        /**
         * step 8 : 使用条件删除
         */
        int length = OpenDroid.delete(DemoUseBean.class, "_id>?", "-1");
        System.out.println(length);
    }


}
