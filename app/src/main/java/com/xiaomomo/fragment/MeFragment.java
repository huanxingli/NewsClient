package com.xiaomomo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomomo.R;

/**
 * Created by lihuanxing on 2017/7/1.
 */

public class MeFragment extends BaseFragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_me_layout,container,false);
        return mContentView;
    }
}
