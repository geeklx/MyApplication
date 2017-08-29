package com.example.p035_sugar_sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.p035_sugar_sqlite.domain.DemoBean;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv0;
    private TextView tv_setting;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv0 = (TextView) findViewById(R.id.tv0);
        tv_setting = (TextView) findViewById(R.id.tv_setting);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv_setting.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);

//        //find
//        List<Book> booklist=Book.find(Book.class, "number=?", "510110");
//        Log.i("sugertest","booklist"+booklist.size());
//        //findWithQuery
//        List<Book> books=Book.findWithQuery(Book.class,"Select * from Book where number=?","java");
//        Log.i("sugertest","books"+books.size());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting:
                //重置
                deleteAllData();
                for (int i = 0; i < 10; i++) {
                    DemoBean stu = new DemoBean();
//                    stu.text_id = i;
//                    stu.text_content1 = "sugar" + i;
//                    stu.text_content2 = "sugars" + i;

                    stu.setTextid(i);
                    stu.setTextcontent1("sugar" + i);
                    stu.setTextcontent2("sugars" + i);

                    stu.save(stu);
                }
                select_data();
                break;
            case R.id.tv1:
                //查询
                select_data();
                break;
            case R.id.tv2:
                //添加
                add_data();
                select_data();
                break;
            case R.id.tv3:
                //更新
                update_data();
                select_data();
                break;
            case R.id.tv4:
                //删除
                delete_data();
                select_data();
                break;
            default:
                break;
        }
    }

    private void select_data() {
        //查询一条数据
//        List<DemoBean> users = DemoBean.find(DemoBean.class,"text_id>?","-1");
        List<DemoBean> users = DemoBean.listAll(DemoBean.class);
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getId() + "      " +
                    users.get(i).getTextid() + "       " +
                    users.get(i).getTextcontent1() + "     " +
                    users.get(i).getTextcontent2() + "\n\n";
        }
        tv0.setText(userName);
//        DemoBean queryBook = DemoBean.findById(DemoBean.class, 1);
//        Log.i("sugertest", "queryBook" + queryBook.toString());
    }

    private void add_data() {
        //增加一条数据
        DemoBean book = new DemoBean();
        book.setTextid(101);
        book.setTextcontent1("geek100");
        book.setTextcontent2("geek200");
        long rec = book.save();
//        Log.i("sugertest", "rec" + String.valueOf(rec));    //rec=2
    }

    private void update_data() {
        //更新一条数据
//        List<DemoBean> book1 = DemoBean.find(DemoBean.class, "text_id=?", "101");
        List<DemoBean> book1=DemoBean.findWithQuery(DemoBean.class,"Select * from Demo_Bean where textid=?","9");
        book1.get(0).setTextid(9);
        book1.get(0).setTextcontent1("geek109");
        book1.get(0).setTextcontent2("geek109");
        book1.get(0).save();
    }

    private void delete_data() {
        //删除一条数据
//        DemoBean book2 = DemoBean.findById(DemoBean.class, 101);
        List<DemoBean> book2 = DemoBean.find(DemoBean.class, "textid=?", "101");
        book2.get(0).delete();
    }

    //删全部
    private void deleteAllData() {
        DemoBean.deleteAll(DemoBean.class);
    }

}
