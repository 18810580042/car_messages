package com.example.car_message.activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.base.BaseActivity;
import com.example.car_message.utils.RegexUtils;

public class LoginActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

        Button login = findViewById(R.id.login);

        ImageView img_back = findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.GONE);
        TextView text_show = findViewById(R.id.tal_text_show);
        text_show.setText("登录");
        EditText username = findViewById(R.id.username);
        EditText passwork = findViewById(R.id.password);
        String phone_number = username.getText().toString();
        String phone_pass = passwork.getText().toString();
        RegexUtils.checkMobile(phone_number);//正则判断手机号

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
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
