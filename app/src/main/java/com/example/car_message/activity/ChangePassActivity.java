package com.example.car_message.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.base.BaseActivity;
import com.example.car_message.utils.ButtonUtils;

import org.w3c.dom.Text;

public class ChangePassActivity extends BaseActivity {

    private Button dismiss_bt;
    private Button ok_bt;
    private EditText new_passwork2;
    private EditText new_passwork;
    private EditText old_passwork;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pass;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //需要api在21以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(Color.TRANSPARENT);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        ImageView img_back = findViewById(R.id.tal_img_back);
        img_back.setOnClickListener(this);
        TextView text_tal = findViewById(R.id.tal_text_show);
        text_tal.setText("修改密码");
        old_passwork = findViewById(R.id.old_passwork);
        new_passwork = findViewById(R.id.new_passwork);
        new_passwork2 = findViewById(R.id.new_passwork2);
        ok_bt = findViewById(R.id.ok_bt);
        dismiss_bt = findViewById(R.id.dismiss_bt);
        ok_bt.setOnClickListener(this);
        dismiss_bt.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tal_img_back:
                if(!ButtonUtils.isFastDoubleClick()){
                    this.finish();
                }
                break;
            case R.id.ok_bt:
                if(!ButtonUtils.isFastDoubleClick()){
                    this.finish();
                }
                break;
            case R.id.dismiss_bt:
                if(!ButtonUtils.isFastDoubleClick()){
                    this.finish();
                }
                break;
        }
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onReloading(String type) {

    }
}
