package com.example.shining.p048_sinavideoplay;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:geek
 * modification:2016年4月26日14:21:48
 */

public class RecycleAdapter2 extends RecyclerView.Adapter<RecycleAdapter2.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Biaoge_listBean> mratings;

    public RecycleAdapter2(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<Biaoge_listBean>();
    }

    public void setContacts(List<Biaoge_listBean> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<Biaoge_listBean> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<Biaoge_listBean> getMratings() {
        return mratings;
    }

    @Override
    public int getItemCount() {
        if (mratings == null)
            return 0;
        return mratings.size();
    }

    public Object getItem(int position) {
        return mratings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleviewbiaoge_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_content_tag1 = (TextView) view.findViewById(R.id.tv_content_tag1);
        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Biaoge_listBean ratings = mratings.get(position);
        viewHolder.tv_content_tag1.setText(ratings.getText_content());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content_tag1;//

        ViewHolder(View view) {
            super(view);
        }
    }
}