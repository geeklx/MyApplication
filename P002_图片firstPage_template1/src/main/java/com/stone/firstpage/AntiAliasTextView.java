package com.stone.firstpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *
 * 
 * @author Sistone.zhang
 * 
 */
@SuppressLint("DrawAllocation")
public class AntiAliasTextView extends TextView {
	private Paint paint;

	public AntiAliasTextView(Context context) {
		this(context, null);
	}

	public AntiAliasTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AntiAliasTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(7);
		paint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0,
				Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG));
		super.onDraw(canvas);

		// ���
		int width = getWidth();
		int height = getHeight();

		canvas.drawLine(0, 0, width, 0, paint);
		canvas.drawLine(width, 0, width, height, paint);
		canvas.drawLine(width, height, 0, height, paint);
		canvas.drawLine(0, height, 0, 0, paint);
	}
}
