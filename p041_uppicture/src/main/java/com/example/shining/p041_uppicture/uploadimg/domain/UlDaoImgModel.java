package com.example.shining.p041_uppicture.uploadimg.domain;

import android.graphics.Bitmap;

public class UlDaoImgModel {

    private int imgid;
    private String imgcontent;
    private String imgpath;
    private Bitmap imgbitmap;

    public UlDaoImgModel() {
    }

    public UlDaoImgModel(int imgid, String imgcontent, String imgpath, Bitmap imgbitmap) {
        this.imgid = imgid;
        this.imgcontent = imgcontent;
        this.imgpath = imgpath;
        this.imgbitmap = imgbitmap;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getImgcontent() {
        return imgcontent;
    }

    public void setImgcontent(String imgcontent) {
        this.imgcontent = imgcontent;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Bitmap getImgbitmap() {
        return imgbitmap;
    }

    public void setImgbitmap(Bitmap imgbitmap) {
        this.imgbitmap = imgbitmap;
    }


}
