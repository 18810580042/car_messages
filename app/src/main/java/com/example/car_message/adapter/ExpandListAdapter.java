package com.example.car_message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.car_message.R;

import org.w3c.dom.Text;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    Context context;
    String[] stringlist;
    int[] imglist;
    String[][] childlist;

    public ExpandListAdapter(Context context, String[] stringlist, int[] imglist, String[][] chlidlist) {
        this.stringlist = stringlist;
        this.imglist = imglist;
        this.childlist = chlidlist;
        this.context = context;
    }

    //父 布局
    @Override
    public int getGroupCount() {
        return stringlist.length;
    }

    //获得组的子项个数
    @Override
    public int getChildrenCount(int i) {
        return childlist[i].length;
    }

    //获取某组数据
    @Override
    public Object getGroup(int i) {
        return stringlist[i];
    }

    //获得指定子项
    @Override
    public Object getChild(int i, int i1) {
        return childlist[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.parent_message_item, viewGroup, false);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.tvtitle = view.findViewById(R.id.group_text);
            groupViewHolder.imgtitle=view.findViewById(R.id.group_img_year);
            groupViewHolder.tvTime=view.findViewById(R.id.group_year_time);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }
        groupViewHolder.tvtitle.setText(stringlist[i]);
        groupViewHolder.tvTime.setText("2020.01.01 00:00:00");
        Glide.with(context).load(imglist[i]).into(groupViewHolder.imgtitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.childe_layout_item, viewGroup, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView) view.findViewById(R.id.child_item_text);
            childViewHolder.tvTime=view.findViewById(R.id.chlid_item_yeartime);
            view.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        childViewHolder.tvTitle.setText(childlist[i][i1]);
        childViewHolder.tvTime.setText("2019.01.01 12:00:00");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupViewHolder{
        TextView tvtitle;
        ImageView imgtitle;
        TextView tvTime;
    }
    class ChildViewHolder{
       TextView tvTitle;
       TextView tvTime;
    }
}
