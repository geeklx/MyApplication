package com.shining.p010_recycleviewall.shoucang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shining.p010_recycleviewall.R;
import com.shining.p010_recycleviewall.net.glide.GlideOptions;
import com.shining.p010_recycleviewall.net.glide.GlideUtil;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodItemBean;
import com.shining.p010_recycleviewall.shoucang.lookall.LookAllActivity;
import com.shining.p010_recycleviewall.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * shining
 * 2017年2月28日12:35:33
 */

public class ShoucangLookAllAdapter extends RecyclerView.Adapter<ShoucangLookAllAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<FmNewFoodItemBean> mratings;
    private boolean is_vis_rl_unuser;
    private List<String> listAllChoose = new ArrayList<>();//批量删除收集IDbufen

    //获取所有foodid List
    public List<String> getAllListids() {
        return listAllChoose;
    }

    //清除所有foodid String
    public void setAll_ids() {
        if (listAllChoose != null || listAllChoose.size() > 0) {
            listAllChoose.clear();
        }
    }

    //获取所有foodid String
    public String getAllString_ids() {
        if (listAllChoose == null || listAllChoose.size() == 0) {
            return null;
        }
        return getAllfoodstr(listAllChoose);
    }

    //单选 add
    public void addAllfoodid(String id) {
        listAllChoose.add(id);
    }

    //单选 remove
    public void removeAllfoodid(String id) {
        listAllChoose.remove(id);
    }

    //获取所有id String 方法 bufen
    public String getAllfoodstr(List<String> ids) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (String id : ids) {
            if (i == 0) {
                result.append(id);
            } else {
                result.append("," + id);
            }
            i++;
        }
        return result.toString();
    }

    public boolean is_vis_rl_unuser() {
        return is_vis_rl_unuser;
    }

    public void setIs_vis_rl_unuser(boolean is_vis_rl_unuser) {
        this.is_vis_rl_unuser = is_vis_rl_unuser;
    }

    public ShoucangLookAllAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<FmNewFoodItemBean>();
    }

    public void setContacts(List<FmNewFoodItemBean> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<FmNewFoodItemBean> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<FmNewFoodItemBean> getMratings() {
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
        View view = inflater.inflate(R.layout.activity_shoucang_lookall_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_img = (ImageView) view.findViewById(R.id.iv_img);
        viewHolder.rl_unuser = (RelativeLayout) view.findViewById(R.id.rl_unuser);
        viewHolder.tv_choose = (TextView) view.findViewById(R.id.tv_choose);
        viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        FmNewFoodItemBean ratings = mratings.get(position);
        GlideOptions glideOptions = new GlideOptions(R.drawable.no_fridge_food, R.drawable.no_fridge_food, 0);
        GlideUtil.display(context, viewHolder.iv_img, ratings.getFood_image(), glideOptions);//GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT)
        viewHolder.tv_name.setText(ratings.getFood_name());

        viewHolder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即充值bufen
                FmNewFoodItemBean ratings = mratings.get(position);
                if (ratings == null) {
                    return;
                }
                ToastUtil.showToastCenter("跳转操作");
            }
        });
        viewHolder.rl_unuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单选bufen
                FmNewFoodItemBean ratings = mratings.get(position);
                //设置对号是否显示bufen  如果显示状态设为不选 反之
                if (ratings.ischoose()) {
                    //已选
                    viewHolder.tv_choose.setBackgroundResource(R.drawable.iv_sku_unchecked);
                    ratings.setIschoose(false);
                    removeAllfoodid(ratings.getFridge_food_id());
                } else {
                    //未选
                    viewHolder.tv_choose.setBackgroundResource(R.drawable.iv_sku_checked);
                    ratings.setIschoose(true);
                    addAllfoodid(ratings.getFridge_food_id());
                }
                //如果全选了 就设置布局全选勾勾 1 勾 0 不勾
                if (listAllChoose.size() == mratings.size()) {
                    ((LookAllActivity) context).shezhi_quanxuan_gougou(1);
                } else {
                    ((LookAllActivity) context).shezhi_quanxuan_gougou(0);
                }
            }
        });

        //选择处理逻辑bufen
        //设置对号是否显示bufen
        ratings_ischoose_info(ratings.ischoose(), viewHolder.tv_choose);
        //如何显示有对号的层bufen
        if (is_vis_rl_unuser()) {
            //设置显示对号的层bufen
            viewHolder.rl_unuser.setVisibility(View.VISIBLE);
        } else {
            //设置隐藏对号的层bufen
            viewHolder.rl_unuser.setVisibility(View.GONE);
        }


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
        RelativeLayout rl_unuser;
        TextView tv_choose;
        TextView tv_name;

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

    /**
     * 设置对号显示bufen
     *
     * @param ischooses
     * @param tv_choose
     */
    private void ratings_ischoose_info(boolean ischooses, TextView tv_choose) {
        if (ischooses) {
            //设置选中
            tv_choose.setBackgroundResource(R.drawable.iv_sku_checked);
        } else {
            //设置未选中
            tv_choose.setBackgroundResource(R.drawable.iv_sku_unchecked);
        }
    }

}