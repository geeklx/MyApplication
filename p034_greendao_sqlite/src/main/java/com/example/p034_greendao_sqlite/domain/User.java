package com.example.p034_greendao_sqlite.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by shining on 2017/8/29.
 */

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String age;
    private String sex;
    private String salary;
    public String getSalary() {
        return this.salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1281958717)
    public User(Long id, String name, String age, String sex, String salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
