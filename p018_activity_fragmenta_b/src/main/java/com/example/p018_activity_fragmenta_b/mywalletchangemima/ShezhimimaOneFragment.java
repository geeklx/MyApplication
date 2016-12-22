package com.example.p018_activity_fragmenta_b.mywalletchangemima;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.p018_activity_fragmenta_b.R;


/**
 * Created gyy
 */
public class ShezhimimaOneFragment extends MywalletBaseFragment {

    private TextView tv_title;
    private Button btn_next;
    private View container_view;
    private String mText;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container_view = LayoutInflater.from(mContext).inflate(R.layout.fragment_pop_shezhimima_one, container, false);
        tv_title = (TextView) container_view.findViewById(R.id.tv_title);
        btn_next = (Button) container_view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下一步
                if (getActivity() != null && getActivity() instanceof ShezhimimaActivity) {
                    ((ShezhimimaActivity) mContext).callFragment("来自ShezhimimaOneFragment的传值~", ShezhimimaTwoFragment.class.getName());

                }
                ((ShezhimimaActivity) mContext).changeView(1);

            }
        });

        return container_view;
    }
}
