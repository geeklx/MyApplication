package com.example.p034_greendao_sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p034_greendao_sqlite.application.DemoApplication;
import com.example.p034_greendao_sqlite.domain.User;
import com.example.p034_greendao_sqlite.greendao.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv0;
    private TextView tv_setting;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private UserDao mUserDao;

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

        mUserDao = DemoApplication.get().getDaoSession().getUserDao();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting:
                //重置
                deleteAllData();
                for (int i = 0; i < 10; i++) {
                    User stu = new User();
                    stu.setId((long) i);
                    stu.setName("greendao" + i);
                    stu.setAge(i + "");
                    stu.setSex("男" + i);
                    stu.setSalary(i + "" + i);
                    mUserDao.insert(stu);
                }
                queryData();
                break;
            case R.id.tv1:
                //查询
                queryData();
                break;
            case R.id.tv2:
                //添加
                insertData(1001);
                queryData();
                break;
            case R.id.tv3:
                //更新
                updateData(1001);
                queryData();
                break;
            case R.id.tv4:
                //删除
                deleteData(1001);
                queryData();
                break;
            default:
                break;
        }
    }


    //查
    private void queryData() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + "\n\n";
        }
        tv0.setText(userName);
//        Toast.makeText(this, "查询全部数据==>" + userName, Toast.LENGTH_SHORT).show();
    }

    //增
    private void insertData(int i) {
        User insertData = new User(null, i + "", "geek", "100", "男", "100");
        mUserDao.insert(insertData);
    }

    //改
    private void updateData(int i) {
//        User updateData = new User(null, i + "", "geek1", "101", "女", "101");
//        User updateData = new User((long) i, "geek1", "101", "女", "101");
//        mUserDao.update(updateData);

        User user = mUserDao.queryBuilder()
                .where(UserDao.Properties.Userid.eq(i + "")/*, UserDao.Properties.Name.like("%90%")*/).build().unique();
        if (user == null) {
            Toast.makeText(MainActivity.this, "用户不存在!", Toast.LENGTH_SHORT).show();
        } else {
            user.setUserid(i + "");
            user.setName("geek1");
            user.setAge("101");
            user.setSex("女");
            user.setSalary("101");
            mUserDao.update(user);
        }
    }

    //删

    private void deleteData(int id) {
        User user = mUserDao.queryBuilder().where(UserDao.Properties.Userid.eq(id + "")).build().unique();
        if (user == null) {
            Toast.makeText(MainActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
        } else {
            mUserDao.deleteByKey(user.getId());
        }
//        User updateData = new User((long) i, "geek1", "101", "女", "101");
//        mUserDao.deleteByKey((long) id);
    }

    //删全部
    private void deleteAllData() {
        mUserDao.deleteAll();
    }
}
