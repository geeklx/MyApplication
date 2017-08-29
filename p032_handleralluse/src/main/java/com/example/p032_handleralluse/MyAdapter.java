package com.example.p032_handleralluse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.p032_handleralluse.utils.ImageLoader;
import com.example.p032_handleralluse.utils.ImageLoader.Type;

import java.util.List;


public class MyAdapter extends BaseAdapter
{

	private Context mContext;
	private List<String> mData;
	private String mDirPath;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;

	public MyAdapter(Context context, List<String> mData, String dirPath)
	{
		this.mContext = context;
		this.mData = mData;
		this.mDirPath = dirPath;
		mInflater = LayoutInflater.from(mContext);

		mImageLoader = ImageLoader.getInstance(3 , Type.LIFO);
	}

	@Override
	public int getCount()
	{
		return mData.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mData.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.grid_item, parent,
					false);
			holder.mImageView = (ImageView) convertView
					.findViewById(R.id.id_item_image);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mImageView
				.setImageResource(R.drawable.friends_sends_pictures_no);
		//使用Imageloader去加载图片
		mImageLoader.loadImage(mDirPath + "/" + mData.get(position),
				holder.mImageView);
		return convertView;
	}

	private final class ViewHolder
	{
		ImageView mImageView;
	}

}
