package com.xiaomomo.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaomomo.R;
import com.xiaomomo.activity.base.BaseActivity;
import com.xiaomomo.fragment.HomeFragment;
import com.xiaomomo.fragment.MeFragment;
import com.xiaomomo.fragment.MessageFragment;

/**
 * Created by lihuanxing on 2017/7/1.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private FrameLayout mContentLayout;
    private RelativeLayout mHomeLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMeLayout;
    private ImageView mHomeImage;
    private ImageView mMessageImage;
    private ImageView mMeImage;
    private TextView mHomeText;
    private TextView mMessageText;
    private TextView mMeText;

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private MeFragment meFragment;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        initView();

        //默认显示首页
        homeFragment = new HomeFragment();
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_layout,homeFragment);
        transaction.commit();

    }
    
    private void initView(){
        mContentLayout = (FrameLayout) findViewById(R.id.content_layout);
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMeLayout = (RelativeLayout) findViewById(R.id.me_layout_view);
        mHomeImage = (ImageView) findViewById(R.id.home_image_view);
        mMessageImage = (ImageView) findViewById(R.id.message_image_view);
        mMeImage = (ImageView) findViewById(R.id.me_image_view);
        mHomeText = (TextView) findViewById(R.id.home_text_view);
        mMessageText = (TextView) findViewById(R.id.message_text_view);
        mMeText = (TextView) findViewById(R.id.me_text_view);
        
        mHomeLayout.setOnClickListener(this);
        mMessageLayout.setOnClickListener(this);
        mMeLayout.setOnClickListener(this);
        mHomeImage.setImageResource(R.drawable.main_tab_home_selected);
        mHomeText.setTextColor(getResources().getColor(R.color.tab_select));
    }

    /**
     *
     * @param fragment
     * @param transaction
     */
    private void hideFragment(Fragment fragment,FragmentTransaction transaction){
        if (fragment != null){
            transaction.hide(fragment);
        }
    }

    /**
     * 切换的时候，首先将所有的tab转化成未选择状态
     */
    private void changeTabUnSelect(){
        mHomeImage.setImageResource(R.drawable.main_tab_home);
        mMessageImage.setImageResource(R.drawable.main_tab_message);
        mMeImage.setImageResource(R.drawable.main_tab_person);

        mHomeText.setTextColor(getResources().getColor(R.color.tab_item));
        mMessageText.setTextColor(getResources().getColor(R.color.tab_item));
        mMeText.setTextColor(getResources().getColor(R.color.tab_item));

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_layout_view:
                hideFragment(messageFragment,transaction);
                hideFragment(meFragment,transaction);
                changeTabUnSelect();
                mHomeImage.setImageResource(R.drawable.main_tab_home_selected);
                mHomeText.setTextColor(getResources().getColor(R.color.tab_select));
                if (homeFragment != null){
                    transaction.show(homeFragment);
                }else{
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content_layout,homeFragment);
                }
                break;
            case R.id.message_layout_view:
                hideFragment(homeFragment,transaction);
                hideFragment(meFragment,transaction);
                changeTabUnSelect();
                mMessageImage.setImageResource(R.drawable.main_tab_message_selected);
                mMessageText.setTextColor(getResources().getColor(R.color.tab_select));
                if (messageFragment != null){
                    transaction.show(messageFragment);
                }else{
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.content_layout,messageFragment);
                }
                break;
            case R.id.me_layout_view:
                hideFragment(homeFragment,transaction);
                hideFragment(messageFragment,transaction);
                changeTabUnSelect();
                mMeImage.setImageResource(R.drawable.main_tab_person_selected);
                mMeText.setTextColor(getResources().getColor(R.color.tab_select));
                if (meFragment != null){
                    transaction.show(meFragment);
                }else{
                    meFragment = new MeFragment();
                    transaction.add(R.id.content_layout,meFragment);
                }
                break;
        }

        transaction.commit();
    }

}
