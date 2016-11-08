package com.example.p015_recycleview_gundong_vertical.recyclerviewsnap;


public class App {

    private int mDrawable;
    private String mName;
    private float mRating;

    public App(String name, int drawable, float rating) {
        mName = name;
        mDrawable = drawable;
        mRating = rating;
    }

    public float getRating() {
        return mRating;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }
}

