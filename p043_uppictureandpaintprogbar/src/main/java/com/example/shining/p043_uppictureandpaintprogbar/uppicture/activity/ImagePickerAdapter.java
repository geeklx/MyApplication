package com.example.shining.p043_uppictureandpaintprogbar.uppicture.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shining.p043_uppictureandpaintprogbar.R;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private List<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private OnRecyclerViewItemClickListenerX listener_x;
    private boolean isAdded;   //是否额外添加了最后一个图片
    private boolean isAllchoose;//全选的状态值bufen

    public boolean isAllchoose() {
        return isAllchoose;
    }

    public void setAllchoose(boolean allchoose) {
        isAllchoose = allchoose;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnRecyclerViewItemClickListenerX {
        void onItemClickX(View view, int position);
    }

    public void setOnItemClickListenerX(OnRecyclerViewItemClickListenerX listener_x) {
        this.listener_x = listener_x;
    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_x;
        private ImageView iv_img;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_x = (ImageView) itemView.findViewById(R.id.iv_x);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }

        public void bind(int position) {
            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
                iv_img.setImageResource(R.drawable.selector_image_add);
                clickPosition = WechatActivity.IMAGE_ITEM_ADD;
            } else {
                ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, item.path, iv_img, 0, 0);
                clickPosition = position;
            }
            //设置全选
            if (isAllchoose) {
                if (isAdded && position == getItemCount() - 1) {
                    //隐藏
                    iv_x.setVisibility(View.INVISIBLE);
                } else {
                    //显示
                    iv_x.setVisibility(View.VISIBLE);
                }
            } else {
                //隐藏
                iv_x.setVisibility(View.INVISIBLE);
            }

            //设置条目的点击事件
            if (listener_x != null) {
                iv_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener_x.onItemClickX(iv_x, clickPosition);
                    }
                });
            }
            if (listener != null) {
                iv_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(iv_img, clickPosition);
                    }
                });
            }
            iv_img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (clickPosition == WechatActivity.IMAGE_ITEM_ADD) {

                    } else {
                        if (isAllchoose) {
                            //隐藏
                            setAllchoose(false);
                        } else {
                            //显示
                            setAllchoose(true);
                        }
                        notifyDataSetChanged();
                    }
                    return false;
                }
            });
        }
    }
}