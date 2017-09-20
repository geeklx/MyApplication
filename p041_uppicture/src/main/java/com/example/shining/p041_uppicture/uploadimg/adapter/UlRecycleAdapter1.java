package com.example.shining.p041_uppicture.uploadimg.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shining.p041_uppicture.R;
import com.example.shining.p041_uppicture.uploadimg.bitmap.UlBimp;
import com.example.shining.p041_uppicture.uploadimg.domain.UlDataImgModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UlRecycleAdapter1 extends RecyclerView.Adapter<UlRecycleAdapter1.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<UlDataImgModel> mratings;
    private int selectedPosition = -1;// 选中的位置

    public UlRecycleAdapter1(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<UlDataImgModel>();
    }

    public void setContacts(List<UlDataImgModel> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<UlDataImgModel> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<UlDataImgModel> getMratings() {
        return mratings;
    }

    @Override
    public int getItemCount() {
        return (UlBimp.bmp.size() + 1);
//        if (mratings == null)
//            return 0;
//        return mratings.size();
    }

    public Object getItem(int position) {
        return mratings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_uploading_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv1 = (ImageView) view.findViewById(R.id.iv1);
        viewHolder.btn1 = (Button) view.findViewById(R.id.btn1);
        viewHolder.tv1 = (TextView) view.findViewById(R.id.tv1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
//        final UlDataImgModel ratings = mratings.get(position);
        //设置图片bufen
        if (UlBimp.drr.size() > 0) {
//            noScrollgridview.setNumColumns(4);
            viewHolder.tv1.setVisibility(View.GONE);
            if (position == UlBimp.bmp.size()) {
                viewHolder.btn1.setVisibility(View.GONE);
                viewHolder.iv1.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused));
                if (position == 9) {
                    viewHolder.iv1.setVisibility(View.GONE);
                }
            } else {
                viewHolder.btn1.setVisibility(View.VISIBLE);
                viewHolder.iv1.setImageBitmap(UlBimp.bmp.get(position));
            }
        } else {
//            noScrollgridview.setNumColumns(1);
            viewHolder.tv1.setVisibility(View.VISIBLE);
            viewHolder.btn1.setVisibility(View.GONE);
            viewHolder.iv1.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic_unfocused));
        }

        //如果设置了回调，则设置点击事件


        if (mOnItemImgClickLitener != null) {
            viewHolder.iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemImgClickLitener.onItemImgClick(viewHolder.btn1, position);
                }
            });
        }
        if (mOnItemImgXXClickLitener != null) {
            viewHolder.btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemImgXXClickLitener.onItemImgXXClick(viewHolder.btn1, position);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv1;//
        private TextView btn1;//
        private TextView tv1;//

        ViewHolder(View view) {
            super(view);
        }
    }

    /**
     * ItemClick的回调接口 img
     */
    public interface OnItemImgClickLitener {
        void onItemImgClick(View view, int position);
    }

    private OnItemImgClickLitener mOnItemImgClickLitener;

    public void setOnItemImgClickLitener(OnItemImgClickLitener mOnItemImgClickLitener) {
        this.mOnItemImgClickLitener = mOnItemImgClickLitener;
    }

    /**
     * ItemClick的回调接口 imgXX
     */
    public interface OnItemImgXXClickLitener {
        void onItemImgXXClick(View view, int position);
    }

    private OnItemImgXXClickLitener mOnItemImgXXClickLitener;

    public void setOnItemImgXXClickLitener(OnItemImgXXClickLitener mOnItemImgXXClickLitener) {
        this.mOnItemImgXXClickLitener = mOnItemImgXXClickLitener;
    }

    public String formatPrice2(double price) {
        DecimalFormat df = new DecimalFormat("######0.00");

        return df.format(price);
    }
}