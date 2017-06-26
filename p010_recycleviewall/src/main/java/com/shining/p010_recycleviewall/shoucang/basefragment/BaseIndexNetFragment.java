package com.shining.p010_recycleviewall.shoucang.basefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by qibin on 2016/8/30.
 */

public abstract class BaseIndexNetFragment extends BaseIndexFragment {


    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
//        if (mEmptyView != null) { mEmptyView.setUnReachableListener(this);}
    }

}
