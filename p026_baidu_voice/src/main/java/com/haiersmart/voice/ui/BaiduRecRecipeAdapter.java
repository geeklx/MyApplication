///**
// * Copyright 2016,Smart Haier.All rights reserved
// * Description:
// * Author:jiayuzhen
// * ModifyBy:
// * ModifyDate:
// * ModifyDes:
// */
//package com.haiersmart.voice.ui;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.haiersmart.sfnation.R;
//import com.haiersmart.voice.bean.foodRec.BaiduRecipe;
//
//import java.util.List;
//
///**
// * Created by yuzhen on 2017/2/20.
// *@time 2017/2/20  9:58
// */
//public class BaiduRecRecipeAdapter extends BaseAdapter {
//    List<BaiduRecipe> mList;
//    public Context mContext;
//    public BaiduRecRecipeAdapter(Context context,List<BaiduRecipe> mList){
//        mContext = context;
//        this.mList = mList;
//    }
//    public void setRecipeList(List<BaiduRecipe> mList){
//        this.mList = mList;
//    }
//    @Override
//    public int getCount() {
//        return null== mList? 0:mList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder mHolder;
//        View view = convertView;
//        if (view == null) {
//            view = LayoutInflater.from(mContext).inflate(R.layout.item_baidurec_recipe,null);
//            mHolder = new ViewHolder();
//            mHolder.recipeName=(TextView)view.findViewById(R.id.tv_baidu_recipe_name);
//            mHolder.recipePic = (ImageView) view.findViewById(R.id.iv_baidu_recipe_picture);
//            view.setTag(mHolder);
//        } else {
//            mHolder = (ViewHolder) view.getTag();
//        }
//        mHolder.recipeName.setText(mList.get(position).getRecipeName());
//        Glide.with(mContext).load(mList.get(position).getRecipeImgUrl()).into(mHolder.recipePic);
//        return view;
//    }
//
//
//    class ViewHolder{
//        ImageView recipePic;
//        TextView recipeName;
//    }
//}
