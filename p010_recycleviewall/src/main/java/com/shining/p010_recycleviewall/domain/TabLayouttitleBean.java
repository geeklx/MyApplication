package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/4/6.
 */
public class TabLayouttitleBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String categoryId; //分类ID
    private String name; //标题
    private String tag;
    private boolean hasTag;
    private boolean hasSelected;// 是否选中

    public TabLayouttitleBean() {
    }

    public TabLayouttitleBean( String categoryId,String name, String tag, boolean hasTag, boolean hasSelected) {
        this.categoryId = categoryId;
        this.name = name;
        this.tag = tag;
        this.hasTag = hasTag;
        this.hasSelected = hasSelected;
    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isHasTag() {
        return hasTag;
    }

    public void setHasTag(boolean hasTag) {
        this.hasTag = hasTag;
    }

    public boolean isHasSelected() {
        return hasSelected;
    }

    public void setHasSelected(boolean hasSelected) {
        this.hasSelected = hasSelected;
    }


}
