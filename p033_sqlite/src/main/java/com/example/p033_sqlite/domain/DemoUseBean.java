package com.example.p033_sqlite.domain;


import com.example.opendroidlibrary.db.OpenDroid;

/**
 * Created by geek on 2016/2/25.
 */
public class DemoUseBean extends OpenDroid {
    private int text_id;
    private String text_content;

    public DemoUseBean() {
    }

    public DemoUseBean(int text_id, String text_content) {
        this.text_id = text_id;
        this.text_content = text_content;
    }

    public int getText_id() {
        return text_id;
    }

    public void setText_id(int text_id) {
        this.text_id = text_id;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }
}
