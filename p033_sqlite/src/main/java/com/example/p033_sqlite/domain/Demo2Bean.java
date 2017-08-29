package com.example.p033_sqlite.domain;


import com.example.opendroidlibrary.db.OpenDroid;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Demo2Bean extends OpenDroid implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int text_id;
    private String text_content1;
    private String text_content2;

    public Demo2Bean() {
    }

    public Demo2Bean(int text_id, String text_content1, String text_content2) {
        this.text_id = text_id;
        this.text_content1 = text_content1;
        this.text_content2 = text_content2;
    }

    public int getText_id() {
        return text_id;
    }

    public void setText_id(int text_id) {
        this.text_id = text_id;
    }

    public String getText_content1() {
        return text_content1;
    }

    public void setText_content1(String text_content1) {
        this.text_content1 = text_content1;
    }

    public String getText_content2() {
        return text_content2;
    }

    public void setText_content2(String text_content2) {
        this.text_content2 = text_content2;
    }
}
