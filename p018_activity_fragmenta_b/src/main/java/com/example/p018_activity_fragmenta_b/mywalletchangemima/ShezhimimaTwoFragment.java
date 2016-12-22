package com.example.p018_activity_fragmenta_b.mywalletchangemima;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p018_activity_fragmenta_b.R;


/**
 * Created gyy
 */
public class ShezhimimaTwoFragment extends MywalletBaseFragment {

    private TextView tv_title;
    private ImageView iv_next;
    private Button btn_next;
    private View container_view;
    private String mText;
    private Context mContext;

    @Override
    public void call(Object value) {
        super.call(value);
        mText = value.toString();
        ToastUtil toastUtil = new ToastUtil(mContext.getApplicationContext());
        toastUtil.showToastCenter(mText);
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container_view = LayoutInflater.from(mContext).inflate(R.layout.fragment_pop_shezhimima_two, container, false);
        iv_next = (ImageView) container_view.findViewById(R.id.iv_next);
        tv_title = (TextView) container_view.findViewById(R.id.tv_title);
        btn_next = (Button) container_view.findViewById(R.id.btn_next);
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                ((ShezhimimaActivity) mContext).changeView(0);
            }
        });

        return container_view;
    }
}
