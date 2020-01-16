package com.example.car_message.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.adapter.ExpandListAdapter;
import com.example.car_message.base.BaseFragment;
import com.example.car_message.utils.AutoAdjustHeightExpendListView;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
//+

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener {


    // @BindView(R.id.message_rv)
    RecyclerView messageRv;
    private List<String> messagelist;
    private AutoAdjustHeightExpendListView expandableListView;
    private String[] stringlist = {"年审提醒", "超速提醒", "交接审批"};
    private int[] imglist = {R.mipmap.space_twety_four,
            R.mipmap.space_twety_three,
            R.mipmap.space_twety_five};
    private String[][] chlidlist = {
            {"车牌号", "年审时间"},
            {"报警1", "报警2", "报警3"},
            {"审批1", "审批2", "审批3", "审批4"}
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        ImageView warning_img = view.findViewById(R.id.warning_img);
        TextView warning_text = view.findViewById(R.id.warning_text);
        warning_img.setOnClickListener(this);
        warning_text.setOnClickListener(this);
        expandableListView = view.findViewById(R.id.message_list);
        ExpandListAdapter exAdapter = new ExpandListAdapter(getContext(), stringlist, imglist, chlidlist);
        expandableListView.setAdapter(exAdapter);
        ImageView img_back = view.findViewById(R.id.tal_img_back);
        img_back.setVisibility(View.GONE);
        TextView text_show = view.findViewById(R.id.tal_text_show);
        text_show.setText("消息");
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
               // Toast.makeText(getContext(), "1" + stringlist[i], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
               // Toast.makeText(getContext(), "1" + chlidlist[i][i1], Toast.LENGTH_SHORT).show();
                return true;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.warning_img:
            case R.id.warning_text:
//                Intent intent = new Intent(getActivity(), WarningActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
