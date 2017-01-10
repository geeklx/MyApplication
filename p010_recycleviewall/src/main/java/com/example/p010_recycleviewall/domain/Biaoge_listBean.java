package com.example.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Biaoge_listBean implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String text_content;

    public Biaoge_listBean() {
    }

    public Biaoge_listBean(String text_content) {
        this.text_content = text_content;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }
}
