package com.xiaomomo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomomo.R;

/**
 * Created by Administrator on 2017/7/1.
 */

public class HomeFragment extends BaseFragment {

    private View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_home_layout,container,false);
        return contentView;
    }
}
