package com.shining.p010_recycleviewall.tablayout.fragmentviewpager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shining.p010_recycleviewall.domain.TabLayoutcontentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author:geek
 * modification:2016年4月26日14:21:48
 */

public class RecycleViewAdapter1 extends RecyclerView.Adapter<RecycleViewAdapter1.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<TabLayoutcontentBean> mratings;

    public RecycleViewAdapter1(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<TabLayoutcontentBean>();
    }

    public void setContacts(List<TabLayoutcontentBean> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<TabLayoutcontentBean> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<TabLayoutcontentBean> getMratings() {
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
        View view = inflater.inflate(com.shining.p010_recycleviewall.R.layout.fragment_a_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_content = (TextView) view.findViewById(com.shining.p010_recycleviewall.R.id.tv_content);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final TabLayoutcontentBean ratings = mratings.get(position);
        viewHolder.tv_content.setText(ratings.getName());
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        ViewHolder(View view) {
            super(view);
        }
    }

    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}