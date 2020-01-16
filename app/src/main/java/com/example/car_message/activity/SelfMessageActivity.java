package com.example.car_message.activity;

import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.base.BaseActivity;

public class SelfMessageActivity extends BaseActivity {

    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_self_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        context = getApplicationContext();
        TextView text_show = findViewById(R.id.tal_text_show);
        text_show.setText("个人资料");
        ImageView imgback = findViewById(R.id.tal_img_back);
        imgback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tal_img_back:
                this.finish();
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
