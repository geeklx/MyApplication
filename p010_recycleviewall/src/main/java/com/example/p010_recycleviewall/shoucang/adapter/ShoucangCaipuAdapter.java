package com.example.p010_recycleviewall.shoucang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.p010_recycleviewall.R;
import com.example.p010_recycleviewall.net.glide.GlideOptions;
import com.example.p010_recycleviewall.net.glide.GlideUtil;
import com.example.p010_recycleviewall.shoucang.domain.ShoucangCaipuIndexModel;

import java.util.ArrayList;
import java.util.List;

/**
 * shining
 * 2017年2月28日12:35:33
 */

public class ShoucangCaipuAdapter extends RecyclerView.Adapter<ShoucangCaipuAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<ShoucangCaipuIndexModel> mratings;

    public ShoucangCaipuAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<ShoucangCaipuIndexModel>();
    }

    public void setContacts(List<ShoucangCaipuIndexModel> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<ShoucangCaipuIndexModel> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<ShoucangCaipuIndexModel> getMratings() {
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
        View view = inflater.inflate(R.layout.activity_shoucang_shangpin_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_img = (ImageView) view.findViewById(R.id.iv_img);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        ShoucangCaipuIndexModel ratings = mratings.get(position);
        GlideOptions glideOptions = new GlideOptions(R.drawable.no_fridge_food, R.drawable.no_fridge_food, 0);
        GlideUtil.display(context, viewHolder.iv_img, ratings.getImgURL(), glideOptions);//GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT)

        viewHolder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //如果设置了回调，则设置点击事件
//        if (mOnItemClickLitener != null) {
//            viewHolder.thethree_ll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
//                }
//            });
//        }


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;//imgbufen

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