package com.example.car_message.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.car_message.R;
import com.example.car_message.adapter.PayPhoneAdapter;
import com.example.car_message.base.BaseActivity;

public class PayPhoneActivity extends BaseActivity {

    private TextView tal_text_show;
    private ImageView img_back;
    private RecyclerView phone_rv;

    private int[] imglist = {R.mipmap.space_four,
            R.mipmap.miswokr,
            R.mipmap.space_four
            , R.mipmap.join};
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_phone;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        context = getBaseContext();
        tal_text_show =  findViewById(R.id.tal_text_show);
        tal_text_show.setText("交车照片");
        img_back =  findViewById(R.id.tal_img_back);
        img_back.setOnClickListener(this);
        phone_rv =  findViewById(R.id.pay_phone_rv);
        phone_rv.setLayoutManager(new GridLayoutManager(this, 3));
        PayPhoneAdapter adapter = new PayPhoneAdapter(this,imglist);
        phone_rv.setAdapter(adapter);
        adapter.setShowphone(new PayPhoneAdapter.Showphone() {
            @Override
            public void Lookpicture(int url) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View imgEntryView = inflater.inflate(R.layout.dialog_photo, null);
                final AlertDialog dialog = new AlertDialog.Builder(PayPhoneActivity.this).create();
                ImageView img = (ImageView) imgEntryView.findViewById(R.id.large_image);
                Glide.with(PayPhoneActivity.this).load(url).into(img);
                dialog.setView(imgEntryView); // 自定义dialog
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
                // 点击大图关闭dialog
                imgEntryView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                        dialog.cancel();
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tal_img_back:
                 finish();
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
