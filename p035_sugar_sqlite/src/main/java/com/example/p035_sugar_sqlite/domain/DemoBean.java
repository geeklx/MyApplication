package com.example.p035_sugar_sqlite.domain;


import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by geek on 2017年8月29日20:44:05.
 * <p>
 * Bean的属性名所采用驼峰命名法，那么大写的字母会在创建的表中字段转换成下划线。
 * 比如spuId这个属性对应的表中的字段名为spu_id
 * 之所以实现Serializable是因为这个Bean在代码中不仅仅为SugarORM创建表而服务，
 * 同时也是为了能在Android组件中传递（比如Handler中的message.obj）而用，所以这里和官网的直接继承自SugarRecord<T>不同，推荐大家用我这种方式
 *
 * @Column(name = "textID", unique = true) 这个注解意思是说你想强制按照你的规定的名字来创建表中对应的字段名字，所以这里的skuId在Goods表中的字段名就不是默认的sku_id了，而是你自己给的sku_ID
 * @Expoes 是来自于Gson的的一个注解，后面会说到
 * @Ignore 这个注解强调这个属性在表中不要创建对应的字段
 * @SerializedName("id") 采用@SerializedName注解，重新指定id的名称
 */

public class DemoBean extends SugarRecord implements Serializable {
    @Expose
    private int textid;
    @Expose
    private String textcontent1;
    @Expose
    private String textcontent2;

    public DemoBean() {
    }

    public DemoBean(int textid, String textcontent1, String textcontent2) {
        this.textid = textid;
        this.textcontent1 = textcontent1;
        this.textcontent2 = textcontent2;
    }

    public int getTextid() {
        return textid;
    }

    public void setTextid(int textid) {
        this.textid = textid;
    }

    public String getTextcontent1() {
        return textcontent1;
    }

    public void setTextcontent1(String textcontent1) {
        this.textcontent1 = textcontent1;
    }

    public String getTextcontent2() {
        return textcontent2;
    }

    public void setTextcontent2(String textcontent2) {
        this.textcontent2 = textcontent2;
    }
}
