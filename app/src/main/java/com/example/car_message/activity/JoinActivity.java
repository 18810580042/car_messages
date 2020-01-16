package com.example.car_message.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_message.R;
import com.example.car_message.adapter.JoinAdapter;
import com.example.car_message.base.BaseActivity;

public class JoinActivity extends BaseActivity {
    private ImageView img_back;
    private TextView text_show;
    private RecyclerView join_rv;
    private Context context;

    private String[] titlelist = {"车牌号", "交车人", "交车人电话"
            , "交车时间", "当前里程数", "随车配置装备", "交车照片", "备注"};
    private String[] stringlist = {"冀A1234", "张三", "13423423523", "2019.09.23 09:38:30",
            "57934", "随车工具、记录仪、加油站、ETC",
            "4张", "2222222222222"};
    private TextView join_receive;
    private TextView join_turndown;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_join;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        context = getBaseContext();

        img_back = findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        text_show = findViewById(R.id.tal_text_show);
        text_show.setText("交接");
        join_rv = findViewById(R.id.join_rv);
        join_rv.setLayoutManager(new LinearLayoutManager(context));
        JoinAdapter adapter = new JoinAdapter(context, titlelist, stringlist);
        join_rv.setAdapter(adapter);
        join_turndown = findViewById(R.id.join_turndown);
        join_receive = findViewById(R.id.join_receive);

        join_receive.setOnClickListener(this);
        join_turndown.setOnClickListener(this);
        adapter.setShowpicture(new JoinAdapter.Showpicture() {
            @Override
            public void LookPicture(String position) {
                if ("交车照片".equals(position)) {
                    Intent intent = new Intent(JoinActivity.this, PayPhoneActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tal_img_back:
                this.finish();
                break;
            case R.id.join_turndown:
                Toast.makeText(context, "驳回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.join_receive:
                Toast.makeText(context, "接受", Toast.LENGTH_SHORT).show();
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
