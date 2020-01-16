package com.example.car_message.activity;


import android.graphics.drawable.Drawable;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.example.car_message.R;
import com.example.car_message.base.BaseActivity;

import com.example.car_message.fragment.GroupTaskFragment;
import com.example.car_message.fragment.MessageFragment;
import com.example.car_message.fragment.MineFragment;
import com.example.car_message.fragment.NewHomeFragment;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private MyViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> titleList;
    private List<Fragment> listframent;
    private NewHomeFragment newHomeFragment;
    private GroupTaskFragment groupTaskFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        titleList = new ArrayList<>();
        listframent = new ArrayList<>();
        //   listframent.add(new HomeFragment());
        newHomeFragment = new NewHomeFragment();
        groupTaskFragment = new GroupTaskFragment();
        messageFragment = new MessageFragment();
        mineFragment = new MineFragment();
        listframent.add(newHomeFragment);
        listframent.add(groupTaskFragment);
        listframent.add(messageFragment);
        listframent.add(mineFragment);

        titleList.add("首页");
        titleList.add("任务");
        titleList.add("消息");
        titleList.add("我的");
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), titleList);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        //    mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //     mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
                    d = getResources().getDrawable(R.drawable.tab_menu_deal_home);
                    break;
                case 1:
                    d = getResources().getDrawable(R.drawable.tab_selecte_message);
                    break;
                case 2:
                    d = getResources().getDrawable(R.drawable.tab_weixin_selector);
                    break;
                case 3:
                    d = getResources().getDrawable(R.drawable.tab_me_selector);
                    break;
            }
            tab.setIcon(d);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }


    @Override
    public void onReloading(String type) {

    }


    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        List<String> title;

        public MyViewPagerAdapter(@NonNull FragmentManager fm, List<String> titleList) {
            super(fm);
            title = titleList;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return super.isViewFromObject(view, object);
        }

        @Override
        public int getCount() {
            if (listframent.size() == 0) {
                return 0;
            } else {
                return listframent.size();
            }
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return listframent.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (title != null && title.size() > 0) {
                return title.get(position);
            }
            return null;
        }
    }


}
