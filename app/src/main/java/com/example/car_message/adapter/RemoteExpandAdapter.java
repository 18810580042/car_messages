package com.example.car_message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.car_message.R;

public class RemoteExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    String[] grouplist;
    String[][] childList;

    public RemoteExpandAdapter(Context applicationContext, String[] grouplist, String[][] childList) {
        this.context = applicationContext;
        this.grouplist = grouplist;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return grouplist.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return childList[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return childList[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList[i][i1];
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
        GroupHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_remotegroup, viewGroup, false);
            holder = new GroupHolder();
            holder.grouptextnumber = view.findViewById(R.id.remote_group_textnumber);
            holder.grouptextlast = view.findViewById(R.id.remote_group_textlast);
        } else {
            holder = (GroupHolder) view.getTag();
        }
        holder.grouptextnumber.setText(grouplist[i]);
        return view;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_remotechild, viewGroup);
            holder = new ChildHolder();
            holder.childtextname = view.findViewById(R.id.item_remotechild_name);
            holder.childtextinfo = view.findViewById(R.id.item_remotehchild_info);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        holder.childtextinfo.setText(childList[i][i1]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupHolder {
        TextView grouptextnumber;
        TextView grouptextlast;
    }

    class ChildHolder {
        TextView childtextname;
        TextView childtextinfo;
    }
}
