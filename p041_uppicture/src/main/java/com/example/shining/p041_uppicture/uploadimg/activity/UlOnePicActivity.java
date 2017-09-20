package com.example.shining.p041_uppicture.uploadimg.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.shining.p041_uppicture.R;
import com.example.shining.p041_uppicture.uploadimg.adapter.UlAlbumHelper;
import com.example.shining.p041_uppicture.uploadimg.adapter.UlImageBucket;
import com.example.shining.p041_uppicture.uploadimg.adapter.UlImageBucketAdapter;

import java.io.Serializable;
import java.util.List;

public class UlOnePicActivity extends Activity {
	// ArrayList<Entity> dataList;//用来装载数据源的列表
	List<UlImageBucket> dataList;
	GridView gridView;
	UlImageBucketAdapter adapter;// 自定义的适配器
	UlAlbumHelper helper;
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	public static Bitmap bimap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uploading_image_bucket);

		helper = UlAlbumHelper.getHelper();
		helper.init(getApplicationContext());

		initData();
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// /**
		// * 这里，我们假设已经从网络或者本地解析好了数据，所以直接在这里模拟了10个实体类，直接装进列表中
		// */
		// dataList = new ArrayList<Entity>();
		// for(int i=-0;i<10;i++){
		// Entity entity = new Entity(R.drawable.picture, false);
		// dataList.add(entity);
		// }
		dataList = helper.getImagesBucketList(false);
		bimap = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon_addpic_unfocused);
	}

	/**
	 * 初始化view视图
	 */
	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new UlImageBucketAdapter(UlOnePicActivity.this, dataList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * 根据position参数，可以获得跟GridView的子View相绑定的实体类，然后根据它的isSelected状态，
				 * 来判断是否显示选中效果。 至于选中效果的规则，下面适配器的代码中会有说明
				 */
				// if(dataList.get(position).isSelected()){
				// dataList.get(position).setSelected(false);
				// }else{
				// dataList.get(position).setSelected(true);
				// }
				/**
				 * 通知适配器，绑定的数据发生了改变，应当刷新视图
				 */
				// adapter.notifyDataSetChanged();
				Intent intent = new Intent(UlOnePicActivity.this,
						UlTwoPicActivity.class);
				intent.putExtra(UlOnePicActivity.EXTRA_IMAGE_LIST,
						(Serializable) dataList.get(position).imageList);
				// startActivity(intent);
				startActivityForResult(intent, 10);
				// finish();
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			setResult(RESULT_OK, data);
			finish();
			onBackPressed();
		} else {
			if (requestCode == 10) {
				setResult(RESULT_OK, data);
				finish();
				onBackPressed();
			}
		}

	}

	protected void onResume() {
		super.onResume();
		// 关闭
		IntentFilter intentFilter_close = new IntentFilter();
		intentFilter_close.addAction("close_message");
		registerReceiver(broadcastReceiver_close, intentFilter_close);
	};

	private BroadcastReceiver broadcastReceiver_close = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			onBackPressed();
			// finish();
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcastReceiver_close);
	}
	public void onBackPressed() {
		super.onBackPressed();
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
