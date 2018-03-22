package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Biaoge_listBean2 implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String text_content;

    public Biaoge_listBean2() {
    }

    public Biaoge_listBean2(int id, String text_content) {
        this.id = id;
        this.text_content = text_content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }
}
