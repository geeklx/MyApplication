package com.example.p028_cardswipelayout.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p028_cardswipelayout.R;
import com.example.p028_cardswipelayout.domain.Biaoge_listBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


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
        View view = inflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_imgurl = (ImageView) view.findViewById(R.id.iv_avatar);
        viewHolder.iv_dislike = (ImageView) view.findViewById(R.id.iv_dislike);
        viewHolder.iv_like = (ImageView) view.findViewById(R.id.iv_like);
        viewHolder.tv_content1 = (TextView) view.findViewById(R.id.tv_name);
        viewHolder.tv_dingyue = (TextView) view.findViewById(R.id.tv_dingyue);

        return viewHolder;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Biaoge_listBean ratings = mratings.get(position);
        //设置图片bufen
//        GlideUtil.display(context, viewHolder.iv_imgurl, ratings.getSku_image(), GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        viewHolder.iv_imgurl.setImageResource(ratings.getImg_url());
        viewHolder.tv_content1.setText(ratings.getText_content());

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.iv_imgurl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.iv_imgurl, position);
                }
            });
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_imgurl;//
        public ImageView iv_dislike;//
        public ImageView iv_like;//
        public TextView tv_content1;//
        public TextView tv_dingyue;//

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
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public String formatPrice2(double price) {
        DecimalFormat df = new DecimalFormat("######0.00");

        return df.format(price);
    }
}