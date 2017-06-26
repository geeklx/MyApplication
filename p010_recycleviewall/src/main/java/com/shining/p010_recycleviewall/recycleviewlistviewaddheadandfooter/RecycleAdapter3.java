package com.shining.p010_recycleviewall.recycleviewlistviewaddheadandfooter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shining.p010_recycleviewall.domain.Coupon_listBeanNew;
import com.shining.p010_recycleviewall.domain.PackageOneKeyBuyBeanNew;
import com.shining.p010_recycleviewall.domain.TagBeanNew;
import com.shining.p010_recycleviewall.widget.FlowLayout;
import com.shining.p010_recycleviewall.widget.SimpleTagImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author:geek
 * modification:2016年4月26日14:21:48
 */

public class RecycleAdapter3 extends RecyclerView.Adapter<RecycleAdapter3.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<PackageOneKeyBuyBeanNew> mratings;
    private List<TagBeanNew> tags = new ArrayList<TagBeanNew>();
    private TextView tvNav1;
    private TextView tvNav;

    public RecycleAdapter3(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<PackageOneKeyBuyBeanNew>();
    }

    public void setContacts(List<PackageOneKeyBuyBeanNew> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<PackageOneKeyBuyBeanNew> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<PackageOneKeyBuyBeanNew> getMratings() {
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
        View view = inflater.inflate(com.shining.p010_recycleviewall.R.layout.yijiangou_item1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_imgurl = (SimpleTagImageView) view
                .findViewById(com.shining.p010_recycleviewall.R.id.iv_ViewPager_Fourthly_Left);
        viewHolder.tv_content1 = (TextView) view.findViewById(com.shining.p010_recycleviewall.R.id.tv_content1);
        viewHolder.tv_content2 = (TextView) view.findViewById(com.shining.p010_recycleviewall.R.id.tv_content2);
        viewHolder.tv_content3 = (TextView) view.findViewById(com.shining.p010_recycleviewall.R.id.tv_content3);
        viewHolder.tv_content5 = (TextView) view.findViewById(com.shining.p010_recycleviewall.R.id.tv_content5);
        viewHolder.tv_content6 = (TextView) view.findViewById(com.shining.p010_recycleviewall.R.id.tv_content6);
        viewHolder.ll_pingjia1 = (LinearLayout) view.findViewById(com.shining.p010_recycleviewall.R.id.ll_pingjia1);
        viewHolder.layou231 = (FlowLayout) view.findViewById(com.shining.p010_recycleviewall.R.id.layou231);
        viewHolder.ll_pingjia = (LinearLayout) view.findViewById(com.shining.p010_recycleviewall.R.id.ll_pingjia);
        viewHolder.layou23 = (FlowLayout) view.findViewById(com.shining.p010_recycleviewall.R.id.layou23);
        return viewHolder;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final PackageOneKeyBuyBeanNew ratings = mratings.get(position);
        //设置图片bufen
//        GlideUtil.display(context, viewHolder.iv_imgurl, ratings.getSku_image(), GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        viewHolder.iv_imgurl.setTagText(ratings.getSpeed());
        viewHolder.tv_content1.setText(ratings.getSku_title());
        viewHolder.tv_content2.setText(ratings.getSku_notice());
        if (ratings.getShop_info() != null) {
            viewHolder.tv_content3.setText(ratings.getShop_info().getShop_name());
        } else {
            viewHolder.tv_content3.setText("");
        }
        viewHolder.layou231.removeAllViews();
        //设置首免部分
        if (ratings.getCoupon_list() != null && ratings.getCoupon_list().size() > 0) {
            viewHolder.ll_pingjia1.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;
            // 加载TextView
            for (int i = 0; i < ratings.getCoupon_list().size(); i++) {
//                Pingjia ping = pingjia[i];
                Coupon_listBeanNew tag = ratings.getCoupon_list().get(i);
                LinearLayout ll = (LinearLayout) View.inflate(context,
                        com.shining.p010_recycleviewall.R.layout.tag_item, null);
                TextView tv1 = (TextView) ll.findViewById(com.shining.p010_recycleviewall.R.id.tv_content_tag1);
                TextView tv2 = (TextView) ll.findViewById(com.shining.p010_recycleviewall.R.id.tv_content_tag2);
                //第一部分
                tv1.setTextSize(15.0f);
                tv1.setText(tag.getCoupon_str());
                if (i % 2 == 0) {
                    params.setMargins(0, 10,10, 0);
                }
                tv1.setBackground(context.getResources().getDrawable(com.shining.p010_recycleviewall.R.drawable.hongbao_color));
                tv1.setTag(false);
                tv1.setPadding(5, 1, 5, 1);
                //context.getResources().getColor(R.color.orange_color)
                tv1.setTextColor(context.getResources().getColor(com.shining.p010_recycleviewall.R.color.white));//Color.parseColor("#FFFFFF")
                // tag.getCoupon_color().substring(1)= #FFFFFF  tag.getCoupon_color().substring(1)
                int color = Integer.parseInt(tag.getCoupon_color().substring(1), 16);//tag.getCoupon_color().substring(1)  FF5001
                color = 0xFF000000 + color;
                tv1.getBackground().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
                //第二部分
                tv2.setTextSize(12.0f);
                tv2.setText(tag.getCoupon_info());
                if (i % 2 == 0) {
                    params.setMargins(0, 10, 10, 0);
                }
                tv2.setTag(false);
                tv2.setPadding(5, 1, 15, 1);
                //context.getResources().getColor(R.color.orange_color)
                tv2.setTextColor(context.getResources().getColor(com.shining.p010_recycleviewall.R.color.gray));//Color.parseColor("#FFFFFF")

                viewHolder.layou231.addView(ll, params);
            }
        } else {
            viewHolder.ll_pingjia1.setVisibility(View.INVISIBLE);
        }
        viewHolder.tv_content5.setText("￥" + formatPrice2(ratings.getSku_price()));
        viewHolder.tv_content6.setText(ratings.getSku_pki());
        tags = ratings.getTag_list();//TagBean
        viewHolder.layou23.removeAllViews();
        if (tags != null) {
            viewHolder.ll_pingjia.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;
            // 加载TextView
            for (int i = 0; i < tags.size(); i++) {
                TagBeanNew tag = tags.get(i);
                tvNav = new TextView(context);
                tvNav.setTextSize(15.0f);
                tvNav.setText(tag.getTag_name());
                if (i % 2 == 0) {
                    params.setMargins(0,10,10, 0);
                }
                tvNav.setBackgroundResource(com.shining.p010_recycleviewall.R.drawable.tag_color);
                tvNav.setTag(false);
                tvNav.setTextColor(context.getResources().getColor(com.shining.p010_recycleviewall.R.color.orange_color));//Color.parseColor("#FFFFFF")
                viewHolder.layou23.addView(tvNav, params);
            }
        } else {
            viewHolder.ll_pingjia.setVisibility(View.GONE);
        }

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

        private SimpleTagImageView iv_imgurl;//ImgUrl
        private TextView tv_content1;//
        private TextView tv_content2;//
        private TextView tv_content3;//
        private TextView tv_content5;//
        private TextView tv_content6;//￥
        private LinearLayout ll_pingjia1;
        private FlowLayout layou231;
        private LinearLayout ll_pingjia;
        private FlowLayout layou23;

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

    public  String formatPrice2(double price){
        DecimalFormat df   = new DecimalFormat("######0.00");

        return df.format(price);
    }
}