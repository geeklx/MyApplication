package com.example.p010_recycleviewall.bluetoothold;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.bluetoothold.bluecommon.bean.BlueDevice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * author:geek
 * modification:2016年4月26日14:21:48
 */

public class RecycleAdapterbluetooth1 extends RecyclerView.Adapter<RecycleAdapterbluetooth1.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<BlueDevice> mratings;

    public RecycleAdapterbluetooth1(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<>();
    }

    public void setContacts(List<BlueDevice> ratings) {
        this.mratings = ratings;
        List<BlueDevice> list1 = new ArrayList<>();
        list1 = removeDuplicateWithOrder(mratings);
        mratings = list1;
    }

    public void addConstacts(List<BlueDevice> ratings) {
        this.mratings.addAll(ratings);
        List<BlueDevice> list1 = new ArrayList<>();
        list1 = removeDuplicateWithOrder(mratings);
        mratings = list1;
    }

    public List<BlueDevice> removeDuplicateWithOrder(List<BlueDevice> list) {
        Set<BlueDevice> set = new HashSet<>();
        List<BlueDevice> newList = new ArrayList<>();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            BlueDevice element = (BlueDevice) iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }

    public List<BlueDevice> getMratings() {
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
        View view = inflater.inflate(R.layout.activity_main_recyclelistview_bluetooth_item_old, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tv_content_tag1 = (TextView) view.findViewById(R.id.tv_content_tag1);
        viewHolder.tv_content_tag2 = (TextView) view.findViewById(R.id.tv_content_tag2);
        viewHolder.tv_content_tag3 = (TextView) view.findViewById(R.id.tv_content_tag3);
        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final BlueDevice ratings = mratings.get(position);
        viewHolder.tv_content_tag1.setText("图标：" + ratings.getDevice().getType());
        viewHolder.tv_content_tag2.setText(ratings.getName());
        viewHolder.tv_content_tag3.setText(ratings.getStatus());
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position, ratings.getType());
                }
            });
        }
        //如果设置了回调，则设置点击事件2
        if (mOnLongItemClickLitener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnLongItemClickLitener.onLongItemClick(viewHolder.itemView, position);
                    return false;
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content_tag1;//
        private TextView tv_content_tag2;//
        private TextView tv_content_tag3;//

        ViewHolder(View view) {
            super(view);
        }
    }

    /**
     * ItemClick的回调接口
     *
     * @author geek
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position, int setType);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * ItemClick的回调接口2
     *
     * @author geek
     */
    public interface OnLongItemClickLitener {
        void onLongItemClick(View view, int position);
    }

    private OnLongItemClickLitener mOnLongItemClickLitener;

    public void setOnLongItemClickLitener(OnLongItemClickLitener mOnLongItemClickLitener) {
        this.mOnLongItemClickLitener = mOnLongItemClickLitener;
    }
}