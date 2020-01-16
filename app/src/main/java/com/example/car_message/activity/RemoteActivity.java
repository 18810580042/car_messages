package com.example.car_message.activity;


import android.os.Bundle;

import com.example.car_message.R;
import com.example.car_message.adapter.RemoteExpandAdapter;
import com.example.car_message.base.BaseActivity;
import com.example.car_message.utils.AutoAdjustHeightExpendListView;

// 远程调试
public class RemoteActivity extends BaseActivity {

    private AutoAdjustHeightExpendListView expandableListView;
    private String[] grouplist={"001702901358","001702901358","001702901358","001702901358"};
    private String[][] childList={
            {"2019-11-20 17:38:40","$00000.STZZ:1,HBTQDB100075","10076",
                    "2019-11-20 17:38:40","#00000.STZZ:1,HBTQDB1000180910013D"},
            {"2019-11-20 17:38:40","$00000.STZZ:1,HBTQDB100075","10076",
                    "2019-11-20 17:38:40","#00000.STZZ:1,HBTQDB1000180910013D"},
            {"2019-11-20 17:38:40","$00000.STZZ:1,HBTQDB100075","10076",
                    "2019-11-20 17:38:40","#00000.STZZ:1,HBTQDB1000180910013D"},
            {"2019-11-20 17:38:40","$00000.STZZ:1,HBTQDB100075","10076",
                    "2019-11-20 17:38:40","#00000.STZZ:1,HBTQDB1000180910013D"},
            {"2019-11-20 17:38:40","$00000.STZZ:1,HBTQDB100075","10076",
                    "2019-11-20 17:38:40","#00000.STZZ:1,HBTQDB1000180910013D"}
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remote;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        expandableListView = findViewById(R.id.remote_edlist);
        RemoteExpandAdapter adapter=new RemoteExpandAdapter(getApplicationContext(),grouplist,childList);
        expandableListView.setAdapter(adapter);
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
