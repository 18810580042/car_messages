package com.example.car_message.activity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.adapter.OutofAdapter;
import com.example.car_message.base.BaseActivity;


//设备  故障
public class OutofActivity extends BaseActivity {

    private RecyclerView order_rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_outof;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        View tal_layout = findViewById(R.id.outof_order);
        tal_layout.setBackgroundColor(getResources().getColor(R.color.view_f2f2f2));
        TextView text_show = findViewById(R.id.tal_text_show);
        text_show.setTextColor(getResources().getColor(R.color.color_222222));
        text_show.setText("设备故障");
        ImageView img_back = findViewById(R.id.tal_img_back);
        img_back.setImageResource(R.drawable.drug_close);
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(img_back.getLayoutParams());
        margin.leftMargin = 40;
        margin.topMargin = 140;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        layoutParams.height = 40;//设置图片的高度
        layoutParams.width = 40; //设置图片的宽度
        img_back.setLayoutParams(layoutParams);

        order_rv = findViewById(R.id.outof_order_rv);
        order_rv.setLayoutManager(new LinearLayoutManager(this));
        OutofAdapter adapter = new OutofAdapter(getApplicationContext());
        order_rv.setAdapter(adapter);
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
