package com.shining.p010_recycleviewall.recycleviewlistviewaddheadandfooterhorizontal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shining.p010_recycleviewall.R;
import com.shining.p010_recycleviewall.domain.Scroll_listBean;
import com.shining.p010_recycleviewall.net.glide.GlideOptions;
import com.shining.p010_recycleviewall.net.glide.GlideUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RecycleAdapterHorizontal extends RecyclerView.Adapter<RecycleAdapterHorizontal.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Scroll_listBean> mratings;
    private int selectIndex = -1;//当前选中的条目


    public RecycleAdapterHorizontal(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<Scroll_listBean>();
    }

    public void setContacts(List<Scroll_listBean> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<Scroll_listBean> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<Scroll_listBean> getMratings() {
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
        View view = inflater.inflate(R.layout.activity_recycleview_horizontal_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv1 = (ImageView) view.findViewById(R.id.iv1);
        viewHolder.iv2 = (ImageView) view.findViewById(R.id.iv2);
        viewHolder.tv1 = (TextView) view.findViewById(R.id.tv1);
        viewHolder.tv_position = (TextView) view.findViewById(R.id.tv_position);

        return viewHolder;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Scroll_listBean ratings = mratings.get(position);
        //设置图片bufen
        GlideOptions glideOptions = new GlideOptions(R.drawable.ic_def_loading, R.drawable.ic_def_loading, 300);
        GlideUtil.display(context, viewHolder.iv1, ratings.getImg_url(), glideOptions);
        GlideUtil.display(context, viewHolder.iv2, ratings.getImg_url(), glideOptions);//GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS)
        viewHolder.tv1.setText(ratings.getText_content());
        viewHolder.tv_position.setText(position + "");

        if (position == selectIndex) {
            //选中状态bufen
            viewHolder.iv1.setVisibility(View.GONE);
            viewHolder.iv2.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv1.setVisibility(View.VISIBLE);
            viewHolder.iv2.setVisibility(View.GONE);
        }

        //如果设置了回调，则设置点击事件
        if (mOnItemiv1ClickLitener != null) {
            viewHolder.iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemiv1ClickLitener.onItemClick(viewHolder.iv1, position);
                }
            });
        }

        if (mOnItemiv2ClickLitener != null) {
            viewHolder.iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemiv2ClickLitener.onItemClick(viewHolder.iv2, position);
                }
            });
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv1;//
        private ImageView iv2;//
        private TextView tv1;//
        private TextView tv_position;//


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

    /**
     * ItemClick iv2的回调接口
     *
     * @author geek
     */
    public interface OnItemiv2ClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemiv2ClickLitener mOnItemiv2ClickLitener;

    public void setOnItemiv2ClickLitener(OnItemiv2ClickLitener mOnItemiv2ClickLitener) {
        this.mOnItemiv2ClickLitener = mOnItemiv2ClickLitener;
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