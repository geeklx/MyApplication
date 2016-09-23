package com.example.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/4/6.
 */
public class TagBeanNew implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tag_id;
    private String tag_name;

    public TagBeanNew() {
    }

    public TagBeanNew(String tag_id, String tag_name) {
        this.tag_id = tag_id;
        this.tag_name = tag_name;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
