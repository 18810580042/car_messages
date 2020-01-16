package com.example.car_message.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;

import com.example.car_message.adapter.WarningAdapter;
import com.example.car_message.base.BaseFragment;
import com.example.car_message.utils.DensityUtils;
import com.example.car_message.utils.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends BaseFragment {

    private RecyclerView task_rv;
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        context = getContext();
        TextView text_show = view.findViewById(R.id.tal_text_show);
        text_show.setText("任务");
        ImageView img_back = view.findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.GONE);
        task_rv = view.findViewById(R.id.task_order_rv);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        task_rv.setLayoutManager(manager);
        WarningAdapter adapter = new WarningAdapter(context);
        task_rv.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(context, 15)));
        task_rv.setAdapter(adapter);
        adapter.setShowInfo(new WarningAdapter.ShowInfo() {
            @Override
            public void Seesomething(int position) {
                //详情  页

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
