package com.xiaomomo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomomo.R;

/**
 * Created by Administrator on 2017/7/1.
 */

public class MessageFragment extends BaseFragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_message_layout,container,false);
        return mContentView;
    }
}
