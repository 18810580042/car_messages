package com.example.car_message.fragment;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_message.R;
import com.example.car_message.activity.JoinActivity;
import com.example.car_message.adapter.GroupTaskAdapter;
import com.example.car_message.base.BaseFragment;
import com.example.car_message.utils.DensityUtils;
import com.example.car_message.utils.SpaceItemDecoration;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GroupTaskFragment extends BaseFragment {

    private RecyclerView group_task_rv;
    private int[] imglist = {R.mipmap.join, R.mipmap.inmessage,
            R.mipmap.help, R.mipmap.miswokr, R.mipmap.desgress};
    private String[] titlelist = {"交接管理", "年审管理", "保养管理",
            "维修管理", "事故管理"};
    private String[] infostring = {"交接车辆,系统记录更方便", "车辆年审后随时记录",
            "车辆保养记录添加,记录真实保养数据",
            "记录车辆维修时间费用", "车辆发生事故及时记录与上报"};
    private Context context;

    private final int JOINTITLE = 0;
    private final int YEARMEEASGE = 1;
    private final int HELPMESSAGE = 2;
    private final int MISTASKE = 3;
    private final int INDISGRESS = 4;
    private FragmentManager manager;
    private FragmentTransaction ft;

    @Override
    protected int getLayoutId() {
        manager = getFragmentManager();
        return R.layout.fragment_group_task;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        context = getContext();
        group_task_rv = view.findViewById(R.id.group_task_rv);
        TextView text_show = view.findViewById(R.id.tal_text_show);
        text_show.setText("任务");
        ImageView img_back = view.findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.GONE);
        group_task_rv.setLayoutManager(new LinearLayoutManager(context));
        GroupTaskAdapter adapter = new GroupTaskAdapter(context, imglist, titlelist, infostring);
        group_task_rv.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(context, 15)));
        group_task_rv.setAdapter(adapter);

        adapter.setItemClick(new GroupTaskAdapter.ItemClick() {
            @Override
            public void ShowIntent(int position) {
                switch (position) {
                    case JOINTITLE://交接管理
//                        JoinFragment joinFragment = JoinFragment.newInstance("从GroupTaskFragment传来的参数");
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.add(R.id.home_layout,joinFragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
                        Intent intent=new Intent(getActivity(), JoinActivity.class);
                        startActivity(intent);
                        break;
                    case YEARMEEASGE://年审管理
                        Toast.makeText(context, "年审管理", Toast.LENGTH_SHORT).show();
                        break;
                    case HELPMESSAGE://保养管理
                        Toast.makeText(context, "保养管理", Toast.LENGTH_SHORT).show();
                        break;
                    case MISTASKE://维修管理
                        Toast.makeText(context, "维修管理", Toast.LENGTH_SHORT).show();
                        break;
                    case INDISGRESS://事故管理
                        Toast.makeText(context, "事故管理", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onReloading(String type) {

    }

}
