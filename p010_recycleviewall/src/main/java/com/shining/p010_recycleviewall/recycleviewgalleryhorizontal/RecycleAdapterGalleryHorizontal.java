package com.shining.p010_recycleviewall.recycleviewgalleryhorizontal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shining.p010_recycleviewall.R;
import com.shining.p010_recycleviewall.domain.Biaoge_listBean;
import com.shining.p010_recycleviewall.net.glide.GlideOptions;
import com.shining.p010_recycleviewall.net.glide.GlideUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RecycleAdapterGalleryHorizontal extends RecyclerView.Adapter<RecycleAdapterGalleryHorizontal.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Biaoge_listBean> mratings;
    private int selectIndex = -1;//当前选中的条目


    public RecycleAdapterGalleryHorizontal(Context context) {
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
        View view = inflater.inflate(R.layout.activity_recycleview_gallery_horizontal_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv1 = (ImageView) view.findViewById(R.id.iv1);
        viewHolder.tv1 = (TextView) view.findViewById(R.id.tv1);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Biaoge_listBean ratings = mratings.get(position);
        //设置图片bufen
        GlideOptions glideOptions = new GlideOptions(R.drawable.ic_def_loading, R.drawable.ic_def_loading, 300);
        GlideUtil.display(context, viewHolder.iv1, ratings.getText_content(), glideOptions);
//        viewHolder.tv1.setText(ratings.getText_content());

        if (position == selectIndex) {
            //选中状态bufen

        } else {

        }

        //如果设置了回调，则设置点击事件
        if (mOnItemiv1ClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemiv1ClickLitener.onItemClick(viewHolder.itemView, position);
                }
            });
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv1;//
        private TextView tv1;//

        ViewHolder(View view) {
            super(view);
        }
    }

    /**
     * ItemClick iv1的回调接口
     *
     * @author geek
     */
    public interface OnItemiv1ClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemiv1ClickLitener mOnItemiv1ClickLitener;

    public void setOnItemiv1ClickLitener(OnItemiv1ClickLitener mOnItemiv1ClickLitener) {
        this.mOnItemiv1ClickLitener = mOnItemiv1ClickLitener;
    }

    public String formatPrice2(double price) {
        DecimalFormat df = new DecimalFormat("######0.00");

        return df.format(price);
    }

    /**
     * 标记选中
     */
    public void setSelectIndex(int i) {
        selectIndex = i;
    }

}