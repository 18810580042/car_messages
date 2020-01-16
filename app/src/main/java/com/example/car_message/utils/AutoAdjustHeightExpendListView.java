package com.example.car_message.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;


public class AutoAdjustHeightExpendListView extends ExpandableListView {
    public AutoAdjustHeightExpendListView(Context context) {
        super(context);
    }

    public AutoAdjustHeightExpendListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoAdjustHeightExpendListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
