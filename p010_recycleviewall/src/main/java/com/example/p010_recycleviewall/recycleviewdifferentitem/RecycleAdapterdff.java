package com.example.p010_recycleviewall.recycleviewdifferentitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.domain.Biaoge_listBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author:geek
 * modification:2016年4月26日14:21:48
 */

public class RecycleAdapterdff extends RecyclerView.Adapter<RecycleAdapterdff.CommHolder> {
    private static final int TAG_ONE = 1;
    private static final int TAG_TWO = 2;
    private LayoutInflater inflater;
    private Context context;
    private List<Biaoge_listBean> mratings;


    public RecycleAdapterdff(Context context) {
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
    public int getItemViewType(int position) {
        return getItemType(position, mratings);
    }

    public int getItemType(int position, List<Biaoge_listBean> mratingss) {
        if (mratingss.size() == 1) {
            return TAG_ONE;
        } else {
            return TAG_TWO;
        }
    }

    @Override
    public CommHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommHolder.getHolder(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(final CommHolder viewHolder, final int position) {
        final Biaoge_listBean item = mratings.get(position);
        int viewType = getItemType(position, mratings);
        onBind(viewHolder, position, viewType, item);
    }

    private void onBind(CommHolder viewHolder, int position, int viewType, Biaoge_listBean item) {
        set_itemview_findviewbyid(viewHolder, viewType, item);
    }

    private void set_itemview_findviewbyid(CommHolder viewHolder, int viewType, Biaoge_listBean item) {
        switch (viewType) {
            case TAG_ONE:
                TextView tv_content_tag10 = viewHolder.getView(R.id.tv_content_tag10);
                TextView tv_content_tag20 = viewHolder.getView(R.id.tv_content_tag20);
                tv_content_tag10.setText(item.getText_content());
                tv_content_tag20.setText(item.getText_content());
                break;
            case TAG_TWO:
                TextView tv_content_tag11 = viewHolder.getView(R.id.tv_content_tag11);
                TextView tv_content_tag22 = viewHolder.getView(R.id.tv_content_tag22);
                tv_content_tag11.setText(item.getText_content());
                tv_content_tag22.setText(item.getText_content());
                break;
            default:
                break;
        }
    }

    public int getLayoutId(int itemType) {
        switch (itemType) {
            case TAG_ONE:
                return R.layout.tag1_item;//
            case TAG_TWO:
                return R.layout.tag2_item;//
            default:
                return -1;
        }
    }

    public static class CommHolder extends RecyclerView.ViewHolder {
        private View mItemView;
        private SparseArray<View> mViews;

        private CommHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mViews = new SparseArray<>();
        }

        public <T extends View> T getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = mItemView.findViewById(id);
                mViews.put(id, view);
            }
            return (T) view;
        }

        public static CommHolder getHolder(ViewGroup parent, int layoutId) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new CommHolder(layout);
        }
    }
}