package com.example.car_message.activity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_message.R;
import com.example.car_message.adapter.GridAdapter;
import com.example.car_message.base.BaseActivity;
import com.example.car_message.utils.ButtonUtils;
import com.example.car_message.utils.GridDivider;

import org.w3c.dom.Text;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageActivity extends BaseActivity {

    private RecyclerView recyclerview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        SetTaL();
        recyclerview = findViewById(R.id.home_page_rv);
        recyclerview.addItemDecoration(new GridDivider(this, 2,
                this.getResources().getColor(R.color.color_f0f0f0)));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerview.setLayoutManager(gridLayoutManager);
        GridAdapter adapter = new GridAdapter(getApplicationContext());
        recyclerview.setAdapter(adapter);
        TextView home_pagename = findViewById(R.id.home_page_name); //网名
        TextView home_user = findViewById(R.id.home_page_user);  //管理员
        TextView backlogin = findViewById(R.id.home_backlogin); //退出登录
        ImageView home_headimage = findViewById(R.id.home_page_headimage);//头像
        backlogin.setOnClickListener(this); //退出登录
        home_headimage.setOnClickListener(this);
        adapter.setGridClick(new GridAdapter.GridClick() {
            @Override
            public void ShowIntent(int i) {
                switch (i){
                    case 0: //定位
                        if (!ButtonUtils.isFastDoubleClick()){
                            Toast.makeText(HomePageActivity.this, "定位", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1://轨迹回放
                        if (!ButtonUtils.isFastDoubleClick()){
                            Toast.makeText(HomePageActivity.this, "轨迹回放", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2://超载报警
                        if (!ButtonUtils.isFastDoubleClick()){
                            Toast.makeText(HomePageActivity.this, "超载报警", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 3://远程调试
                        if (!ButtonUtils.isFastDoubleClick()){
                            Toast.makeText(HomePageActivity.this, "远程调试", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 4://设备故障
                        if (!ButtonUtils.isFastDoubleClick()){

                        }
                        Toast.makeText(HomePageActivity.this, "设备故障", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void SetTaL() {
        View home_page_tal = findViewById(R.id.home_page_tal);
        home_page_tal.setBackgroundColor(getResources().getColor(R.color.view_f2f2f2));
        TextView text_show = findViewById(R.id.tal_text_show);
        text_show.setTextColor(getResources().getColor(R.color.color_222222));
        text_show.setText("首页");
        ImageView img_back = findViewById(R.id.tal_img_back);
        img_back.setImageResource(R.drawable.drug_close);
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(img_back.getLayoutParams());
        margin.leftMargin = 40;
        margin.topMargin = 140;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        layoutParams.height = 40;//设置图片的高度
        layoutParams.width = 40; //设置图片的宽度
        img_back.setLayoutParams(layoutParams);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tal_img_back:
                if (!ButtonUtils.isFastDoubleClick()){
                    this.finish();
                }

                break;
            case R.id.home_backlogin:
                if (!ButtonUtils.isFastDoubleClick()){
                    //退出登录
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_page_headimage:
                if (!ButtonUtils.isFastDoubleClick()){
                    //头像
                    Toast.makeText(this, "头像", Toast.LENGTH_SHORT).show();
                }
                break;
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
}
