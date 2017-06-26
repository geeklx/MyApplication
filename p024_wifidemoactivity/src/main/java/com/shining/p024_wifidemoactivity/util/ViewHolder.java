package com.shining.p024_wifidemoactivity.util;

import android.util.SparseArray;
import android.view.View;

/**
 * @function:	viewholder with tag
 * @description:
 * @history: 1.  date:2016/2/16 14:38
 * author:PengLiang
 * modification:
 */
public class ViewHolder {

	
	private ViewHolder(){}
	
	public static <T extends View> T getView(View mView , int id){
		
		SparseArray<View> mViews = (SparseArray<View>) mView.getTag();
		
		if(mViews == null){
			
			mViews = new SparseArray<View>();
			mView.setTag(mViews);
		}
		
		View v = mViews.get(id);
		
		if(v == null){
			
			v = mView.findViewById(id);
			
			mViews.put(id, v);
		}
		
		return (T) v;
		
	}
}
